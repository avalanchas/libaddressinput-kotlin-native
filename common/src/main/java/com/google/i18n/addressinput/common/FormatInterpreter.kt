package com.kurviger.shared.models.address


import com.kurviger.shared.models.address.AddressField.WidthType

/**
 * Address format interpreter. A utility to find address format related info.
 */
class FormatInterpreter(options: FormOptions.Snapshot) {
    private val formOptions: FormOptions.Snapshot

    /**
     * Creates a new instance of [FormatInterpreter].
     */
    init {
        Util.checkNotNull(
            RegionDataConstants.countryFormatMap, "null country name map not allowed"
        )
        Util.checkNotNull(options)
        this.formOptions = options
        Util.checkNotNull(
            getJsonValue("ZZ", AddressDataKey.FMT),
            "Could not obtain a default address field order."
        )
    }

    /**
     * Returns a list of address fields based on the format of `regionCode`. Script type is
     * needed because some countries uses different address formats for local/Latin scripts.
     *
     * @param scriptType if [ScriptType.LOCAL], use local format; else use Latin format.
     */
    // TODO: Consider not re-doing this work every time the widget is re-rendered.
    @Suppress("deprecation") // For legacy address fields.
    fun getAddressFieldOrder(
        scriptType: LookupKey.ScriptType,
        regionCode: String
    ): List<AddressField> {
        Util.checkNotNull(scriptType)
        Util.checkNotNull(regionCode)
        val formatString = getFormatString(scriptType, regionCode)
        return getAddressFieldOrder(formatString, regionCode)
    }

    fun getAddressFieldOrder(formatString: String?, regionCode: String): List<AddressField> {
        val visibleFields: MutableSet<AddressField> = mutableSetOf()
        val fieldOrder: MutableList<AddressField> = mutableListOf()
        // TODO: Change this to just enumerate the address fields directly.
        for (substring in getFormatSubstrings(formatString)) {
            // Skips un-escaped characters and new lines.
            if (!substring.matches("%.".toRegex()) || substring == NEW_LINE) {
                continue
            }
            val field = getFieldForFormatSubstring(substring)
            // Accept only the first instance for any duplicate fields (which can occur because the
            // string we start with defines format order, which can contain duplicate fields).
            if (visibleFields.add(field)) {
                fieldOrder.add(field)
            }
        }
        applyFieldOrderOverrides(regionCode, fieldOrder)

        // Uses two address lines instead of street address.
        for (n in fieldOrder.indices) {
            if (fieldOrder[n] == AddressField.STREET_ADDRESS) {
                fieldOrder[n] = AddressField.ADDRESS_LINE_1
                fieldOrder.add(n + 1, AddressField.ADDRESS_LINE_2)
                break
            }
        }
        return fieldOrder
    }

    private fun applyFieldOrderOverrides(
        regionCode: String,
        fieldOrder: MutableList<AddressField>
    ) {
        var customFieldOrder: MutableList<AddressField> =
            formOptions.getCustomFieldOrder(regionCode).toMutableList()

        // We can assert that fieldOrder and customFieldOrder contain no duplicates.
        // We know this by the construction above and in FormOptions but we still have to think
        // about fields in the custom ordering which aren't visible (the loop below will fail if
        // a non-visible field appears in the custom ordering). However in that case it's safe to
        // just ignore the extraneous field.
        val nonVisibleCustomFields: MutableSet<AddressField> = customFieldOrder.toMutableSet()
        nonVisibleCustomFields.removeAll(fieldOrder.toSet())
        if (nonVisibleCustomFields.isNotEmpty()) {
            // Local mutable copy to remove non visible fields - this shouldn't happen often.
            customFieldOrder.removeAll(nonVisibleCustomFields)
        }
        // It is vital for this loop to work correctly that every element in customFieldOrder
        // appears in fieldOrder exactly once.
        var fieldIdx = 0
        var customIdx = 0
        while (fieldIdx < fieldOrder.size) {
            if (customFieldOrder.contains(fieldOrder[fieldIdx])) {
                fieldOrder[fieldIdx] = customFieldOrder[customIdx++]
            }
            fieldIdx++
        }
    }

    /**
     * Gets formatted address. For example,
     *
     *
     *  John Doe
     * Dnar Corp
     * 5th St
     * Santa Monica CA 90123
     *
     * This method does not validate addresses. Also, it will "normalize" the result strings by
     * removing redundant spaces and empty lines.
     */
    fun getEnvelopeAddress(address: AddressData): List<String?> {
        Util.checkNotNull(address, "null input address not allowed")
        val regionCode = address.postalCountry

        val lc = address.languageCode
        var scriptType = LookupKey.ScriptType.LOCAL
        if (lc != null) {
            scriptType =
                if (Util.isExplicitLatinScript(lc)) LookupKey.ScriptType.LATIN else LookupKey.ScriptType.LOCAL
        }

        val prunedFormat: MutableList<String> = mutableListOf()
        val formatString = getFormatString(scriptType, regionCode)
        val formatSubstrings = getFormatSubstrings(formatString)
        for (i in formatSubstrings.indices) {
            val formatSubstring = formatSubstrings[i]
            // Always keep the newlines.
            if (formatSubstring == NEW_LINE) {
                prunedFormat.add(NEW_LINE)
            } else if (formatSubstringRepresentsField(formatSubstring)) {
                // Always keep the non-empty address fields.
                if (addressHasValueForField(address, getFieldForFormatSubstring(formatSubstring))) {
                    prunedFormat.add(formatSubstring)
                }
            } else if (// Only keep literals that satisfy these 2 conditions:
            // (1) Not preceding an empty field.
                (i == formatSubstrings.size - 1 || formatSubstrings[i + 1] == NEW_LINE || addressHasValueForField(
                    address, getFieldForFormatSubstring(
                        formatSubstrings[i + 1]
                    )
                )) // (2) Not following a removed field.
                && (i == 0 || !formatSubstringRepresentsField(
                    formatSubstrings[i - 1]
                )
                        || (!prunedFormat.isEmpty()
                        && formatSubstringRepresentsField(prunedFormat[prunedFormat.size - 1])))
            ) {
                prunedFormat.add(formatSubstring)
            }
        }

        val lines: MutableList<String?> = mutableListOf()
        val currentLine = StringBuilder()
        for (formatSubstring in prunedFormat) {
            if (formatSubstring == NEW_LINE) {
                if (currentLine.isNotEmpty()) {
                    lines.add(currentLine.toString())
                    currentLine.setLength(0)
                }
            } else if (formatSubstringRepresentsField(formatSubstring)) {
                when (getFieldForFormatSubstring(formatSubstring)) {
                    AddressField.STREET_ADDRESS -> {
                        // The field "street address" represents the street address lines of an address, so
                        // there can be multiple values.
                        val addressLines = address.addressLines
                        if (addressLines!!.isNotEmpty()) {
                            currentLine.append(addressLines[0])
                            if (addressLines.size > 1) {
                                lines.add(currentLine.toString())
                                currentLine.setLength(0)
                                lines.addAll(addressLines.subList(1, addressLines.size))
                            }
                        }
                    }

                    AddressField.COUNTRY -> {}
                    AddressField.ADMIN_AREA -> currentLine.append(address.administrativeArea)
                    AddressField.LOCALITY -> currentLine.append(address.locality)
                    AddressField.DEPENDENT_LOCALITY -> currentLine.append(address.dependentLocality)
                    AddressField.RECIPIENT -> currentLine.append(address.recipient)
                    AddressField.ORGANIZATION -> currentLine.append(address.organization)
                    AddressField.POSTAL_CODE -> currentLine.append(address.postalCode)
                    AddressField.SORTING_CODE -> currentLine.append(address.sortingCode)
                    else -> {}
                }
            } else {
                // Not a symbol we recognise, so must be a literal. We append it unchanged.
                currentLine.append(formatSubstring)
            }
        }
        if (currentLine.isNotEmpty()) {
            lines.add(currentLine.toString())
        }
        return lines
    }

    /**
     * Tokenizes the format string and returns the token string list. "%" is treated as an escape
     * character. For example, "%n%a%nxyz" will be split into "%n", "%a", "%n", "xyz".
     * Escaped tokens correspond to either new line or address fields. The output of this method
     * may contain duplicates.
     */
    // TODO: Create a common method which does field parsing in one place (there are about 4 other
    // places in this library where format strings are parsed).
    private fun getFormatSubstrings(formatString: String?): List<String> {
        val parts: MutableList<String> = mutableListOf()

        var escaped = false
        val currentLiteral = StringBuilder()
        for (c in formatString!!.toCharArray()) {
            if (escaped) {
                escaped = false
                parts.add("%$c")
            } else if (c == '%') {
                if (currentLiteral.isNotEmpty()) {
                    parts.add(currentLiteral.toString())
                    currentLiteral.setLength(0)
                }
                escaped = true
            } else {
                currentLiteral.append(c)
            }
        }
        if (currentLiteral.isNotEmpty()) {
            parts.add(currentLiteral.toString())
        }
        return parts
    }

    companion object {
        private const val NEW_LINE = "%n"

        /**
         * Returns true if this format substring (e.g. %C) represents an address field. Returns false if
         * it is a literal or newline.
         */
        private fun formatSubstringRepresentsField(formatSubstring: String): Boolean {
            return formatSubstring != NEW_LINE && formatSubstring.startsWith("%")
        }

        /**
         * Gets data from the address represented by a format substring such as %C. Will throw an
         * exception if no field can be found.
         */
        private fun getFieldForFormatSubstring(formatSubstring: String): AddressField {
            return AddressField.of(formatSubstring[1])
        }

        /**
         * Returns true if the address has any data for this address field.
         */
        private fun addressHasValueForField(address: AddressData, field: AddressField): Boolean {
            if (field == AddressField.STREET_ADDRESS) {
                return address.addressLines!!.isNotEmpty()
            } else {
                val value = address.getFieldValue(field)
                return (value != null && !value.isEmpty())
            }
        }

        /**
         * Returns the fields that are required to be filled in for this country. This is based upon the
         * "required" field in RegionDataConstants for `regionCode`, and handles falling back to
         * the default data if necessary.
         */
        fun getRequiredFields(regionCode: String): Set<AddressField> {
            Util.checkNotNull(regionCode)
            val requireString = getRequiredString(regionCode)
            return getRequiredFields(requireString, regionCode)
        }

        fun getRequiredFields(requireString: String?, regionCode: String?): Set<AddressField> {
            val required: MutableSet<AddressField> = mutableSetOf(AddressField.COUNTRY)
            for (c in requireString!!.toCharArray()) {
                required.add(AddressField.of(c))
            }
            return required
        }

        private fun getRequiredString(regionCode: String): String? {
            var required = getJsonValue(regionCode, AddressDataKey.REQUIRE)
            if (required == null) {
                required = getJsonValue("ZZ", AddressDataKey.REQUIRE)
            }
            return required
        }

        /**
         * Returns the field width override for the specified country, or null if there's none. This is
         * based upon the "width_overrides" field in RegionDataConstants for `regionCode`.
         */
        fun getWidthOverride(field: AddressField, regionCode: String?): WidthType? {
            return getWidthOverride(field, regionCode, RegionDataConstants.countryFormatMap)
        }

        /**
         * Visible for Testing - same as [.getWidthOverride] but testable with
         * fake data.
         */
        fun getWidthOverride(
            field: AddressField, regionCode: String?, regionDataMap: Map<String, String>
        ): WidthType? {
            Util.checkNotNull(regionCode)
            val overridesString =
                getJsonValue(regionCode, AddressDataKey.WIDTH_OVERRIDES, regionDataMap)
            if (overridesString == null || overridesString.isEmpty()) {
                return null
            }

            // The field width overrides string starts with a %, so we skip the first one.
            // Example string: "%C:L%S:S" which is a repeated string of
            // '<%> field_character <:> width_character'.
            var pos = 0
            while (pos != -1) {
                val keyStartIndex = pos + 1
                val valueStartIndex = overridesString.indexOf(':', keyStartIndex + 1) + 1
                if (valueStartIndex == 0 || valueStartIndex == overridesString.length) {
                    // Malformed string -- % not followed by ':' or trailing ':'
                    return null
                }
                // Prepare for next iteration.
                pos = overridesString.indexOf('%', valueStartIndex + 1)
                if (valueStartIndex != keyStartIndex + 2 ||
                    overridesString[keyStartIndex] != field.char
                ) {
                    // Key is not a high level field (unhandled by this code) or does not match.
                    // Also catches malformed string where key is of zero length (skip, not error).
                    continue
                }
                val valueLength = (if (pos != -1) pos else overridesString.length) - valueStartIndex
                if (valueLength != 1) {
                    // Malformed string -- value has length other than 1
                    return null
                }
                return WidthType.of(overridesString[valueStartIndex])
            }

            return null
        }

        private fun getFormatString(
            scriptType: LookupKey.ScriptType,
            regionCode: String?
        ): String? {
            var format = if ((scriptType === LookupKey.ScriptType.LOCAL)
            ) getJsonValue(regionCode, AddressDataKey.FMT)
            else getJsonValue(regionCode, AddressDataKey.LFMT)
            if (format == null) {
                format = getJsonValue("ZZ", AddressDataKey.FMT)
            }
            return format
        }

        private fun getJsonValue(regionCode: String?, key: AddressDataKey): String? {
            return getJsonValue(regionCode, key, RegionDataConstants.countryFormatMap)
        }

        /** Visible for testing only.  */
        fun getJsonValue(
            regionCode: String?, key: AddressDataKey, regionDataMap: Map<String, String>
        ): String? {
            Util.checkNotNull(regionCode)
            var jsonString = regionDataMap[regionCode]
            if (jsonString == null) {
                if (regionCode == "CQ") {
                    // TODO: Return data for Guernsey for now for Sark but we should set up some mapping
                    // constants file in the long term.
                    jsonString = regionDataMap["GG"]
                }
                if (jsonString == null) {
                    // TODO: Set up some logging to track region codes we need to account for in the
                    // aforementioned mapping constants file.
                    return null
                }
            }
            try {
                val jsonObj: org.json.JSONObject =
                    org.json.JSONObject(org.json.JSONTokener(jsonString))
                if (!jsonObj.has(Util.toLowerCaseLocaleIndependent(key.name()))) {
                    // Key not found. Return null.
                    return null
                }
                // Gets the string for this key.
                val parsedJsonString: String =
                    jsonObj.getString(Util.toLowerCaseLocaleIndependent(key.name()))
                return parsedJsonString
            } catch (e: org.json.JSONException) {
                throw java.lang.RuntimeException("Invalid json for region code $regionCode: $jsonString")
            }
        }
    }
}
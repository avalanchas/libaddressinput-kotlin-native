package com.kurviger.shared.models.address


import com.kurviger.shared.models.address.AddressData.Builder

/**
 * An immutable data structure for international postal addresses, built using the nested
 * [Builder] class.
 *
 *
 * Addresses may seem simple, but even within the US there are many quirks (hyphenated street
 * addresses, etc.), and internationally addresses vary a great deal. The most sane and complete in
 * many ways is the OASIS "extensible Address Language", xAL, which is a published and documented
 * XML schema:<br></br>
 * [
 * http://www.oasis-open.org/committees/ciq/download.shtml](http://www.oasis-open.org/committees/ciq/download.shtml)
 *
 *
 * An example address:
 * <pre>
 * postalCountry: US
 * streetAddress: 1098 Alta Ave
 * adminstrativeArea: CA
 * locality: Mountain View
 * dependentLocality:
 * postalCode: 94043
 * sortingCode:
 * organization: Google
 * recipient: Chen-Kang Yang
 * language code: en
</pre> *
 *
 *
 * When using this class it's advised to do as little pre- or post-processing of the fields as
 * possible. Typically we expect instances of this class to be used by the address widget and then
 * transmitted or converted to other representations using the standard conversion libraries or
 * formatted using one of the supported formatters. Attempting to infer semantic information from
 * the values of the fields in this class is generally a bad idea.
 *
 *
 * Specifically the [.getFieldValue] is a problematic API as it encourages the
 * belief that it is semantically correct to iterate over the fields in order. In general it should
 * not be necessary to iterate over the fields in this class; instead use just the specific getter
 * methods for the fields you need.
 *
 *
 * There are valid use cases for examining individual fields, but these are almost always region
 * dependent:
 *
 *  * Finding the region of the address. This is really the only completely safe field you can
 * examine which gives an unambiguous and well defined result under all circumstances.
 *  * Testing if two addresses have the same administrative area. This is only reliable if the
 * data was entered via a drop-down menu, and the size of administrative areas varies greatly
 * between and within countries so it doesn't infer much about locality.
 *  * Extracting postal codes or sorting codes for address validation or external lookup. This
 * only works for certain countries, such as the United Kingdom, where postal codes have a high
 * resolution.
 *
 *
 *
 * All values stored in this class are trimmed of ASCII whitespace. Setting an empty, or whitespace
 * only field in the builder will clear it and result in a `null` being returned from the
 * corresponding `AddressData` instance..
 */
// This is an external class and part of the widget's public API.
// TODO: Review public API for external classes and tidy JavaDoc.
class AddressData private constructor(builder: Builder) {
    /**
     * Returns the CLDR region code for this address; note that this is *not* the same as the
     * ISO 3166-1 2-letter country code. While technically optional, this field will always be set
     * by the address widget when an address is entered or edited, and will be assumed to be set by
     * many other tools.
     *
     *
     * While they have most of their values in common with the CLDR region codes, the ISO 2-letter
     * country codes have one significant disadvantage; they are not stable and values can change over
     * time. For example `"CS"` was originally used to represent Czechoslovakia, but later
     * represented Serbia and Montenegro. In contrast, CLDR region codes are never reused and can
     * represent more regions, such as Ascension Island (AC).
     *
     *
     * See the page on
     * [
 * Territory Containment](http://unicode.org/cldr/charts/26/supplemental/territory_containment_un_m_49.html) for a list of CLDR region codes.
     *
     *
     * Note that the region codes not user-presentable; "GB" is Great Britain but this should always
     * be displayed to a user as "UK" or "United Kingdom".
     */
    // When this is merged for use by GWT, remember to add @NonFinalForGwt in place of final fields.
    // Detailed information on these fields is available in the javadoc for their respective getters.
    // CLDR (Common Locale Data Repository) country code.
    val postalCountry: String?

    /**
     * Returns multiple free-form address lines representing low level parts of an address,
     * possibly empty. The first line represents the lowest level part of the address, other than
     * recipient or organization.
     *
     *
     * Note that the number of lines returned by this method may be greater than the number of lines
     * set on the original builder if some of the lines contained embedded newlines. The values
     * returned by this method will never contain embedded newlines.
     *
     *
     * For example:
     * <pre>`data = AddressData.builder()
     * .setAddressLine1("First line\nSecond line")
     * .setAddressLine2("Last line")
     * .build();
     * // We end up with 3 lines in the final AddressData instance:
     * // data.getAddressLines() == [ "First line", "Second line", "Last line" ]
    `</pre> *
     */
    // The most specific part of any address. They may be left empty if more detailed fields are used
    // instead, or they may be used in addition to these if the more detailed fields do not fulfill
    // requirements, or they may be used instead of more detailed fields to represent the street-level
    // part.
    val addressLines: List<String?>?

    /** Returns the landmark address descriptor.  */
    // The landmark address descriptor.
    val landmarkAddressDescriptor: String?

    /** Returns the landmark affix.  */
    // The landmark affix.
    val landmarkAffix: String?

    /** Returns the landmark name.  */
    // The landmark name.
    val landmarkName: String?

    /**
     * Returns the top-level administrative subdivision of this country. Different postal countries
     * use different names to refer to their administrative areas. For example: "state" (US), "region"
     * (Italy) or "prefecture" (Japan).
     *
     *
     * Where data is available, the user will be able to select the administrative area name from a
     * drop-down list, ensuring that it has only expected values. However this is not always possible
     * and no strong assumptions about validity should be made by the user for this value.
     */
    // Top-level administrative subdivision of this country.
    val administrativeArea: String?

    /**
     * Returns the language specific locality, if present. The usage of this field varies by region,
     * but it generally refers to the "city" or "town" of the address. Some regions do not use this
     * field; their address lines combined with things like postal code or administrative area are
     * sufficient to locate an address.
     *
     *
     * Different countries use different names to refer to their localities. For example: "city" (US),
     * "comune" (Italy) or "post town" (Great Britain). For Japan this would return the shikuchouson
     * and sub-shikuchouson.
     */
    // Generally refers to the city/town portion of an address.
    val locality: String?

    /**
     * Returns the dependent locality, if present.
     *
     *
     * This is used for neighborhoods and suburbs. Typically a dependent locality will represent a
     * smaller geographical area than a locality, but need not be contained within it.
     */
    // Dependent locality or sublocality. Used for neighborhoods or suburbs.
    val dependentLocality: String?

    /**
     * Returns the postal code of the address, if present. This value is not language specific but
     * may contain arbitrary formatting characters such as spaces or hyphens and might require
     * normalization before any meaningful comparison of values.
     *
     *
     * For example: "94043", "94043-1351", "SW1W", "SW1W 9TQ".
     */
    // Values are frequently alphanumeric.
    val postalCode: String?

    /**
     * Returns the sorting code if present. Sorting codes are distinct from postal codes and only
     * used in a handful of regions (eg, France).
     *
     *
     * For example in France this field would contain a
     * [CEDEX](http://en.wikipedia.org/wiki/List_of_postal_codes_in_France) value.
     */
    // This corresponds to the SortingCode sub-element of the xAL PostalServiceElements element.
    // Use is very country-specific.
    val sortingCode: String?

    /**
     * Returns the free form organization string, if present. No assumptions should be made about
     * the contents of this field. This field exists because in some situations the organization
     * and recipient fields must be treated specially during formatting. It is not a good idea to
     * allow users to enter the organization or recipient in the street address lines as this will
     * result in badly formatted and non-geocodeable addresses.
     */
    // The firm or organization. This goes at a finer granularity than address lines in the address.
    val organization: String?

    /**
     * Returns the free form recipient string, if present. No assumptions should be made about the
     * contents of this field. This field exists because in some situations the organization
     * and recipient fields must be treated specially during formatting. It is not a good idea to
     * allow users to enter the organization or recipient in the street address lines as this will
     * result in badly formatted and non-geocodeable addresses.
     */
    // The recipient. This goes at a finer granularity than address lines in the address. Not present
    // in xAL.
    val recipient: String?

    /**
     * Returns the BCP-47 language code for this address which defines the language we expect to be
     * used for any language specific fields. If this method returns `null` then the language
     * is assumed to be in the default (most used) language for the region code of the address;
     * although the precise determination of a default language is often approximate and may change
     * over time. Wherever possible it is recommended to construct `AddressData` instances
     * with a specific language code.
     *
     *
     * Languages are used to guide how the address is [ formatted for
 * display](http://en.wikipedia.org/wiki/Mailing_address_format_by_country). The same address may have different [AddressData] representations in
     * different languages. For example, the French name of "New Mexico" is "Nouveau-Mexique".
     */
    // BCP-47 language code for the address. Can be set to null.
    val languageCode: String?

    // NOTE: If you add a new field which is semantically significant, you must also add a check for
    // that field in {@link equals} and {@link hashCode}.
    init {
        this.postalCountry = builder.fields[AddressField.COUNTRY]
        this.administrativeArea = builder.fields[AddressField.ADMIN_AREA]
        this.locality = builder.fields[AddressField.LOCALITY]
        this.dependentLocality = builder.fields[AddressField.DEPENDENT_LOCALITY]
        this.postalCode = builder.fields[AddressField.POSTAL_CODE]
        this.sortingCode = builder.fields[AddressField.SORTING_CODE]
        this.organization = builder.fields[AddressField.ORGANIZATION]
        this.recipient = builder.fields[AddressField.RECIPIENT]
        this.landmarkAddressDescriptor = builder.fields[AddressField.LANDMARK_ADDRESS_DESCRIPTOR]
        this.landmarkAffix = builder.fields[AddressField.LANDMARK_AFFIX]
        this.landmarkName = builder.fields[AddressField.LANDMARK_NAME]
        this.addressLines = normalizeAddressLines(builder.addressLines)
        this.languageCode = builder.language
    }

    /**
     * Returns a string representation of the address, used for debugging.
     */
    override fun toString(): String {
        val output: StringBuilder = StringBuilder(
            "(AddressData: "
                    + "POSTAL_COUNTRY=" + postalCountry + "; "
                    + "LANGUAGE=" + languageCode + "; "
        )
        for (line: String? in addressLines!!) {
            output.append("$line; ")
        }
        output
            .append("ADMIN_AREA=")
            .append(administrativeArea)
            .append("; " + "LOCALITY=")
            .append(locality)
            .append("; " + "DEPENDENT_LOCALITY=")
            .append(dependentLocality)
            .append("; " + "POSTAL_CODE=")
            .append(postalCode)
            .append("; " + "SORTING_CODE=")
            .append(sortingCode)
            .append("; " + "ORGANIZATION=")
            .append(organization)
            .append("; " + "RECIPIENT=")
            .append(recipient)
            .append("; " + "LANDMARK_ADDRESS_DESCRIPTOR=")
            .append(landmarkAddressDescriptor)
            .append("; " + "LANDMARK_AFFIX=")
            .append(landmarkAffix)
            .append("; " + "LANDMARK_NAME=")
            .append(landmarkName)
            .append(")")
        return output.toString()
    }

    override fun equals(o: Any?): Boolean {
        if (o === this) {
            return true
        }
        if (o !is AddressData) {
            return false
        }
        val addressData = o

        return (((if (postalCountry == null
        ) addressData.postalCountry == null
        else (postalCountry == addressData.postalCountry))
                && (if (addressLines == null
        ) addressData.addressLines == null
        else (addressLines == addressData.addressLines))
                && (if (administrativeArea == null
        ) addressData.administrativeArea == null
        else (this.administrativeArea == addressData.administrativeArea))
                && (if (locality == null
        ) addressData.locality == null
        else (locality == addressData.locality))
                && (if (dependentLocality == null
        ) addressData.dependentLocality == null
        else (dependentLocality == addressData.dependentLocality))
                && (if (postalCode == null
        ) addressData.postalCode == null
        else (postalCode == addressData.postalCode))
                && (if (sortingCode == null
        ) addressData.sortingCode == null
        else (sortingCode == addressData.sortingCode))
                && (if (organization == null
        ) addressData.organization == null
        else (organization == addressData.organization))
                && (if (recipient == null
        ) addressData.recipient == null
        else (recipient == addressData.recipient))
                && (if (languageCode == null
        ) addressData.languageCode == null
        else (languageCode == addressData.languageCode))
                && (if (landmarkAddressDescriptor == null
        ) addressData.landmarkAddressDescriptor == null
        else (landmarkAddressDescriptor == addressData.landmarkAddressDescriptor))
                && (if (landmarkAffix == null
        ) addressData.landmarkAffix == null
        else (landmarkAffix == addressData.landmarkAffix))
                && (if (landmarkName == null
        ) addressData.landmarkName == null
        else (landmarkName == addressData.landmarkName))))
    }

    override fun hashCode(): Int {
        // 17 and 31 are arbitrary seed values.
        var result = 17

        val fields =
            arrayOf(
                postalCountry,
                administrativeArea,
                locality,
                dependentLocality,
                postalCode,
                sortingCode,
                organization,
                recipient,
                languageCode,
                landmarkAddressDescriptor,
                landmarkAffix,
                landmarkName
            )

        for (field: String? in fields) {
            result = 31 * result + (field?.hashCode() ?: 0)
        }

        // The only significant field which is not a String.
        result = 31 * result + (addressLines?.hashCode() ?: 0)

        return result
    }


    @get:Deprecated("Use {@link #getAddressLines} in preference. ")
    val addressLine1: String?
        get() = getAddressLine(1)


    @get:Deprecated("Use {@link #getAddressLines} in preference. ")
    val addressLine2: String?
        get() = getAddressLine(2)

    // Helper for returning the Nth address line. This is split out here so that it's easily to
    // change the maximum number of address lines we support.
    private fun getAddressLine(lineNumber: Int): String? {
        // If not the last available line, OR if we're the last line but there are no extra lines...
        if (lineNumber < ADDRESS_LINE_COUNT || lineNumber >= addressLines!!.size) {
            return if ((lineNumber <= addressLines!!.size)) addressLines[lineNumber - 1] else null
        }
        // We're asking for the last available line and there are additional lines in the data.
        // Here it should be true that: lineNumber == ADDRESS_LINE_COUNT
        // Guava equivalent:
        // return Joiner.on(", ")
        //     .join(addressLines.subList(ADDRESS_LINE_COUNT - 1, addressLines.size()));
        val joinedLastLine: StringBuilder =
            StringBuilder(addressLines!![lineNumber - 1]!!)
        for (line: String? in addressLines.subList(lineNumber, addressLines.size)) {
            joinedLastLine.append(", ").append(line)
        }
        return joinedLastLine.toString()
    }

    /**
     * Returns a value for those address fields which map to a single string value.
     *
     *
     * Note that while it is possible to pass [AddressField.ADDRESS_LINE_1] and
     * [AddressField.ADDRESS_LINE_2] into this method, these fields are deprecated and will be
     * removed. In general you should be using named methods to obtain specific values for the address
     * (eg, [.getAddressLines]) and avoid iterating in a general way over the fields.
     * This method has very little value outside of the widget itself and is scheduled for removal.
     *
     */
    @Suppress("deprecation") // TODO: Move this to a utility method rather than exposing it in the public API.
    @Deprecated("Do not use; scheduled for removal from the public API.")
    fun getFieldValue(field: AddressField): String? {
        when (field) {
            AddressField.COUNTRY -> return postalCountry
            AddressField.ADMIN_AREA -> return administrativeArea
            AddressField.LOCALITY -> return locality
            AddressField.DEPENDENT_LOCALITY -> return dependentLocality
            AddressField.POSTAL_CODE -> return postalCode
            AddressField.SORTING_CODE -> return sortingCode
            AddressField.ADDRESS_LINE_1 -> return addressLine1
            AddressField.ADDRESS_LINE_2 -> return addressLine2
            AddressField.ORGANIZATION -> return organization
            AddressField.RECIPIENT -> return recipient
            AddressField.LANDMARK_ADDRESS_DESCRIPTOR -> return landmarkAddressDescriptor
            AddressField.LANDMARK_AFFIX -> return landmarkAffix
            AddressField.LANDMARK_NAME -> return landmarkName
            else -> throw IllegalArgumentException("multi-value fields not supported: $field")
        }
    }

    /** Builder for AddressData.  */
    class Builder {
        // A map of single value address fields to their values.
        val fields: MutableMap<AddressField, String> = mutableMapOf()

        // The address lines, not normalized.
        val addressLines: MutableList<String?> = mutableListOf()

        // The BCP-47 language of the address.
        var language: String? = null

        /**
         * Constructs an empty builder for AddressData instances. Prefer to use one of the
         * [AddressData.builder] methods in preference to this.
         */
        // TODO: Migrate users and make this private.
        constructor()

        /**
         * Constructs a builder for AddressData instances using data from the given address.
         * Prefer to use one of the [AddressData.builder] methods in preference to this.
         *
         */
        @Deprecated("Use the builder() methods on AddressData in preference to this.")
        // TODO: Migrate users and delete this method.
        constructor(address: AddressData) {
            set(address)
        }

        /**
         * Sets the 2-letter CLDR region code of the address; see
         * [AddressData.getPostalCountry]. Unlike other values passed to the builder, the
         * region code can never be null.
         *
         * @param regionCode the CLDR region code of the address.
         */
        // TODO: Rename to setRegionCode.
        fun setCountry(regionCode: String?): Builder {
            return set(AddressField.COUNTRY, checkNotNull(regionCode))
        }

        /**
         * Sets or clears the administrative area of the address; see
         * [AddressData.getAdministrativeArea].
         *
         * @param adminAreaName the administrative area name, or null to clear an existing value.
         */
        // TODO: Rename to setAdministrativeArea.
        fun setAdminArea(adminAreaName: String?): Builder {
            return set(AddressField.ADMIN_AREA, adminAreaName)
        }

        /**
         * Sets or clears the locality of the address; see [AddressData.getLocality].
         *
         * @param locality the locality name, or null to clear an existing value.
         */
        fun setLocality(locality: String?): Builder {
            return set(AddressField.LOCALITY, locality)
        }

        /**
         * Sets or clears the dependent locality of the address; see
         * [AddressData.getDependentLocality].
         *
         * @param dependentLocality the dependent locality name, or null to clear an existing value.
         */
        fun setDependentLocality(dependentLocality: String?): Builder {
            return set(AddressField.DEPENDENT_LOCALITY, dependentLocality)
        }

        /**
         * Sets or clears the postal code of the address; see [AddressData.getPostalCode].
         *
         * @param postalCode the postal code, or null to clear an existing value.
         */
        fun setPostalCode(postalCode: String?): Builder {
            return set(AddressField.POSTAL_CODE, postalCode)
        }

        /**
         * Sets or clears the sorting code of the address; see [AddressData.getSortingCode].
         *
         * @param sortingCode the sorting code, or null to clear an existing value.
         */
        fun setSortingCode(sortingCode: String?): Builder {
            return set(AddressField.SORTING_CODE, sortingCode)
        }

        /**
         * Sets or clears the BCP-47 language code for this address (eg, "en" or "zh-Hant"). If the
         * language is not set, then the address will be assumed to be in the default language of the
         * country of the address; however it is highly discouraged to rely on this as the default
         * language may change over time. See [AddressData.getLanguageCode].
         *
         * @param languageCode the BCP-47 language code, or null to clear an existing value.
         */
        fun setLanguageCode(languageCode: String?): Builder {
            this.language = languageCode
            return this
        }

        /**
         * Sets multiple unstructured street level lines in the address. Calling this method will
         * always discard any existing address lines before adding new ones.
         *
         *
         * Note that the number of lines set by this method is preserved in the builder's state but a
         * single line set here may result in multiple lines in the resulting `AddressData`
         * instance if it contains embedded newline characters.
         *
         *
         * For example:
         * <pre>`data = AddressData.builder()
         * .setAddressLines(Arrays.asList("First line\nSecond line"))
         * .setAddressLine2("Last line");
         * .build();
         * // data.getAddressLines() == [ "First line", "Second line", "Last line" ]
        `</pre> *
         */
        fun setAddressLines(lines: Iterable<String>): Builder {
            addressLines.clear()
            for (line: String in lines) {
                addressLines.add(line)
            }
            return this
        }

        /**
         * Adds another address line. Embedded newlines will be normalized when "build()" is called.
         */
        // TODO: Consider removing this method if nobody is using it to simplify the API.
        fun addAddressLine(value: String): Builder {
            addressLines.add(value)
            return this
        }

        /**
         * Sets multiple street lines from a single street string, clearing any existing address lines
         * first. The input string may contain new lines which will result in multiple separate lines
         * in the resulting `AddressData` instance. After splitting, each line is trimmed and
         * empty lines are ignored.
         *
         *
         * Example: `"  \n   \n1600 Amphitheatre Ave\n\nRoom 122"` will set the lines:
         *
         *  1. "1600 Amphitheatre Ave"
         *  1. "Room 122"
         *
         *
         * @param value a string containing one or more address lines, separated by `"\n"`.
         */
        fun setAddress(value: String): Builder {
            addressLines.clear()
            addressLines.add(value)
            normalizeAddressLines(addressLines)
            return this
        }

        /**
         * Copies all the data of the given address into the builder. Any existing data in the builder
         * is discarded.
         */
        fun set(data: AddressData): Builder {
            fields.clear()
            for (addressField: AddressField in SINGLE_VALUE_FIELDS) {
                set(addressField, data.getFieldValue(addressField))
            }
            addressLines.clear()
            addressLines.addAll(data.addressLines!!)
            setLanguageCode(data.languageCode)
            return this
        }

        /**
         * TODO: Remove this method in favor of setAddressLines(Iterable<String>).
         *
        </String> */
        @Deprecated("Use {@link #setAddressLines} instead.")
        fun setAddressLine1(value: String?): Builder {
            return setAddressLine(1, value)
        }

        /**
         * TODO: Remove this method in favor of setAddressLines(Iterable<String>).
         *
        </String> */
        @Deprecated("Use {@link #setAddressLines} instead.")
        fun setAddressLine2(value: String?): Builder {
            return setAddressLine(2, value)
        }

        /**
         * Sets or clears the organization of the address; see [AddressData.getOrganization].
         *
         * @param organization the organization, or null to clear an existing value.
         */
        fun setOrganization(organization: String?): Builder {
            return set(AddressField.ORGANIZATION, organization)
        }

        /**
         * Sets or clears the recipient of the address; see [AddressData.getRecipient].
         *
         * @param recipient the recipient, or null to clear an existing value.
         */
        fun setRecipient(recipient: String?): Builder {
            return set(AddressField.RECIPIENT, recipient)
        }

        /**
         * Sets or clears the landmark address descriptor of the address; see [ ][AddressData.Landmark.getPrefix] and [AddressData.Landmark.getName].
         *
         * @param landmarkAddressDescriptor the landmark address descriptor, or null to clear an
         * existing value.
         */
        fun setLandmarkAddressDescriptor(landmarkAddressDescriptor: String?): Builder {
            return set(AddressField.LANDMARK_ADDRESS_DESCRIPTOR, landmarkAddressDescriptor)
        }

        /**
         * Sets or clears the landmark affix of the address; see [ ][AddressData.Landmark.getPrefix].
         *
         * @param landmarkAffix the landmark affix, or null to clear an existing value.
         */
        fun setLandmarkAffix(landmarkAffix: String?): Builder {
            return set(AddressField.LANDMARK_AFFIX, landmarkAffix)
        }

        /**
         * Sets or clears the landmark name of the address; see [AddressData.Landmark.getName].
         *
         * @param landmarkName the landmark name, or null to clear an existing value.
         */
        fun setLandmarkName(landmarkName: String?): Builder {
            return set(AddressField.LANDMARK_NAME, landmarkName)
        }

        /**
         * Sets an address field with the specified value. If the value is empty (null or whitespace),
         * the original value associated with the field will be removed.
         *
         */
        @Suppress("deprecation") // TODO: Reimplement using public API as a utility function in AddressWidget (the only caller).
        @Deprecated("Do not use; scheduled for removal from the public API.")
        fun set(field: AddressField, value: String?): Builder {
            var value = value
            if (SINGLE_VALUE_FIELDS.contains(field)) {
                value = Util.trimToNull(value)
                if (value == null) {
                    fields.remove(field)
                } else {
                    fields[field] = value
                }
            } else if (field === AddressField.STREET_ADDRESS) {
                if (value == null) {
                    addressLines.clear()
                } else {
                    setAddress(value)
                }
            } else {
                val lineNum = ADDRESS_LINE_FIELDS.indexOf(field) + 1
                if (lineNum > 0) {
                    setAddressLine(lineNum, value)
                }
            }
            return this
        }

        // This may preserve whitespace at the ends of lines, but this gets normalized when we build
        // the data instance.
        private fun setAddressLine(lineNum: Int, value: String?): Builder {
            if (Util.trimToNull(value) == null) {
                if (lineNum < addressLines.size) {
                    // Clearing an element that isn't the last in the list.
                    addressLines[lineNum - 1] = null
                } else if (lineNum == addressLines.size) {
                    // Clearing the last element (remove it and clear up trailing nulls).
                    addressLines.removeAt(lineNum - 1)
                    var i = addressLines.size - 1
                    while (i >= 0 && addressLines[i] == null) {
                        addressLines.removeAt(i)
                        i--
                    }
                }
            } else {
                // Padding the list with nulls if necessary.
                for (i in addressLines.size until lineNum) {
                    addressLines.add(null)
                }
                // Set the non-null value.
                addressLines[lineNum - 1] = value
            }
            return this
        }

        /**
         * Builds an AddressData instance from the current state of the builder. A builder instance may
         * be used to build multiple data instances.
         *
         *
         * During building the street address line information is normalized and the following will be
         * true for any build instance.
         *
         *  1. The order of address lines is retained relative to the builder.
         *  1. Empty address lines (empty strings, whitespace only or null) are removed.
         *  1. Remaining address lines are trimmed of whitespace.
         *
         */
        fun build(): AddressData {
            return AddressData(this)
        }
    }

    companion object {
        // The list of deprecated address fields which are superseded by STREET_ADDRESS.
        @Suppress("deprecation") // For legacy address fields.
        private val ADDRESS_LINE_FIELDS: List<AddressField> =
            listOf(AddressField.ADDRESS_LINE_1, AddressField.ADDRESS_LINE_2)

        private val ADDRESS_LINE_COUNT = ADDRESS_LINE_FIELDS.size

        // The set of address fields for which a single string value can be mapped.
        private val SINGLE_VALUE_FIELDS: Set<AddressField>

        init {
            SINGLE_VALUE_FIELDS = AddressField.entries.toSet().toMutableSet()
            SINGLE_VALUE_FIELDS.removeAll(ADDRESS_LINE_FIELDS)
            SINGLE_VALUE_FIELDS.remove(AddressField.STREET_ADDRESS)
        }

        // Helper to normalize a list of address lines. The input may contain null entries or strings
        // which must be split into multiple lines. The resulting list entries will be trimmed
        // consistently with String.trim() and any empty results are ignored.
        // TODO: Trim entries properly with respect to Unicode whitespace.
        private fun normalizeAddressLines(lines: MutableList<String?>): List<String?> {
            // Guava equivalent code for each line would look something like:
            // Splitter.on("\n").trimResults(CharMatcher.inRange('\0', ' ')).omitEmptyStrings();
            var index = 0
            while (index < lines.size) {
                val line: String = lines.removeAt(index) ?: continue
                if (line.contains("\n")) {
                    for (splitLine: String in line.split("\n".toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray()) {
                        index = maybeAddTrimmedLine(splitLine, lines, index)
                    }
                } else {
                    index = maybeAddTrimmedLine(line, lines, index)
                }
            }
            return lines
        }

        // Helper to trim a string and (if not empty) add it to the given list at the specified index.
        // Returns the new index at which any following elements should be added.
        private fun maybeAddTrimmedLine(line: String, lines: MutableList<String?>, index: Int): Int {
            var line: String? = line
            var index = index
            line = Util.trimToNull(line)
            if (line != null) {
                lines.add(index++, line)
            }
            return index
        }

        /** Returns a new builder to construct an `AddressData` instance.  */
        fun builder(): Builder {
            return Builder()
        }

        /** Returns a new builder to construct an `AddressData` instance.  */
        fun builder(address: AddressData): Builder {
            return builder().set(address)
        }
    }
}
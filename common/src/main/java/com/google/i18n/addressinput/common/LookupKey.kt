package com.kurviger.shared.models.address


/**
 * A builder for creating keys that are used to lookup data in the local cache and fetch data from
 * the server. There are two key types: [KeyType.DATA] or [KeyType.EXAMPLES].
 *
 *
 * The [KeyType.DATA] key is built based on a universal Address hierarchy, which is:<br></br>
 * [AddressField.COUNTRY] -> [AddressField.ADMIN_AREA] -> [AddressField.LOCALITY]
 * -> [AddressField.DEPENDENT_LOCALITY]
 *
 *
 * The [KeyType.EXAMPLES] key is built with the following format:<br></br>
 * [AddressField.COUNTRY] -> [ScriptType] -> language.
 */
class LookupKey private constructor(builder: Builder) {
    /**
     * Key types. Address Widget organizes address info based on key types. For example, if you want
     * to know how to verify or format an US address, you need to use [KeyType.DATA] to get
     * that info; if you want to get an example address, you use [KeyType.EXAMPLES] instead.
     */
    enum class KeyType {
        /**
         * Key type for getting address data.
         */
        DATA,

        /**
         * Key type for getting examples.
         */
        EXAMPLES
    }

    /**
     * Script types. This is used for countries that do not use Latin script, but accept it for
     * transcribing their addresses. For example, you can write a Japanese address in Latin script
     * instead of Japanese:
     * <pre>7-2, Marunouchi 2-Chome, Chiyoda-ku, Tokyo 100-8799 </pre>
     *
     *
     * Notice that [ScriptType] is based on country/region, not language.
     */
    enum class ScriptType {
        /**
         * The script that uses Roman characters like ABC (as opposed to scripts like Cyrillic or
         * Arabic).
         */
        LATIN,

        /**
         * Local scripts. For Japan, it's Japanese (including Hiragana, Katagana, and Kanji); For
         * Saudi Arabia, it's Arabic. Notice that for US, the local script is actually Latin script
         * (The same goes for other countries that use Latin script). For these countries, we do not
         * provide two set of data (Latin and local) since they use only Latin script. You have to
         * specify the [ScriptType] as local instead Latin.
         */
        LOCAL
    }

    val keyType: KeyType?

    private val scriptType: ScriptType

    // Values for each address field in the hierarchy.
    private val nodes: Map<AddressField, String?>

    private val keyString: String

    private val languageCode: String?

    init {
        this.keyType = builder.keyType
        this.scriptType = builder.script
        this.nodes = builder.nodes
        this.languageCode = builder.languageCode
        this.keyString = createKeyString()
    }

    /**
     * Gets a lookup key built from the values of nodes in the hierarchy up to and including the input
     * address field. This method does not allow keys with a key type of [KeyType.EXAMPLES].
     *
     * @param field a field in the address hierarchy.
     * @return key of the specified address field. If address field is not in the hierarchy, or is
     * more granular than the data present in the current key, returns null. For example, if your
     * current key is "data/US" (down to COUNTRY level), and you want to get the key for LOCALITY
     * (more granular than COUNTRY), it will return null.
     */
    @Nullable
    fun getKeyForUpperLevelField(field: AddressField): LookupKey? {
        if (keyType != KeyType.DATA) {
            // We only support getting the parent key for the data key type.
            throw java.lang.RuntimeException("Only support getting parent keys for the data key type.")
        }
        val newKeyBuilder = Builder(this)

        var removeNode = false
        var fieldInHierarchy = false
        for (hierarchyField in HIERARCHY) {
            if (removeNode) {
                if (newKeyBuilder.nodes.containsKey(hierarchyField)) {
                    newKeyBuilder.nodes.remove(hierarchyField)
                }
            }
            if (hierarchyField == field) {
                if (!newKeyBuilder.nodes.containsKey(hierarchyField)) {
                    return null
                }
                removeNode = true
                fieldInHierarchy = true
            }
        }

        if (!fieldInHierarchy) {
            return null
        }

        newKeyBuilder.languageCode = languageCode
        newKeyBuilder.script = scriptType

        return newKeyBuilder.build()
    }

    /**
     * Returns the string value of a field in a key for a particular
     * AddressField. For example, for the key "data/US/CA" and the address
     * field AddressField.COUNTRY, "US" would be returned. Returns an empty
     * string if the key does not have this field in it.
     */
    fun getValueForUpperLevelField(field: AddressField): String? {
        if (!nodes.containsKey(field)) {
            return ""
        }

        return nodes[field]
    }

    @get:Nullable
    val parentKey: LookupKey?
        /**
         * Gets parent key for data key. For example, parent key for "data/US/CA" is "data/US". This
         * method does not allow key with key type of [KeyType.EXAMPLES].
         */
        get() {
            if (keyType != KeyType.DATA) {
                throw java.lang.RuntimeException("Only support getting parent keys for the data key type.")
            }
            // Root key's parent should be null.
            if (!nodes.containsKey(AddressField.COUNTRY)) {
                return null
            }

            val parentKeyBuilder = Builder(this)
            var mostGranularField = AddressField.COUNTRY

            for (hierarchyField in HIERARCHY) {
                if (!nodes.containsKey(hierarchyField)) {
                    break
                }
                mostGranularField = hierarchyField
            }
            parentKeyBuilder.nodes.remove(mostGranularField)
            return parentKeyBuilder.build()
        }

    /**
     * Creates the string format of the given key. E.g., "data/US/CA".
     */
    private fun createKeyString(): String {
        val keyBuilder: java.lang.StringBuilder = java.lang.StringBuilder(
            Util.toLowerCaseLocaleIndependent(
                keyType!!.name
            )
        )

        if (keyType == KeyType.DATA) {
            for (field in HIERARCHY) {
                if (!nodes.containsKey(field)) {
                    break
                }
                keyBuilder.append(SLASH_DELIM).append(nodes[field])
            }
            // Only append the language if this is not the root key and there was a language.
            if (languageCode != null && nodes.size > 0) {
                keyBuilder.append(DASH_DELIM).append(languageCode)
            }
        } else {
            if (nodes.containsKey(AddressField.COUNTRY)) {
                // Example key. E.g., "examples/TW/local/_default".
                keyBuilder.append(SLASH_DELIM)
                    .append(nodes[AddressField.COUNTRY])
                    .append(SLASH_DELIM)
                    .append(Util.toLowerCaseLocaleIndependent(scriptType.name))
                    .append(SLASH_DELIM)
                    .append(DEFAULT_LANGUAGE)
            }
        }

        return keyBuilder.toString()
    }

    /**
     * Gets a lookup key as a plain text string., e.g., "data/US/CA".
     */
    override fun toString(): String {
        return keyString
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if ((obj == null) || (obj.javaClass != this.javaClass)) {
            return false
        }

        return (obj as LookupKey).toString() == keyString
    }

    override fun hashCode(): Int {
        return keyString.hashCode()
    }

    /**
     * Builds lookup keys.
     */
    // TODO: This is used in AddressWidget in a small number of places and it should be possible
    // to hide this type within this package quite easily.
    class Builder {
        var keyType: KeyType? = null

        // Default to LOCAL script.
        var script: ScriptType = ScriptType.LOCAL

        val nodes: MutableMap<AddressField, String?> = java.util.EnumMap<AddressField, String>(
            AddressField::class.java
        )

        var languageCode: String? = null

        /**
         * Creates a new builder for the specified key type. keyType cannot be null.
         */
        constructor(keyType: KeyType?) {
            this.keyType = keyType
        }

        /**
         * Creates a new builder for the specified key. oldKey cannot be null.
         */
        internal constructor(oldKey: LookupKey) {
            this.keyType = oldKey.keyType
            this.script = oldKey.scriptType
            this.languageCode = oldKey.languageCode
            for (field in HIERARCHY) {
                if (!oldKey.nodes.containsKey(field)) {
                    break
                }
                nodes[field] = oldKey.nodes[field]
            }
        }

        /**
         * Builds the [LookupKey] with the input key string. Input string has to represent
         * either a [KeyType.DATA] key or a [KeyType.EXAMPLES] key. Also, key hierarchy
         * deeper than [AddressField.DEPENDENT_LOCALITY] is not allowed. Notice that if any
         * node in the hierarchy is empty, all the descendant nodes' values will be neglected. For
         * example, input string "data/US//Mt View" will become "data/US".
         *
         * @param keyString e.g., "data/US/CA"
         */
        constructor(keyString: String) {
            var parts = keyString.split(SLASH_DELIM.toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()

            if (parts[0] != Util.toLowerCaseLocaleIndependent(KeyType.DATA.name)
                && parts[0] != Util.toLowerCaseLocaleIndependent(KeyType.EXAMPLES.name)
            ) {
                throw java.lang.RuntimeException("Wrong key type: " + parts[0])
            }
            if (parts.size > HIERARCHY.size + 1) {
                // Assume that any extra elements found in the key belong in the 'dependent locality' field.
                // This means that a key string of /EXAMPLES/C/A/L/D/E would result in a dependent locality
                // value of 'D/E'. This also means that if it's the actual locality name has a slash in it
                // (for example 'L/D'), the locality field which we break down will be incorrect
                // (for example: 'L'). Regardless, the actual breakdown of the key doesn't impact the server
                // lookup, so there will be no problems.
                val extraParts: Array<String> =
                    java.util.Arrays.copyOfRange<String>(parts, HIERARCHY.size + 1, parts.size + 1)

                // Update the original array to only contain the number of elements which we expect.
                parts = java.util.Arrays.copyOfRange<String>(parts, 0, HIERARCHY.size + 1)

                // Append the extra parts to the last element (dependent locality).
                for (element in extraParts) {
                    if (element != null) {
                        parts[4] += SLASH_DELIM + element
                    }
                }
            }

            if (parts[0] == "data") {
                keyType = KeyType.DATA

                // Process all parts of the key, starting from the country.
                for (i in 1 until parts.size) {
                    // TODO: We shouldn't need the trimToNull here.
                    var substr: String = Util.trimToNull(parts[i]) ?: break
                    // If a language code specification was present, extract this. This should only be there
                    // (if it ever is) on the last node.
                    if (substr.contains(DASH_DELIM)) {
                        val s = substr.split(DASH_DELIM.toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()
                        if (s.size != 2) {
                            throw RuntimeException(
                                "Wrong format: Substring should be <last node value>--<language code>"
                            )
                        }
                        substr = s[0]
                        languageCode = s[1]
                    }

                    nodes[HIERARCHY[i - 1]] = substr
                }
            } else if (parts[0] == "examples") {
                keyType = KeyType.EXAMPLES

                // Parses country info.
                if (parts.size > 1) {
                    nodes[AddressField.COUNTRY] =
                        parts[1]
                }

                // Parses script types.
                if (parts.size > 2) {
                    val scriptStr = parts[2]
                    if (scriptStr == "local") {
                        this.script = ScriptType.LOCAL
                    } else if (scriptStr == "latin") {
                        this.script = ScriptType.LATIN
                    } else {
                        throw RuntimeException("Script type has to be either latin or local.")
                    }
                }

                // Parses language code. Example: "zh_Hant" in
                // "examples/TW/local/zH_Hant".
                if (parts.size > 3 && parts[3] != DEFAULT_LANGUAGE) {
                    languageCode = parts[3]
                }
            }
        }

        fun setLanguageCode(languageCode: String?): Builder {
            this.languageCode = languageCode
            return this
        }

        /**
         * Sets key using [AddressData]. Notice that if any node in the hierarchy is empty,
         * all the descendant nodes' values will be neglected. For example, the following address
         * misses [AddressField.ADMIN_AREA], thus its data key will be "data/US".
         *
         *
         *  country: US<br></br> administrative area: null<br></br> locality: Mt. View
         */
        fun setAddressData(data: AddressData): Builder {
            languageCode = data.languageCode
            if (languageCode != null) {
                if (Util.isExplicitLatinScript(languageCode)) {
                    script = ScriptType.LATIN
                }
            }

            if (data.postalCountry == null) {
                return this
            }
            nodes[AddressField.COUNTRY] = data.postalCountry

            if (data.administrativeArea == null) {
                return this
            }
            nodes[AddressField.ADMIN_AREA] =
                data.administrativeArea

            if (data.locality == null) {
                return this
            }
            nodes[AddressField.LOCALITY] =
                data.locality

            if (data.dependentLocality == null) {
                return this
            }
            nodes[AddressField.DEPENDENT_LOCALITY] =
                data.dependentLocality
            return this
        }

        fun build(): LookupKey {
            return LookupKey(this)
        }
    }

    companion object {
        /**
         * The universal address hierarchy. Notice that sub-administrative area is neglected here since
         * it is not required to fill out address forms.
         */
        private val HIERARCHY = arrayOf(
            AddressField.COUNTRY,
            AddressField.ADMIN_AREA,
            AddressField.LOCALITY,
            AddressField.DEPENDENT_LOCALITY
        )

        private const val SLASH_DELIM = "/"

        private const val DASH_DELIM = "--"

        private const val DEFAULT_LANGUAGE = "_default"

        fun hasValidKeyPrefix(key: String): Boolean {
            for (type in KeyType.entries) {
                val lowercase = Util.toLowerCaseLocaleIndependent(type.name)
                if (lowercase != null && key.startsWith(lowercase)) {
                    return true
                }
            }
            return false
        }
    }
}
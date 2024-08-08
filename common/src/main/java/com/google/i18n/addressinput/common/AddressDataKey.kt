package com.kurviger.shared.models.address

import com.kurviger.shared.models.address.Util.toLowerCaseLocaleIndependent


/**
 * Enumerates all the data fields found in the JSON-format address property data that are used by
 * the Android Address Input Widget.
 */
enum class AddressDataKey {
    /**
     * Identifies the countries for which data is provided.
     */
    COUNTRIES,

    /**
     * The standard format string. This identifies which fields can be used in the address, along with
     * their order. This also carries additional information for use in formatting the fields into
     * multiple lines. This is also used to indicate which fields should _not_ be used for an address.
     */
    FMT,

    /**
     * The unique ID of the region, in the form of a path from parent IDs to the key.
     */
    ID,

    /**
     * The CLDR (Common Locale Data Repository) country code (http://goto/iii) for this region, if
     * there is one. This value must be present.
     */
    ISOID,

    /**
     * The key of the region, unique to its parent. If there is an accepted abbreviation for this
     * region, then the key will be set to this and name will be set to the local name for this
     * region. If there is no accepted abbreviation, then this key will be the local name and there
     * will be no local name specified. This value must be present.
     */
    KEY,

    /**
     * The default language of any data for this region, if known.
     */
    LANG,

    /**
     * The languages used by any data for this region, if known.
     */
    LANGUAGES,

    /**
     * The latin format string [.FMT] used when a country defines an alternative format for
     * use with the latin script, such as in China.
     */
    LFMT,

    /**
     * Indicates the type of the name used for the locality (city) field.
     */
    LOCALITY_NAME_TYPE,

    /**
     * Indicates which fields must be present in a valid address.
     */
    REQUIRE,

    /**
     * Indicates the type of the name used for the state (administrative area) field.
     */
    STATE_NAME_TYPE,

    /**
     * Indicates the type of the name used for the sublocality field.
     */
    SUBLOCALITY_NAME_TYPE,

    /**
     * Encodes the [.KEY] value of all the children of this region.
     */
    SUB_KEYS,

    /**
     * Encodes the transliterated latin name value of all the children of this region, if the local
     * names are not in latin script already.
     */
    SUB_LNAMES,

    /**
     * Indicates, for each child of this region, whether that child has additional children.
     */
    SUB_MORES,

    /**
     * Encodes the local name value of all the children of this region.
     */
    SUB_NAMES,

    /**
     * Encodes width overrides for specific fields.
     */
    WIDTH_OVERRIDES,

    /**
     * Encodes the [.ZIP] value for the subtree beneath this region.
     */
    XZIP,

    /**
     * Encodes the postal code pattern if at the country level, and the postal code prefix if at a
     * level below country.
     */
    ZIP,

    /** Indicates the type of the name used for the ZIP (postal code) field.  */
    ZIP_NAME_TYPE,

    /** Common prefix for postal code.  */
    POSTPREFIX,

    /** Helper URL for postal code.  */
    POSTURL;

    companion object {
        /**
         * Returns a field based on its keyname (value in the JSON-format file), or null if no field
         * matches.
         */
        fun get(keyname: String?): AddressDataKey? {
            return ADDRESS_KEY_NAME_MAP[toLowerCaseLocaleIndependent(keyname)]
        }

        private val ADDRESS_KEY_NAME_MAP: MutableMap<String?, AddressDataKey> = mutableMapOf()

        init {
            // Populates the map of enums against their lower-cased string values for easy look-up.
            for (field in entries) {
                ADDRESS_KEY_NAME_MAP[toLowerCaseLocaleIndependent(field.toString())] =
                    field
            }
        }
    }
}
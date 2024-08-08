package com.kurviger.shared.models.address


/**
 * Identifiers for the input fields of the address widget, used to control options related to
 * visibility and ordering of UI elements. Note that one `AddressField` may represent more
 * than one input field in the UI (eg, [.STREET_ADDRESS]), but each input field can be
 * identified by exactly one `AddressField`.
 *
 *
 * In certain use cases not all fields are necessary, and you can hide fields using
 * [FormOptions.setHidden]. An example of this is when you are collecting postal addresses not
 * intended for delivery and wish to suppress the collection of a recipient's name or organization.
 *
 *
 * An alternative to hiding fields is to make them read-only, using [FormOptions.setReadonly].
 * An example of this would be in the case that the country of an address was already determined but
 * we wish to make it clear to the user that we have already taken it into account and do not want
 * it entered again.
 *
 * @see FormOptions
 */
enum class AddressField(
    /**
     * Returns the field's identification character, as used in the metadata.
     *
     * @return identification char.
     */
    // Defines the character codes used in the metadata to specify the types of fields used in
    // address formatting. Note that the metadata also has a character for newlines, which is
    // not defined here.
    val char: Char
) {
    /** The drop-down menu used to select a region for [AddressData.getPostalCountry].  */
    COUNTRY('R'),

    /**
     * The input field used to enter a value for [AddressData.getAddressLine1].
     */
    @Deprecated("Use {@link #STREET_ADDRESS} instead.")
    ADDRESS_LINE_1('1'),

    /**
     * The input field used to enter a value for [AddressData.getAddressLine2].
     */
    @Deprecated("Use {@link #STREET_ADDRESS} instead.")
    ADDRESS_LINE_2('2'),

    /** The input field(s) used to enter values for [AddressData.getAddressLines].  */
    STREET_ADDRESS('A'),

    /** The input field used to enter a value for [AddressData.getAdministrativeArea].  */
    ADMIN_AREA('S'),

    /** The input field used to enter a value for [AddressData.getLocality].  */
    LOCALITY('C'),

    /** The input field used to enter a value for [AddressData.getDependentLocality].  */
    DEPENDENT_LOCALITY('D'),

    /** The input field used to enter a value for [AddressData.getPostalCode].  */
    POSTAL_CODE('Z'),

    /** The input field used to enter a value for [AddressData.getSortingCode].  */
    SORTING_CODE('X'),

    /** The input field used to enter a value for [AddressData.getRecipient].  */
    RECIPIENT('N'),

    /** The input field used to enter a value for [AddressData.getOrganization].  */
    ORGANIZATION('O'),

    /** The input field used to enter a value for [AddressData.Landmark].  */
    LANDMARK_ADDRESS_DESCRIPTOR('T'),

    /** The input field used to enter a value for [AddressData.Landmark.getPrefix].  */
    LANDMARK_AFFIX('F'),

    /** The input field used to enter a value for [AddressData.Landmark.getName].  */
    LANDMARK_NAME('L'),
    ;

    /** Classification of the visual width of address input fields.  */
    enum class WidthType {
        /**
         * Identifies an input field as accepting full-width input, such as address lines or recipient.
         */
        LONG,

        /**
         * Identifies an input field as accepting short (often bounded) input, such as postal code.
         */
        SHORT;

        companion object {
            fun of(c: Char): WidthType {
                return when (c) {
                    'N', 'S' -> SHORT
                    'L' -> LONG
                    else -> throw IllegalArgumentException("invalid width character: $c")
                }
            }
        }
    }

    private val defaultWidthType: WidthType
        /**
         * Returns default width of this address field. This may be overridden for a specific country when
         * we have data for the possible inputs in that field and use a drop-down, rather than a text
         * field, in the UI.
         */
        get() = if (this == POSTAL_CODE || (this == SORTING_CODE)) WidthType.SHORT else WidthType.LONG

    /**
     * Returns default width of this address field. Takes per-country heuristics into account for
     * text input fields. This may be overridden for a specific country when we have data for the
     * possible inputs in that field and use a drop-down, rather than a text field, in the UI.
     */
    fun getWidthTypeForRegion(regionCode: String?): WidthType {
        Util.checkNotNull(regionCode)
        val width = FormatInterpreter.getWidthOverride(this, regionCode)
        return width ?: defaultWidthType
    }

    companion object {
        private val FIELD_MAPPING: Map<Char, AddressField>

        init {
            val map: MutableMap<Char, AddressField> = mutableMapOf()
            for (value in entries) {
                map[value.char] = value
            }
            FIELD_MAPPING = map.toMap()
        }

        /**
         * Returns the AddressField corresponding to the given identification character.
         *
         * @throws IllegalArgumentException if the identifier does not correspond to a valid field.
         */
        fun of(c: Char): AddressField {
            val field =
                FIELD_MAPPING[c] ?: throw IllegalArgumentException("invalid field character: $c")
            return field
        }
    }
}
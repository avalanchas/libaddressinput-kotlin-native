package com.kurviger.shared.models.address

import com.kurviger.shared.models.address.Util.toUpperCaseLocaleIndependent


/**
 * Configuration options for the address input widget used to control the visibility and interaction
 * of specific fields to suit specific use cases (eg, collecting business addresses, collecting
 * addresses for credit card verification etc...).
 *
 *
 * When form options are passed to the address widget a snapshot is taken and any further changes to
 * the options are ignored.
 *
 *
 * This design is somewhat like using a builder but has the advantage that the caller only sees the
 * outer (mutable) type and never needs to know about the "built" snapshot. This reduces the public
 * API footprint and simplifies usage of this class.
 */
// This is an external class and part of the widget's public API.
class FormOptions
/** Creates an empty, mutable, form options instance.  */
{
    // These fields must never be null).
    private val hiddenFields: MutableSet<AddressField> = mutableSetOf()
    private val readonlyFields: MutableSet<AddressField> = mutableSetOf()
    private val blacklistedRegions: MutableSet<String?> = mutableSetOf()

    // Key is ISO 2-letter region code.
    private val customFieldOrder: MutableMap<String, List<AddressField>> = mutableMapOf()

    /**
     * Hides the given address field. Calls to this method **are cumulative**. Fields
     * which are specified here but not part of a country's specified fields will be ignored.
     *
     *
     * Note also that hiding fields occurs after custom ordering has been applied, although combining
     * these two features is not generally recommended due to the confusion it is likely to cause.
     */
    fun setHidden(field: AddressField): FormOptions {
        hiddenFields.add(field)
        return this
    }

    /**
     * Sets the given address field as read-only. Calls to this method **are cumulative
     ** * . Fields which are specified here but not part of a country's specified fields will be
     * ignored.
     *
     *
     * This method is j2objc- & iOS API friendly as the signature does not expose varargs / Java
     * arrays or collections.
     */
    fun setReadonly(field: AddressField): FormOptions {
        readonlyFields.add(field)
        return this
    }

    /**
     * Sets the order of address input fields for the given ISO 3166-1 two letter country code.
     *
     *
     * Input fields affected by custom ordering will be shown in the widget in the order they are
     * given to this method (for the associated region code). Fields which are visible for a region,
     * but which are not specified here, will appear in their original position in the form. For
     * example, if a region defines the following fields:
     * <pre>
     * [ RECIPIENT -> ORGANIZATION -> STREET_ADDRESS -> LOCALITY -> ADMIN_AREA -> COUNTRY ]
    </pre> *
     * and the custom ordering for that region is (somewhat contrived):
     * <pre>
     * [ ORGANIZATION -> COUNTRY -> RECIPIENT ]
    </pre> *
     * Then the visible order of the input fields will be:
     * <pre>
     * [ ORGANIZATION -> COUNTRY -> STREET_ADDRESS -> LOCALITY -> ADMIN_AREA -> RECIPIENT ]
    </pre> *
     *
     *  * Fields not specified in the custom ordering (STREET_ADDRESS, LOCALITY, ADMIN_AREA)
     * remain in their original, absolute, positions.
     *  * Custom ordered fields are re-positioned such that their relative order is now as
     * specified (but other, non custom-ordered, fields can appear between them).
     *
     *
     *
     * If the custom order contains a field which is not present for the specified region, it is
     * silently ignored. Setting a custom ordering can never be used as a way to add fields for a
     * region.
     *
     *
     * Typically this feature is used to reverse things like RECIPIENT and ORGANIZATION for certain
     * business related use cases. It should not be used to "correct" perceived bad field ordering
     * or make different countries "more consistent with each other".
     */
    fun setCustomFieldOrder(regionCode: String, vararg fields: AddressField): FormOptions {
        // TODO: Consider checking the given region code for validity against RegionDataConstants.
        val fieldList: List<AddressField> = listOf(*fields)
        if (fieldList.isNotEmpty()) {
            fieldList.toSet().size
            if (fieldList.toSet().size != fieldList.size) {
                throw IllegalArgumentException("duplicate address field: $fieldList")
            }
            customFieldOrder[regionCode] = fieldList
        } else {
            customFieldOrder.remove(regionCode)
        }
        return this
    }

    /**
     * Blacklist the given CLDR (Common Locale Data Repository) region (country) code
     * indicating countries that for legal or other reasons should not be available.
     *
     *
     * Calls are cumulative, call this method once for each region that needs to be blacklisted.
     *
     *
     * We reserve the right to change this API from taking individual regions to taking a set.
     */
    fun blacklistRegion(regionCode: String?): FormOptions {
        requireNotNull(regionCode)
        // TODO(user): Add region code validation against RegionDataConstants.
        blacklistedRegions.add(toUpperCaseLocaleIndependent(regionCode))
        return this
    }

    /** Returns an immutable snapshot of the current state of the form options.  */
    fun createSnapshot(): Snapshot {
        return Snapshot(this)
    }

    /** An immutable snapshot of a `FormOptions` instance.  */
    class Snapshot internal constructor(options: FormOptions) {
        private val hiddenFields: Set<AddressField> = options.hiddenFields

        private val readonlyFields: Set<AddressField> = options.readonlyFields

        private val blacklistedRegions: Set<String?> = options.blacklistedRegions

        // Shallow copy as lists are already immutable.
        private val customFieldOrder: Map<String, List<AddressField>> = options.customFieldOrder

        fun isHidden(field: AddressField): Boolean {
            return hiddenFields.contains(field)
        }

        fun isReadonly(field: AddressField): Boolean {
            return readonlyFields.contains(field)
        }

        /**
         * Gets the overridden field orders with their corresponding region code. Returns null if field
         * orders for `regionCode` is not specified.
         */
        fun getCustomFieldOrder(regionCode: String): List<AddressField> {
            return customFieldOrder[regionCode]!!
        }

        fun isBlacklistedRegion(regionCode: String?): Boolean {
            return blacklistedRegions.contains(toUpperCaseLocaleIndependent(regionCode))
        }
    }
}
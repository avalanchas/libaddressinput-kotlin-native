package com.kurviger.shared.models.address


/**
 * Utility functions used by the address widget.
 */
object Util {
    /**
     * This variable is in upper-case, since we convert the language code to upper case before doing
     * string comparison.
     */
    private val LATIN_SCRIPT = "LATN"

    /**
     * Map of countries that have non-latin local names, with the language that their local names
     * are in. We only list a country here if we have the appropriate data. Only language sub-tags
     * are listed.
     * TODO(user): Delete this: the information should be read from RegionDataConstants.java.
     */
    private val nonLatinLocalLanguageCountries: MutableMap<String?, String> = mutableMapOf(
        "AE" to "ar",
        "AM" to "hy",
        "CN" to "zh",
        "EG" to "ar",
        "HK" to "zh",
        "JP" to "ja",
        "KP" to "ko",
        "KR" to "ko",
        "MO" to "zh",
        "RU" to "ru",
        "TH" to "th",
        "TW" to "zh",
        "UA" to "uk",
        "VN" to "vi",
    )

    /**
     * Returns true if the language code is explicitly marked to be in the latin script. For
     * example, "zh-Latn" would return true, but "zh-TW", "en" and "zh" would all return false.
     */
    fun isExplicitLatinScript(languageCode: String?): Boolean {
        // Convert to upper-case for easier comparison.
        var languageCode = languageCode
        languageCode = toUpperCaseLocaleIndependent(languageCode)
        // Check to see if the language code contains a script modifier.
        val languageCodePattern = "\\w{2,3}[-_](\\w{4})".toRegex()
        val m: java.util.regex.Matcher = languageCodePattern.matcher(languageCode)
        if (m.lookingAt()) {
            val script: String = m.group(1)
            if (script == LATIN_SCRIPT) {
                return true
            }
        }
        return false
    }

    /**
     * Returns the language subtag of a language code. For example, returns "zh" if given "zh-Hans",
     * "zh-CN" or other "zh" variants. If no language subtag can be found or the language tag is
     * malformed, returns "und".
     */
    fun getLanguageSubtag(languageCode: String?): String? {
        val languageCodePattern = "(\\w{2,3})(?:[-_]\\w{4})?(?:[-_]\\w{2})?".toRegex()
        val m: java.util.regex.Matcher = languageCodePattern.matcher(languageCode)
        if (m.matches()) {
            return toLowerCaseLocaleIndependent(m.group(1))
        }
        return "und"
    }

    /**
     * Trims the string. If the field is empty after trimming, returns null instead. Note that this
     * only trims ASCII white-space.
     */
    fun trimToNull(originalStr: String?): String? {
        if (originalStr == null) {
            return null
        }
        val trimmedString = originalStr.trim { it <= ' ' }
        return if ((trimmedString.length == 0)) null else trimmedString
    }

    /**
     * Throws an exception if the object is null, with a generic error message.
     */
    fun <T> checkNotNull(o: T): T {
        return checkNotNull<T>(o, "This object should not be null.")
    }

    /**
     * Throws an exception if the object is null, with the error message supplied.
     */
    fun <T> checkNotNull(o: T?, message: String?): T {
        if (o == null) {
            throw NullPointerException(message)
        }
        return o
    }

    /**
     * Joins input string with the given separator. If an input string is null, it will be skipped.
     */
    fun joinAndSkipNulls(separator: String?, vararg strings: String?): String? {
        var sb: StringBuilder? = null
        for (s: String? in strings) {
            if (s != null) {
                val result = s.trim { it <= ' ' }
                if (result.length > 0) {
                    if (sb == null) {
                        sb = StringBuilder(result)
                    } else {
                        sb.append(separator).append(result)
                    }
                }
            }
        }
        return if (sb == null) null else sb.toString()
    }

    /**
     * Builds a map of the lower-cased values of the keys, names and local names provided. Each name
     * and local name is mapped to its respective key in the map.
     *
     * @throws IllegalStateException if the names or lnames array is greater than the keys array.
     */
    fun buildNameToKeyMap(
        keys: Array<String?>?, names: Array<String>?, lnames: Array<String>?
    ): Map<String?, String?>? {
        if (keys == null) {
            return null
        }

        val nameToKeyMap: MutableMap<String?, String?> = mutableMapOf()

        val keyLength = keys.size
        for (k: String? in keys) {
            nameToKeyMap[toLowerCaseLocaleIndependent(k)] = k
        }
        if (names != null) {
            if (names.size > keyLength) {
                throw IllegalStateException(
                    "names length (" + names.size
                            + ") is greater than keys length (" + keys.size + ")"
                )
            }
            for (i in 0 until keyLength) {
                // If we have less names than keys, we ignore all missing names. This happens
                // generally because reg-ex splitting methods on different platforms (java, js etc)
                // behave differently in the default case. Since missing names are fine, we opt to
                // be more robust here.
                if (i < names.size && names[i].length > 0) {
                    nameToKeyMap[toLowerCaseLocaleIndependent(names.get(i))] = keys.get(i)
                }
            }
        }
        if (lnames != null) {
            if (lnames.size > keyLength) {
                throw IllegalStateException(
                    ("lnames length (" + lnames.size
                            + ") is greater than keys length (" + keys.size + ")")
                )
            }
            for (i in 0 until keyLength) {
                if (i < lnames.size && lnames[i].length > 0) {
                    nameToKeyMap[toLowerCaseLocaleIndependent(lnames.get(i))] = keys.get(i)
                }
            }
        }
        return nameToKeyMap
    }

    /**
     * Returns a language code that the widget can use when fetching data, based on a [ ] language and the current selected country in the address widget. This
     * method is necessary since we have to determine later whether a language is "local" or "latin"
     * for certain countries.
     *
     * @param language the current user language
     * @param currentCountry the current selected country
     * @return a language code string in BCP-47 format (e.g. "en", "zh-Latn", "zh-Hans" or
     * "en-US").
     */
    fun getWidgetCompatibleLanguageCode(
        languageTag: String,
        currentCountry: String?
    ): String {
        val country = toUpperCaseLocaleIndependent(currentCountry)
        // Only do something if the country is one of those where we have names in the local
        // language as well as in latin script.
        if (nonLatinLocalLanguageCountries.containsKey(country)) {
            // Only do something if the language tag is _not_ the local language.
            if (languageTag != nonLatinLocalLanguageCountries.get(country)) {
                // Build up the language tag with the country and language specified, and add in the
                // script-tag of "Latn" explicitly, since this is _not_ a local language. This means
                // that we might create a language tag of "th-Latn", which is not what the actual
                // language being used is, but it indicates that we prefer "Latn" names to whatever
                // the local alternative was.
                val languageTagBuilder = StringBuilder(languageTag)
                languageTagBuilder.append("_latn")
                if (language.getCountry().length > 0) {
                    languageTagBuilder.append("_")
                    languageTagBuilder.append(language.getCountry())
                }
                return languageTagBuilder.toString()
            }
        }
        return language.toString()
    }

    /**
     * Converts all of the characters in this String to lower case using the rules of English. This is
     * equivalent to calling toLowerCase(Locale.ENGLISH). Thus avoiding locale-sensitive case folding
     * such as the Turkish i, which could mess e.g. with lookup keys and country codes.
     */
    fun toLowerCaseLocaleIndependent(value: String?): String? {
        return if ((value != null)) value.lowercase() else null
    }

    /**
     * Converts all of the characters in this String to upper case using the rules of English. This is
     * equivalent to calling toUpperCase(Locale.ENGLISH). Thus avoiding locale-sensitive case folding
     * such as the Turkish i, which could mess e.g. with lookup keys and country codes.
     */
    fun toUpperCaseLocaleIndependent(value: String?): String? {
        return if ((value != null)) value.uppercase() else null
    }
}
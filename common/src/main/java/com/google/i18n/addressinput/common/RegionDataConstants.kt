package com.kurviger.shared.models.address


/**
 * Initial data in JSON format for regions. This has some formatting information for some countries,
 * and a list of all the regions that the widget can support.
 */
object RegionDataConstants {
    val countryFormatMap: Map<String, String> = createMap()

    private fun createMap(): Map<String, String> {
        val map = linkedMapOf<String, String>()
        map["AC"] = "{\"name\":\"ASCENSION ISLAND\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\"}"
        map["AD"] =
            "{\"name\":\"ANDORRA\",\"lang\":\"ca\",\"languages\":\"ca\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["AE"] =
            "{\"name\":\"UNITED ARAB EMIRATES\",\"lang\":\"ar\",\"languages\":\"ar\",\"lfmt\":\"%N%n%O%n%A%n%S\",\"fmt\":\"%N%n%O%n%A%n%S\",\"require\":\"AS\",\"state_name_type\":\"emirate\"}"
        map["AF"] = "{\"name\":\"AFGHANISTAN\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\"}"
        map["AG"] = "{\"name\":\"ANTIGUA AND BARBUDA\",\"require\":\"A\"}"
        map["AI"] = "{\"name\":\"ANGUILLA\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\"}"
        map["AL"] = "{\"name\":\"ALBANIA\",\"fmt\":\"%N%n%O%n%A%n%Z%n%C\"}"
        map["AM"] =
            "{\"name\":\"ARMENIA\",\"lang\":\"hy\",\"languages\":\"hy\",\"lfmt\":\"%N%n%O%n%A%n%Z%n%C%n%S\",\"fmt\":\"%N%n%O%n%A%n%Z%n%C%n%S\"}"
        map["AO"] = "{\"name\":\"ANGOLA\"}"
        map["AQ"] = "{\"name\":\"ANTARCTICA\"}"
        map["AR"] =
            "{\"name\":\"ARGENTINA\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%Z %C%n%S\",\"upper\":\"ACZ\"}"
        map["AS"] =
            "{\"name\":\"AMERICAN SAMOA\",\"fmt\":\"%N%n%O%n%A%n%C %S %Z\",\"require\":\"ACSZ\",\"upper\":\"ACNOS\",\"state_name_type\":\"state\",\"zip_name_type\":\"zip\"}"
        map["AT"] = "{\"name\":\"AUSTRIA\",\"fmt\":\"%O%n%N%n%A%n%Z %C\",\"require\":\"ACZ\"}"
        map["AU"] =
            "{\"name\":\"AUSTRALIA\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%O%n%N%n%A%n%C %S %Z\",\"require\":\"ACSZ\",\"upper\":\"CS\",\"locality_name_type\":\"suburb\",\"state_name_type\":\"state\"}"
        map["AW"] = "{\"name\":\"ARUBA\"}"
        map["AX"] =
            "{\"name\":\"FINLAND\",\"fmt\":\"%O%n%N%n%A%nAX-%Z %C%nÅLAND\",\"require\":\"ACZ\",\"postprefix\":\"AX-\"}"
        map["AZ"] =
            "{\"name\":\"AZERBAIJAN\",\"fmt\":\"%N%n%O%n%A%nAZ %Z %C\",\"postprefix\":\"AZ \"}"
        map["BA"] = "{\"name\":\"BOSNIA AND HERZEGOVINA\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["BB"] =
            "{\"name\":\"BARBADOS\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%N%n%O%n%A%n%C, %S %Z\",\"state_name_type\":\"parish\"}"
        map["BD"] = "{\"name\":\"BANGLADESH\",\"fmt\":\"%N%n%O%n%A%n%C - %Z\"}"
        map["BE"] = "{\"name\":\"BELGIUM\",\"fmt\":\"%O%n%N%n%A%n%Z %C\",\"require\":\"ACZ\"}"
        map["BF"] = "{\"name\":\"BURKINA FASO\",\"fmt\":\"%N%n%O%n%A%n%C %X\"}"
        map["BG"] = "{\"name\":\"BULGARIA (REP.)\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["BH"] = "{\"name\":\"BAHRAIN\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["BI"] = "{\"name\":\"BURUNDI\"}"
        map["BJ"] = "{\"name\":\"BENIN\",\"upper\":\"AC\"}"
        map["BL"] =
            "{\"name\":\"SAINT BARTHELEMY\",\"fmt\":\"%O%n%N%n%A%n%Z %C %X\",\"require\":\"ACZ\",\"upper\":\"ACX\"}"
        map["BM"] = "{\"name\":\"BERMUDA\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["BN"] = "{\"name\":\"BRUNEI DARUSSALAM\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["BO"] = "{\"name\":\"BOLIVIA\",\"upper\":\"AC\"}"
        map["BQ"] = "{\"name\":\"BONAIRE, SINT EUSTATIUS, AND SABA\"}"
        map["BR"] =
            "{\"name\":\"BRAZIL\",\"lang\":\"pt\",\"languages\":\"pt\",\"fmt\":\"%O%n%N%n%A%n%D%n%C-%S%n%Z\",\"require\":\"ASCZ\",\"upper\":\"CS\",\"sublocality_name_type\":\"neighborhood\",\"state_name_type\":\"state\",\"width_overrides\":\"%C:L%S:S\",\"label_overrides\":[{\"field\":\"S2\",\"label\":\"Setor/ADE/Folha\"},{\"field\":\"S3\",\"label\":\"Quadra\"},{\"field\":\"S4\",\"label\":\"Trecho/AE/Modulo\"},{\"field\":\"S5\",\"label\":\"Cj/Bl/MI/Projeção/Etapa\"},{\"field\":\"LP\",\"label\":\"Lote\"},{\"field\":\"BI\",\"label\":\"Casa/Comercio\"},{\"field\":\"CG\",\"label\":\"Complexo/Chácara\"}]}"
        map["BS"] =
            "{\"name\":\"BAHAMAS\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%N%n%O%n%A%n%C, %S\",\"state_name_type\":\"island\"}"
        map["BT"] = "{\"name\":\"BHUTAN\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["BV"] = "{\"name\":\"BOUVET ISLAND\"}"
        map["BW"] = "{\"name\":\"BOTSWANA\"}"
        map["BY"] =
            "{\"name\":\"BELARUS\",\"lang\":\"be\",\"languages\":\"be~ru\",\"fmt\":\"%O%n%N%n%A%n%Z, %C%n%S\"}"
        map["BZ"] = "{\"name\":\"BELIZE\"}"
        map["CA"] =
            "{\"name\":\"CANADA\",\"lang\":\"en\",\"languages\":\"en~fr\",\"fmt\":\"%N%n%O%n%A%n%C %S %Z\",\"require\":\"ACSZ\",\"upper\":\"ACNOSZ\"}"
        map["CC"] =
            "{\"name\":\"COCOS (KEELING) ISLANDS\",\"fmt\":\"%O%n%N%n%A%n%C %S %Z\",\"upper\":\"CS\"}"
        map["CD"] = "{\"name\":\"CONGO (DEM. REP.)\"}"
        map["CF"] = "{\"name\":\"CENTRAL AFRICAN REPUBLIC\"}"
        map["CG"] = "{\"name\":\"CONGO (REP.)\"}"
        map["CH"] =
            "{\"name\":\"SWITZERLAND\",\"fmt\":\"%O%n%N%n%A%nCH-%Z %C\",\"require\":\"ACZ\",\"upper\":\"\",\"postprefix\":\"CH-\"}"
        map["CI"] = "{\"name\":\"COTE D'IVOIRE\",\"fmt\":\"%N%n%O%n%X %A %C %X\"}"
        map["CK"] = "{\"name\":\"COOK ISLANDS\"}"
        map["CL"] =
            "{\"name\":\"CHILE\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%Z %C%n%S\"}"
        map["CM"] = "{\"name\":\"CAMEROON\"}"
        map["CN"] =
            "{\"name\":\"CHINA\",\"lang\":\"zh\",\"languages\":\"zh\",\"lfmt\":\"%N%n%O%n%A%n%D%n%C%n%S, %Z\",\"fmt\":\"%Z%n%S%C%D%n%A%n%O%n%N\",\"require\":\"ACSZ\",\"upper\":\"S\",\"sublocality_name_type\":\"district\",\"width_overrides\":\"%S:S%C:S%D:S\",\"label_overrides\":[{\"field\":\"C\",\"label\":\"市/自治州/地区/盟\",\"lang\":\"zh\"},{\"field\":\"S\",\"label\":\"省/自治区/直辖市\",\"lang\":\"zh\"},{\"field\":\"D\",\"label\":\"区/县/旗\",\"lang\":\"zh\"}]}"
        map["CO"] =
            "{\"name\":\"COLOMBIA\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%D%n%C, %S, %Z\",\"require\":\"AS\",\"state_name_type\":\"department\",\"label_overrides\":[{\"field\":\"LL\",\"label\":\"Vereda\"},{\"field\":\"A3\",\"label\":\"Corregimiento\"},{\"field\":\"A2\",\"label\":\"Municipio\"}]}"
        map["CR"] =
            "{\"name\":\"COSTA RICA\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%S, %C%n%Z\",\"require\":\"ACS\"}"
        map["CU"] =
            "{\"name\":\"CUBA\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%C %S%n%Z\"}"
        map["CV"] =
            "{\"name\":\"CAPE VERDE\",\"lang\":\"pt\",\"languages\":\"pt\",\"fmt\":\"%N%n%O%n%A%n%Z %C%n%S\",\"state_name_type\":\"island\"}"
        map["CW"] = "{\"name\":\"CURACAO\"}"
        map["CX"] =
            "{\"name\":\"CHRISTMAS ISLAND\",\"fmt\":\"%O%n%N%n%A%n%C %S %Z\",\"upper\":\"CS\"}"
        map["CY"] = "{\"name\":\"CYPRUS\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["CZ"] =
            "{\"name\":\"CZECH REP.\",\"fmt\":\"%N%n%O%n%A%n%Z %C\",\"require\":\"ACZ\",\"label_overrides\":[{\"field\":\"NH\",\"label\":\"Obecní část\",\"lang\":\"cs\"},{\"field\":\"NH\",\"label\":\"Obecný časť\",\"lang\":\"sk\"},{\"field\":\"BI\",\"label\":\"Descriptive No.\"},{\"field\":\"BI\",\"label\":\"Popisné číslo\",\"lang\":\"cs\"},{\"field\":\"BI\",\"label\":\"Súpisné číslo\",\"lang\":\"sk\"},{\"field\":\"SN\",\"label\":\"Orientation No.\"},{\"field\":\"SN\",\"label\":\"Orientační číslo\",\"lang\":\"cs\"},{\"field\":\"SN\",\"label\":\"Orientačné číslo\",\"lang\":\"sk\"},{\"field\":\"S1\",\"label\":\"City District\"},{\"field\":\"S1\",\"label\":\"Městská část\",\"lang\":\"cs\"},{\"field\":\"S1\",\"label\":\"Mestská časť\",\"lang\":\"sk\"}]}"
        map["DE"] = "{\"name\":\"GERMANY\",\"fmt\":\"%N%n%O%n%A%n%Z %C\",\"require\":\"ACZ\"}"
        map["DJ"] = "{\"name\":\"DJIBOUTI\"}"
        map["DK"] = "{\"name\":\"DENMARK\",\"fmt\":\"%N%n%O%n%A%n%Z %C\",\"require\":\"ACZ\"}"
        map["DM"] = "{\"name\":\"DOMINICA\"}"
        map["DO"] = "{\"name\":\"DOMINICAN REP.\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["DZ"] = "{\"name\":\"ALGERIA\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["EC"] = "{\"name\":\"ECUADOR\",\"fmt\":\"%N%n%O%n%A%n%Z%n%C\",\"upper\":\"CZ\"}"
        map["EE"] =
            "{\"name\":\"ESTONIA\",\"lang\":\"et\",\"languages\":\"et\",\"fmt\":\"%N%n%O%n%A%n%Z %C %S\",\"require\":\"ACZ\",\"label_overrides\":[{\"field\":\"C\",\"label\":\"Linn/vald\",\"lang\":\"et\"},{\"field\":\"C\",\"label\":\"City/Parish\",\"lang\":\"en\"},{\"field\":\"S\",\"label\":\"Maakond\",\"lang\":\"et\"},{\"field\":\"S\",\"label\":\"Region\",\"lang\":\"en\"}]}"
        map["EG"] =
            "{\"name\":\"EGYPT\",\"lang\":\"ar\",\"languages\":\"ar\",\"lfmt\":\"%N%n%O%n%A%n%C%n%S%n%Z\",\"fmt\":\"%N%n%O%n%A%n%C%n%S%n%Z\"}"
        map["EH"] = "{\"name\":\"WESTERN SAHARA\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["ER"] = "{\"name\":\"ERITREA\"}"
        map["ES"] =
            "{\"name\":\"SPAIN\",\"lang\":\"es\",\"languages\":\"es~ca~gl~eu\",\"fmt\":\"%N%n%O%n%A%n%Z %C %S\",\"require\":\"ACSZ\",\"upper\":\"CS\",\"width_overrides\":\"%S:S\"}"
        map["ET"] = "{\"name\":\"ETHIOPIA\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["FI"] =
            "{\"name\":\"FINLAND\",\"fmt\":\"%O%n%N%n%A%nFI-%Z %C\",\"require\":\"ACZ\",\"postprefix\":\"FI-\"}"
        map["FJ"] = "{\"name\":\"FIJI\"}"
        map["FK"] =
            "{\"name\":\"FALKLAND ISLANDS (MALVINAS)\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\",\"require\":\"ACZ\",\"upper\":\"CZ\"}"
        map["FM"] =
            "{\"name\":\"MICRONESIA (Federated State of)\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%N%n%O%n%A%n%C %S %Z\",\"require\":\"ACSZ\",\"upper\":\"ACNOS\",\"state_name_type\":\"state\",\"zip_name_type\":\"zip\"}"
        map["FO"] =
            "{\"name\":\"FAROE ISLANDS\",\"fmt\":\"%N%n%O%n%A%nFO%Z %C\",\"postprefix\":\"FO\"}"
        map["FR"] =
            "{\"name\":\"FRANCE\",\"fmt\":\"%O%n%N%n%A%n%Z %C\",\"require\":\"ACZ\",\"upper\":\"CX\"}"
        map["GA"] = "{\"name\":\"GABON\"}"
        map["GB"] =
            "{\"name\":\"UNITED KINGDOM\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\",\"require\":\"ACZ\",\"upper\":\"CZ\",\"locality_name_type\":\"post_town\",\"label_overrides\":[{\"field\":\"LL\",\"message\":\"MSG_DEPENDENT_LOCALITY_LABEL\"},{\"field\":\"Z\",\"label\":\"Postcode\",\"lang\":\"en\"}]}"
        map["GD"] = "{\"name\":\"GRENADA (WEST INDIES)\"}"
        map["GE"] = "{\"name\":\"GEORGIA\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["GF"] =
            "{\"name\":\"FRENCH GUIANA\",\"fmt\":\"%O%n%N%n%A%n%Z %C %X\",\"require\":\"ACZ\",\"upper\":\"ACX\"}"
        map["GG"] =
            "{\"name\":\"CHANNEL ISLANDS\",\"fmt\":\"%N%n%O%n%A%n%C%nGUERNSEY%n%Z\",\"require\":\"ACZ\",\"upper\":\"CZ\"}"
        map["GH"] = "{\"name\":\"GHANA\"}"
        map["GI"] =
            "{\"name\":\"GIBRALTAR\",\"fmt\":\"%N%n%O%n%A%nGIBRALTAR%n%Z\",\"require\":\"A\"}"
        map["GL"] = "{\"name\":\"GREENLAND\",\"fmt\":\"%N%n%O%n%A%n%Z %C\",\"require\":\"ACZ\"}"
        map["GM"] = "{\"name\":\"GAMBIA\"}"
        map["GN"] = "{\"name\":\"GUINEA\",\"fmt\":\"%N%n%O%n%Z %A %C\"}"
        map["GP"] =
            "{\"name\":\"GUADELOUPE\",\"fmt\":\"%O%n%N%n%A%n%Z %C %X\",\"require\":\"ACZ\",\"upper\":\"ACX\"}"
        map["GQ"] = "{\"name\":\"EQUATORIAL GUINEA\"}"
        map["GR"] = "{\"name\":\"GREECE\",\"fmt\":\"%N%n%O%n%A%n%Z %C\",\"require\":\"ACZ\"}"
        map["GS"] =
            "{\"name\":\"SOUTH GEORGIA\",\"fmt\":\"%N%n%O%n%A%n%n%C%n%Z\",\"require\":\"ACZ\",\"upper\":\"CZ\"}"
        map["GT"] = "{\"name\":\"GUATEMALA\",\"fmt\":\"%N%n%O%n%A%n%Z- %C\"}"
        map["GU"] =
            "{\"name\":\"GUAM\",\"fmt\":\"%N%n%O%n%A%n%C %Z\",\"require\":\"ACZ\",\"upper\":\"ACNO\",\"zip_name_type\":\"zip\"}"
        map["GW"] = "{\"name\":\"GUINEA-BISSAU\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["GY"] = "{\"name\":\"GUYANA\"}"
        map["HK"] =
            "{\"name\":\"HONG KONG\",\"lang\":\"zh-Hant\",\"languages\":\"zh-Hant~en\",\"lfmt\":\"%N%n%O%n%A%n%C%n%S\",\"fmt\":\"%S%n%C%n%A%n%O%n%N\",\"require\":\"AS\",\"upper\":\"S\",\"locality_name_type\":\"district\",\"state_name_type\":\"area\",\"width_overrides\":\"%S:S%C:L\",\"label_overrides\":[{\"field\":\"C\",\"label\":\"地区\",\"lang\":\"zh\"},{\"field\":\"C\",\"label\":\"地區\",\"lang\":\"zh-HK\"},{\"field\":\"C\",\"label\":\"地區\",\"lang\":\"zh-TW\"},{\"field\":\"CS\",\"label\":\"Flat / Room\",\"lang\":\"en\"},{\"field\":\"CS\",\"label\":\"單位編號\",\"lang\":\"zh-HK\"},{\"field\":\"BG\",\"label\":\"大廈名稱\",\"lang\":\"zh-HK\"}]}"
        map["HM"] =
            "{\"name\":\"HEARD AND MCDONALD ISLANDS\",\"fmt\":\"%O%n%N%n%A%n%C %S %Z\",\"upper\":\"CS\"}"
        map["HN"] =
            "{\"name\":\"HONDURAS\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%C, %S%n%Z\",\"require\":\"ACS\",\"state_name_type\":\"department\"}"
        map["HR"] = "{\"name\":\"CROATIA\",\"fmt\":\"%N%n%O%n%A%nHR-%Z %C\",\"postprefix\":\"HR-\"}"
        map["HT"] = "{\"name\":\"HAITI\",\"fmt\":\"%N%n%O%n%A%nHT%Z %C\",\"postprefix\":\"HT\"}"
        map["HU"] =
            "{\"name\":\"HUNGARY (Rep.)\",\"fmt\":\"%N%n%O%n%C%n%A%n%Z\",\"require\":\"ACZ\",\"upper\":\"ACNO\"}"
        map["ID"] =
            "{\"name\":\"INDONESIA\",\"lang\":\"id\",\"languages\":\"id\",\"fmt\":\"%N%n%O%n%A%n%C%n%S %Z\",\"require\":\"AS\",\"label_overrides\":[{\"field\":\"A7\",\"label\":\"RT\"},{\"field\":\"A6\",\"label\":\"RW\"},{\"field\":\"A5\",\"label\":\"Dusun/Banjar\"},{\"field\":\"BI\",\"label\":\"Blok\"},{\"field\":\"A4\",\"message\":\"MSG_VILLAGE\"},{\"field\":\"A3\",\"label\":\"Kecamatan\"},{\"field\":\"S1\",\"label\":\"Pasar\"}]}"
        map["IE"] =
            "{\"name\":\"IRELAND\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%N%n%O%n%A%n%D%n%C%n%S%n%Z\",\"sublocality_name_type\":\"townland\",\"state_name_type\":\"county\",\"zip_name_type\":\"eircode\",\"label_overrides\":[{\"field\":\"S\",\"label\":\"郡\",\"lang\":\"zh\"}]}"
        map["IL"] = "{\"name\":\"ISRAEL\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["IM"] =
            "{\"name\":\"ISLE OF MAN\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\",\"require\":\"ACZ\",\"upper\":\"CZ\"}"
        map["IN"] =
            "{\"name\":\"INDIA\",\"lang\":\"en\",\"languages\":\"en~hi\",\"fmt\":\"%N%n%O%n%A%n%T%n%F%n%L%n%C %Z%n%S\",\"require\":\"ACSZ\",\"state_name_type\":\"state\",\"zip_name_type\":\"pin\",\"label_overrides\":[{\"field\":\"S1\",\"label\":\"Sublocality 1\"},{\"field\":\"S2\",\"label\":\"Sublocality 2\"},{\"field\":\"S3\",\"label\":\"Sublocality 3\"},{\"field\":\"S4\",\"label\":\"Sublocality 4\"}]}"
        map["IO"] =
            "{\"name\":\"BRITISH INDIAN OCEAN TERRITORY\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\",\"require\":\"ACZ\",\"upper\":\"CZ\"}"
        map["IQ"] =
            "{\"name\":\"IRAQ\",\"lang\":\"ar\",\"languages\":\"ar\",\"fmt\":\"%O%n%N%n%A%n%C, %S%n%Z\",\"require\":\"ACS\",\"upper\":\"CS\"}"
        map["IR"] =
            "{\"name\":\"IRAN\",\"lang\":\"fa\",\"languages\":\"fa\",\"fmt\":\"%O%n%N%n%S%n%C, %D%n%A%n%Z\",\"sublocality_name_type\":\"neighborhood\"}"
        map["IS"] = "{\"name\":\"ICELAND\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["IT"] =
            "{\"name\":\"ITALY\",\"lang\":\"it\",\"languages\":\"it\",\"fmt\":\"%N%n%O%n%A%n%Z %C %S\",\"require\":\"ACSZ\",\"upper\":\"CS\",\"width_overrides\":\"%S:S\"}"
        map["JE"] =
            "{\"name\":\"CHANNEL ISLANDS\",\"fmt\":\"%N%n%O%n%A%n%C%nJERSEY%n%Z\",\"require\":\"ACZ\",\"upper\":\"CZ\"}"
        map["JM"] =
            "{\"name\":\"JAMAICA\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%N%n%O%n%A%n%C%n%S %X\",\"require\":\"ACS\",\"state_name_type\":\"parish\"}"
        map["JO"] = "{\"name\":\"JORDAN\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["JP"] =
            "{\"name\":\"JAPAN\",\"lang\":\"ja\",\"languages\":\"ja\",\"lfmt\":\"%N%n%O%n%A, %S%n%Z\",\"fmt\":\"〒%Z%n%S%n%A%n%O%n%N\",\"require\":\"ASZ\",\"upper\":\"S\",\"state_name_type\":\"prefecture\",\"width_overrides\":\"%S:S\"}"
        map["KE"] = "{\"name\":\"KENYA\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\"}"
        map["KG"] = "{\"name\":\"KYRGYZSTAN\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["KH"] = "{\"name\":\"CAMBODIA\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["KI"] =
            "{\"name\":\"KIRIBATI\",\"fmt\":\"%N%n%O%n%A%n%S%n%C\",\"upper\":\"ACNOS\",\"state_name_type\":\"island\"}"
        map["KM"] = "{\"name\":\"COMOROS\",\"upper\":\"AC\"}"
        map["KN"] =
            "{\"name\":\"SAINT KITTS AND NEVIS\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%N%n%O%n%A%n%C, %S\",\"require\":\"ACS\",\"state_name_type\":\"island\"}"
        map["KP"] =
            "{\"name\":\"NORTH KOREA\",\"lang\":\"ko\",\"languages\":\"ko\",\"lfmt\":\"%N%n%O%n%A%n%C%n%S, %Z\",\"fmt\":\"%Z%n%S%n%C%n%A%n%O%n%N\"}"
        map["KR"] =
            "{\"name\":\"SOUTH KOREA\",\"lang\":\"ko\",\"languages\":\"ko\",\"lfmt\":\"%N%n%O%n%A%n%D%n%C%n%S%n%Z\",\"fmt\":\"%S %C%D%n%A%n%O%n%N%n%Z\",\"require\":\"ACSZ\",\"upper\":\"Z\",\"sublocality_name_type\":\"district\",\"state_name_type\":\"do_si\",\"label_overrides\":[{\"field\":\"BI\",\"message\":\"MSG_STREET_NUMBER\"},{\"field\":\"S2\",\"message\":\"MSG_NEIGHBORHOOD\"},{\"field\":\"S4\",\"message\":\"MSG_STREET_NAME\"}]}"
        map["KW"] = "{\"name\":\"KUWAIT\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["KY"] =
            "{\"name\":\"CAYMAN ISLANDS\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%N%n%O%n%A%n%S %Z\",\"require\":\"AS\",\"state_name_type\":\"island\"}"
        map["KZ"] =
            "{\"name\":\"KAZAKHSTAN\",\"lang\":\"kk\",\"languages\":\"kk~ru\",\"fmt\":\"%Z%n%S%n%C%n%A%n%O%n%N\"}"
        map["LA"] = "{\"name\":\"LAO (PEOPLE'S DEM. REP.)\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["LB"] = "{\"name\":\"LEBANON\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["LC"] = "{\"name\":\"SAINT LUCIA\"}"
        map["LI"] =
            "{\"name\":\"LIECHTENSTEIN\",\"fmt\":\"%O%n%N%n%A%nFL-%Z %C\",\"require\":\"ACZ\",\"postprefix\":\"FL-\"}"
        map["LK"] = "{\"name\":\"SRI LANKA\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\"}"
        map["LR"] = "{\"name\":\"LIBERIA\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["LS"] = "{\"name\":\"LESOTHO\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["LT"] =
            "{\"name\":\"LITHUANIA\",\"fmt\":\"%O%n%N%n%A%nLT-%Z %C %S\",\"require\":\"ACZ\",\"postprefix\":\"LT-\"}"
        map["LU"] =
            "{\"name\":\"LUXEMBOURG\",\"fmt\":\"%O%n%N%n%A%nL-%Z %C\",\"require\":\"ACZ\",\"postprefix\":\"L-\"}"
        map["LV"] =
            "{\"name\":\"LATVIA\",\"fmt\":\"%N%n%O%n%A%n%S%n%C, %Z\",\"require\":\"ACZ\",\"label_overrides\":[{\"field\":\"C\",\"label\":\"Novads\",\"lang\":\"lv\"},{\"field\":\"C\",\"label\":\"Municipality\",\"lang\":\"en\"},{\"field\":\"S\",\"label\":\"Pagasts/pilsēta\",\"lang\":\"lv\"},{\"field\":\"S\",\"label\":\"Parish/town\",\"lang\":\"en\"}]}"
        map["LY"] = "{\"name\":\"LIBYA\"}"
        map["MA"] = "{\"name\":\"MOROCCO\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["MC"] =
            "{\"name\":\"MONACO\",\"fmt\":\"%N%n%O%n%A%nMC-%Z %C %X\",\"postprefix\":\"MC-\"}"
        map["MD"] =
            "{\"name\":\"Rep. MOLDOVA\",\"fmt\":\"%N%n%O%n%A%nMD-%Z %C\",\"postprefix\":\"MD-\"}"
        map["ME"] = "{\"name\":\"MONTENEGRO\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["MF"] =
            "{\"name\":\"SAINT MARTIN\",\"fmt\":\"%O%n%N%n%A%n%Z %C %X\",\"require\":\"ACZ\",\"upper\":\"ACX\"}"
        map["MG"] = "{\"name\":\"MADAGASCAR\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["MH"] =
            "{\"name\":\"MARSHALL ISLANDS\",\"fmt\":\"%N%n%O%n%A%n%C %S %Z\",\"require\":\"ACSZ\",\"upper\":\"ACNOS\",\"state_name_type\":\"state\",\"zip_name_type\":\"zip\"}"
        map["MK"] = "{\"name\":\"MACEDONIA\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["ML"] = "{\"name\":\"MALI\"}"
        map["MM"] = "{\"name\":\"MYANMAR\",\"fmt\":\"%N%n%O%n%A%n%C, %Z\"}"
        map["MN"] =
            "{\"name\":\"MONGOLIA\",\"lang\":\"mn\",\"languages\":\"mn\",\"fmt\":\"%N%n%O%n%A%n%C%n%S %Z\"}"
        map["MO"] =
            "{\"name\":\"MACAO\",\"lang\":\"zh-Hant\",\"languages\":\"zh-Hant\",\"lfmt\":\"%N%n%O%n%A\",\"fmt\":\"%A%n%O%n%N\",\"require\":\"A\"}"
        map["MP"] =
            "{\"name\":\"NORTHERN MARIANA ISLANDS\",\"fmt\":\"%N%n%O%n%A%n%C %S %Z\",\"require\":\"ACSZ\",\"upper\":\"ACNOS\",\"state_name_type\":\"state\",\"zip_name_type\":\"zip\"}"
        map["MQ"] =
            "{\"name\":\"MARTINIQUE\",\"fmt\":\"%O%n%N%n%A%n%Z %C %X\",\"require\":\"ACZ\",\"upper\":\"ACX\"}"
        map["MR"] = "{\"name\":\"MAURITANIA\",\"upper\":\"AC\"}"
        map["MS"] = "{\"name\":\"MONTSERRAT\"}"
        map["MT"] = "{\"name\":\"MALTA\",\"fmt\":\"%N%n%O%n%A%n%C %Z\",\"upper\":\"CZ\"}"
        map["MU"] = "{\"name\":\"MAURITIUS\",\"fmt\":\"%N%n%O%n%A%n%Z%n%C\",\"upper\":\"CZ\"}"
        map["MV"] = "{\"name\":\"MALDIVES\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["MW"] = "{\"name\":\"MALAWI\",\"fmt\":\"%N%n%O%n%A%n%C %X\"}"
        map["MX"] =
            "{\"name\":\"MEXICO\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%D%n%Z %C, %S\",\"require\":\"ACSZ\",\"upper\":\"CSZ\",\"sublocality_name_type\":\"neighborhood\",\"state_name_type\":\"state\",\"width_overrides\":\"%S:S\",\"label_overrides\":[{\"field\":\"S1\",\"label\":\"Delegación\"},{\"field\":\"S2\",\"label\":\"Supermanzana\"},{\"field\":\"S3\",\"label\":\"Manzana\"},{\"field\":\"LP\",\"label\":\"Lote\"}]}"
        map["MY"] =
            "{\"name\":\"MALAYSIA\",\"lang\":\"ms\",\"languages\":\"ms\",\"fmt\":\"%N%n%O%n%A%n%D%n%Z %C%n%S\",\"require\":\"ACZ\",\"upper\":\"CS\",\"sublocality_name_type\":\"village_township\",\"state_name_type\":\"state\"}"
        map["MZ"] =
            "{\"name\":\"MOZAMBIQUE\",\"lang\":\"pt\",\"languages\":\"pt\",\"fmt\":\"%N%n%O%n%A%n%Z %C%S\"}"
        map["NA"] = "{\"name\":\"NAMIBIA\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\"}"
        map["NC"] =
            "{\"name\":\"NEW CALEDONIA\",\"fmt\":\"%O%n%N%n%A%n%Z %C %X\",\"require\":\"ACZ\",\"upper\":\"ACX\"}"
        map["NE"] = "{\"name\":\"NIGER\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["NF"] =
            "{\"name\":\"NORFOLK ISLAND\",\"fmt\":\"%O%n%N%n%A%n%C %S %Z\",\"upper\":\"CS\"}"
        map["NG"] =
            "{\"name\":\"NIGERIA\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%N%n%O%n%A%n%D%n%C %Z%n%S\",\"upper\":\"CS\",\"state_name_type\":\"state\",\"label_overrides\":[{\"field\":\"D\",\"label\":\"Local government area\",\"lang\":\"en\"}]}"
        map["NI"] =
            "{\"name\":\"NICARAGUA\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%Z%n%C, %S\",\"upper\":\"CS\",\"state_name_type\":\"department\"}"
        map["NL"] = "{\"name\":\"NETHERLANDS\",\"fmt\":\"%O%n%N%n%A%n%Z %C\",\"require\":\"ACZ\"}"
        map["NO"] =
            "{\"name\":\"NORWAY\",\"fmt\":\"%N%n%O%n%A%n%Z %C\",\"require\":\"ACZ\",\"locality_name_type\":\"post_town\"}"
        map["NP"] = "{\"name\":\"NEPAL\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["NR"] =
            "{\"name\":\"NAURU CENTRAL PACIFIC\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%N%n%O%n%A%n%S\",\"require\":\"AS\",\"state_name_type\":\"district\"}"
        map["NU"] = "{\"name\":\"NIUE\"}"
        map["NZ"] =
            "{\"name\":\"NEW ZEALAND\",\"fmt\":\"%N%n%O%n%A%n%D%n%C %Z\",\"require\":\"ACZ\"}"
        map["OM"] = "{\"name\":\"OMAN\",\"fmt\":\"%N%n%O%n%A%n%Z%n%C\"}"
        map["PA"] =
            "{\"name\":\"PANAMA (REP.)\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%C%n%S\",\"upper\":\"CS\"}"
        map["PE"] =
            "{\"name\":\"PERU\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%C %Z%n%S\",\"locality_name_type\":\"district\"}"
        map["PF"] =
            "{\"name\":\"FRENCH POLYNESIA\",\"fmt\":\"%N%n%O%n%A%n%Z %C %S\",\"require\":\"ACSZ\",\"upper\":\"CS\",\"state_name_type\":\"island\"}"
        map["PG"] =
            "{\"name\":\"PAPUA NEW GUINEA\",\"lang\":\"tpi\",\"languages\":\"tpi~en~ho\",\"fmt\":\"%N%n%O%n%A%n%C %Z %S\",\"require\":\"ACS\"}"
        map["PH"] =
            "{\"name\":\"PHILIPPINES\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%N%n%O%n%A%n%D, %C%n%Z %S\"}"
        map["PK"] =
            "{\"name\":\"PAKISTAN\",\"fmt\":\"%N%n%O%n%A%n%D%n%C-%Z\",\"label_overrides\":[{\"field\":\"D\",\"label\":\"Sector\",\"lang\":\"en\"}]}"
        map["PL"] = "{\"name\":\"POLAND\",\"fmt\":\"%N%n%O%n%A%n%Z %C\",\"require\":\"ACZ\"}"
        map["PM"] =
            "{\"name\":\"ST. PIERRE AND MIQUELON\",\"fmt\":\"%O%n%N%n%A%n%Z %C %X\",\"require\":\"ACZ\",\"upper\":\"ACX\"}"
        map["PN"] =
            "{\"name\":\"PITCAIRN\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\",\"require\":\"ACZ\",\"upper\":\"CZ\"}"
        map["PR"] =
            "{\"name\":\"PUERTO RICO\",\"fmt\":\"%N%n%O%n%A%n%C PR %Z\",\"require\":\"ACZ\",\"upper\":\"ACNO\",\"zip_name_type\":\"zip\",\"postprefix\":\"PR \"}"
        map["PS"] = "{\"name\":\"PALESTINIAN TERRITORY\"}"
        map["PT"] = "{\"name\":\"PORTUGAL\",\"fmt\":\"%N%n%O%n%A%n%Z %C\",\"require\":\"ACZ\"}"
        map["PW"] =
            "{\"name\":\"PALAU\",\"lang\":\"pau\",\"languages\":\"pau~en\",\"fmt\":\"%N%n%O%n%A%n%C %S %Z\",\"require\":\"ACSZ\",\"upper\":\"ACNOS\",\"state_name_type\":\"state\",\"zip_name_type\":\"zip\"}"
        map["PY"] = "{\"name\":\"PARAGUAY\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["QA"] = "{\"name\":\"QATAR\",\"upper\":\"AC\"}"
        map["RE"] =
            "{\"name\":\"REUNION\",\"fmt\":\"%O%n%N%n%A%n%Z %C %X\",\"require\":\"ACZ\",\"upper\":\"ACX\"}"
        map["RO"] =
            "{\"name\":\"ROMANIA\",\"lang\":\"ro\",\"languages\":\"ro\",\"fmt\":\"%N%n%O%n%A%n%Z %S %C\",\"require\":\"ACZ\",\"upper\":\"AC\",\"label_overrides\":[{\"field\":\"C\",\"label\":\"Județe/sectoare\",\"lang\":\"ro\"},{\"field\":\"C\",\"label\":\"County/sector\",\"lang\":\"en\"},{\"field\":\"S\",\"label\":\"Municipiu/oraș/comună\",\"lang\":\"ro\"},{\"field\":\"S\",\"label\":\"Municipality/city/town/commune\",\"lang\":\"en\"}]}"
        map["RS"] = "{\"name\":\"REPUBLIC OF SERBIA\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["RU"] =
            "{\"name\":\"RUSSIAN FEDERATION\",\"lang\":\"ru\",\"languages\":\"ru\",\"lfmt\":\"%N%n%O%n%A%n%C%n%S%n%Z\",\"fmt\":\"%N%n%O%n%A%n%C%n%S%n%Z\",\"require\":\"ACSZ\",\"upper\":\"AC\",\"state_name_type\":\"oblast\",\"label_overrides\":[{\"field\":\"CS\",\"message\":\"MSG_OFFICE_UNIT_NUMBER\"}]}"
        map["RW"] = "{\"name\":\"RWANDA\",\"upper\":\"AC\"}"
        map["SA"] = "{\"name\":\"SAUDI ARABIA\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["SB"] = "{\"name\":\"SOLOMON ISLANDS\"}"
        map["SC"] =
            "{\"name\":\"SEYCHELLES\",\"fmt\":\"%N%n%O%n%A%n%C%n%S\",\"upper\":\"S\",\"state_name_type\":\"island\"}"
        map["SD"] =
            "{\"name\":\"SUDAN\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\",\"locality_name_type\":\"district\"}"
        map["SE"] =
            "{\"name\":\"SWEDEN\",\"fmt\":\"%O%n%N%n%A%nSE-%Z %C\",\"require\":\"ACZ\",\"locality_name_type\":\"post_town\",\"postprefix\":\"SE-\"}"
        map["SG"] =
            "{\"name\":\"REP. OF SINGAPORE\",\"fmt\":\"%N%n%O%n%A%nSINGAPORE %Z\",\"require\":\"AZ\"}"
        map["SH"] =
            "{\"name\":\"SAINT HELENA\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\",\"require\":\"ACZ\",\"upper\":\"CZ\"}"
        map["SI"] =
            "{\"name\":\"SLOVENIA\",\"fmt\":\"%N%n%O%n%A%nSI-%Z %C\",\"postprefix\":\"SI-\"}"
        map["SJ"] =
            "{\"name\":\"SVALBARD AND JAN MAYEN ISLANDS\",\"fmt\":\"%N%n%O%n%A%n%Z %C\",\"require\":\"ACZ\",\"locality_name_type\":\"post_town\"}"
        map["SK"] =
            "{\"name\":\"SLOVAKIA\",\"fmt\":\"%N%n%O%n%A%n%Z %C\",\"require\":\"ACZ\",\"label_overrides\":[{\"field\":\"NH\",\"label\":\"Obecní část\",\"lang\":\"cs\"},{\"field\":\"NH\",\"label\":\"Obecný časť\",\"lang\":\"sk\"},{\"field\":\"BI\",\"label\":\"Descriptive No.\"},{\"field\":\"BI\",\"label\":\"Popisné číslo\",\"lang\":\"cs\"},{\"field\":\"BI\",\"label\":\"Súpisné číslo\",\"lang\":\"sk\"},{\"field\":\"SN\",\"label\":\"Orientation No.\"},{\"field\":\"SN\",\"label\":\"Orientační číslo\",\"lang\":\"cs\"},{\"field\":\"SN\",\"label\":\"Orientačné číslo\",\"lang\":\"sk\"},{\"field\":\"S1\",\"label\":\"City District\"},{\"field\":\"S1\",\"label\":\"Městská část\",\"lang\":\"cs\"},{\"field\":\"S1\",\"label\":\"Mestská časť\",\"lang\":\"sk\"}]}"
        map["SL"] = "{\"name\":\"SIERRA LEONE\"}"
        map["SM"] = "{\"name\":\"SAN MARINO\",\"fmt\":\"%N%n%O%n%A%n%Z %C\",\"require\":\"AZ\"}"
        map["SN"] = "{\"name\":\"SENEGAL\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["SO"] =
            "{\"name\":\"SOMALIA\",\"lang\":\"so\",\"languages\":\"so\",\"fmt\":\"%N%n%O%n%A%n%C, %S %Z\",\"require\":\"ACS\",\"upper\":\"ACS\"}"
        map["SR"] =
            "{\"name\":\"SURINAME\",\"lang\":\"nl\",\"languages\":\"nl\",\"fmt\":\"%N%n%O%n%A%n%C%n%S\",\"upper\":\"AS\"}"
        map["SS"] = "{\"name\":\"SOUTH SUDAN\"}"
        map["ST"] = "{\"name\":\"SAO TOME AND PRINCIPE\"}"
        map["SV"] =
            "{\"name\":\"EL SALVADOR\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%Z-%C%n%S\",\"require\":\"ACS\",\"upper\":\"CSZ\"}"
        map["SX"] = "{\"name\":\"SINT MAARTEN\"}"
        map["SY"] = "{\"name\":\"SYRIA\",\"locality_name_type\":\"district\"}"
        map["SZ"] = "{\"name\":\"SWAZILAND\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\",\"upper\":\"ACZ\"}"
        map["TA"] = "{\"name\":\"TRISTAN DA CUNHA\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\"}"
        map["TC"] =
            "{\"name\":\"TURKS AND CAICOS ISLANDS\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\",\"require\":\"ACZ\",\"upper\":\"CZ\"}"
        map["TD"] = "{\"name\":\"CHAD\"}"
        map["TF"] = "{\"name\":\"FRENCH SOUTHERN TERRITORIES\"}"
        map["TG"] = "{\"name\":\"TOGO\"}"
        map["TH"] =
            "{\"name\":\"THAILAND\",\"lang\":\"th\",\"languages\":\"th\",\"lfmt\":\"%N%n%O%n%A%n%D, %C%n%S %Z\",\"fmt\":\"%N%n%O%n%A%n%D %C%n%S %Z\",\"upper\":\"S\",\"label_overrides\":[{\"field\":\"C\",\"label\":\"Amphoe / Khet\"},{\"field\":\"C\",\"label\":\"อำเภอ/เขต\",\"lang\":\"th\"},{\"field\":\"C\",\"label\":\"アムプー/ケート\",\"lang\":\"ja\"},{\"field\":\"C\",\"label\":\"암프/켓\",\"lang\":\"ko\"},{\"field\":\"C\",\"label\":\"郡/区\",\"lang\":\"zh\"},{\"field\":\"C\",\"label\":\"郡/區\",\"lang\":\"zh-TW\"},{\"field\":\"C\",\"label\":\"郡/區\",\"lang\":\"zh-HK\"},{\"field\":\"D\",\"label\":\"Tambon / Khwaeng\"},{\"field\":\"D\",\"label\":\"ตำบล/แขวง\",\"lang\":\"th\"},{\"field\":\"D\",\"label\":\"タムボン/クウェーン\",\"lang\":\"ja\"},{\"field\":\"D\",\"label\":\"땀본/쾡\",\"lang\":\"ko\"},{\"field\":\"D\",\"label\":\"区/小区\",\"lang\":\"zh\"},{\"field\":\"D\",\"label\":\"區/小區\",\"lang\":\"zh-TW\"},{\"field\":\"D\",\"label\":\"區/小區\",\"lang\":\"zh-HK\"}]}"
        map["TJ"] = "{\"name\":\"TAJIKISTAN\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["TK"] = "{\"name\":\"TOKELAU\"}"
        map["TL"] = "{\"name\":\"TIMOR-LESTE\"}"
        map["TM"] = "{\"name\":\"TURKMENISTAN\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["TN"] = "{\"name\":\"TUNISIA\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["TO"] = "{\"name\":\"TONGA\"}"
        map["TR"] =
            "{\"name\":\"TURKEY\",\"lang\":\"tr\",\"languages\":\"tr\",\"fmt\":\"%N%n%O%n%A%n%Z %C/%S\",\"require\":\"ACZ\",\"locality_name_type\":\"district\",\"label_overrides\":[{\"field\":\"C\",\"label\":\"İlçe\",\"lang\":\"tr\"},{\"field\":\"S\",\"label\":\"İl\",\"lang\":\"tr\"},{\"field\":\"A4\",\"message\":\"MSG_NEIGHBORHOOD\"}]}"
        map["TT"] = "{\"name\":\"TRINIDAD AND TOBAGO\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\"}"
        map["TV"] =
            "{\"name\":\"TUVALU\",\"lang\":\"tyv\",\"languages\":\"tyv\",\"fmt\":\"%N%n%O%n%A%n%C%n%S\",\"upper\":\"ACS\",\"state_name_type\":\"island\"}"
        map["TW"] =
            "{\"name\":\"TAIWAN\",\"lang\":\"zh-Hant\",\"languages\":\"zh-Hant\",\"lfmt\":\"%N%n%O%n%A%n%C, %S %Z\",\"fmt\":\"%Z%n%S%C%n%A%n%O%n%N\",\"require\":\"ACSZ\",\"locality_name_type\":\"district\",\"state_name_type\":\"county\"}"
        map["TZ"] = "{\"name\":\"TANZANIA (UNITED REP.)\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["UA"] =
            "{\"name\":\"UKRAINE\",\"lang\":\"uk\",\"languages\":\"uk\",\"lfmt\":\"%N%n%O%n%A%n%C%n%S%n%Z\",\"fmt\":\"%N%n%O%n%A%n%C%n%S%n%Z\",\"require\":\"ACZ\",\"state_name_type\":\"oblast\",\"label_overrides\":[{\"field\":\"CS\",\"message\":\"MSG_OFFICE_UNIT_NUMBER\"}]}"
        map["UG"] = "{\"name\":\"UGANDA\"}"
        map["UM"] =
            "{\"name\":\"UNITED STATES MINOR OUTLYING ISLANDS\",\"fmt\":\"%N%n%O%n%A%n%C %S %Z\",\"require\":\"ACS\",\"upper\":\"ACNOS\",\"state_name_type\":\"state\",\"zip_name_type\":\"zip\"}"
        map["US"] =
            "{\"name\":\"UNITED STATES\",\"lang\":\"en\",\"languages\":\"en\",\"fmt\":\"%N%n%O%n%A%n%C, %S %Z\",\"require\":\"ACSZ\",\"upper\":\"CS\",\"state_name_type\":\"state\",\"zip_name_type\":\"zip\",\"width_overrides\":\"%S:S\"}"
        map["UY"] =
            "{\"name\":\"URUGUAY\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%Z %C %S\",\"upper\":\"CS\"}"
        map["UZ"] =
            "{\"name\":\"UZBEKISTAN\",\"lang\":\"uz\",\"languages\":\"uz~ru\",\"fmt\":\"%N%n%O%n%A%n%Z %C%n%S\",\"upper\":\"CS\"}"
        map["VA"] = "{\"name\":\"VATICAN\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["VC"] =
            "{\"name\":\"SAINT VINCENT AND THE GRENADINES (ANTILLES)\",\"fmt\":\"%N%n%O%n%A%n%C %Z\"}"
        map["VE"] =
            "{\"name\":\"VENEZUELA\",\"lang\":\"es\",\"languages\":\"es\",\"fmt\":\"%N%n%O%n%A%n%C %Z, %S\",\"require\":\"ACS\",\"upper\":\"CS\",\"state_name_type\":\"state\"}"
        map["VG"] =
            "{\"name\":\"VIRGIN ISLANDS (BRITISH)\",\"fmt\":\"%N%n%O%n%A%n%C%n%Z\",\"require\":\"A\"}"
        map["VI"] =
            "{\"name\":\"VIRGIN ISLANDS (U.S.)\",\"fmt\":\"%N%n%O%n%A%n%C %S %Z\",\"require\":\"ACSZ\",\"upper\":\"ACNOS\",\"state_name_type\":\"state\",\"zip_name_type\":\"zip\"}"
        map["VN"] =
            "{\"name\":\"VIET NAM\",\"lang\":\"vi\",\"languages\":\"vi\",\"lfmt\":\"%N%n%O%n%A%n%C%n%S %Z\",\"fmt\":\"%N%n%O%n%A%n%C%n%S %Z\",\"label_overrides\":[{\"field\":\"S1\",\"label\":\"Ward/Township/Commune\"},{\"field\":\"S1\",\"label\":\"Phường/Thị trấn/Xã\",\"lang\":\"vi\"}]}"
        map["VU"] = "{\"name\":\"VANUATU\"}"
        map["WF"] =
            "{\"name\":\"WALLIS AND FUTUNA ISLANDS\",\"fmt\":\"%O%n%N%n%A%n%Z %C %X\",\"require\":\"ACZ\",\"upper\":\"ACX\"}"
        map["WS"] = "{\"name\":\"SAMOA\"}"
        map["XK"] = "{\"name\":\"KOSOVO\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["YE"] = "{\"name\":\"YEMEN\"}"
        map["YT"] =
            "{\"name\":\"MAYOTTE\",\"fmt\":\"%O%n%N%n%A%n%Z %C %X\",\"require\":\"ACZ\",\"upper\":\"ACX\"}"
        map["ZA"] =
            "{\"name\":\"SOUTH AFRICA\",\"fmt\":\"%N%n%O%n%A%n%D%n%C%n%Z\",\"require\":\"ACZ\"}"
        map["ZM"] = "{\"name\":\"ZAMBIA\",\"fmt\":\"%N%n%O%n%A%n%Z %C\"}"
        map["ZW"] = "{\"name\":\"ZIMBABWE\"}"
        map["ZZ"] =
            "{\"fmt\":\"%N%n%O%n%A%n%C\",\"require\":\"AC\",\"upper\":\"C\",\"sublocality_name_type\":\"suburb\",\"locality_name_type\":\"city\",\"state_name_type\":\"province\",\"zip_name_type\":\"postal\"}"
        return map
    }
}
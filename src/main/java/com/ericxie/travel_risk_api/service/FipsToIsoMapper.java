package com.ericxie.travel_risk_api.service;

import java.util.HashMap;
import java.util.Map;

public class FipsToIsoMapper {

    private static final Map<String, String> FIPS_TO_ISO = new HashMap<>();
    private static final Map<String, String> ISO_TO_FIPS = new HashMap<>();

    static {
        FIPS_TO_ISO.put("AF", "AF");  // Afghanistan
        FIPS_TO_ISO.put("AL", "AL");  // Albania
        FIPS_TO_ISO.put("AG", "DZ");  // Algeria
        FIPS_TO_ISO.put("AQ", "AS");  // American Samoa
        FIPS_TO_ISO.put("AN", "AD");  // Andorra
        FIPS_TO_ISO.put("AO", "AO");  // Angola
        FIPS_TO_ISO.put("AV", "AI");  // Anguilla
        FIPS_TO_ISO.put("AC", "AG");  // Antigua and Barbuda
        FIPS_TO_ISO.put("AR", "AR");  // Argentina
        FIPS_TO_ISO.put("AM", "AM");  // Armenia
        FIPS_TO_ISO.put("AA", "AW");  // Aruba
        FIPS_TO_ISO.put("AS", "AU");  // Australia
        FIPS_TO_ISO.put("AU", "AT");  // Austria
        FIPS_TO_ISO.put("AJ", "AZ");  // Azerbaijan
        FIPS_TO_ISO.put("BF", "BS");  // Bahamas
        FIPS_TO_ISO.put("BA", "BH");  // Bahrain
        FIPS_TO_ISO.put("BG", "BD");  // Bangladesh
        FIPS_TO_ISO.put("BB", "BB");  // Barbados
        FIPS_TO_ISO.put("BO", "BY");  // Belarus
        FIPS_TO_ISO.put("BE", "BE");  // Belgium
        FIPS_TO_ISO.put("BH", "BZ");  // Belize
        FIPS_TO_ISO.put("BN", "BJ");  // Benin
        FIPS_TO_ISO.put("BD", "BM");  // Bermuda
        FIPS_TO_ISO.put("BT", "BT");  // Bhutan
        FIPS_TO_ISO.put("BL", "BO");  // Bolivia
        FIPS_TO_ISO.put("BK", "BA");  // Bosnia and Herzegovina
        FIPS_TO_ISO.put("BC", "BW");  // Botswana
        FIPS_TO_ISO.put("BR", "BR");  // Brazil
        FIPS_TO_ISO.put("IO", "IO");  // British Indian Ocean Territory
        FIPS_TO_ISO.put("BX", "BN");  // Brunei
        FIPS_TO_ISO.put("BU", "BG");  // Bulgaria
        FIPS_TO_ISO.put("UV", "BF");  // Burkina Faso
        FIPS_TO_ISO.put("BM", "MM");  // Burma
        FIPS_TO_ISO.put("BY", "BI");  // Burundi
        FIPS_TO_ISO.put("CB", "KH");  // Cambodia
        FIPS_TO_ISO.put("CM", "CM");  // Cameroon
        FIPS_TO_ISO.put("CA", "CA");  // Canada
        FIPS_TO_ISO.put("CV", "CV");  // Cape Verde
        FIPS_TO_ISO.put("CJ", "KY");  // Cayman Islands
        FIPS_TO_ISO.put("CT", "CF");  // Central African Republic
        FIPS_TO_ISO.put("CD", "TD");  // Chad
        FIPS_TO_ISO.put("CI", "CL");  // Chile
        FIPS_TO_ISO.put("CH", "CN");  // China
        FIPS_TO_ISO.put("CO", "CO");  // Colombia
        FIPS_TO_ISO.put("CN", "KM");  // Comoros
        FIPS_TO_ISO.put("CG", "CD");  // Congo, Democratic Republic of the
        FIPS_TO_ISO.put("CF", "CG");  // Congo, Republic of the
        FIPS_TO_ISO.put("CS", "CR");  // Costa Rica
        FIPS_TO_ISO.put("IV", "CI");  // Cote d'Ivoire
        FIPS_TO_ISO.put("HR", "HR");  // Croatia
        FIPS_TO_ISO.put("CU", "CU");  // Cuba
        FIPS_TO_ISO.put("UC", "CW");  // Curacao
        FIPS_TO_ISO.put("CY", "CY");  // Cyprus
        FIPS_TO_ISO.put("EZ", "CZ");  // Czech Republic
        FIPS_TO_ISO.put("DA", "DK");  // Denmark
        FIPS_TO_ISO.put("DJ", "DJ");  // Djibouti
        FIPS_TO_ISO.put("DO", "DM");  // Dominica
        FIPS_TO_ISO.put("DR", "DO");  // Dominican Republic
        FIPS_TO_ISO.put("EC", "EC");  // Ecuador
        FIPS_TO_ISO.put("EG", "EG");  // Egypt
        FIPS_TO_ISO.put("ES", "SV");  // El Salvador
        FIPS_TO_ISO.put("EK", "GQ");  // Equatorial Guinea
        FIPS_TO_ISO.put("ER", "ER");  // Eritrea
        FIPS_TO_ISO.put("EN", "EE");  // Estonia
        FIPS_TO_ISO.put("ET", "ET");  // Ethiopia
        FIPS_TO_ISO.put("FJ", "FJ");  // Fiji
        FIPS_TO_ISO.put("FI", "FI");  // Finland
        FIPS_TO_ISO.put("FR", "FR");  // France
        FIPS_TO_ISO.put("FG", "GF");  // French Guiana
        FIPS_TO_ISO.put("FP", "PF");  // French Polynesia
        FIPS_TO_ISO.put("GB", "GA");  // Gabon
        FIPS_TO_ISO.put("GA", "GM");  // Gambia
        FIPS_TO_ISO.put("GG", "GE");  // Georgia
        FIPS_TO_ISO.put("GM", "DE");  // Germany
        FIPS_TO_ISO.put("GH", "GH");  // Ghana
        FIPS_TO_ISO.put("GR", "GR");  // Greece
        FIPS_TO_ISO.put("GL", "GL");  // Greenland
        FIPS_TO_ISO.put("GJ", "GD");  // Grenada
        FIPS_TO_ISO.put("GT", "GT");  // Guatemala
        FIPS_TO_ISO.put("GV", "GN");  // Guinea
        FIPS_TO_ISO.put("PU", "GW");  // Guinea-Bissau
        FIPS_TO_ISO.put("GY", "GY");  // Guyana
        FIPS_TO_ISO.put("HA", "HT");  // Haiti
        FIPS_TO_ISO.put("HO", "HN");  // Honduras
        FIPS_TO_ISO.put("HK", "HK");  // Hong Kong
        FIPS_TO_ISO.put("HU", "HU");  // Hungary
        FIPS_TO_ISO.put("IC", "IS");  // Iceland
        FIPS_TO_ISO.put("IN", "IN");  // India
        FIPS_TO_ISO.put("ID", "ID");  // Indonesia
        FIPS_TO_ISO.put("IR", "IR");  // Iran
        FIPS_TO_ISO.put("IZ", "IQ");  // Iraq
        FIPS_TO_ISO.put("EI", "IE");  // Ireland
        FIPS_TO_ISO.put("IS", "IL");  // Israel
        FIPS_TO_ISO.put("IT", "IT");  // Italy
        FIPS_TO_ISO.put("JM", "JM");  // Jamaica
        FIPS_TO_ISO.put("JA", "JP");  // Japan
        FIPS_TO_ISO.put("JO", "JO");  // Jordan
        FIPS_TO_ISO.put("KZ", "KZ");  // Kazakhstan
        FIPS_TO_ISO.put("KE", "KE");  // Kenya
        FIPS_TO_ISO.put("KR", "KI");  // Kiribati
        FIPS_TO_ISO.put("KN", "KP");  // Korea, North
        FIPS_TO_ISO.put("KS", "KR");  // Korea, South
        FIPS_TO_ISO.put("KU", "KW");  // Kuwait
        FIPS_TO_ISO.put("KG", "KG");  // Kyrgyzstan
        FIPS_TO_ISO.put("LA", "LA");  // Laos
        FIPS_TO_ISO.put("LG", "LV");  // Latvia
        FIPS_TO_ISO.put("LE", "LB");  // Lebanon
        FIPS_TO_ISO.put("LT", "LS");  // Lesotho
        FIPS_TO_ISO.put("LI", "LR");  // Liberia
        FIPS_TO_ISO.put("LY", "LY");  // Libya
        FIPS_TO_ISO.put("LS", "LI");  // Liechtenstein
        FIPS_TO_ISO.put("LH", "LT");  // Lithuania
        FIPS_TO_ISO.put("LU", "LU");  // Luxembourg
        FIPS_TO_ISO.put("MC", "MO");  // Macau
        FIPS_TO_ISO.put("MK", "MK");  // Macedonia
        FIPS_TO_ISO.put("MA", "MG");  // Madagascar
        FIPS_TO_ISO.put("MI", "MW");  // Malawi
        FIPS_TO_ISO.put("MY", "MY");  // Malaysia
        FIPS_TO_ISO.put("MV", "MV");  // Maldives
        FIPS_TO_ISO.put("ML", "ML");  // Mali
        FIPS_TO_ISO.put("MT", "MT");  // Malta
        FIPS_TO_ISO.put("RM", "MH");  // Marshall Islands
        FIPS_TO_ISO.put("MR", "MR");  // Mauritania
        FIPS_TO_ISO.put("MP", "MU");  // Mauritius
        FIPS_TO_ISO.put("MX", "MX");  // Mexico
        FIPS_TO_ISO.put("FM", "FM");  // Micronesia
        FIPS_TO_ISO.put("MD", "MD");  // Moldova
        FIPS_TO_ISO.put("MG", "MN");  // Mongolia
        FIPS_TO_ISO.put("MJ", "ME");  // Montenegro
        FIPS_TO_ISO.put("MH", "MS");  // Montserrat
        FIPS_TO_ISO.put("MO", "MA");  // Morocco
        FIPS_TO_ISO.put("MZ", "MZ");  // Mozambique
        FIPS_TO_ISO.put("WA", "NA");  // Namibia
        FIPS_TO_ISO.put("NR", "NR");  // Nauru
        FIPS_TO_ISO.put("NP", "NP");  // Nepal
        FIPS_TO_ISO.put("NL", "NL");  // Netherlands
        FIPS_TO_ISO.put("NC", "NC");  // New Caledonia
        FIPS_TO_ISO.put("NZ", "NZ");  // New Zealand
        FIPS_TO_ISO.put("NU", "NI");  // Nicaragua
        FIPS_TO_ISO.put("NG", "NE");  // Niger
        FIPS_TO_ISO.put("NI", "NG");  // Nigeria
        FIPS_TO_ISO.put("NO", "NO");  // Norway
        FIPS_TO_ISO.put("MU", "OM");  // Oman
        FIPS_TO_ISO.put("PK", "PK");  // Pakistan
        FIPS_TO_ISO.put("PS", "PW");  // Palau
        FIPS_TO_ISO.put("PM", "PA");  // Panama
        FIPS_TO_ISO.put("PP", "PG");  // Papua New Guinea
        FIPS_TO_ISO.put("PA", "PY");  // Paraguay
        FIPS_TO_ISO.put("PE", "PE");  // Peru
        FIPS_TO_ISO.put("RP", "PH");  // Philippines
        FIPS_TO_ISO.put("PL", "PL");  // Poland
        FIPS_TO_ISO.put("PO", "PT");  // Portugal
        FIPS_TO_ISO.put("QA", "QA");  // Qatar
        FIPS_TO_ISO.put("RO", "RO");  // Romania
        FIPS_TO_ISO.put("RS", "RU");  // Russia
        FIPS_TO_ISO.put("RW", "RW");  // Rwanda
        FIPS_TO_ISO.put("SC", "KN");  // Saint Kitts and Nevis
        FIPS_TO_ISO.put("ST", "LC");  // Saint Lucia
        FIPS_TO_ISO.put("VC", "VC");  // Saint Vincent and the Grenadines
        FIPS_TO_ISO.put("WS", "WS");  // Samoa
        FIPS_TO_ISO.put("TP", "ST");  // Sao Tome and Principe
        FIPS_TO_ISO.put("SA", "SA");  // Saudi Arabia
        FIPS_TO_ISO.put("SG", "SN");  // Senegal
        FIPS_TO_ISO.put("RI", "RS");  // Serbia
        FIPS_TO_ISO.put("SE", "SC");  // Seychelles
        FIPS_TO_ISO.put("SL", "SL");  // Sierra Leone
        FIPS_TO_ISO.put("SN", "SG");  // Singapore
        FIPS_TO_ISO.put("NN", "SX");  // Sint Maarten
        FIPS_TO_ISO.put("LO", "SK");  // Slovakia
        FIPS_TO_ISO.put("SI", "SI");  // Slovenia
        FIPS_TO_ISO.put("BP", "SB");  // Solomon Islands
        FIPS_TO_ISO.put("SO", "SO");  // Somalia
        FIPS_TO_ISO.put("SF", "ZA");  // South Africa
        FIPS_TO_ISO.put("OD", "SS");  // South Sudan
        FIPS_TO_ISO.put("SP", "ES");  // Spain
        FIPS_TO_ISO.put("CE", "LK");  // Sri Lanka
        FIPS_TO_ISO.put("SU", "SD");  // Sudan
        FIPS_TO_ISO.put("NS", "SR");  // Suriname
        FIPS_TO_ISO.put("SW", "SE");  // Sweden
        FIPS_TO_ISO.put("SZ", "CH");  // Switzerland
        FIPS_TO_ISO.put("SY", "SY");  // Syria
        FIPS_TO_ISO.put("TW", "TW");  // Taiwan
        FIPS_TO_ISO.put("TI", "TJ");  // Tajikistan
        FIPS_TO_ISO.put("TZ", "TZ");  // Tanzania
        FIPS_TO_ISO.put("TH", "TH");  // Thailand
        FIPS_TO_ISO.put("TT", "TL");  // Timor-Leste
        FIPS_TO_ISO.put("TO", "TG");  // Togo
        FIPS_TO_ISO.put("TN", "TO");  // Tonga
        FIPS_TO_ISO.put("TD", "TT");  // Trinidad and Tobago
        FIPS_TO_ISO.put("TS", "TN");  // Tunisia
        FIPS_TO_ISO.put("TU", "TR");  // Turkey
        FIPS_TO_ISO.put("TX", "TM");  // Turkmenistan
        FIPS_TO_ISO.put("TK", "TC");  // Turks and Caicos Islands
        FIPS_TO_ISO.put("TV", "TV");  // Tuvalu
        FIPS_TO_ISO.put("UG", "UG");  // Uganda
        FIPS_TO_ISO.put("UP", "UA");  // Ukraine
        FIPS_TO_ISO.put("AE", "AE");  // United Arab Emirates
        FIPS_TO_ISO.put("UK", "GB");  // United Kingdom
        FIPS_TO_ISO.put("US", "US");  // United States
        FIPS_TO_ISO.put("UY", "UY");  // Uruguay
        FIPS_TO_ISO.put("UZ", "UZ");  // Uzbekistan
        FIPS_TO_ISO.put("NH", "VU");  // Vanuatu
        FIPS_TO_ISO.put("VE", "VE");  // Venezuela
        FIPS_TO_ISO.put("VM", "VN");  // Vietnam
        FIPS_TO_ISO.put("YM", "YE");  // Yemen
        FIPS_TO_ISO.put("ZA", "ZM");  // Zambia
        FIPS_TO_ISO.put("ZI", "ZW");  // Zimbabwe
        FIPS_TO_ISO.put("WZ", "SZ");  // Eswatini
        FIPS_TO_ISO.put("GW", "GN");  // Guinea-Bissau
        FIPS_TO_ISO.put("KV", "XK");  // Kosovo
    }

    static {
        // populate by inverting FIPS_TO_ISO
        for (Map.Entry<String, String> entry : FIPS_TO_ISO.entrySet()) {
            ISO_TO_FIPS.put(entry.getValue(), entry.getKey());
        }
    }

    public static String toIso(String fipsCode) {
        return FIPS_TO_ISO.get(fipsCode);
    }
    public static String toFips(String isoCode) {return ISO_TO_FIPS.get(isoCode);
    }
}
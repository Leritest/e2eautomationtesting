package automation.de.dg.endpoints.cimrest.utils;

import automation.de.dg.endpoints.constants.CimUriPath;
import automation.de.dg.enumation.CustomerTypes;
import automation.de.dg.enumation.Portfolios;
import automation.de.dg.enumation.RouterTypes;
import automation.de.dg.enumation.WaipuTypes;
import automation.utilities.TestDataGenerator;

/**
 * <b>RestAPI : CIM Rest Suite</b> Util<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define static context<br>
 *  that is used during testing CIM Rest API.
 */

public class StaticContext {

    static String url;

    /**
     * <b>[Method]</b> - Set CIM URL Instance<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality set URL Instance that is <br>
     * used for CIM Rest calls based on Customer type
     * @param baseUrl String
     * @param ct CustomerTypes
     * @param version String
     */

    public static void setCimInstanceUrl(String baseUrl, CustomerTypes ct, String version) {
        String additionalPart;
        switch(ct) {
            case DGH -> {
                if (version.equals("v3")) {
                    additionalPart = CimUriPath.REST_URL_DGH_v3;
                } else {
                    additionalPart = CimUriPath.REST_URL_DGH_V4;
                }
            }
            case DGB -> {
                if (version.equals("v3")) {
                    additionalPart = CimUriPath.REST_URL_DGB_v3;
                } else {
                    additionalPart = CimUriPath.REST_URL_DGB_V4;
                }
            }
            case NEW -> {
                if (version.equals("v3")) {
                    additionalPart = CimUriPath.REST_URL_NEW_v3;
                } else {
                    additionalPart = CimUriPath.REST_URL_NEW_V4;
                }
            }
            default ->  additionalPart = CimUriPath.REST_URL_DGH_V4;
        }

        url = baseUrl + additionalPart;
    }

    /**
     * <b>[Method]</b> - Get CIM URL Instance<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality return URL Instance<br>
     *
     * @return url
     */

    public static String getCimInstanceUrl() {
        return url;
    }

    /**
     * <b>[Method]</b> - Return Random First Name<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns random first name<br>
     * generated based on ISP type, Portfolio, Tariff
     */
    public static String generateRandomFirstNameBasedOnData(CustomerTypes ct, Portfolios portfolio, int tariff, RouterTypes rt, WaipuTypes wt) {
        String name = null;
        switch (ct) {
            case DGH -> name = "DghIsp M";
            case DGB -> name = "DgbIsp M";
            case NEW -> name = "NewIsp M";
        }

        switch (portfolio) {
            case Fifteen, Seventeen, Seventeen_Mig -> name = name + "A";
            case Eighteen, Eighteen_Mig -> name = name + "B";
            case Twentythree -> name = name + "C";
            case Legacy -> name = name + "L";
        }

        switch (tariff) {
            case 100, 300 -> name = name + "A";
            case 400, 500 -> name = name + "B";
            case 600, 800 -> name = name + "C";
            case 1000, 1012 -> name = name + "D";
        }

        switch (rt) {
            case Null, Ker, Ker_Titanium -> name = name + "A";
            case Basic_2018, Wlanplus -> name = name + "B";
            case Classic_2018, Classic_Mig_2018, Fritzbox -> name = name + "C";
            case Premium_2018, Bundle -> name = name + "D";
            case Titanium, Fast_2017, Platinium_2015, Platinium_2017, Fast_Ersatzgerat_2017 -> name = name + "O";
        }

        switch (wt) {
            case Null -> name = name + "A";
            case Comfort -> name = name + "B";
            case Perfect, Perfect_5_2018, Perfect_10_2018 -> name = name + "B";
            case Perfect_Plus_15_2018 -> name = name + "D";
        }

        return name;
    }

    /**
     * <b>[Method]</b> - Return Random First Name for Legacy<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns random first name for Legacy Portfolio<br>
     */

    public static String generateRandomFirmaName() {
        String[] range = {"GmbH", "Praxis, KG", "Büro", "Feuerwehr", "Company", "Consulting", "Restaurant", " Apotheke GBR", "UG", "Gemeinde", "Gemeinde",
                "Kinder", "Kita", "e.V.", "Stadt", "Bauhof", "Verein", "Kath", "Katholisch", "evangelisch", "ev", "diakoni", "rathaus", "DRK",
                "feuerwehr", "feuerwache", "rettungsdienst", "schule", "pastorat", "Fahrschule", "Tanzschule", "Kampfkunstschule", "Privatschule",
                "Baumschule", "Rosenschule", "Rasenschule", "Hundeschule", "Ballettschule", "Stadtwerke", "Werkstadt", "Heilpraktikerschule",
                "Musaik", "Immo", "Ingenieurbüro", "Kanzlei", "Altesrathaus", "kinderleicht", "Medienhaus", "Apotheke", "Kamino"};
        int num = TestDataGenerator.generateRandomNumber(0, range.length - 1);
        return range[num];
    }

}

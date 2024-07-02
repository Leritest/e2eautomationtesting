package automation.de.dg.database.manipulation.queries;

/**
 * <b>DATABASE</b> [Queries]: AR/AC Database Queries
 *  <i>Class functionality:</i><br>
 *  Class is used to define strings<br>
 *  that will be executed as AR/AC DB queries.<br>
 *  Execution is done in AcQueryManipulation class
 */

public class AcQueries {

    public static String distinctZipCodes = "SELECT DISTINCT ac.ZIP_CODE_NUMBERS from AC_CONNECTION ac";

    public static String distinctZipChars(String zipCode) {
        String zipChar;
        return zipChar = "SELECT DISTINCT ac.ZIP_CODE_CHARACTERS \n" +
                "from AC_CONNECTION ac where ac.ZIP_CODE_NUMBERS = " + zipCode;
    }

    public static String returnHouseId(String zipCode, String zipChar) {
        String houseId;
        return houseId = "SELECT ac.HOUSE_NUMBER_VALUE, ac.HOUSE_NUMBER_EXTENSION, ac.ROOM \n" +
                "from AC_CONNECTION ac where ac.ZIP_CODE_NUMBERS = " + zipCode + "\n" +
                "and ac.ZIP_CODE_CHARACTERS = '" + zipChar + "'";
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                         Find city with available units
    //----------------------------------------------------------------------------------------------------------------//

    public static String returnCityWithAvailableUnits() {
        String response = "SELECT DISTINCT ac.CITY, ac.*\n" +
                "from AC_CONNECTION ac\n" +
                "where \n" +
                "ac.FRAME in (101, 201) and ac.CONNECTIONID is null and ac.CONST_TYPE in ('EXISTING_CONSTRUCTION', 'NEW_CONSTRUCTION')\n" +
                "and HASDATE is NULL and ac.CITY is not null ";
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                          Find address with available units
    //----------------------------------------------------------------------------------------------------------------//

    public static String returnAddressWithAvailableUnits(String city) {
        String response = "SELECT ac.CITY, ac.ZIP_CODE_NUMBERS, ac.STREET, ac.HOUSE_NUMBER_VALUE, ac.HOUSE_NUMBER_EXTENSION, ac.*  \n" +
                "from AC_CONNECTION ac \n" +
                "where ac.DELIVERYSTATUS = 'DELIVERYSTATUS102' and ac.REASONNOTCONNECTED = 'R20'\n" +
                "and HASDATE is NULL and ac.CITY = '" + city + "' and ac.HOUSE_NUMBER_EXTENSION = ''";
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                                 Find Project ID
    //----------------------------------------------------------------------------------------------------------------//

    public static String returnProjectId(int id) {
        String response = "SELECT * from AC_PROJECT ap where ID = " + id;
        return response;
    }

}

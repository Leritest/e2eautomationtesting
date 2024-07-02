package automation.de.dg.database.manipulation.queryManipulation;

import automation.database.DataBaseConnection;
import automation.de.dg.database.manipulation.queries.AcQueries;
import automation.de.dg.utils.config.TestingResource;
import automation.enumaration.DatabaseNames;
import automation.utilities.TestDataGenerator;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;

/**
 * <b>DATABASE</b> [Queries]: AR/AC Query Manipulation
 */

public class AcQueryManipulation {

    /**
     * <b>[Method]</b> - Get City with available Unit<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns Cities<br>
     * with available units
     */

    public String getCityWithAvailableUnit() {
        String city;
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.AC, AcQueries.returnCityWithAvailableUnits());
            // get some random row
            int row = TestDataGenerator.generateRandomNumber(0, QueryManipulation.countQueryRows(rs));
            city = QueryManipulation.returnString(rs, "CITY", row);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return city;
    }

    /**
     * <b>[Method]</b> - Get Address with available Unit<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns Address<br>
     * with available units
     */

    public void getAddressWithAvailableUnit(String city) {
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.AC, AcQueries.returnAddressWithAvailableUnits(city));
            // get some random row
            int row = TestDataGenerator.generateRandomNumber(0, QueryManipulation.countQueryRows(rs));

            int zip = Integer.parseInt(QueryManipulation.returnString(rs, "ZIP_CODE_NUMBERS", row));
            String street = QueryManipulation.returnString(rs, "STREET", row);
            int number = Integer.parseInt(QueryManipulation.returnString(rs, "HOUSE_NUMBER_VALUE", row));

            int projectId = Integer.parseInt(QueryManipulation.returnString(rs, "PROJECT_ID", row));

            System.out.println("Found random address");
            System.out.println("City: " + city);
            System.out.println("ZIP code: " + zip);
            System.out.println("Street: " + street);
            System.out.println("House number: " + number);

            TestingResource resource = new TestingResource();
            String[] list = new String[5];
            list[0] = "DE";
            list[1] = city;
            list[2] = String.valueOf(zip);
            list[3] = street;
            list[4] = String.valueOf(number);
            resource.setAddressDetail(list);

            // find project id
            rs = DataBaseConnection.getQueryResponse(DatabaseNames.AC, AcQueries.returnProjectId(projectId));
            // get some random row
            //row = TestDataGenerator.generateRandomNumber(0, QueryManipulation.countQueryRows(rs));
            int project = QueryManipulation.returnInt(rs, "PROJECTCODE", 1);
            //int project = Integer.parseInt(QueryManipulation.returnString(rs, "PROJECTCODE", 1));
            System.out.println("Project ID: " + project);
            resource.setProjectId(project);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get House ID<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns House ID<br>
     *
     * @throws UnsupportedEncodingException exception
     */

    public String getHouseId() {
        String houseId = null;
        try {
            // get distinct zip codes
            String zipCode = AcQueries.distinctZipCodes;
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.AC, zipCode);
            // get some random row
            int row = TestDataGenerator.generateRandomNumber(0, QueryManipulation.countQueryRows(rs));
            zipCode = QueryManipulation.returnString(rs, "ZIP_CODE_NUMBERS", row);

            // get distinct zip codes
            String zipChar = AcQueries.distinctZipChars(zipCode);
            // execute
            rs = DataBaseConnection.getQueryResponse(DatabaseNames.AC, zipChar);
            // get some random row
            row = TestDataGenerator.generateRandomNumber(0, QueryManipulation.countQueryRows(rs));
            zipChar = QueryManipulation.returnString(rs, "ZIP_CODE_CHARACTERS", row);

            // get house number
            String house = AcQueries.returnHouseId(zipCode, zipChar);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return houseId;
    }
}

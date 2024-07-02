package automation.de.dg.database.manipulation.queryManipulation;

import automation.database.DataBaseConnection;
import automation.de.dg.database.manipulation.queries.PlmQueries;
import automation.enumaration.DatabaseNames;
import automation.de.dg.domain.enums.DgfMarketingSegment;
import automation.utilities.TestDataGenerator;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;

/**
 * <b>DATABASE</b> [Queries]: PLM Query Manipulation
 */

public class PlmQueryManipulation {

    /**
     * <b>[Method]</b> - Get Available Campaign<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality checks if Get Campaign Instance endpoint is up<br>
     ** <i>Steps of this scenario:</i><br>
     * 1. Execute Get Campaign Instance endpoint<br>
     * 2. Verify that endpoint response contains proper status code<br>
     * @throws UnsupportedEncodingException exception
     */

    public String[] getAvailableCampaigns(DgfMarketingSegment segment) {
        try {
            String[] value = new String[2];

            // prepare query
            String query = PlmQueries.returnCampaign(String.valueOf(segment));
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.PLM, query);
            // get some random row
            int row = TestDataGenerator.generateRandomNumber(0, QueryManipulation.countQueryRows(rs)-1);
            value[0] = QueryManipulation.returnString(rs, "id", row);
            value[1] = QueryManipulation.returnString(rs, "name", row);
            // return column value
            return value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

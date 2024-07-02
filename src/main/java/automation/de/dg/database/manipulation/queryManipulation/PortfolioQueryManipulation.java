package automation.de.dg.database.manipulation.queryManipulation;

import automation.database.DataBaseConnection;
import automation.de.dg.database.manipulation.queries.PortfolioQueries;
import automation.de.dg.domain.CampaignInstances;
import automation.enumaration.DatabaseNames;
import automation.utilities.TestDataGenerator;
import java.sql.ResultSet;

/**
 * <b>DATABASE</b> [Queries]: Portfolio Query Manipulation
 */

public class PortfolioQueryManipulation {

    /**
     * <b>[Method]</b> - Get House ID<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns boolean value if House ID exists in Address table<br>
     * returns true, otherwise returns false when response is empty
     */

    public boolean getHouseId(String houseId) {
        String query = PortfolioQueries.getHouses(houseId);
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.PORTFOLIO, query);
            // get count of returned rows (max 1000)
            int num = QueryManipulation.countQueryRows(rs);
            boolean hasValue = num > 0;
            return hasValue;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get Campaign Instance<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns Campaign Instance ID
     */

    public boolean getCampaignInstance(String table, String value) {
        String query = PortfolioQueries.getCampaingInstance(table, value);
        boolean response = false;
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.PORTFOLIO, query);
            // get count of returned rows (max 1000) and generate random number between 0 and count
            int count = QueryManipulation.countQueryRows(rs);
            // find campaign ID if number of rows are equal or greater than 1
            if (count > 0) {
                int num;
                if (count < 2) {
                    num = 0;
                } else {
                    num = TestDataGenerator.generateRandomNumber(0, QueryManipulation.countQueryRows(rs)-1);
                }
                response = true;
                CampaignInstances campaign = new CampaignInstances();
                campaign.setUuid(QueryManipulation.returnString(rs, "uuid", num));
                campaign.setCampaignId(Integer.parseInt(QueryManipulation.returnString(rs, "campaign_id", num)));
                campaign.setCampaignName(QueryManipulation.returnString(rs, "campaign_name", num));
                campaign.setSegment(QueryManipulation.returnString(rs, "marketing_segment", num));
                campaign.setChannel(QueryManipulation.returnString(rs, "channel", num));
                campaign.setRanking(Integer.parseInt(QueryManipulation.returnString(rs, "ranking", num)));
                //response = QueryManipulation.returnString(rs, "uuid", TestDataGenerator.generateRandomNumber(0, num-1));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // return instance
        return response;
    }

    /**
     * <b>[Method]</b> - Get Campaign Instance max one assigned per House<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns Campaign Instance ID when house ID has only one Campaign
     */

    public String getCampaignInstanceForOneHouse() {
        String query = PortfolioQueries.getOneCampaingInstance;
        String response = null;
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.PORTFOLIO, query);
            // get count of returned rows (max 1000)
            int num = QueryManipulation.countQueryRows(rs);
            // find campaign ID if number of rows are equal or greater than 1
            String houseId = QueryManipulation.returnString(rs, "fk_address_id", TestDataGenerator.generateRandomNumber(0, num-1));
            System.out.println("Found House ID: " + houseId);
            getCampaignInstance("fk_address_id", houseId);

            // return campaign uuid as response
            CampaignInstances campaign = new CampaignInstances();
            response = campaign.getUuid();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // return instance
        return response;
    }

}

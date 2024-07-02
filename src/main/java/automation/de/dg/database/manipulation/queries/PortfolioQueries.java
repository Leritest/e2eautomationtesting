package automation.de.dg.database.manipulation.queries;

/**
 * <b>DATABASE</b> [Queries]: Portfolio Database Queries
 *  <i>Class functionality:</i><br>
 *  Class is used to define strings<br>
 *  that will be executed as Portfolio DB queries.<br>
 *  Execution is done in PortfolioQueryManipulation class
 */

public class PortfolioQueries {

    // Query to get assigned houses to campaign instance
    public static String getHouses(String houseId) {
        String query = "select * from address a where a.house_id = '" + houseId + "'";
        return query;
    }

    // Query to get campaign instance
    public static String getCampaingInstance(String table, String id) {
        String addText;
        if ( id.isEmpty() || id.isBlank()) {
            addText = "";
        } else {
            addText = " where " + table + " = '" + id + "'";
        }
        String campaingInstance = "select * from campaign_instance" + addText;
        return campaingInstance;
    }

    // Query to get only One Campaign per House
    public static String getOneCampaingInstance = "select ci.fk_address_id, count(ci.fk_address_id)  \n" +
            "from campaign_instance ci \n" +
            "group by ci.fk_address_id \n" +
            "having count(ci.fk_address_id) < 2";

}

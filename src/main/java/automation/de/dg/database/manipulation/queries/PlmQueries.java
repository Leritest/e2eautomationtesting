package automation.de.dg.database.manipulation.queries;

/**
 * <b>DATABASE</b> [Queries]: PLM Database Queries
 *  <i>Class functionality:</i><br>
 *  Class is used to define strings<br>
 *  that will be executed as PLM DB queries.<br>
 *  Execution is done in PlmQueryManipulation class
 */

public class PlmQueries {

    public static String returnCampaign(String campaign) {
        String query = "select * from campaign c where c.validfrom > '2023-01-31'  \n" +
                "and stage = 'PRODUCTIVE' and c.name like '%2023%' \n" +
                "and c.marketing_segment = '" + campaign + "'";
        return query;
    }

}

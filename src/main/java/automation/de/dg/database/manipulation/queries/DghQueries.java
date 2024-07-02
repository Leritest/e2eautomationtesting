package automation.de.dg.database.manipulation.queries;

import automation.database.DataBaseConnection;
import automation.enumaration.DatabaseNames;

/**
 * <b>DATABASE</b> [Queries]: DGH Database Queries
 *  <i>Class functionality:</i><br>
 *  Class is used to define strings<br>
 *  that will be executed as DGH DB queries.<br>
 *  Execution is done in DghQueryManipulation class
 */

public class DghQueries {

    //----------------------------------------------------------------------------------------------------------------//
    //                                                      Contract
    //----------------------------------------------------------------------------------------------------------------//

    public static String getCustomerContract(int customerId) {
        String response = "SELECT * FROM CONTRACTS c WHERE c.CUSTOMER_ID = " + customerId + " AND DATE_DEACTIVE IS NULL";
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                                   Customer Address
    //----------------------------------------------------------------------------------------------------------------//

    public static String getCustomerAddress(int customerId, int addressType) {
        String address = "SELECT * FROM CUSTOMER_ADDRESSES ca WHERE ca.CUSTOMER_ID = " + customerId + " AND ca.ADDRESS_TYPE_ID = " + addressType;
        return address;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                                      Customer
    //----------------------------------------------------------------------------------------------------------------//

    public static String getCustomer(int customerId) {
        String customer = "SELECT c.* FROM CUSTOMER c WHERE c.CUSTOMER_ID = " + customerId;
        return customer;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                                  Customer Group
    //----------------------------------------------------------------------------------------------------------------//

    public static String getGroups = "SELECT * FROM CUSTOMER_GROUPS WHERE DGH_PROJEKT_ID IS NOT NULL AND FLAG IS NOT NULL";

    //----------------------------------------------------------------------------------------------------------------//
    //                                                    Subscriber
    //----------------------------------------------------------------------------------------------------------------//

    public static String getSubscriber(int customerId) {
        String add;
        if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.NEW)) {
            add = "NEW_TYPE_ID";
        } else {
            add = "DGH_TYPE_ID";
        }
        String response = "SELECT s.SUBS_ID, s.STATUS_ID, s.* \n" +
                "FROM SUBSCRIBER s \n" +
                "JOIN CO_VOICEDATA cv ON cv.COV_ID = s.COV_ID \n" +
                "JOIN CONTRACTS c ON cv.CO_ID = c.CO_ID \n" +
                "WHERE c.CUSTOMER_ID = " + customerId + " AND s." + add + " = 100";
        return response;
    }

    public static String getSubscriberForUpdating(int customerId) {
        String response = "SELECT s.SUBS_ID, s.* FROM SUBSCRIBER s\n" +
                "JOIN CO_VOICEDATA cv ON cv.COV_ID = s.COV_ID \n" +
                "JOIN CONTRACTS c ON cv.CO_ID = c.CO_ID \n" +
                "WHERE c.customer_id = " + customerId;
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                                   CO Products
    //----------------------------------------------------------------------------------------------------------------//

    public static String getCoProducts(int customerId) {
        String response = "SELECT cp.CO_PRODUCT_ID, cp.* FROM CO_PRODUCTS cp \n" +
                "JOIN CONTRACTS c ON c.CO_ID = cp.CO_ID \n" +
                "JOIN DGH_LOGISTIC dl ON dl.PRODUCT_REFERENCE = cp.CO_PRODUCT_ID \n" +
                "WHERE c.CUSTOMER_ID = " + customerId ;
        return response;
    }

    public static String getCoProductDescription(int productId) {
        String response = "SELECT p.DESCRIPTION, cp.CO_PRODUCT_ID, cp.* FROM CO_PRODUCTS cp \n" +
                "JOIN PRODUCTS p ON p.PRODUCT_ID = cp.PRODUCT_ID\n" +
                "WHERE CO_PRODUCT_ID = " + productId ;
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                              CO Products for updating
    //----------------------------------------------------------------------------------------------------------------//

    public static String getCoProductsUpdating(int custId) {
        String response = "SELECT p.DESCRIPTION, cp.CO_PRODUCT_ID, cp.* FROM CO_PRODUCTS cp \n" +
                "JOIN CONTRACTS c ON c.CO_ID = cp.CO_ID \n" +
                "JOIN products p ON p.PRODUCT_ID = cp.PRODUCT_ID \n" +
                "WHERE c.CUSTOMER_ID = " + custId + " AND (cp.date_active != '2100-01-01' or cp.date_active is NULL)";
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                          One-time CO Products for updating
    //----------------------------------------------------------------------------------------------------------------//

    public static String getOneTimeCoProductsUpdating(int custId) {
        String response = "select cop.co_product_id, ep.PRODUCT_NAME, cop.*\n" +
                "from co_products cop\n" +
                "join appsup_bil_605_setze_ende_datum ep on ep.product_id = cop.product_id\n" +
                "join contracts c on c.co_id = cop.co_id\n" +
                "where cop.date_deactive is null\n" +
                "and  cop.date_active is not null\n" +
                "and c.customer_id = " + custId;
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                              CO Voicedata Products
    //----------------------------------------------------------------------------------------------------------------//

    public static String getCoVoiceProducts(int custId) {
        String response = "SELECT p.DESCRIPTION AS DESCRIPTION, cvp.COV_PRODUCT_ID, cvp.*\n" +
                "FROM co_voicedata_products cvp\n" +
                "JOIN co_voicedata cv on cv.COV_ID = cvp.COV_ID\n" +
                "JOIN contracts c ON cv.co_id = c.CO_ID \n" +
                "JOIN DGH_LOGISTIC dl ON cvp.COV_PRODUCT_ID = dl.PRODUCT_REFERENCE \n" +
                "JOIN PRODUCTS p ON p.PRODUCT_ID = cvp.PRODUCT_ID \n" +
        "WHERE c.customer_id = " + custId;
        return response;
    }

    public static String getCoVoiceProductsUpdating(int custId) {
        String response = "SELECT cvp.* FROM CO_VOICEDATA_PRODUCTS cvp \n" +
                "JOIN CO_VOICEDATA cv ON cvp.COV_ID = cv.COV_ID \n" +
                "JOIN CONTRACTS c ON c.CO_ID  = cv.CO_ID \n" +
                "JOIN PRODUCTS p ON p.PRODUCT_ID = cvp.PRODUCT_ID \n" +
                "WHERE c.CUSTOMER_ID = " + custId;
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                          One-time COV Products for updating
    //----------------------------------------------------------------------------------------------------------------//

    public static String getOneTimeCovProductsUpdating(int custId) {
        String response = "select cov_product_id, ep.PRODUCT_NAME, covp.*\n" +
                "from co_voicedata_products covp\n" +
                "join appsup_bil_605_setze_ende_datum ep on ep.product_id = covp.product_id\n" +
                "join co_voicedata cv on cv.cov_id = covp.cov_id\n" +
                "join contracts c on c.co_id = cv.co_id\n" +
                "where covp.date_deactive is null\n" +
                "and  covp.date_active is not null\n" +
                "and c.customer_id = " + custId;
        return response;
    }

}

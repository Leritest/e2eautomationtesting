package automation.de.dg.database.manipulation.queries;

import automation.database.DataBaseConnection;
import automation.enumaration.DatabaseNames;

import static java.lang.Math.abs;

/**
 * <b>DATABASE</b> [Queries]: DGH Database Queries
 *  <i>Class functionality:</i><br>
 *  Class is used to define strings<br>
 *  that will be executed as DGH DB queries as update.<br>
 *  Execution is done in DghQueryManipulation class
 */

public class DghUpdateQueries {

    public static String updateCustomerGroup(int custId, long groupId) {
        String response = "UPDATE customer SET group_id = " + groupId + " WHERE CUSTOMER_ID = " + custId;
        return response;
    }

    public static String updateCustomerUcStatus(int custId, int status, int addType) {
        String response = "UPDATE CUSTOMER_ADDRESSES ca SET status_id = " + status + " WHERE ca.CUSTOMER_ID = " + custId + " AND ca.ADDRESS_TYPE_ID = " + addType;
        return response;
    }

    public static String executeActivateProcedure(int contract) {
        String response;
        if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.NEW)) {
            response = "EXECUTE PROCEDURE NEW_PLM_ACTIVATE_PRODUCT(" + contract + ",'AZ')";
        } else {
            response = "EXECUTE PROCEDURE DGH_PLM_ACTIVATE_PRODUCT_AT_WOT(" + contract + ",'AZ',current_date)";
        }
        return response;
    }

    public static String updateContract(int custId) {
        String add;
        if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.NEW)) {
            add = "NEW_START_OF_CONTRACT = current_date, NEW_CANCELATIONPERIOD = 1";
        } else {
            add = "DGH_START_OF_CONTRACT = current_date, DGH_CANCELATIONPERIOD = 1";
        }
        String response = "update CONTRACTS set \n" +
                "DATE_ACTIVE = current_date, DATE_BOUND_UNTIL = current_date+730, " + add + " \n" +
                "where customer_id = " + custId;
        return response;
    }

    public static String updateCustomerStatus(int custId, int status) {
        String response = "UPDATE CUSTOMER SET status_id = " + status + " WHERE CUSTOMER_ID = " + custId;
        return response;
    }

    public static String updateCustAddEmailValidation(int custId, int addType) {
        String add;
        if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.NEW)) {
            add = "NEW_EMAIL_VALID";
        } else {
            add = "dgh_email_valid";
        }
        String response = "UPDATE CUSTOMER_ADDRESSES SET " + add + " = 2\n" +
                "WHERE address_type_id = " + addType + " AND CUSTOMER_ID = " + custId;
        return response;
    }

    public static String activatePhone(int subsId, int phone) {
        String add;
        if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.NEW)) {
            add = "NEW_TYPE_ID = 100, NEW_STATUS_ID = 30";
        } else {
            add = "dgh_type_id = 100, dgh_status_id = 30";
        }
        String response = "UPDATE SUBSCRIBER SET date_active = current_date, subscriber_id = '+49 211 " + phone + "', \n" +
                "status_id = 200, " + add + "\n" +
                "WHERE subs_id = " + subsId;
        return response;
    }

    public static String deliverRouter(int prodId) {
        String response = "UPDATE DGH_LOGISTIC SET LIEFERSTATUS = 'DELIVERED', PAKET_CODE = '00340434695799999999', SERIENNUMMER = 'JY23074GJ009999',\n" +
                "MAC_ADRESSE = 'DC97E605XXXX', MODELTYP = 'TESTMODELL'\n" +
                "WHERE product_reference = " + prodId;
        return response;
    }

    public static String activateCoProduct(int prodId) {
        String response = "UPDATE CO_PRODUCTS SET Status_id = 200 WHERE CO_PRODUCT_ID = " + prodId;
        return response;
    }

    public static String activateCovProduct(int prodId) {
        String response = "UPDATE CO_VOICEDATA_PRODUCTS SET Status_id = 200 WHERE cov_product_id = " + prodId;
        return response;
    }

    public static String updateCustCampaign(int custId, int campId) {
        String response = "update CUSTOMER set campaign_id = " + campId +
                "where customer_id = " + custId;
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                                    Backdays
    //----------------------------------------------------------------------------------------------------------------//

    public static String backdaysVoiceProduct(int prodId, int days) {
        String sign = "-";
        if (days > 0) {
            sign = "+";
        }
        days = abs(days);
        String response = "UPDATE CO_VOICEDATA_PRODUCTS \n" +
                "SET DATE_ACTIVE = DATE_ACTIVE" + sign + days + ", DATE_DEACTIVE = DATE_DEACTIVE" + sign + days + ",\n" +
                "dgh_wish_start_date = dgh_wish_start_date" + sign + days + ", dgh_wish_end_date = dgh_wish_end_date" + sign + days + "\n" +
                "WHERE cov_product_id = " + prodId + " AND (date_active != '2100-01-01' OR date_active IS null)";
        return response;
    }

    public static String backdaysCoProduct(int prodId, int days) {
        String sign = "-";
        if (days > 0) {
            sign = "+";
        }
        days = abs(days);
        String response = "UPDATE CO_PRODUCTS SET DATE_ACTIVE = DATE_ACTIVE" + sign + days + ", DATE_DEACTIVE = DATE_DEACTIVE" + sign + days + ",\n" +
                "DGH_WISH_START_DATE = DGH_WISH_START_DATE" + sign + days + ", DGH_WISH_END_DATE = DGH_WISH_END_DATE" + sign + days + " \n" +
                "WHERE co_product_id = " + prodId;
        return response;
    }

    public static String backdaysContract(int custId, int days) {
        String sign = "-";
        if (days > 0) {
            sign = "+";
        }
        days = abs(days);
        String response = "update CONTRACTS\n" +
                "set date_created = date_created" + sign + days + ",\n" +
                "date_signed = date_signed" + sign + days + ",\n" +
                "date_active = date_active" + sign + days + ",\n" +
                "date_deactive = date_deactive" + sign + days + ",\n" +
                "date_bound_until = date_bound_until" + sign + days + ",\n" +
                "date_archived = date_archived" + sign + days + ",\n" +
                "date_active_part = date_active_part" + sign + days + ",\n" +
                "status_reminder_date = status_reminder_date" + sign + days + ",\n" +
                "wbci_porting_date = wbci_porting_date" + sign + days + ",\n" +
                "dgh_start_of_contract = dgh_start_of_contract" + sign + days + "\n" +
                "where customer_id = " + custId;
        return response;
    }

    public static String backdaysSubscription(int subsId, int days) {
        String sign = "-";
        if (days > 0) {
            sign = "+";
        }
        days = abs(days);
        String response = "UPDATE SUBSCRIBER \n" +
                "SET DATE_ACTIVE=DATE_ACTIVE" + sign + days + ", DATE_DEACTIVE=DATE_DEACTIVE" + sign + days + ", DATE_PORTI_REQ=DATE_PORTI_REQ" + sign + days + ", \n" +
                "STATUS_REMINDER_DATE=STATUS_REMINDER_DATE" + sign + days + ", DGH_SIP_LETTER_SENT=DGH_SIP_LETTER_SENT" + sign + days + "\n" +
                "WHERE subs_id = " + subsId;
        return response;
    }


    //----------------------------------------------------------------------------------------------------------------//
    //                                                One time products
    //----------------------------------------------------------------------------------------------------------------//

    public static String changeOneTimeCoProduct(int prodId) {
        String response = "update co_products SET date_deactive = date_active+1\n" +
                "where co_product_id = " + prodId;
        return response;
    }

    public static String changeOneTimeCovProduct(int prodId) {
        String response = "update co_voicedata_products SET date_deactive = date_active+1 \n" +
                "where cov_product_id = " + prodId;
        return response;
    }

}

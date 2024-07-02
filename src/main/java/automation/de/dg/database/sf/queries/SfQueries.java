package automation.de.dg.database.sf.queries;

import automation.de.dg.enumation.AddressTypesSalesforce;

/**
 * <b>DATABASE</b> [Queries]: SF Database Queries
 *  <i>Class functionality:</i><br>
 *  Class is used to define strings<br>
 *  that will be executed as Salesforce DB queries.<br>
 *  Execution is done in AcQueryManipulation class
 */

public class SfQueries {

    //----------------------------------------------------------------------------------------------------------------//
    //                                                   Get Account
    //----------------------------------------------------------------------------------------------------------------//

    public static String getCustomerId(String custId) {
        String response = "SELECT Id From ACCOUNT Where CimId__c='" + custId + "'";
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                            Get Account's Address
    //----------------------------------------------------------------------------------------------------------------//

    public static String getAccountAddress(String accountId, AddressTypesSalesforce at) {
        String response;
        switch (at) {
            case BILLING -> {
                response = "SELECT ISP__c, Billing_Street__c, BillingHouseNumber__c, BillingPostalCode__c, BillingCity__c, "
                        + "BillingCountry__c FROM Account WHERE Id = '" + accountId + "'";
            }
            case SHIPPING -> {
                response = "SELECT ISP__c, Shipping_Street__c, ShippingHouseNumber__c, ShippingPostalCode__c, ShippingCity__c, "
                        + "BillingCity__c, CustomerAddressCountry__c FROM Account WHERE Id = '" + accountId + "'";
            }
            case CONNECTION, CONNECTION_OWNER -> {
                response = "SELECT ISP__c, CustomerAddressStreet__c, CustomerAddressHouseNumber__c, PostalCodeInstallationAddress__c, "
                        + "ShippingCountry__c FROM Account WHERE Id = '" + accountId + "'";
            }
            default -> response = "";
        }

        return response;
    }


    //----------------------------------------------------------------------------------------------------------------//
    //                                            Get Account's Bank Data
    //----------------------------------------------------------------------------------------------------------------//

    public static String getBankAccount(String accountId) {
        String response = "SELECT vlocity_cmt__IBAN__c, IsSEPAActive__c, vlocity_cmt__BankAccountHolderName__c, ISP__c, " +
                "Id FROM vlocity_cmt__PaymentMethod__c WHERE vlocity_cmt__AccountId__c = '" + accountId + "'";
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                            Get Account's Bank Data
    //----------------------------------------------------------------------------------------------------------------//

    public static String getContract(String accountId) {
        String response = "SELECT ItemizedBillFormat__c  from Account WHERE Id='" + accountId + "'";
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                            Get Account's Phone number
    //----------------------------------------------------------------------------------------------------------------//

    public static String getPhoneNumber(String accountId) {
        String response = "SELECT Phone1__c FROM vlocity_cmt__Subscription__c WHERE vlocity_cmt__AccountId__c='" + accountId + "'";
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                        Get Account's Address based on type
    //----------------------------------------------------------------------------------------------------------------//

    public static String getAddressBasedOnType(String accountId, AddressTypesSalesforce type) {
        String response;
        switch (type) {
            case BILLING -> response = "SELECT CIMIdBillingContact__c FROM Contact WHERE AccountId='" + accountId
                    + "' AND CIMIdBillingContact__c != ''";
            case CONNECTION, CONNECTION_OWNER -> response = "SELECT CimIdConnectionContact__c FROM Contact WHERE AccountId='" + accountId
                    + "' AND CimIdConnectionContact__c != ''";
            case SHIPPING -> response = "SELECT CIMIdShippingContact__c FROM Contact WHERE AccountId='" + accountId
                    + "' AND CIMIdShippingContact__c != ''";
            default -> response = "";
        }
        return response;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                            Get Account's Optin
    //----------------------------------------------------------------------------------------------------------------//

    public static String getOptin(String accountId, AddressTypesSalesforce type) {
        String response;
        switch (type) {
            case BILLING -> response = "SELECT EmailOptIn__c, PhoneOptIn__c, TrafficDataUsageOptIn__c, EmailOptInChangeDate__c, " +
                    "PhoneOptInChangeDate__c, TrafficDataUsageChangeDate__c  FROM Contact WHERE AccountId = '" + accountId + "'";
            case CONNECTION, CONNECTION_OWNER ->  response = "SELECT EmailOptIn__c, PhoneOptIn__c, "
                    + "TrafficDataUsageOptIn__c, EmailOptInChangeDate__c, PhoneOptInChangeDate__c, TrafficDataUsageChangeDate__c "
                    + "FROM Contact WHERE AccountId = '" + accountId + "'";
            case SHIPPING -> response = "SELECT EmailOptIn__c, PhoneOptIn__c, "
                    + "TrafficDataUsageOptIn__c, EmailOptInChangeDate__c, PhoneOptInChangeDate__c, TrafficDataUsageChangeDate__c "
                    + "FROM Contact WHERE AccountId = '" + accountId + "'";
            default -> response = "";
        }
        return response;
    }

}

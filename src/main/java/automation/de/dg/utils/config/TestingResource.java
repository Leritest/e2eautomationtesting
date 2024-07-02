package automation.de.dg.utils.config;

import automation.de.dg.enumation.AddressTypesSalesforce;
import automation.de.dg.enumation.CustomerTypes;

import java.util.HashMap;

/**
 * <b>De.Dg : Utils</b> Testing Resource<br>
 * <i>Class functionality:</i><br>
 *  Class is used for storing testing resources.
 */

public class TestingResource {

    static CustomerTypes customerType;
    static AddressTypesSalesforce addressType;

    //----------------------------------------------------------------------------------------------------------------//
    //                                                      CIM
    //----------------------------------------------------------------------------------------------------------------//

    static int customerId;
    static int contractId;
    static int custStatusId;
    static int custGroupId;
    static int custAddressStatusId;
    static int custAddressEmailValid;
    static int custCampaignId;
    static int subsId;
    static int subStatusId;
    static int custProductId;
    static int[] voiceProduct;
    static int[] oneTimeVoiceProduct;
    static int[] product;
    static int[] oneTimeProduct;
    static int[] subsIdList;
    static HashMap<String, Integer> productList;
    static HashMap<String, HashMap<Integer, Integer>> productVoiceList;
    static String[] bankAccount;
    static String phoneNumber;
    static String[] customerAddress;
    static int projectId;
    static HashMap<String, Boolean> optin;

    //----------------------------------------------------------------------------------------------------------------//
    //                                                 Salesforce
    //----------------------------------------------------------------------------------------------------------------//
    static String sfAccountNumber;
    static String sfPhoneId;
    static String sfBankAccountId;
    static String sfContractId;

    //----------------------------------------------------------------------------------------------------------------//
    //                                              Setting Methods
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * <b>[Test Method]</b> - Setting Customer Type<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Customer Type
     * @param ct CustomerTypes
     */

    public void setCustomerType(CustomerTypes ct) {
        customerType = ct;
    }

    /**
     * <b>[Test Method]</b> - Setting Address Type<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Address Type
     * @param at CustomerTypes
     */

    public void setAddressTypesSalesforce(AddressTypesSalesforce at) {
        addressType = at;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                                      CIM
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * <b>[Test Method]</b> - Setting Customer ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Customer ID
     * @param cus int
     */

    public void setCustomerId(int cus) {
        customerId = cus;
    }

    /**
     * <b>[Test Method]</b> - Setting Contract ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Contract ID
     * @param con int
     */

    public void setContractId(int con) {
        contractId = con;
    }

    /**
     * <b>[Test Method]</b> - Setting Customer's Status ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Customer ID
     * @param status int
     */

    public void setCustomerStatusId(int status) {
        custStatusId = status;
    }

    /**
     * <b>[Test Method]</b> - Setting Customer's Group ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Customer ID
     * @param group int
     */

    public void setCustomerGroupId(int group) {
        custGroupId = group;
    }

    /**
     * <b>[Test Method]</b> - Setting Customer's Campaign ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Campaign ID
     * @param id int
     */

    public void setCustomerCampaignId(int id) {
        custCampaignId = id;
    }

    /**
     * <b>[Test Method]</b> - Setting Customer's address Status ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Status ID for customer's address
     * @param status int
     */

    public void setCustomerAddressStatusId(int status) {
        custAddressStatusId = status;
    }

    /**
     * <b>[Test Method]</b> - Setting Customer's address Email Valid Status<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Status email in customer's address
     * @param status int
     */

    public void setCustomerAddressValidEmail(int status) {
        custAddressEmailValid = status;
    }

    /**
     * <b>[Test Method]</b> - Setting Subscriber ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Subscriber ID
     * @param id int
     */

    public void setSubscriberId(int id) {
        subsId = id;
    }

    /**
     * <b>[Test Method]</b> - Setting Subscriber Status ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Subscriber Status ID
     * @param id int
     */

    public void setSubscriberStatusId(int id) {
        subStatusId = id;
    }

    /**
     * <b>[Test Method]</b> - Setting Customer's Product ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Customer's Product ID
     * @param id int
     */

    public void setCustomerProductId(int id) {
        custProductId = id;
    }

    /**
     * <b>[Test Method]</b> - Setting Customer's CO Voice Products<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing CO Voice Products ID
     * @param count int[]
     */

    public void setCoVoiceProductId(int[] count) {
        voiceProduct = new int[count.length];
        System.arraycopy(count, 0, voiceProduct, 0, count.length);
    }

    public void setCoVoiceProductIdList(HashMap<String, HashMap<Integer, Integer>> productMap) {
        productVoiceList = productMap;
    }

    /**
     * <b>[Test Method]</b> - Setting One-time COV Products<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing one-time CO Voice Products ID
     * @param count int[]
     */

    public void setOneTimeCovProductId(int[] count) {
        oneTimeVoiceProduct = new int[count.length];
        System.arraycopy(count, 0, oneTimeVoiceProduct, 0, count.length);
    }

    /**
     * <b>[Test Method]</b> - Setting Customer's CO Products<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing CO Products ID=
     */

    public void setCoProductIdList(HashMap<String, Integer> list) {
        productList = list;
    }

    public void setCoProductId(int[] count) {
        product = new int[count.length];
        System.arraycopy(count, 0, product, 0, count.length);
    }

    /**
     * <b>[Test Method]</b> - Setting One-time CO Products<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing one-time CO Products ID
     * @param count int[]
     */

    public void setOneTimeCoProductId(int[] count) {
        oneTimeProduct = new int[count.length];
        System.arraycopy(count, 0, oneTimeProduct, 0, count.length);
    }

    /**
     * <b>[Test Method]</b> - Setting Subscriber list<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Customer's Subscriber list
     * @param count int[]
     */

    public void setSubscriberList(int[] count) {
        subsIdList = new int[count.length];
        System.arraycopy(count, 0, subsIdList, 0, count.length);
    }

    /**
     * <b>[Test Method]</b> - Setting Details for Bank Account<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Customer's Bank Account data
     * @param list String[]
     */

    public void setBankAccountDetail(String[] list) {
        // 0 - holder, 1 - iban, 2 - isActive
        bankAccount = new String[list.length];
        System.arraycopy(list, 0, bankAccount, 0, list.length);
    }

    /**
     * <b>[Test Method]</b> - Setting Details for Phone Number<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Customer's Phone Number data
     * @param number String
     */

    public void setPhoneNumberDetail(String number) {
        phoneNumber = number;
    }

    /**
     * <b>[Test Method]</b> - Setting Details for Address<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Customer's Address data
     * @param list String[]
     */

    public void setAddressDetail(String[] list) {
        // 0 - country, 1 - city, 2 - zipCode, 3 - street, 4 - number
        customerAddress = new String[list.length];
        System.arraycopy(list, 0, customerAddress, 0, list.length);
    }

    /**
     * <b>[Test Method]</b> - Setting Details for Project ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Project ID based on city
     * @param number int
     */

    public void setProjectId(int number) {
        projectId = number;
    }

    /**
     * <b>[Test Method]</b> - Setting Details for Optin<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Customer's Optin data
     * @param list HashMap<String, Boolean>
     */

    public void setOptinDetail(HashMap<String, Boolean> list) {
        // 0 - country, 1 - city, 2 - zipCode, 3 - street, 4 - number
        optin = list;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                                 Salesforce
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * <b>[Test Method]</b> - Setting SF Account Number<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Salesforce Account Number
     * @param num String
     */

    public void setSfAccountId(String num) {
        sfAccountNumber = num;
    }

    /**
     * <b>[Test Method]</b> - Setting SF Bank Account ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Salesforce Bank Account ID
     * @param id String
     */

    public void setSfBankAccountId(String id) {
        sfBankAccountId = id;
    }

    /**
     * <b>[Test Method]</b> - Setting SF Phone ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Salesforce Phone ID
     * @param id String
     */

    public void setSfPhoneId(String id) {
        sfPhoneId = id;
    }

    /**
     * <b>[Test Method]</b> - Setting SF Contract ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Salesforce Contract ID
     * @param id String
     */

    public void setSfContractId(String id) {
        sfContractId = id;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                              Getting Methods
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * <b>[Test Method]</b> - Getting Customer Type<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer Type
     * @return customerId int
     */

    public CustomerTypes getCustomerType() {
        return customerType;
    }

    /**
     * <b>[Test Method]</b> - Getting Address Type<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Address Type
     * @return customerId int
     */

    public AddressTypesSalesforce getAddressTypesSalesforce() {
        return addressType;
    }


    /**
     * <b>[Test Method]</b> - Getting Customer ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer ID
     * @return customerId int
     */

    public int getCustomerId() {
        return customerId;
    }

    /**
     * <b>[Test Method]</b> - Getting Contract ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer ID
     * @return contractId int
     */

    public int getContractId() {
        return contractId;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's Status ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's Status ID
     * @return custStatusId int
     */

    public int getCustomerStatusId() {
        return custStatusId;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's Group ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's Group ID
     * @return custGroupId int
     */

    public int getCustomerGroupId() {
        return custGroupId;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's Campaign ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's Campaign ID
     * @return custGroupId int
     */

    public int getCustomerCampaignId() {
        return custCampaignId;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's Address Status ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Status ID of Customer's address
     * @return custAddressStatusId int
     */

    public int getCustomerAddressStatusId() {
        return custAddressStatusId;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's Address Email Validation<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Email Validation Status in Customer's address
     * @return custAddressEmailValid int
     */

    public int getCustomerAddressValidEmail() {
        return custAddressEmailValid;
    }


    /**
     * <b>[Test Method]</b> - Getting Subscriber ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Subscriber ID
     * @return subsId int
     */

    public int getSubscriberId() {
        return subsId;
    }

    /**
     * <b>[Test Method]</b> - Getting Subscriber Status ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Subscriber Status ID
     * @return subsId int
     */

    public int getSubscriberStatusId() {
        return subStatusId;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's Product ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's Product ID
     * @return subsId int
     */

    public int getCustomerProductId() {
        return custProductId;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's CO Voice Product IDs<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's CO Voice Product IDs
     *
     * @return subsId int
     */

    public int[] getCoVoiceProductId() {
        return voiceProduct;
    }

    public HashMap<String, HashMap<Integer, Integer>> getProductVoiceList() {
        return productVoiceList;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's one-time COV Product IDs<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's one-time CO Voice Product IDs
     *
     * @return subsId int
     */

    public int[] getOneTimeCovProductId() {
        return oneTimeVoiceProduct;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's CO Product IDs<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's CO Product IDs
     *
     * @return subsId int
     */

    public HashMap<String, Integer> getCoProductIdList() {
        return productList;
    }
    public int[] getCoProductId() {
        return product;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's one-time CO Product IDs<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's one-time CO Product IDs
     *
     * @return subsId int
     */

    public int[] getOneTimeCoProductId() {
        return oneTimeProduct;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's Subscribers IDs<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's list of Subscriber IDs
     *
     * @return subsIdList int[]
     */

    public int[] getSubscriberList() {
        return subsIdList;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's Bank Account Details<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's detail regarding Bank Account Details
     *
     * @return subsIdList int[]
     */

    public String[] getBankAccountDetail() {
        return bankAccount;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's Phone Number Details<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's detail regarding Phone Number Details
     *
     * @return subsIdList int[]
     */

    public String getSPhoneNumberDetail() {
        return phoneNumber;
    }

    /**
     * <b>[Test Method]</b> - Getting Customer's Address Details<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's detail regarding Address Details
     *
     * @return subsIdList int[]
     */

    public String[] getCustomerAddress() {
        return customerAddress;
    }

    /**
     * <b>[Test Method]</b> - Getting Project ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Customer's detail regarding Project ID
     *
     * @return subsIdList int[]
     */

    public int getProjectId() {
        return projectId;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                                 Salesforce
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * <b>[Test Method]</b> - Getting Salesforce Account ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Salesforce Account Number
     *
     * @return sfAccountNumber String
     */

    public String getSfAccountId() {
        return sfAccountNumber;
    }

    /**
     * <b>[Test Method]</b> - Getting Salesforce Bank Account ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Salesforce Bank Account ID
     *
     * @return sfPhoneId String
     */

    public String getSfPBankAccountId() {
        return sfBankAccountId;
    }

    /**
     * <b>[Test Method]</b> - Getting Salesforce Phone ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Salesforce Phone ID
     *
     * @return sfPhoneId String
     */

    public String getSfPhoneId() {
        return sfPhoneId;
    }

    /**
     * <b>[Test Method]</b> - Getting Salesforce Contract ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Salesforce Contract ID
     *
     * @return sfContractId String
     */

    public String getSfContractId() {
        return sfContractId;
    }

}

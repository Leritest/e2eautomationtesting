package automation.de.dg.utils.config;

import org.json.JSONObject;

/**
 * <b>De.Dg : Utils</b> Testing Customer Creation Data<br>
 * <i>Class functionality:</i><br>
 *  Class is used for storing testing data<br>
 *  used for Customer Creation
 */

public class CustomerCreationTestingData {

    static JSONObject address;
    static JSONObject bankHolder;
    static JSONObject optin;

    //----------------------------------------------------------------------------------------------------------------//
    //                                              Setting Methods
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * <b>[Test Method]</b> - Setting Address<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Address data
     * @param object JSONObject
     */

    public static void setAddress(JSONObject object) {
        address = object;
    }

    /**
     * <b>[Test Method]</b> - Setting Bank Holder<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Bank Holder data
     * @param object JSONObject
     */

    public static void setBankHolder(JSONObject object) {
        bankHolder = object;
    }

    /**
     * <b>[Test Method]</b> - Setting Optin<br>
     * <br><i>Test Method functionality:</i><br>
     * Storing Optin data
     * @param object JSONObject
     */
    public static void setOptinPart(JSONObject object) {
        optin = object;
    }


    //----------------------------------------------------------------------------------------------------------------//
    //                                              Getting Methods
    //----------------------------------------------------------------------------------------------------------------//

    /**
     * <b>[Test Method]</b> - Getting Address<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Address data
     * @return bankHolder JSONObject
     */
    public static JSONObject getAddress() {
        return address;
    }

    /**
     * <b>[Test Method]</b> - Getting Bank Holder<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Bank Holder data
     * @return bankHolder JSONObject
     */
    public static JSONObject getBankHolder() {
        return bankHolder;
    }

    /**
     * <b>[Test Method]</b> - Getting Optin<br>
     * <br><i>Test Method functionality:</i><br>
     * Returning Optin data
     * @return bankHolder JSONObject
     */
    public static JSONObject getOptinPart() {
        return optin;
    }

}

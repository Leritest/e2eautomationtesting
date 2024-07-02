package automation.enumaration;

/**
 * <b>Enumeration : RestAPI</b> RestAPI Names
 */

public enum RestApiNames {

    /**
     * Using RestAPI Names as Enum list
     */

    CAMEL("camel-api"),
    CAMPAIGN("campaign-api"),
    CIMREST("cim-rest-api"),
    SALESFORCE("salesforce-api");

    public final String option;


    RestApiNames(String option) {
        this.option = option;
    }
}

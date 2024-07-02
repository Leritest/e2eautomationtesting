package automation.de.dg.salesforce.enumation;


/**
 * <b>De.Dg/Salesforce/Enumation : User Roles Enums/b> User Roles Enums
 * * <i>Class functionality:</i><br>
 * Class is used to define Salesforce User Roles.
 */

public enum UserRoles {

    ACCOUNT_MANAGER("Account-manager"),
    CUSTOMER_OPERATIONS("Customer-operations"),
    CUSTOMER_OPERATIONS_AGENT("Auto-Customer-Operations-Agent"),
    SALES_OPERATIONS("Sales-operations"),
    AUTO_CUSTOMER_OPERATIONS_AGENT("Auto-Customer-Operations-Agent"),
    AUTO_ADMIN("Auto-Admin");

    public final String option;

    UserRoles(String option) {
        this.option = option;
    }

}

package automation.de.dg.salesforce.enumation;


/**
 * <b>De.Dg/Salesforce/Enumation : App List Enums/b> App List Enums
 * * <i>Class functionality:</i><br>
 * Class is used to define Salesforce App List.
 */

public enum AppList {

    CASES_APP("Cases-App"),
    ACCOUNTS_APP("Accounts-App"),
    CONTACTS_APP("Contacts-App"),
    KONTAKTE_APP("Kontakte-App"),
    SERVICE_CONSOLE_APP("Service-Console-App");

    public final String option;

    AppList(String option) {
        this.option = option;
    }

}

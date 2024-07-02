package automation.de.dg.salesforce.enumation;

/**
 * <b>De.Dg/Salesforce/Enumation : Record Types Enums/b> Record Types Enums
 * * <i>Class functionality:</i><br>
 *  Class is used to define Salesforce Record Types<br>
 *  got from Account API Response in case DB Query is used
 */

public enum RecordTypes {

    ACCOUNT("Account"),
    BANK_ACCOUNT("vlocity_cmt__PaymentMethod__c"),
    SUBSCRIPTION("vlocity_cmt__Subscription__c");

    public final String option;

    RecordTypes(String option) {
        this.option = option;
    }

}

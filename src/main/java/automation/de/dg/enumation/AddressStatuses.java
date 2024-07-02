package automation.de.dg.enumation;

/**
 * <b>De.Dg/Enumation : Address Statuses Enums/b> Address Statuses Enums
 */

public enum AddressStatuses {

    TZIP_search(0),
    TZIP_found(100),
    UNIT_no_free_unit(114),
    UNIT_found(200),
    CAMEL_open_ProvideOrder(320),
    HOMES_served(700),
    CAMEL_denied(800),
    UNIT_technical_prob(999);


    public final int option;

    AddressStatuses(int option) {
        this.option = option;
    }

}

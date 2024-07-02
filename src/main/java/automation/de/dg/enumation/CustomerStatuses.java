package automation.de.dg.enumation;

/**
 * <b>De.Dg/Enumation : Customer Statuses Enums/b> Customer Statuses Enums
 */

public enum CustomerStatuses {

    Vertragseingang(10000),
    Nachbearbeitung(10001),
    AEB_zu_verschicken(10002),
    AEB_verschickt(10003),
    AB_zu_verschicken(10004),
    AB_verschickt(10005),
    IB_zu_verschicken(10006),
    IB_verschickt(10007),
    Portierung_Ready(10008),
    Aktiv(10021),
    Kündigung_in_Bearbeitung(10028);


    public final int option;

    CustomerStatuses(int option) {
        this.option = option;
    }

}

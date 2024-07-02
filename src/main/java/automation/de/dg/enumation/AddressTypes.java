package automation.de.dg.enumation;

/**
 * <b>De.Dg/Enumation : Address Types Enums/b> Address Types Enums
 */

public enum AddressTypes {

    Lieferanschrift(1000),
    Anschlussinhaberanschrift(2000),
    Rechnungsanschrift(9999),
    Installationsanschrift(10010),
    Telefonbuchanschrift(10011),
    Altprovideranschrift(10016);

    public final int option;

    AddressTypes(int option) {
        this.option = option;
    }

}

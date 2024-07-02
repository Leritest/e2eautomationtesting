package automation.de.dg.enumation.dghIsp;

/**
 * <b>De.Dg/Enumation : 2023 Portfolio Tariffs Enums/b> 2023 Portfolio Tariffs Enums
 */

public enum TariffTwentythree {

    Hundred(100),
    ThreeHundred(300),
    FourHundred(400),
    FiveHundred(500),
    Thousand(1000),
    Thousand_Twelve(1012),
    Universaldienst(0);

    public final int option;

    TariffTwentythree(int option) {
        this.option = option;
    }

}

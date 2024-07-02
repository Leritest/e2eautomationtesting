package automation.de.dg.enumation.dghIsp;

/**
 * <b>De.Dg/Enumation : 2018 Portfolio Tariffs Enums/b> 2018 Portfolio Tariffs Enums
 */

public enum TariffEighteen {

    ThreeHundred(300),
    FourHundred(400),
    SixHundred(600),
    EightHundred(800),
    Thousand(1000),
    Thousand_Twelve(1012);

    public final int option;

    TariffEighteen(int option) {
        this.option = option;
    }

}

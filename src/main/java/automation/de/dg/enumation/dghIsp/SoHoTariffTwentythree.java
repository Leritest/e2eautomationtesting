package automation.de.dg.enumation.dghIsp;

/**
 * <b>De.Dg/Enumation : SoHo 2023 Portfolio Tariffs Enums/b> SoHo2023 Portfolio Tariffs Enums
 */
public enum SoHoTariffTwentythree {

    ThreeHundred(300),
    SixHundred(600),
    Thousand(1000),
    Thousand_Twelve(1012)
    ;

    public final int option;

    SoHoTariffTwentythree(int option) {
        this.option = option;
    }


}

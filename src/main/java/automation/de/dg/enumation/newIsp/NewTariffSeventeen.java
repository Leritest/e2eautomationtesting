package automation.de.dg.enumation.newIsp;

/**
 * <b>De.Dg/Enumation : New 2017 Portfolio Tariffs Enums/b> New 2017 Portfolio Tariffs Enums<br>
 * <i>Class functionality:</i><br>
 * Class is used to define available tariffs<br>
 * for Portfolio 2017 for New type
 */
public enum NewTariffSeventeen {

    NewHundred(100),
    NewTwoHundred(200),
    NewFiveHundred(500);

    public final int option;

    NewTariffSeventeen(int option) {
        this.option = option;
    }


}

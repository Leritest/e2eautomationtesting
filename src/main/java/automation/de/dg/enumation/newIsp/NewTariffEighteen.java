package automation.de.dg.enumation.newIsp;

/**
 * <b>De.Dg/Enumation : New 2019 Portfolio Tariffs Enums/b> New 2019 Portfolio Tariffs Enums<br>
 * <i>Class functionality:</i><br>
 * Class is used to define available tariffs<br>
 * for Portfolio 2019 for New type
 */

public enum NewTariffEighteen {

    NewThreeHundred(300),
    NewFourHundred(400),
    NewSixHundred(600),
    NewThousand(1000),
    NewThousand_Twelve(1012);

    public final int option;

    NewTariffEighteen(int option) {
        this.option = option;
    }

}

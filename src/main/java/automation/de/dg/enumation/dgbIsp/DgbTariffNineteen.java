package automation.de.dg.enumation.dgbIsp;


/**
 * <b>De.Dg/Enumation : DGB 2019 Portfolio Tariffs Enums/b> DGB 2019 Portfolio Tariffs Enums<br>
 * <i>Class functionality:</i><br>
 * Class is used to define available tariffs<br>
 * for Portfolio 2019 for DGB type
 */

public enum DgbTariffNineteen {

    BusinessThreeHundred(300),
    BusinessSixHundred(600),
    BusinessThousand(1000);

    public final int option;

    DgbTariffNineteen(int option) {
        this.option = option;
    }

}

package automation.de.dg.enumation;

/**
 * <b>De.Dg/Enumation : Portfolios Enums/b> Portfolios Enums
 */

public enum Portfolios {

    Fifteen(2015),
    Legacy(2016),
    Seventeen(2017),
    Seventeen_Mig(2017),
    SoHo2017(2017),
    Eighteen(2018),
    Eighteen_Mig(2018),
    SoHo2018(2018),
    Dgb_Nineteen(2019),
    Twentythree(2023),
    SoHo2023(2023)
    ;

    public final int option;

    Portfolios(int option) {
        this.option = option;
    }

}

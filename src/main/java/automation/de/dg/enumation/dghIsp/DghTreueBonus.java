package automation.de.dg.enumation.dghIsp;

/**
 * <b>De.Dg/Enumation : Treuebonus Enums/b> Treuebonus Enums
 */
public enum DghTreueBonus {

    Treuebonus_24_x_5_2018(10230),
    Treuebonus_24_x_10_2018(10232),
    Treuebonus_24_x_15_2018(10272),
    Treuebonus_24_x_20_2018(10474),
    Treuebonus_24_x_25_2018(10576),
    Treuebonus_24_x_30_2018(10578),
    Treuebonus_24_x_5_2023(28001),
    Treuebonus_24_x_10_2023(28002),
    Treuebonus_24_x_15_2023(28003),
    Treuebonus_24_x_20_2023(28004),
    Treuebonus_24_x_25_2023(28006),
    Treuebonus_24_x_30_2023(28007),
    Folge_Treuebonus_24_x_5_2018(10492),
    Folge_Treuebonus_24_x_10_2018(10493),
    Folge_Treuebonus_24_x_15_2018(10494),
    Folge_Treuebonus_24_x_20_2018(10575),
    Folge_Treuebonus_24_x_25_2018(10577),
    Folge_Treuebonus_24_x_30_2018(10579),
    ;

    public final int option;

    DghTreueBonus(int option) {
        this.option = option;
    }
}

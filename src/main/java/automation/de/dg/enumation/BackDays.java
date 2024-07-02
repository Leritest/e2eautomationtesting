package automation.de.dg.enumation;

/**
 * <b>De.Dg/Database/Enumation : Change days Enums/b> Change days Enums
 */

public enum BackDays {

    FUTURE_MORE_TWO_YEARS(800),
    FUTURE_TWO_YEARS(730),
    FUTURE_EIGHTEEN_MONTHS(540),
    FUTURE_ONE_YEAR(365),
    FUTURE_SIX_MONTHS(180),
    PAST_SIX_MONTHS(-180),
    PAST_ONE_DAY(-1),
    PAST_NINE_MONTHS(-255),
    PAST_TEN_MONTHS(-285),
    PAST_TWELVE_MONTHS(-350),
    PAST_THIRTEEN_MONTHS(-375),
    PAST_FOURTEEN_MONTHS(-405),
    PAST_FIFTEEN_MONTHS(-435),
    PAST_EIGHTEEN_MONTHS(-540),
    PAST_TWENTY_MONTHS(-585),
    PAST_TWENTY_ONE_MONTHS(-625),
    PAST_TWENTY_THREE_MONTHS(-685),
    PAST_TWO_YEARS(-730),
    PAST_MORE_TWO_YEARS(-745),
    PAST_TWO_AND_HALF_YEAR(-900);


    public final int option;

    BackDays(int option) {
        this.option = option;
    }

}

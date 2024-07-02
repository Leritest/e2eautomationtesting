package automation.de.dg.enumation.dghIsp;

/**
 * <b>De.Dg/Enumation : Loyalty Bonus Enums/b> Loyalty Bonus Enums
 */
public enum DghLoyaltyBonus {

    Loyalty_Bonus_5_Euro(28050),
    Loyalty_Bonus_10_Euro(28051),
    Loyalty_Bonus_15_Euro(28052),
    Loyalty_Bonus_20_Euro(28053),
    Loyalty_Bonus_25_Euro(28055),
    Loyalty_Bonus_30_Euro(28056),
    Loyalty_Bonus_Fritzbox(28150),
    Loyalty_Bonus_Bundle(28151),
    Loyalty_Bonus_Wlanplus(28152)
    ;

    public final int option;

    DghLoyaltyBonus(int option) {
        this.option = option;
    }

}

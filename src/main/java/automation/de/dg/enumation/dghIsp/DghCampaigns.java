package automation.de.dg.enumation.dghIsp;

/**
 * <b>De.Dg/Enumation : Campaigns Enums/b> Campaigns Enums
 */
public enum DghCampaigns {

    Standard_2018(10004),
    Early_Retention_2018(10136),
    Regular_Retention_2018(10137),
    Last_Retention_2018(10138),
    Portout_Retention_2018(10135),
    Retention_Now_2018(10148),
    Prevention_Loyalty_2018(10112),
    Prevention_Loyalty_Inbound_2018(10151),
    Prevention_Standard_2018(10133),
    Prevention_Standard_Inbound_2018(10134),
    Save_Speed_2018(10122),
    Downgrade_Prevention_2018(10147),
    Early_Retention_2023(20233006),
    Regular_Retention_2023(20233007),
    Last_Retention_2023(20233008),
    Portout_Retention_2023(20233005),
    Retention_Now_2023(20233011),
    Prevention_Loyalty_2023(20233001),
    Prevention_Loyalty_Inbound_2023(20233012),
    Prevention_Standard_2023(20233003),
    Prevention_Standard_Inbound_2023(20233004),
    Save_Speed_2023(20233002),
    Downgrade_Prevention_2023(20233010)
    ;

    public final int option;

    DghCampaigns(int option) {
        this.option = option;
    }

}

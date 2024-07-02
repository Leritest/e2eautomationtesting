package automation.de.dg.domain.enums;

/**
 * <b>Domain : [Enums]</b> DGF Channels
 */

public enum DgfChannels {

    /**
     * Using DeutscheGlasfaser Channels as Enum list<br>
     */

    ALL("All"),
    AGENTUR("Agentur"),
    DG_INTERN("DG_Intern"),
    DG_INTERN_LEAD("DG_Intern_Lead"),
    ONLINE("Online"),
    ONLINE_COMPARISON("Online_Comparison1"),
    POS("Pos"),
    TELE_SALES("Tele_Sales");

    public final String option;

    DgfChannels(String option) {
        this.option = option;
    }

}

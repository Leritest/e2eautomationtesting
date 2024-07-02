package automation.de.dg.domain.enums;

/**
 * <b>Domain : [Enums]</b> DGF Channels
 */

public enum DgfMarketingSegment {

    /**
     * Using DeutscheGlasfaser Marketing Segment as Enum list<br>
     */

    DGB("DGB"),
    DGH("DGH"),
    DG_PROFESSIONAL("DG_PROFESSIONAL");

    public final String option;

    DgfMarketingSegment(String option) {
        this.option = option;
    }

}

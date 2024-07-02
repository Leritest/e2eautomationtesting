package automation.de.dg.domain;

import automation.de.dg.domain.enums.DgfChannels;
import automation.de.dg.domain.enums.DgfMarketingSegment;

/**
 * <b>Domain : Campaign</b> Campaign Instance
 *  <i>Class functionality:</i><br>
 *  Class is used to set values related to Campaign<br>
 *  that are got from Portfolio database by queries.
 */

public class CampaignInstances {

    static String uuid;
    static int campaignId;
    static String campaignName;
    static DgfMarketingSegment segment = null;
    static DgfChannels channel;
    static int ranking;

    //##################################################################################################################
    //###                                               Setting Classes
    //##################################################################################################################

    /**
     * <b>[Test Method]</b> - Setting Campaign UUID<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting UUID value from Campaign table
     */

    public void setUuid(String id) {
        uuid = id;
    }

    /**
     * <b>[Test Method]</b> - Setting Campaign ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting Campaign ID value from Campaign table
     */

    public void setCampaignId(int id) {
        campaignId = id;
    }

    /**
     * <b>[Test Method]</b> - Setting Campaign Name<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting Campaign NAme value from Campaign table
     */

    public void setCampaignName(String id) {
        campaignName = id;
    }

    /**
     * <b>[Test Method]</b> - Setting Marketing Segment<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting Marketing Segment value from Campaign table
     */

    public void setSegment(String id) {
        segment = DgfMarketingSegment.valueOf(id.toUpperCase());
    }

    /**
     * <b>[Test Method]</b> - Setting Channel<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting channel value from Campaign table
     */

    public void setChannel(String id) {
        channel = DgfChannels.valueOf(id.toUpperCase());
    }

    /**
     * <b>[Test Method]</b> - Setting Ranking<br>
     * <br><i>Test Method functionality:</i><br>
     * Setting Ranking value from Campaign table
     */

    public void setRanking(int id) {
        ranking = id;
    }

    //##################################################################################################################
    //###                                               Getting Classes
    //##################################################################################################################

    /**
     * <b>[Test Method]</b> - Getting UUID<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting UUID value taken from Campaign table
     * @return campaign uuid
     */

    public String getUuid() {
        return uuid;
    }

    /**
     * <b>[Test Method]</b> - Getting Campaign ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting Campaign ID value taken from Campaign table
     * @return campaign id
     */

    public int getCampaignId() {
        return campaignId;
    }

    /**
     * <b>[Test Method]</b> - Getting Campaign ID<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting Campaign ID value taken from Campaign table
     * @return campaign id
     */

    public String getCampaignName() {
        return campaignName;
    }

    /**
     * <b>[Test Method]</b> - Getting Campaign Name<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting Campaign Name value taken from Campaign table
     * @return campaign name
     */

    public DgfMarketingSegment getSegment() {
        return segment;
    }

    /**
     * <b>[Test Method]</b> - Getting Marketing Segment<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting Marketing Segment value taken from Campaign table
     * @return marketing segment
     */

    public DgfChannels getChannel() {
        return channel;
    }

    /**
     * <b>[Test Method]</b> - Getting Campaign ranking<br>
     * <br><i>Test Method functionality:</i><br>
     * Getting Campaign ranking value taken from Campaign table
     * @return ranking
     */

    public int getRanking() {
        return ranking;
    }

}

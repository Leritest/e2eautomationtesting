package de.dg.database.dgh;

import automation.de.dg.database.dgh.customer.SuiteUpdatingCustomerData;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteCustomerAddCampaign {

    SuiteUpdatingCustomerData updatingCustomer = new SuiteUpdatingCustomerData();

    static int custId = 3935452;
    static int campId = 10133;

    /**
     * <b>[Test Method]</b> - Test Case updating Campaign<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs Campaign updating<br>
     */

    @Test
    public void tcCampaignUpdating() {
        updatingCustomer.updateCampaign(custId, campId);
    }

}

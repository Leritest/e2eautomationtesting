package automation.de.dg.endpoints.cimrest.customers.testsuite;

import automation.database.DataBaseConnection;
import automation.de.dg.database.dgh.customer.SuiteUpdatingCustomerData;
import automation.de.dg.endpoints.cimrest.customers.controllers.MainProduct;
import automation.de.dg.endpoints.cimrest.customers.controllers.PatchContract;
import automation.de.dg.endpoints.cimrest.customers.controllers.PostCreateCustomer;
import automation.de.dg.endpoints.cimrest.utils.StaticContext;
import automation.de.dg.enumation.*;
import automation.de.dg.utils.config.TestingResource;
import automation.enumaration.DatabaseNames;
import automation.utilities.Log;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

/**
 * <b>RestAPI : CIM Rest Suite</b> Creating Customer Suite<br>
 * <i>Class functionality:</i><br>
 *  Class is used to define flow for test cases<br>
 *  that cover creating Customer flow.
 */

public class SuiteCreatingCustomerDetail {

    /**
     * <b>[Test Method]</b> - Test Case creating Customer<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs flow of creating Customer<br>
     *
     * <i>Steps of this scenario:</i><br>
     *  1. Set Body Request<br>
     *  2. Preparing JSON body request
     *  3. Execute Request<br>
     *  4. Verify Proper HTTP Response status is returned<br>
     *  5. Validating customer
     */
    public void flowCustomerCreation(Portfolios portfolio, int tariff, WaipuTypes waipu, RouterTypes router) {
        try {
            // initialize classes
            PostCreateCustomer postCreation = new PostCreateCustomer();
            TestingResource resource = new TestingResource();
            MainProduct main = new MainProduct();
            // step 1 - define products list (main and instances)
            main.defineMainProduct(portfolio, tariff);
            if (!portfolio.equals(Portfolios.Legacy)) {
                main.setWaipu(portfolio, waipu);
                main.setRouter(portfolio, router);
                main.setShippingCost(portfolio);
                if (main.getWaipuStickNumber() > 1 && !waipu.equals(WaipuTypes.Null)) {
                    main.setWaipuProducts(portfolio);
                }
            }

            // check if Porting should be performed
            if (getPortingStatus()) {
                if (resource.getCustomerType().equals(CustomerTypes.DGH)) {
                    main.setPorting(portfolio, tariff);
                } else if (resource.getCustomerType().equals(CustomerTypes.NEW)) {
                    main.setPortingForIspType(portfolio, tariff);
                }
            }

            // step 2 - creation body and executing request
            JSONObject body = postCreation.body();

            // step 3 - executing request
            Response response = null;
            // check Customer type in order to execute proper endpoint
            if (resource.getCustomerType().equals(CustomerTypes.DGH)) {
                response = postCreation.createCustomer(StaticContext.getCimInstanceUrl(), String.valueOf(body));
            } else {
                PatchContract patchContract = new PatchContract();
                response = patchContract.createCustomer(StaticContext.getCimInstanceUrl(), String.valueOf(body));
            }

            // step 4 - check if customer is created and take customer and contract id
            takeCustomerCreationData(response);

            //step 5 - verify Contract data are properly fulfilled
            SuiteGetContractDetail getContract = new SuiteGetContractDetail();
            getContract.flowGetContractDetailNewCustomer(StaticContext.getCimInstanceUrl(), resource.getCustomerId());

            // print result of customer creation
            Log.info("Customer is successfully created");
            Log.info("Created Customer ID: " + resource.getCustomerId());
            Log.info("Created Contract ID: " + resource.getContractId());

            // step validate email address
            // set db name
            if  (DataBaseConnection.getDatabaseName()==null) {
                DataBaseConnection.setDatabaseName(DatabaseNames.DGH);
            }

            SuiteUpdatingCustomerData updatingCustomer = new SuiteUpdatingCustomerData();
            updatingCustomer.updateEmailValidation(resource.getCustomerId(), AddressTypes.Rechnungsanschrift);
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Take Customer Creation Data<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs saving customer's data<br>
     * taken from Customer creation response
     */

    private void takeCustomerCreationData(Response response) {
        try {
            // take customer id from endpoint's response body
            int customerId = response.jsonPath().getJsonObject("customerId");
            Assert.assertNotEquals(String.valueOf(customerId), "");
            // take contract id from endpoint's response body
            int contractId = response.jsonPath().getJsonObject("contractId");
            Assert.assertNotEquals(String.valueOf(contractId), "");
            // store customer id and contract id for further activation flow
            TestingResource resource = new TestingResource();
            resource.setCustomerId(customerId);
            resource.setContractId(contractId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    static boolean porting;

    public void setPortingStatus(boolean status) {
        porting = status;
    }

    public boolean getPortingStatus() {
        return porting;
    }
}

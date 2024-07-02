package de.dg.end2end.resourceMatrix;

import automation.de.dg.endpoints.cimrest.customers.controllers.MainProduct;
import automation.de.dg.endpoints.cimrest.customers.controllers.PostCreateCustomer;
import automation.de.dg.endpoints.cimrest.customers.testsuite.SuiteCreatingCustomerDetail;
import automation.de.dg.endpoints.cimrest.utils.StaticContext;
import automation.de.dg.enumation.CustomerTypes;
import automation.de.dg.enumation.Portfolios;
import automation.de.dg.enumation.RouterTypes;
import automation.de.dg.enumation.WaipuTypes;
import automation.de.dg.salesforce.endpoints.testsuite.SuiteSfCreatingCustomer;
import automation.de.dg.utils.config.TestingResource;
import automation.endpoints.CommonTest;
import automation.endpoints.enumaration.RestApiAuth;
import automation.enumaration.RestApiNames;
import de.dg.database.dgh.TestSuiteCustomerActivationArrayList;
import de.dg.endpoints.apiSuite.cimrest.contract.TestSuiteVzf;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>End2End Feature : DGH ISP Type Suite</b> Activate SoHo Customer List<br>
 *  <i>Class functionality:</i><br>
 *  Class is used to define end2end test suite<br>
 *  regarding activation of customer list <br>
 *  on SoHo Portfolio for DGH type
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteE2eActivateCustomerListSoHoDghIspType {
    CommonTest common = new CommonTest();
    TestingResource resource = new TestingResource();
    SuiteSfCreatingCustomer createCustomer = new SuiteSfCreatingCustomer();
    TestSuiteCustomerActivationArrayList activationCustomer = new TestSuiteCustomerActivationArrayList();

    List<Integer> cupool = new ArrayList<>();
    boolean fsecure = false;
    boolean porting = false;

    @Test
    public void tcE2eActivateCustomerPortfolioSoho17Type() {
        cupool = new ArrayList<>();

        Portfolios portfolio = Portfolios.SoHo2017;
        RouterTypes router = RouterTypes.Ker;
        WaipuTypes waipu = WaipuTypes.Null;

        MainProduct main = new MainProduct();
        main.setWaipuStickNumber(0);
        main.setComfortConnection(false);
        main.setSecondLine(false);

        int repeat = 2;
        // create customer with tariff 300
        int tariff = 300;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        System.out.println("List of Created Customers on Portfolio " + portfolio + ":");
        for (int i=0; i<cupool.size(); i++) {
            System.out.println("Following Customer is created: " + cupool.get(i));
        }

        activationCustomer.setCustomerList(cupool);
        activationCustomer.tcWholeProcess();

        // sign VZF
        signVzf(cupool);

        System.out.println("List of Activated Customers on Portfolio " + portfolio + ":");
        for (int i=0; i<cupool.size(); i++) {
            System.out.println("Following Customer is finished: " + cupool.get(i));
        }
    }


    public void customerCreation(int repeat, Portfolios portfolio, int tariff, RouterTypes router, WaipuTypes waipu, boolean greater23) {
        for (int i=0; i<repeat; i++) {
            initTestingResource(portfolio, tariff, router, waipu, greater23);

            // perform customer's activation process
            createCustomer.flowSfCustomerCreation(portfolio, tariff, waipu, router);
            // add created customer to array
            cupool.add(resource.getCustomerId());
        }
    }

    public void signVzf(List<Integer> cupool) {
        TestSuiteVzf signVzf = new TestSuiteVzf();
        signVzf.setCustomerList(cupool);
        signVzf.setupApiName();
        signVzf.tcSignVzfList();
    }

    /**
    * <b>[Test Method]</b> - Set Up for RestApi<br>
    * <br><i>Test Method functionality:</i><br>
    * Purpose of the method is to initialize API name used for testing
    * Next step will be setup action in CommonTest class under RestAPI package
    * which allows us to prepare base url and<br>
    * BeforeSuite - annotation
    */

    @BeforeSuite
    public void setupApiName() {
        // set Customer Type
        TestingResource resource = new TestingResource();
        resource.setCustomerType(CustomerTypes.DGH);
        // initialize API name
        CommonTest.setRestApiName(RestApiNames.CIMREST);
        // initialize API Auth type
        CommonTest.setRestApiAuthType(RestApiAuth.BASIC);
        // initialize RestApi URL
        common.setup();
        // define CIM Rest instance
        StaticContext.setCimInstanceUrl(common.getRestApiUrl(), resource.getCustomerType(), "v4");
    }

    /**
     * <b>[Test Method]</b> - Set Up testing Resource<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to initialize testing resource<br>
     * used for POST Customer Creation CIM Rest API Request
     * BeforeTest - annotation
     */

    public void initTestingResource(Portfolios portfolio, int tariff, RouterTypes router, WaipuTypes waipu, boolean greater23) {
        // set first name
        String firstName = StaticContext.generateRandomFirstNameBasedOnData(CustomerTypes.DGH, portfolio, tariff, router, waipu);
        PostCreateCustomer.setFirstName(firstName);
        // set if contract length is greater than 23 months
        PostCreateCustomer.setGreater23(greater23);
        // set email address
        PostCreateCustomer.setEmail("Test_Kundenkommunikation@deutsche-glasfaser.de");
        // set if F-Secure should be added
        PostCreateCustomer.setFsecure(fsecure);
        // set porting status
        SuiteCreatingCustomerDetail createCustomer = new SuiteCreatingCustomerDetail();
        createCustomer.setPortingStatus(porting);
    }

}

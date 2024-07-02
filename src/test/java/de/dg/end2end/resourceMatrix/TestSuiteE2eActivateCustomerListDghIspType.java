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
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>End2End Feature : DGH ISP Type Suite</b> Activate Customer List<br>
 *  <i>Class functionality:</i><br>
 *  Class is used to define end2end test suite<br>
 *  regarding activation of customer list for DGH type
 */
@Listeners(automation.listeners.ExtentListeners.class)
public class TestSuiteE2eActivateCustomerListDghIspType {

    CommonTest common = new CommonTest();
    TestingResource resource = new TestingResource();
    SuiteSfCreatingCustomer createCustomer = new SuiteSfCreatingCustomer();

    TestSuiteCustomerActivationArrayList activationCustomer = new TestSuiteCustomerActivationArrayList();

    List<Integer> cupool = new ArrayList<>();
    boolean fsecure = false;
    boolean porting = false;

    @Test
    public void tcE2eActivateCustomerPortfolio23DghIspType() {
        cupool = new ArrayList<>();

        Portfolios portfolio = Portfolios.Twentythree;
        RouterTypes router = RouterTypes.Ker;
        WaipuTypes waipu = WaipuTypes.Null;
        boolean greater23 = false;
        int tariff;

        int repeat = 3;
        // create customer with tariff 100
        tariff = 100;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        // create customer with tariff 300
        tariff = 300;
        customerCreation(repeat, portfolio, tariff, router, waipu, true);

        // create customer with tariff 400
        tariff = 400;
        customerCreation(repeat, portfolio, tariff, router, waipu, greater23);

        // create customer with tariff 500
        tariff = 500;
        customerCreation(repeat, portfolio, tariff, router, waipu, greater23);

        // create customer with tariff 1000
        tariff = 1000;
        customerCreation(repeat, portfolio, tariff, router, waipu, greater23);

        tariff = 1012;
        //customerCreation(repeat, portfolio, tariff, router, waipu, greater23);

        System.out.println("List of Created Customers:");
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

    @Test
    public void tcE2eActivateCustomerPortfolio18DghIspType() {
        cupool = new ArrayList<>();

        Portfolios portfolio = Portfolios.Eighteen;
        RouterTypes router = RouterTypes.Fritzbox;
        WaipuTypes waipu = WaipuTypes.Null;
        boolean greater23 = false;
        int tariff;

        int repeat = 1;
        // create customer with tariff 300
        tariff = 300;
        customerCreation(repeat, portfolio, tariff, router, waipu, greater23);

        // create customer with tariff 400
        tariff = 400;
        customerCreation(repeat, portfolio, tariff, router, waipu, greater23);

        // create customer with tariff 600
        tariff = 600;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        // create customer with tariff 1000
        tariff = 1000;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        // create customer with tariff 1000 Twelve months
        tariff = 1012;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        System.out.println("List of Created Customers:");
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

    @Test
    public void tcE2eActivateCustomerPortfolio18MigDghIspType() {
        cupool = new ArrayList<>();

        Portfolios portfolio = Portfolios.Eighteen_Mig;
        RouterTypes router = RouterTypes.Premium_2018;
        WaipuTypes waipu = WaipuTypes.Perfect_10_2018;

        MainProduct main = new MainProduct();
        main.setWaipuStickNumber(3);
        int tariff;

        int repeat = 1;
        // create customer with tariff 300
        tariff = 300;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        // create customer with tariff 400
        tariff = 400;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        // create customer with tariff 600
        tariff = 600;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        System.out.println("List of Created Customers:");
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

    @Test
    public void tcE2eActivateCustomerPortfolio17DghIspType() {
        cupool = new ArrayList<>();

        Portfolios portfolio = Portfolios.Seventeen;
        RouterTypes router = RouterTypes.Fast_2017;
        WaipuTypes waipu = WaipuTypes.Perfect_Plus_15_2018;

        MainProduct main = new MainProduct();
        main.setWaipuStickNumber(1);
        main.setSecondLine(false);
        main.setComfortConnection(false);

        int repeat = 2;
        int tariff;
        // create customer with tariff 300
        tariff = 300;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        // create customer with tariff 400
        tariff = 400;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        // create customer with tariff 600
        tariff = 600;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        System.out.println("List of Created Customers:");
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

    @Test
    public void tcE2eActivateCustomerPortfolio17MigDghIspType() {
        cupool = new ArrayList<>();

        Portfolios portfolio = Portfolios.Seventeen_Mig;
        RouterTypes router = RouterTypes.Platinium_2017;
        WaipuTypes waipu = WaipuTypes.Null;

        MainProduct main = new MainProduct();
        main.setWaipuStickNumber(2);
        main.setSecondLine(false);
        main.setComfortConnection(false);

        int tariff;

        int repeat = 2;
        // create customer with tariff 300
        tariff = 300;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        // create customer with tariff 400
        tariff = 400;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);

        // create customer with tariff 600
        tariff = 600;
        customerCreation(repeat, portfolio, tariff, router, waipu, false);


        System.out.println("List of Created Customers:");
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

    @Test
    public void tcE2eActivateCustomerPortfolio15DghIspType() {
        cupool = new ArrayList<>();

        Portfolios portfolio = Portfolios.Fifteen;
        RouterTypes router = RouterTypes.Platinium_2015;
        WaipuTypes waipu = WaipuTypes.Perfect_5_2018;

        MainProduct main = new MainProduct();
        main.setWaipuStickNumber(2);
        main.setComfortConnection(false);
        main.setSecondLine(false);

        int repeat = 2;
        // create customer with tariff 300
        int tariff = 100;
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

    /**
     * <b>[Test Method]</b> - Customer creation<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to perform<br>
     * creation of customer<br>
     * It is called from main testing methods
     */
    public void customerCreation(int repeat, Portfolios portfolio, int tariff, RouterTypes router, WaipuTypes waipu, boolean greater23) {
        for (int i=0; i<repeat; i++) {
            initTestingResource(portfolio, tariff, router, waipu, greater23);

            // perform customer's activation process
            createCustomer.flowSfCustomerCreation(portfolio, tariff, waipu, router);
            // add created customer to array
            cupool.add(resource.getCustomerId());
        }
    }

    /**
     * <b>[Test Method]</b> - Sign Up<br>
     * <br><i>Test Method functionality:</i><br>
     * Purpose of the method is to perform signing VZF<br>
     * It is called from main testing methods
     */
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

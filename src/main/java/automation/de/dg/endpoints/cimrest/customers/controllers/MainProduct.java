package automation.de.dg.endpoints.cimrest.customers.controllers;

import automation.de.dg.enumation.*;
import automation.de.dg.enumation.Portfolios;
import automation.de.dg.enumation.dgbIsp.DgbTariffNineteen;
import automation.de.dg.enumation.dghIsp.TariffEighteen;
import automation.de.dg.enumation.dghIsp.TariffFifteen;
import automation.de.dg.enumation.dghIsp.TariffSeventeen;
import automation.de.dg.enumation.dghIsp.TariffTwentythree;
import automation.de.dg.enumation.newIsp.NewTariffEighteen;
import automation.de.dg.enumation.newIsp.NewTariffSeventeen;
import automation.de.dg.utils.config.TestingResource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.Objects;

/**
 * <b>RestAPI : Cim Rest Suite/Customer</b> Products List Suite<br>
 *  <i>Class functionality:</i><br>
 *  Class is used to prepare list of products<br>
 *  that is used for POST Customer Creation
 */

public class MainProduct {

    /**
     * <b>[Method]</b> - Set Main Product List<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Main Product<br>
     * used for POST Customer Creation
     * @return JSONObject requestParams
     */

    public static JSONObject mainProduct() {
        JSONObject requestParams = new JSONObject();
        JSONArray attribute = new JSONArray();
        requestParams.put("attributes", attribute);
        requestParams.put("id", getMainProduct());
        requestParams.put("productInstances", productInstance());

        return requestParams;
    }

    /**
     * <b>[Method]</b> - Set Product Instance List<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Product Instance<br>
     * based on required Waipu, Router and additional Products
     * @return JSONArray instance
     */

    public static JSONArray productInstance() {
        JSONArray products = getProductList();
        JSONArray instance = new JSONArray();

        for (int i = 0; i < products.length(); i++) {
            int prodId = products.getInt(i);
            JSONObject attribute = new JSONObject();
            JSONArray attr = new JSONArray();
            attribute.put("attributes", attr);
            attribute.put("id", prodId);
            instance.put(attribute);
        }
        if (PostCreateCustomer.getFsecure()) {
            JSONArray fSecure = getFSecureProduct();
            for (int i=0; i<fSecure.length(); i++) {
                instance.put(fSecure.get(i));
            }
            //instance.put(getFSecureProduct());
        }

        return instance;
    }

    /**
     * <b>[Method]</b> - Set Customer Details<br>
     * <i>Method functionality:</i><br>
     * This functionality prepare Customer Details<br>
     * @return JSONArray customer
     */
    public static JSONArray customer() {
        JSONArray customer = new JSONArray();
        JSONObject attribute = new JSONObject();
        attribute.put("key", "emailAddress");
        attribute.put("value", "ratko.zekic11@seavus.com");
        customer.put(attribute);

        attribute = new JSONObject();
        attribute.put("key", "firstname");
        attribute.put("value", "ratko");
        customer.put(attribute);

        attribute = new JSONObject();
        attribute.put("key", "lastname");
        attribute.put("value", "zekic");
        customer.put(attribute);

        return customer;
    }

    /**
     * <b>[Method]</b> - Set Main Product ID<br>
     * <i>Method functionality:</i><br>
     * This functionality define Main Product ID<br>
     * @param portfolio Portfolios
     * @param tariff int
     */
    public void defineMainProduct(Portfolios portfolio, int tariff) {
        JSONArray array = new JSONArray();
        switch (portfolio) {
            case Fifteen -> {
                mainProduct = 10001;
                array.put(10011);
                array.put(10002);
            }
            case Legacy -> {
                mainProduct = 9079;
                array.put(9004);
                array.put(9027);
                array.put(9010);
            }
            case Seventeen -> {
                TestingResource resource = new TestingResource();
                // flow for Customer Type DGH
                if (resource.getCustomerType().equals(CustomerTypes.DGH)) {
                    if (TariffSeventeen.ThreeHundred.option == tariff) {
                        mainProduct = 10185;
                        array.put(10219);
                        array.put(10002);
                        array.put(10063);
                        array.put(10204);
                    } else if (TariffSeventeen.FourHundred.option == tariff) {
                        mainProduct = 10191;
                        array.put(10219);
                        array.put(10002);
                        array.put(10063);
                        array.put(10192);
                    } else if (TariffSeventeen.SixHundred.option == tariff) {
                        mainProduct = 10196;
                        array.put(10002);
                        array.put(10063);
                        array.put(10206);
                        array.put(10219);
                        array.put(10198);
                        array.put(10192);
                    } else {
                        Assert.fail("Invalid tariff " + tariff + " is sent for Portfolio " + portfolio);
                    }
                } // flow for Customer Type New
                else {
                    if (NewTariffSeventeen.NewHundred.option == tariff) {
                        mainProduct = 10201;
                        array.put(10216);
                        array.put(10205);
                        array.put(10133);
                        array.put(10177);
                        array.put(10235);
                        array.put(10220);
                    } else if (NewTariffSeventeen.NewTwoHundred.option == tariff) {
                        mainProduct = 10207;
                        array.put(10217);
                        array.put(10210);
                        array.put(10133);
                        array.put(10177);
                        array.put(10208);
                    } else if (NewTariffSeventeen.NewFiveHundred.option == tariff) {
                        mainProduct = 10212;
                        array.put(10219);
                        array.put(10214);
                        array.put(10133);
                        array.put(10177);
                        array.put(10235);
                        array.put(10222);
                        array.put(10208);
                    }
                }
            }
            case Seventeen_Mig -> {
                if (TariffSeventeen.ThreeHundred.option == tariff) {
                    mainProduct = 10255;
                    array.put(10204);
                    array.put(10063);
                } else if (TariffSeventeen.FourHundred.option == tariff) {
                    mainProduct = 10256;
                    array.put(10063);
                    array.put(10192);
                } else if (TariffSeventeen.SixHundred.option == tariff) {
                    mainProduct = 10282;
                    array.put(10192);
                }
                array.put(10002);
            }
            case Eighteen -> {
                TestingResource resource = new TestingResource();
                // flow for Customer Type DGH
                if (resource.getCustomerType().equals(CustomerTypes.DGH)) {
                    if (TariffEighteen.ThreeHundred.option == tariff) {
                        mainProduct = 10290;
                        array.put(10539);
                        array.put(10002);
                        array.put(10063);
                        array.put(10313);
                        array.put(10295);
                        array.put(10524);
                    } else if (TariffEighteen.FourHundred.option == tariff) {
                        mainProduct = 10291;
                        array.put(10002);
                        //array.put(10308);
                        array.put(10343);
                        array.put(10296);
                        array.put(10313);
                        array.put(10315);
                        array.put(10063);
                        array.put(10330);
                        if (PostCreateCustomer.getGreater23()) {
                            array.put(10525);
                        }
                    } else if (TariffEighteen.SixHundred.option == tariff) {
                        mainProduct = 10292;
                        array.put(10002);
                        //array.put(10309);
                        array.put(10313);
                        array.put(10343);
                        array.put(10297);
                        array.put(10063);
                        if (PostCreateCustomer.getGreater23()) {
                            array.put(10525);
                        }
                    } else if (TariffEighteen.EightHundred.option == tariff) {
                        mainProduct = 10538;
                        array.put(10002);
                        array.put(10313);
                        array.put(10298);
                        array.put(10343);
                        array.put(10063);
                        if (PostCreateCustomer.getGreater23()) {
                            array.put(10525);
                        }
                    } else if (TariffEighteen.Thousand.option == tariff) {
                        mainProduct = 10293;
                        array.put(10002);
                        array.put(10063);
                        array.put(10298);
                        array.put(10313);
                        array.put(10343);
                        array.put(10542);
                        if (PostCreateCustomer.getGreater23()) {
                            array.put(10525);
                        }
                    } else if (TariffEighteen.Thousand_Twelve.option == tariff) {
                        mainProduct = 10674;
                        array.put(10002);
                        array.put(10063);
                        array.put(10313);
                    } else {
                        Assert.fail("Invalid tariff " + tariff + " is sent for Portfolio " + portfolio);
                    }
                    if (PostCreateCustomer.getFsecure()) {
                        int mainId = 10275;
                        int prodId = 10277;
                        setFSecureProduct(mainId, prodId);

                        //array.put(10275);
                        //array.put(10277);
                    }
                } // flow for Customer Type New
                else {
                    if (NewTariffEighteen.NewThreeHundred.option == tariff) {
                        mainProduct = 10270;
                        array.put(10279);
                        array.put(10275);
                        array.put(10276);
                        array.put(10277);
                        array.put(10289);
                    } else if (NewTariffEighteen.NewFourHundred.option == tariff) {
                        mainProduct = 10271;
                        array.put(10280);
                        array.put(10275);
                        array.put(10276);
                        array.put(10277);
                        array.put(10289);
                    } else if (NewTariffEighteen.NewSixHundred.option == tariff) {
                        mainProduct = 10272;
                        array.put(10281);
                        array.put(10275);
                        array.put(10276);
                        array.put(10277);
                        array.put(10289);
                    } else if (NewTariffEighteen.NewThousand.option == tariff) {
                        mainProduct = 10273;
                        array.put(10282);
                        array.put(10275);
                        array.put(10276);
                        array.put(10277);
                        array.put(10289);
                    } else if (NewTariffEighteen.NewThousand_Twelve.option == tariff) {
                        mainProduct = 10605;
                        array.put(10275);
                        array.put(10276);
                        array.put(10277);
                    } else {
                        Assert.fail("Invalid tariff " + tariff + " is sent for Portfolio " + portfolio);
                    }
                }
            }
            case Eighteen_Mig -> {
                if (TariffEighteen.ThreeHundred.option == tariff) {
                    mainProduct = 10368;
                    array.put(10002);
                    array.put(10063);
                } else if (TariffEighteen.FourHundred.option == tariff) {
                    mainProduct = 10369;
                    array.put(10002);
                    array.put(10063);
                } else if (TariffEighteen.SixHundred.option == tariff) {
                    mainProduct = 10370;
                    array.put(10002);
                    array.put(10063);
                } else {
                    Assert.fail("Invalid tariff " + tariff + " is sent for Portfolio " + portfolio);
                }
            }
            case Twentythree -> {
                if (TariffTwentythree.Hundred.option == tariff) {
                    mainProduct = 21001;
                    array.put(26001);
                    array.put(22002);
                    array.put(22004);
                    array.put(26013);
                } else if (TariffTwentythree.ThreeHundred.option == tariff) {
                    mainProduct = 21002;
                    array.put(26001);
                    array.put(22002);
                    array.put(22004);
                    array.put(27001);
                    array.put(26014);
                    if (PostCreateCustomer.getGreater23()) {
                        array.put(27002);
                    }
                } else if (TariffTwentythree.FourHundred.option == tariff) {
                    mainProduct = 21008;
                    array.put(27001);
                    if (PostCreateCustomer.getGreater23()) {
                        array.put(27002);
                    }
                } else if (TariffTwentythree.FiveHundred.option == tariff) {
                    mainProduct = 21003;
                    array.put(26001);
                    array.put(22002);
                    array.put(22004);
                    array.put(27001);
                    array.put(26015);
                    //array.put(26010);
                    if (PostCreateCustomer.getGreater23()) {
                        array.put(27002);
                    }
                } else if (TariffTwentythree.Thousand.option == tariff) {
                    mainProduct = 21004;
                    array.put(26001);
                    array.put(22002);
                    array.put(22004);
                    array.put(27001);
                    array.put(26016);
                    if (PostCreateCustomer.getGreater23()) {
                        array.put(27002);
                    }
                } else if (TariffTwentythree.Thousand_Twelve.option == tariff) {
                    mainProduct = 21005;
                    array.put(26001);
                    array.put(22002);
                    array.put(22004);
                } else if (TariffTwentythree.Universaldienst.option == tariff) {
                    mainProduct = 21007;
                    array.put(22004);
                    array.put(26004);
                } else {
                    Assert.fail("Invalid tariff " + tariff + " is sent for Portfolio " + portfolio);
                }
                if (PostCreateCustomer.getFsecure()) {
                    int mainId = 25003;
                    int prodId = 26027;
                    setFSecureProduct(mainId, prodId);

                    //array.put(25003);
                    //array.put(26027);
                }
            }
            case Dgb_Nineteen -> {
                if (DgbTariffNineteen.BusinessThreeHundred.option == tariff) {
                    mainProduct = 10531;
                    array.put(10219);
                    array.put(10224);
                    array.put(10493);
                    array.put(10345);
                    array.put(10551);
                } else if (DgbTariffNineteen.BusinessSixHundred.option == tariff) {
                    mainProduct = 10529;
                    array.put(10228);
                    array.put(10345);
                    array.put(10551);
                } else if (DgbTariffNineteen.BusinessThousand.option == tariff) {
                    mainProduct = 10533;
                    array.put(10228);
                    array.put(10562);
                    array.put(10551);
                    array.put(10230);
                } else {
                    Assert.fail("Invalid tariff " + tariff + " is sent for Portfolio " + portfolio);
                }
                array.put(10218);
                array.put(10353);
            }
            default -> {
                mainProduct = 0;
            }
        }
        setMainProduct(mainProduct, array);
    }

    /**
     * <b>[Method]</b> - Set Router Products<br>
     * <i>Method functionality:</i><br>
     * This functionality define Router Products<br>
     * @param portfolio Portfolios
     * @param routerType RouterTypes
     */
    public void setRouter(Portfolios portfolio, RouterTypes routerType) {
        TestingResource resource = new TestingResource();
        JSONArray list = getProductList();
        versandkostenR = true;
        if (routerType.equals(RouterTypes.Bundle)) {
            if (portfolio.equals(Portfolios.Twentythree)) {
                list.put(24004);
                list.put(24008);
                list.put(24008);
                list.put(23009);
                list.put(26023);
            } else if (portfolio.equals(Portfolios.Eighteen)) {
                list.put(10680);
                list.put(10683);
                list.put(10728);
                list.put(10686);
                //list.put(10709);
                //list.put(10710);
            }
        } else if (routerType.equals(RouterTypes.Wlanplus)) {
            if (portfolio.equals(Portfolios.Twentythree)) {
                list.put(24004);
                list.put(23009);
            } else if (portfolio.equals(Portfolios.Eighteen)) {
                list.put(10680);
                list.put(10728);
            } else if (portfolio.equals(Portfolios.Fifteen)) {
                list.put(10680);
                list.put(10728);
            }
        } else if (routerType.equals(RouterTypes.Fritzbox)) {
            if (portfolio.equals(Portfolios.Twentythree)) {
                list.put(24001);
                list.put(23010);
            } else if (portfolio.equals(Portfolios.Eighteen)) {
                list.put(10581);
                list.put(10327);
            }
        } else if (routerType.equals(RouterTypes.Ker)) {
            if (portfolio.equals(Portfolios.Twentythree)) {
                list.put(24011);
            } else {
                if (resource.getCustomerType().equals(CustomerTypes.DGH)) {
                    list.put(10070);
                    if (getComfortConnection()) {
                        list.put(10222);
                    }
                    if (getSecondLine()) {
                        list.put(10018);
                    }
                } else if (portfolio.equals(Portfolios.Seventeen)) {
                    list.put(10182);
                } else {
                    list.put(10354);
                }
            }
            versandkostenR = false;
        } else if (routerType.equals(RouterTypes.Ker_with_Telephone)) {
            list.put(10285);
            versandkostenR = false;
        } else if (routerType.equals(RouterTypes.Titanium)) {
            if (portfolio.equals(Portfolios.Seventeen) || portfolio.equals(Portfolios.Fifteen) || portfolio.equals(Portfolios.Seventeen_Mig)) {
                list.put(10010);
            } else {
                list.put(8146);
            }
            versandkostenR = false;
        } else if (routerType.equals(RouterTypes.Classic_2018)) {
            if (portfolio.equals(Portfolios.Seventeen)) {
                list.put(10187);
            } else {
                if (resource.getCustomerType().equals(CustomerTypes.DGH)) {
                    list.put(10431);
                } else {
                    list.put(10307);
                }
            }
        } else if (routerType.equals(RouterTypes.Classic_Mig_2018)) {
            list.put(10486);
            list.put(10466);
        } else if (routerType.equals(RouterTypes.Premium_2018)) {
            if (portfolio.equals(Portfolios.Seventeen)) {
                list.put(10187);
            } else if (portfolio.equals(Portfolios.Fifteen)) {
                list.put(10069);
                list.put(10043);
            } else {
                if (resource.getCustomerType().equals(CustomerTypes.DGH)) {
                    list.put(10420);
                    list.put(10327);
                } else {
                    list.put(10308);
                    list.put(10328);
                }
            }
        } else if (routerType.equals(RouterTypes.Basic_2018)) {
            if (resource.getCustomerType().equals(CustomerTypes.DGH)) {
                list.put(10339);
            } else {
                list.put(10306);
            }
        } else if (routerType.equals(RouterTypes.Ker_Titanium)) {
            list.put(10039);
            versandkostenR = false;
            if (getSecondLine()) {
                list.put(10018);
            }
        } else if (routerType.equals(RouterTypes.Platinium_2015)) {
            list.put(10069);
            versandkostenR = false;
            /*if (portfolio.equals(Portfolios.Seventeen)) {
                versandkostenR = true;
            } else {
                versandkostenR = false;
            }*/
        } else if (routerType.equals(RouterTypes.Platinium_2017)) {
            list.put(10187);
        } else if (routerType.equals(RouterTypes.Fast_2017)) {
            list.put(10442);
            if (portfolio.equals(Portfolios.Seventeen)) {
                versandkostenR = true;
            } else {
                versandkostenR = false;
            }
        } else if (routerType.equals(RouterTypes.Fast_Ersatzgerat_2017)) {
            list.put(10457);
        } else {
            versandkostenR = false;
        }
    }

    /**
     * <b>[Method]</b> - Set Waipu Products<br>
     * <i>Method functionality:</i><br>
     * This functionality define Waipu Products<br>
     * @param portfolio Portfolios
     * @param waipuType WaipuTypes
     */
    public void setWaipu(Portfolios portfolio, WaipuTypes waipuType) {
        JSONArray list = getProductList();
        versandkostenW = true;
        if (waipuType.equals(WaipuTypes.Null)) {
            versandkostenW = false;
        } else {
            if (waipuType.equals(WaipuTypes.Perfect)) {
                if (portfolio.equals(Portfolios.Eighteen)) {
                    list.put(10752);
                    list.put(10753);
                    list.put(10757);
                    list.put(10759);
                } else if (portfolio.equals(Portfolios.Twentythree)) {
                    list.put(25002);
                    list.put(26026);
                    list.put(24012);
                    list.put(24012);
                }
            } else if (waipuType.equals(WaipuTypes.Comfort)) {
                if (portfolio.equals(Portfolios.Eighteen)) {
                    list.put(10750);
                    list.put(10751);
                    list.put(10757);
                    list.put(10759);
                } else if (portfolio.equals(Portfolios.Twentythree)) {
                    list.put(25001);
                    list.put(26025);
                    list.put(24012);
                }
            } else {
                /*list.put(10065);
                list.put(10004);
                list.put(10006);
                list.put(10021);*/
                list.put(10757);
                if (waipuType.equals(WaipuTypes.Perfect_5_2018)) {
                    list.put(10754);
                    //list.put(10757);
                } else if (waipuType.equals(WaipuTypes.Perfect_10_2018)) {
                    list.put(10755);
                    //list.put(10757);
                } else if (waipuType.equals(WaipuTypes.Perfect_Plus_15_2018)) {
                    list.put(10756);
                    //list.put(10757);
                }
            }
            if (portfolio.equals(Portfolios.Fifteen) || portfolio.equals(Portfolios.Seventeen)) {
                versandkostenW = false;
            }
        }
    }

    /**
     * <b>[Method]</b> - Set Shipping costs PRoducts<br>
     * <i>Method functionality:</i><br>
     * This functionality define Shipping costs.<br>
     * Costs are invisible after Waipu is null and Router is Ker
     * @param portfolio Portfolios
     */
    public void setShippingCost(Portfolios portfolio) {
        JSONArray list = getProductList();
        TestingResource resource = new TestingResource();

        if (resource.getCustomerType().equals(CustomerTypes.DGH)) {
            if (versandkostenW || versandkostenR) {
                if (portfolio.equals(Portfolios.Twentythree)) {
                    list.put(22013);
                } else if (portfolio.equals(Portfolios.Eighteen)) {
                    list.put(10345);
                } else if (portfolio.equals(Portfolios.Seventeen)) {
                    list.put(10214);
                } /*else if (portfolio.equals(Portfolios.Fifteen)) {
                    list.put(10034);
                }*/
            }
        } else {
            if (versandkostenW || versandkostenR) {
                list.put(10339);
            }
        }
    }

    /**
     * <b>[Method]</b> - Set Porting Products for DGH ISP Type<br>
     * <i>Method functionality:</i><br>
     * This functionality define Porting Products<br>
     * @param portfolio Portfolios
     * @param tariff int
     */
    public void setPorting(Portfolios portfolio, int tariff) {
        JSONArray list = getProductList();
        if (Objects.requireNonNull(portfolio) == Portfolios.Fifteen) {
            if (TariffFifteen.Basic.option == tariff) {
                list.put(10044);
            }
        } else if (Objects.requireNonNull(portfolio) == Portfolios.Seventeen) {
            if (TariffSeventeen.ThreeHundred.option == tariff) {
                list.put(10189);
            } else if (TariffSeventeen.FourHundred.option == tariff) {
                list.put(10194);
            } else if (TariffSeventeen.SixHundred.option == tariff) {
                list.put(10198);
            }
        } else if (Objects.requireNonNull(portfolio) == Portfolios.Eighteen) {
            if (TariffEighteen.ThreeHundred.option == tariff) {
                list.put(10307);
            } else if (TariffEighteen.FourHundred.option == tariff) {
                list.put(10308);
            } else if (TariffEighteen.SixHundred.option == tariff) {
                list.put(10309);
            } else if (TariffEighteen.Thousand.option == tariff) {
                list.put(10310);
            }
        } else if (Objects.requireNonNull(portfolio) == Portfolios.Twentythree) {
            if (TariffTwentythree.Hundred.option == tariff) {
                list.put(26008);
            } else if (TariffTwentythree.ThreeHundred.option == tariff) {
                list.put(26009);
            } else if (TariffTwentythree.FiveHundred.option == tariff) {
                list.put(26010);
            } else if (TariffTwentythree.Thousand.option == tariff) {
                list.put(26011);
            }
        }
    }

    /**
     * <b>[Method]</b> - Set Porting Products for New ISP Type<br>
     * <i>Method functionality:</i><br>
     * This functionality define Porting Products<br>
     * @param portfolio Portfolios
     * @param tariff int
     */

    public void setPortingForIspType(Portfolios portfolio, int tariff) {
        JSONArray list = getProductList();

        if (Objects.requireNonNull(portfolio) == Portfolios.Eighteen) {
            if (NewTariffEighteen.NewThreeHundred.option == tariff) {
                list.put(10284);
            } else if (NewTariffEighteen.NewFourHundred.option == tariff) {
                list.put(10285);
            } else if (NewTariffEighteen.NewSixHundred.option == tariff) {
                list.put(10286);
            } else if (NewTariffEighteen.NewThousand.option == tariff) {
                list.put(10287);
            }
        } /*else if (Objects.requireNonNull(portfolio) == Portfolios.Seventeen) {
            if (NewTariffSeventeen.NewHundred.option == tariff) {
                list.put(10216);
            } else if (NewTariffSeventeen.NewTwoHundred.option == tariff) {
                list.put(10217);
            } else if (NewTariffSeventeen.NewFiveHundred.option == tariff) {
                list.put(10219);
            }
        }*/
    }

    /**
     * <b>[Method]</b> - Set Waipu Sticks<br>
     * <i>Method functionality:</i><br>
     * This functionality define Waipu Sticks Products<br>
     */
    public void setWaipuProducts(Portfolios portfolio) {
        JSONArray list = getProductList();
        int number = getWaipuStickNumber();

        switch (number) {
            case 1: {
                break;
            }
            case 2: {
                if (portfolio.equals(Portfolios.Twentythree)) {
                    list.put(24012);
                } else {
                    list.put(10759);
                }
                break;
            }
            case 3: {
                if (portfolio.equals(Portfolios.Twentythree)) {
                    list.put(24012);
                    list.put(24012);
                } else {
                    list.put(10759);
                    list.put(10761);
                }
                break;
            }
            default: {
                break;
            }
        }
    }

    static JSONArray fSecureAttribute = new JSONArray();

    /**
     * <b>[Method]</b> - Set F Secure Product<br>
     * <i>Method functionality:</i><br>
     * This functionality set JSON Object<br>
     * for F Secure Product
     * @param mainId int
     * @param prodId int
     */
    public void setFSecureProduct(int mainId, int prodId) {
        fSecureAttribute = new JSONArray();

        JSONObject mainProduct = new JSONObject();
        JSONObject freeProduct = new JSONObject();
        JSONArray instance = new JSONArray();

        freeProduct.put("attributes", new JSONArray());
        freeProduct.put("id", prodId);
        fSecureAttribute.put(freeProduct);

        JSONObject attribute = new JSONObject();
        // add email part
        attribute.put("key", "emailAddress");
        attribute.put("value", "darko.petrovic11@seavus.com");
        instance.put(attribute);

        // add first name part
        attribute = new JSONObject();
        attribute.put("key", "firstname");
        attribute.put("value", "Darko");
        instance.put(attribute);

        // add last name part
        attribute = new JSONObject();
        attribute.put("key", "lastname");
        attribute.put("value", "Petroni");
        instance.put(attribute);

        mainProduct.put("attributes", instance);
        mainProduct.put("id", mainId);

        fSecureAttribute.put(mainProduct);
    }

    public static JSONArray getFSecureProduct() {
        return fSecureAttribute;
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                             Initialization resource part
    //----------------------------------------------------------------------------------------------------------------//


    static int mainProduct;
    static JSONArray productList;
    static boolean versandkostenW = true;
    static boolean versandkostenR = true;
    static int waipuStickNumber;
    static Boolean secondLine = false;
    static Boolean comfortConnection = false;

    public void setMainProduct(int prod, JSONArray list) {
        mainProduct = prod;
        productList = list;
    }

    public static int getMainProduct() {
        return mainProduct;
    }

    public static JSONArray getProductList() {
        return productList;
    }

    public void setWaipuStickNumber(int number) {
        waipuStickNumber = number;
    }

    public int getWaipuStickNumber() {
        return waipuStickNumber;
    }

    public void setSecondLine(Boolean isTrue) {
        secondLine = isTrue;
    }

    public Boolean getSecondLine() {
        return secondLine;
    }

    public void setComfortConnection(Boolean isTrue) {
        comfortConnection = isTrue;
    }

    public Boolean getComfortConnection() {
        return comfortConnection;
    }
}

package automation.de.dg.database.dgh.customer;

import automation.database.DataBaseConnection;
import automation.de.dg.database.manipulation.queries.DghUpdateQueries;
import automation.de.dg.database.manipulation.queryManipulation.DghQueryManipulation;
import automation.de.dg.database.manipulation.queryManipulation.DghUpdateQueryManipulation;
import automation.de.dg.enumation.AddressTypes;
import automation.de.dg.enumation.CustomerStatuses;
import automation.de.dg.utils.config.TestingResource;
import automation.enumaration.DatabaseNames;
import automation.utilities.Log;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>DE.DG : Database/DGH</b> Updating Customer Data<br>
 * <i>Class functionality:</i><br>
 *  Class is used for updating customers on DGH db
 */

public class SuiteUpdatingCustomerData {


    /**
     * <b>[Test Method]</b> - Update Group ID<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs updating Group ID
     * @param custId int
     */

    public void updateGroupId(int custId) {
        Log.info("Updating customer's Project");
        try {
            // initialize classes from interest
            DghQueryManipulation dghQueries = new DghQueryManipulation();
            DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
            TestingResource resource = new TestingResource();
            // step - find random group id
            int groupId = 0;
            int counter = 0;
            // perform loop action to find group id different from current customer's group id
            do {
                // get current customer's data
                dghQueries.getCustomer(custId);
                // find some random group id
                //groupId = dghQueries.getGroupId();
                if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.DGH)) {
                    groupId = 10330;
                } else if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.NEW)) {
                    groupId = 100397;
                }

                // counter to prevent infinitive loop
                counter++;
            } while (groupId == resource.getCustomerGroupId() && counter < 10);
            //groupId = 2010535608;

            int updatedRows = 0;
            // check if customer id or group id are invalid
            if (custId < 1 || groupId < 1) {
                Assert.fail("Customer ID or Group ID cannot be invalid");
            } else {
                System.out.println("Found random Group ID: " + groupId);
                updatedRows = dghUpdateQueries.updateGroupId(custId, groupId);
            }

            // check if at least one row is updated
            if (updatedRows > 0) {
                // get current customer's data
                dghQueries.getCustomer(custId);
                // check if group is updated
                if (groupId != resource.getCustomerGroupId()) {
                    Assert.fail("For Customer ID " + custId + ", Group ID " + groupId + " is not updated");
                } else {
                    System.out.println("Successfully updated Group ID to " + groupId + " for Customer ID " + custId);
                }
            } else {
                Assert.fail("For Customer ID " + custId + ", Group ID is not updated");
            }
        } catch (RuntimeException e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }


    /**
     * <b>[Test Method]</b> - Update UC Status<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs updating Unit Check status
     * @param custId int
     */

    public void updateUcStatus(int custId, AddressTypes addType, int addStatus) {
        System.out.println("Update UC-Status");
        try {
            // initialize classes from interest
            DghQueryManipulation dghQueries = new DghQueryManipulation();
            DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
            TestingResource resource = new TestingResource();

            int updatedRows = 0;
            // check if customer id or uc-status id are invalid
            if (custId < 1 || addStatus < 1) {
                Assert.fail("Customer ID or UC-Status ID cannot be invalid");
            } else {
                updatedRows = dghUpdateQueries.updateUcStatus(custId, addStatus, addType.option);
            }

            if (updatedRows > 0) {
                dghQueries.getCustomerAddress(custId, addType);
                if (addStatus != resource.getCustomerAddressStatusId()) {
                    Assert.fail("For Customer ID " + custId + ", UC Status is " + addStatus + " and not updated");
                } else {
                    System.out.println("Successfully updated UC-Status to " + addStatus + " for Customer ID " + custId);
                }
            } else {
                Assert.fail("For Customer ID " + custId + ", UC Status is not updated");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Execute Activation Product Procedure<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs executing<br>
     * activating product procedure
     * @param custId int
     */

    public void executeActivationProductProcedure(int custId) {
        Log.info("Execute Procedure to activate Products");
        // initialize classes from interest
        TestingResource resource = new TestingResource();
        try {
            // initialize classes from interest
            DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
            dghUpdateQueries.executeProductActivation(resource.getContractId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Update Customer Status<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs updating Customer Status
     * @param custId int
     */

    public void updateContract(int custId) {
        // initialize classes from interest
        DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
        Log.info("Updating customer's contract");
        try {
            // check if customer id is invalid
            if (custId > 1) {
                // get current customer's status
                dghUpdateQueries.updateContract(custId);
            } else {
                Assert.fail("Customer ID cannot be invalid");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Update Customer Status<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs updating Customer Status
     * @param custId int
     */

    public void updateCustomerStatus(int custId) {
        // initialize classes from interest
        DghQueryManipulation dghQueries = new DghQueryManipulation();
        DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
        TestingResource resource = new TestingResource();
        Log.info("Updating customer's status");
        try {
            // check if customer id is invalid
            if (custId > 1) {
                // get current customer's status
                dghQueries.getCustomer(custId);
                // check if customer status is 10003 AEB_verschickt
                if (resource.getCustomerStatusId() == 10005) {
                    dghUpdateQueries.updateCustomerStatus(custId);
                } else if (resource.getCustomerStatusId() == 10003) {
                    DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.AB_zu_verschicken.option));
                    Thread.sleep(500);
                    DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.AB_verschickt.option));
                    Thread.sleep(500);
                    dghUpdateQueries.updateCustomerStatus(custId);
                } else {
                    // fail execution since customer status is not in expected status
                    Assert.fail("Customer ID is not in valid status 10005 AB verschickt, but in status " + resource.getCustomerStatusId());
                }
            } else {
                Assert.fail("Customer ID cannot be invalid");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Update Email Status<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs updating Customer Status
     * @param custId int
     * @param addType AddressTypes
     */

    public void updateEmailValidation(int custId, AddressTypes addType) {
        // initialize classes from interest
        DghQueryManipulation dghQueries = new DghQueryManipulation();
        DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
        TestingResource resource = new TestingResource();
        Log.info("Validate customer's mail address");
        try {
            // check if customer id is invalid
            if (custId > 1) {
                int count = dghUpdateQueries.updateCustAddressEmailValidation(custId, addType.option);
                // check if row is updated
                if (count > 0) {
                    dghQueries.getCustomerAddress(custId, addType);
                    if (resource.getCustomerAddressValidEmail() == 2) {
                        System.out.println("For Customer ID " + custId + ", email is validated");
                    } else {
                        Assert.fail("Customer ID " + custId + " does not have valid email address, but value " + resource.getCustomerAddressValidEmail());
                    }
                }
            } else {
                Assert.fail("Customer ID cannot be invalid");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Activate Phone number<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs activation of phone number
     * @param custID int
     */

    public void activatePhone(int custID) {
        // initialize classes from interest
        DghQueryManipulation dghQueries = new DghQueryManipulation();
        DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
        TestingResource resource = new TestingResource();
        Log.info("Activating customer's phone");
        try {
            dghQueries.getSubscriber(custID);
            int subsId = resource.getSubscriberId();
            if (subsId < 1) {
                Assert.fail("For Customer ID " + custID + ", invalid Subscriber ID is found: " + subsId);
            } else {
                System.out.println("Subs ID for desired phone number is " + subsId);
                int count = dghUpdateQueries.updatePhoneActivation(subsId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * <b>[Test Method]</b> - Activate CO Product<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs activating CO Product
     * @param custID int
     */

    public void activateCoProduct(int custID) {
        // initialize classes from interest
        DghQueryManipulation dghQueries = new DghQueryManipulation();
        DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
        TestingResource resource = new TestingResource();
        Log.info("Activating customer's CO Product");
        try {
            // find CO Products
            // check if customer's router is not KER
            if (!checkCustomerHasKerRouter(custID)) {
                // insert wait element until all backend processes are finished
                System.out.println("Proceeding with 2min waiting timer until all process are performed");

                dghQueries.getCoProducts(custID);
                int[] productId = resource.getCoProductId();

                // counter to prevent infinitive loop
                int counter = 0;
                // Proceed until Router Product is added in Logistic table
                while (productId.length == 0 && counter < 7) {
                    if (counter < 1) {
                        Thread.sleep(1000*60*2);
                    } else if (counter < 3) {
                        Thread.sleep(1000*60);
                    } else if (counter < 5) {
                        Thread.sleep(1000*30);
                    } else {
                        Thread.sleep(1000*10);
                    }

                    dghQueries.getCoProducts(custID);
                    productId = resource.getCoProductId();
                    counter++;
                }


                // insert wait element until all backend processes are finished
                //System.out.println("Proceeding with 2min waiting timer until all process are performed");
                //Thread.sleep(1000*60*2);

                for (int i = 0; i < productId.length; i++) {
                    // update CO Product
                    updateCoProduct(productId[i]);
                    // activate CO Product
                    dghUpdateQueries.activateCoProduct(productId[i]);
                }
            } else {
                Log.info("Customer " + custID + " has KER router");
            }
            dghQueries.getCoProducts(custID);
            int[] productId = resource.getCoProductId();
            // check if customer has CO Products
            if (productId.length > 0) {
                if (!checkCustomerHasKerRouter(custID)) {
                    for (int i = 0; i < productId.length; i++) {
                        // update CO Product
                        //updateCoProduct(productId[i]);
                        // activate CO Product
                        //dghUpdateQueries.activateCoProduct(productId[i]);
                    }
                }
            } else {
                System.out.println("For Customer ID " + custID + ", there are no Product ID in CO Product table");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Activate CO Voice Product<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs activating CO Voice Product
     * @param custID int
     */

    public void activateCoVoiceProduct(int custID) {
        // initialize classes from interest
        DghQueryManipulation dghQueries = new DghQueryManipulation();
        DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
        TestingResource resource = new TestingResource();
        Log.info("Activating customer's CO Voice Product");
        try {
            // find CO Products
            dghQueries.getCovProducts(custID);
            HashMap<String, HashMap<Integer, Integer>> productList = resource.getProductVoiceList();
            // check if list is empty
            if (!productList.isEmpty()) {
                for ( Map.Entry<String, HashMap<Integer, Integer>> entry : productList.entrySet()) {
                    String desc = entry.getKey();
                    Map<Integer, Integer> value = entry.getValue();
                    // check if Product is not HDTV
                    if (!desc.contains("HDTV")) {
                        for (Map.Entry<Integer, Integer> valueSet : value.entrySet()) {
                            System.out.println("Product " + desc + " is in status " + valueSet.getValue());
                            // check if Product status is not Activated
                            if (valueSet.getValue() != 200) {
                                // update CO Product
                                updateCoProduct(valueSet.getKey());
                                // activate CO Voice Product
                                dghUpdateQueries.activateCoVoiceProduct(valueSet.getKey());
                            }
                        }
                    }
                }
            }

            // check if customer has CO Voice Products
            /*int[] productId = resource.getCoVoiceProductId();
            if (productId.length > 0) {
                for (int i = 0; i < productId.length; i++) {
                    // update CO Product
                    updateCoProduct(productId[i]);
                    // activate CO Voice Product
                    dghUpdateQueries.activateCoVoiceProduct(productId[i]);
                }
                //int count = dghUpdateQueries.deliverRouter(productId);
                /*if (count > 0) {
                    System.out.println("For Customer ID " + custID + ", status of Router is changed to delivered");
                }
            } else {
                System.out.println("For Customer ID " + custID + ", there are no Product ID in CO Voice Product table");
            }*/
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Activate CO Product<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs activating CO Product
     * @param prodId int
     */

    private void updateCoProduct(int prodId) {
        System.out.println("Product ID for updating is " + prodId);
        // initialize classes from interest
        DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
        try {
            dghUpdateQueries.deliverRouter(prodId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Update Campoaign<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs updating Campaign
     * @param custId int
     */

    public void updateCampaign(int custId, int campId) {
        // initialize classes from interest
        DghQueryManipulation dghQueries = new DghQueryManipulation();
        DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
        TestingResource resource = new TestingResource();
        System.out.println("Step updating customer's campaign");
        try {
            // check if customer id is invalid
            if (custId > 1) {
                int count = dghUpdateQueries.updateCustCampaign(custId, campId);
                // check if row is updated
                if (count > 0) {
                    dghQueries.getCustomer(custId);
                    int newCampaign = resource.getCustomerCampaignId();
                    if (newCampaign == campId) {
                        System.out.println("For Customer ID " + custId + ", Campaign ID is updated to " + campId);
                    } else {
                        Assert.fail("Campaign ID " + campId + " is not updated for Customer ID " + custId + ". DB written value is " + newCampaign);
                    }
                }
            } else {
                Assert.fail("Customer ID cannot be invalid");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Check Customer has Ker Router<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs checking if Customer has Ker Router
     * @param custID int
     */

    public boolean checkCustomerHasKerRouter(int custID) {
        boolean check = false;
        String description;
        // initialize classes from interest
        DghQueryManipulation dghQueries = new DghQueryManipulation();
        TestingResource resource = new TestingResource();
        try {
            dghQueries.getCoProductsForUpdating(custID);
            HashMap<String, Integer> product = resource.getCoProductIdList();
            for ( Map.Entry<String, Integer> entry : product.entrySet()) {
                String desc = entry.getKey();
                Integer coId = entry.getValue();
                if (desc.toUpperCase().contains("KUNDENEIGENER ROUTER") || desc.toUpperCase().contains("TITANIUM")) {
                    check = true;
                    break;
                }
                System.out.println("Product Description " + desc + " and Product ID " + coId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return check;
    }

}

package automation.de.dg.database.manipulation.queryManipulation;

import automation.database.DataBaseConnection;
import automation.de.dg.database.manipulation.queries.DghQueries;
import automation.de.dg.database.manipulation.queries.DghUpdateQueries;
import automation.de.dg.enumation.AddressTypes;
import automation.de.dg.utils.config.TestingResource;
import automation.enumaration.DatabaseNames;
import automation.utilities.TestDataGenerator;
import org.firebirdsql.jdbc.FBSQLException;
import org.testng.Assert;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 * <b>DATABASE</b> [Queries]: DGH Query Manipulation
 */

public class DghQueryManipulation {

    /**
     * <b>[Method]</b> - Get Customer data<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns Customer data<br>
     * @throws RuntimeException exception
     */
    public void getCustomer(int customer) throws RuntimeException {
        try {
            // execute query
            ResultSet rs = DataBaseConnection.getQueryResponse(DataBaseConnection.getDatabaseName(), DghQueries.getCustomer(customer));
            // storing customer data
            TestingResource resource = new TestingResource();
            // check if query response contains only one line
            if (QueryManipulation.countQueryRows(rs) == 1) {
                // take Customer's Status ID
                resource.setCustomerStatusId(Integer.parseInt(QueryManipulation.returnString(rs, "STATUS_ID", 1)));
                // take Customer's Group ID
                resource.setCustomerGroupId(Integer.parseInt(QueryManipulation.returnString(rs, "GROUP_ID", 1)));
                // take Customer's Campaign ID
                resource.setCustomerCampaignId(Integer.parseInt(QueryManipulation.returnString(rs, "CAMPAIGN_ID", 1)));
            } else {
                Assert.fail("Customer id " + customer + " is not unique in CUSTOMER table DGH database");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get Customer valid Contract<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns Customer valid Contract<br>
     * @throws RuntimeException exception
     */

    public void getCustomerValidContract(int customer) throws RuntimeException {
        try {
            // execute query
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.DGH, DghQueries.getCustomerContract(customer));
            // storing customer data
            TestingResource resource = new TestingResource();
            // check if query response contains only one line
            if (QueryManipulation.countQueryRows(rs) == 1) {
                // take Customer's Status ID
                resource.setContractId(Integer.parseInt(QueryManipulation.returnString(rs, "CO_ID", 1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get House ID<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns Customer data<br>
     * @throws RuntimeException exception
     */
    public void getCustomerAddress(int custId, AddressTypes addType) throws RuntimeException {
        try {
            // execute query
            ResultSet rs = DataBaseConnection.getQueryResponse(DataBaseConnection.getDatabaseName(), DghQueries.getCustomerAddress(custId, addType.option));
            // check if empty response is returned
            if (QueryManipulation.countQueryRows(rs) < 1) {
                Assert.fail("For customer " + custId + " there is no record for request address type " + addType);
            } else {
                int status = Integer.parseInt(QueryManipulation.returnString(rs, "STATUS_ID", 0));
                int validEmail = 0;
                // check if email address is validate
                if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.DGH)) {
                    if (QueryManipulation.returnString(rs, "DGH_EMAIL_VALID", 1) != null) {
                        validEmail = Integer.parseInt(QueryManipulation.returnString(rs, "DGH_EMAIL_VALID", 0));
                    }
                } else if (DataBaseConnection.getDatabaseName().equals(DatabaseNames.NEW)) {
                    if (QueryManipulation.returnString(rs, "NEW_EMAIL_VALID", 1) != null) {
                        validEmail = Integer.parseInt(QueryManipulation.returnString(rs, "NEW_EMAIL_VALID", 0));
                    }
                }

                TestingResource resource = new TestingResource();
                // save status id
                resource.setCustomerAddressStatusId(status);
                resource.setCustomerAddressValidEmail(validEmail);
            }
        } catch (NullPointerException ne) {
            throw new RuntimeException(ne);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get Group ID<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns Group ID<br>
     * @return groupId int
     * @throws RuntimeException exception
     */
    public int getGroupId() throws RuntimeException {
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.DGH, DghQueries.getGroups);
            // get some random row
            int row = TestDataGenerator.generateRandomNumber(0, QueryManipulation.countQueryRows(rs)-1);
            // find some random group ID
            int groupId = Integer.parseInt(QueryManipulation.returnString(rs, "GROUP_ID", row));
            return groupId;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get Subscriber<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get Subscriber data<br>
     * @throws RuntimeException exception
     */

    public void getSubscriber(int custId) {
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DataBaseConnection.getDatabaseName(), DghQueries.getSubscriber(custId));
            // check if empty row is returned
            if (QueryManipulation.countQueryRows(rs) < 1) {
                Assert.fail("For customer " + custId + " there is no record in Subscriber table");
            } else {
                int subsId = Integer.parseInt(QueryManipulation.returnString(rs, "SUBS_ID", 1));
                int subStatusId = Integer.parseInt(QueryManipulation.returnString(rs, "STATUS_ID", 1));
                TestingResource resource = new TestingResource();
                // save Subs ID
                resource.setSubscriberId(subsId);
                // save Sub Status ID
                resource.setSubscriberStatusId(subStatusId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get Subscriber<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get Subscriber data<br>
     * @param custID int
     * @throws Exception exception
     */

    public void getSubscriberForUpdating(int custID) throws Exception {
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.DGH, DghQueries.getSubscriberForUpdating(custID));
            // check if empty row is returned
            int counter = QueryManipulation.countQueryRows(rs);
            if (counter < 1) {
                Assert.fail("For customer " + custID + " there is no record in SUBSCRIBER table");
            } else {
                int[] product = new int[counter];
                for (int i = 0; i < counter; i++) {
                    // taking voice product from database
                    product[i] = Integer.parseInt(QueryManipulation.returnStringForUpdate(rs, "SUBS_ID", i));
                }
                // save Product ID
                TestingResource resource = new TestingResource();
                resource.setSubscriberList(product);
            }
        } catch (FBSQLException fe) {
            throw new FBSQLException(fe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get CO Product<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get CO Product data<br>
     * @throws RuntimeException exception
     */

    public void getCoProducts(int custID) {
        try {
            System.out.println("Query for checking:");
            System.out.println(DghQueries.getCoProducts(custID));
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DataBaseConnection.getDatabaseName(), DghQueries.getCoProducts(custID));
            // check if empty row is returned
            int[] product = new int[0];
            //HashMap<String, Integer> product = new HashMap<>();
            if (QueryManipulation.countQueryRows(rs) < 1) {
                //Assert.fail("For customer " + custID + " there is no record in CO_PRODUCTS table");
            } else {
                product = new int[QueryManipulation.countQueryRows(rs)];
                for (int i = 0; i < QueryManipulation.countQueryRows(rs); i++) {
                    product[i] = Integer.parseInt(QueryManipulation.returnStringForUpdate(rs, "CO_PRODUCT_ID", i));
                    //product.put(QueryManipulation.returnStringForUpdate(rs, "DESCRIPTION", i), Integer.parseInt(QueryManipulation.returnStringForUpdate(rs, "CO_PRODUCT_ID", i)));
                }
            }
            TestingResource resource = new TestingResource();
            // save Product ID
            resource.setCoProductId(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get CO Product Description<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get description of CO Product<br>
     * @throws RuntimeException exception
     */

    public String getCoProductDescription(int productId) {
        String response;
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.DGH, DghQueries.getCoProductDescription(productId));
            response = QueryManipulation.returnString(rs, "DESCRIPTION", 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return response;
    }

    /**
     * <b>[Method]</b> - Get CO Products for updating<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get CO Product data<br>
     * found product will be updated
     * @param custID int
     * @throws Exception exception
     */

    public void getCoProductsForUpdating(int custID) throws Exception {
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DataBaseConnection.getDatabaseName(), DghQueries.getCoProductsUpdating(custID));
            // check if empty row is returned
            int counter = QueryManipulation.countQueryRows(rs);
            if (counter < 1) {
                System.out.println("For customer " + custID + " there is no record in CO_PRODUCTS table");
            } else {
                //int[] product = new int[counter];
                HashMap<String, Integer> product = new HashMap<>();
                for (int i = 0; i < counter; i++) {
                    // taking CO Product from database
                    //product[i] = Integer.parseInt(QueryManipulation.returnStringForUpdate(rs, "CO_PRODUCT_ID", i));
                    product.put(QueryManipulation.returnStringForUpdate(rs, "DESCRIPTION", i), Integer.parseInt(QueryManipulation.returnStringForUpdate(rs, "CO_PRODUCT_ID", i)));
                }
                // save Product ID
                TestingResource resource = new TestingResource();
                resource.setCoProductIdList(product);
            }
        } catch (FBSQLException fe) {
            throw new FBSQLException(fe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get CO One-time Products for updating<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get one-time CO Product data<br>
     * found product will be updated
     * @param custID int
     * @throws Exception exception
     */

    public void getOnetimeCoProductsForUpdating(int custID) throws Exception {
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.DGH, DghQueries.getOneTimeCoProductsUpdating(custID));
            // check if empty row is returned
            int counter = QueryManipulation.countQueryRows(rs);
            if (counter < 1) {
                System.out.println("For customer " + custID + " there is no record in CO_PRODUCTS table regarding one-time products");
            } else {
                int[] product = new int[counter];
                for (int i = 0; i < counter; i++) {
                    // taking one-time CO Product from database
                    product[i] = Integer.parseInt(QueryManipulation.returnStringForUpdate(rs, "CO_PRODUCT_ID", i));
                }
                // save Product ID
                TestingResource resource = new TestingResource();
                resource.setOneTimeCoProductId(product);
            }
        } catch (FBSQLException fe) {
            throw new FBSQLException(fe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get COV Product<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get CO Voice Product data<br>
     * @throws RuntimeException exception
     */

    public void getCovProducts(int custID) {
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.DGH, DghQueries.getCoVoiceProducts(custID));
            // check if empty row is returned
            int[] product = new int[0];
            HashMap<String, HashMap<Integer, Integer>> productMap = new HashMap<>();

            if (QueryManipulation.countQueryRows(rs) < 1) {
                //Assert.fail("For customer " + custID + " there is no record in CO_PRODUCTS table");
            } else {
                product = new int[QueryManipulation.countQueryRows(rs)];
                for (int i = 0; i < QueryManipulation.countQueryRows(rs); i++) {
                    HashMap<Integer, Integer> innerMap = new HashMap<>();
                    product[i] = Integer.parseInt(QueryManipulation.returnStringForUpdate(rs, "COV_PRODUCT_ID", i));
                    innerMap.put(Integer.parseInt(QueryManipulation.returnStringForUpdate(rs, "COV_PRODUCT_ID", i)), Integer.parseInt(QueryManipulation.returnStringForUpdate(rs, "STATUS_ID", i)));
                    productMap.put(QueryManipulation.returnStringForUpdate(rs, "DESCRIPTION", i), innerMap);
                }
                //int productId = Integer.parseInt(QueryManipulation.returnString(rs, "CO_PRODUCT_ID", 1));
            }
            TestingResource resource = new TestingResource();
            // save Product ID
            resource.setCoVoiceProductId(product);
            resource.setCoVoiceProductIdList(productMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get CO Voice Products for updating<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get CO Voice Product data<br>
     * found products will be updated
     * @param custID int
     * @throws Exception exception
     */

    public void getCoVoiceProducts(int custID) throws Exception {
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.DGH, DghQueries.getCoVoiceProductsUpdating(custID));
            // check if empty row is returned
            int counter = QueryManipulation.countQueryRows(rs);
            if (counter < 1) {
                System.out.println("For customer " + custID + " there is no record in CO_VOICEDATA_PRODUCTS table");
            } else {
                int[] voiceProduct = new int[counter];
                for (int i = 0; i < counter; i++) {
                    // taking voice product from database
                    voiceProduct[i] = Integer.parseInt(QueryManipulation.returnStringForUpdate(rs, "COV_PRODUCT_ID", i));
                }
                // save Product ID
                TestingResource resource = new TestingResource();
                resource.setCoVoiceProductId(voiceProduct);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get CO One-time CO Voice Products for updating<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get one-time CO Voice Product data<br>
     * found product will be updated
     * @param custID int
     * @throws Exception exception
     */

    public void getOnetimeCovProductsForUpdating(int custID) throws Exception {
        try {
            // execute
            ResultSet rs = DataBaseConnection.getQueryResponse(DatabaseNames.DGH, DghQueries.getOneTimeCovProductsUpdating(custID));
            // check if empty row is returned
            int counter = QueryManipulation.countQueryRows(rs);
            if (counter < 1) {
                System.out.println("For customer " + custID + " there is no record in CO_VOICEDATA_PRODUCTS table regarding one-time products");
            } else {
                int[] product = new int[counter];
                for (int i = 0; i < counter; i++) {
                    // taking one-time CO Product from database
                    product[i] = Integer.parseInt(QueryManipulation.returnStringForUpdate(rs, "cov_product_id", i));
                }
                // save Product ID
                TestingResource resource = new TestingResource();
                resource.setOneTimeCovProductId(product);
            }
        } catch (FBSQLException fe) {
            throw new FBSQLException(fe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

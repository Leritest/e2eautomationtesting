package automation.de.dg.database.dgh.customer;

import automation.de.dg.database.manipulation.queryManipulation.DghQueryManipulation;
import automation.de.dg.database.manipulation.queryManipulation.DghUpdateQueryManipulation;
import automation.de.dg.utils.config.TestingResource;
import org.testng.Assert;

/**
 * <b>DE.DG : Database/DGH</b> One time Customer's Product<br>
 * <i>Class functionality:</i><br>
 *  Class is used for deactivating customer's one-time product in DGH db
 */

public class SuiteOneTimeProduct {

    /**
     * <b>[Test Method]</b> - Deactivating of One-time Product<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs deactivating<br>
     * of customer's one-time Product
     */

    public void performDeactivateOnetimeProduct(int cuctId) {
        // initialize classes from interest
        TestingResource resource = new TestingResource();
        try {
            System.out.println("#####################################################################################");
            System.out.println("### Deactivating One-time Product");
            System.out.println("#####################################################################################");
            Thread.sleep(1000*10);
            // getting Customer ID
            int custId = resource.getCustomerId();
            System.out.println("Starting process of back-days for Customer ID: " + custId);
            if (custId > 0) {
                // update co product
                deactivatingOnetimeCoProduct(custId);
                // updating voice data product
                deactivatingOnetimeCovProduct(custId);
            } else {
                Assert.fail("Backdays process is aborted due to invalid Customer ID" + custId);
            }
        } catch (NullPointerException ne) {
            throw new RuntimeException(ne);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Deactivating One-time CO Products<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs one-time CO Product deactivation<br>
     * @param custId int
     * @throws NullPointerException
     */
    public void deactivatingOnetimeCoProduct(int custId) {
        System.out.println("Starting processing date for One-time CO Products");
        try {
            // initialize classes from interest
            DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
            TestingResource resource = new TestingResource();
            // find co product ids
            getCoProducts(custId);
            // start changing days
            int[] count = resource.getOneTimeCoProductId();
            if (count != null) {
                System.out.println("Number of products: " + count.length);
                for (int i = 0; i < count.length; i++) {
                    System.out.println("Changing date for Product ID: " + count[i]);
                    Thread.sleep(100);
                    dghUpdateQueries.changingDateOneTimeCoProduct(count[i]);
                }
            } else {
                System.out.println("For Customer ID " + custId + ", there are no one-time Product ID in CO Product table");
            }
            System.out.println("Finished processing date for CO Products");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Deactivating One-time CO Voice Products<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs one-time CO Voice Product deactivation<br>
     * @param custId int
     * @throws NullPointerException
     */
    public void deactivatingOnetimeCovProduct(int custId) {
        System.out.println("Starting processing date for One-time CO Products");
        try {
            // initialize classes from interest
            DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
            TestingResource resource = new TestingResource();
            // find co product ids
            getCovProducts(custId);
            // start changing days
            int[] count = resource.getOneTimeCovProductId();
            if (count != null) {
                System.out.println("Number of products: " + count.length);
                for (int i = 0; i < count.length; i++) {
                    System.out.println("Changing date for Product ID: " + count[i]);
                    Thread.sleep(100);
                    dghUpdateQueries.changingDateOneTimeCovProduct(count[i]);
                }
            } else {
                System.out.println("For Customer ID " + custId + ", there are no one-time Product ID in CO Product table");
            }
            System.out.println("Finished processing date for CO Products");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Get CO Products<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting CO Product<br>
     * @param custId int
     */
    private void getCoProducts(int custId) {
        System.out.println("Gathering CO products");
        try {
            // initialize classes from interest
            DghQueryManipulation dghQueries = new DghQueryManipulation();
            // step gathering voice products
            dghQueries.getOnetimeCoProductsForUpdating(custId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Get CO Voice Products<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting CO Voice Product<br>
     * @param custId int
     */
    private void getCovProducts(int custId) {
        System.out.println("Gathering CO products");
        try {
            // initialize classes from interest
            DghQueryManipulation dghQueries = new DghQueryManipulation();
            // step gathering voice products
            dghQueries.getOnetimeCovProductsForUpdating(custId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

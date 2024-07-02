package automation.de.dg.database.dgh.customer;

import automation.de.dg.database.manipulation.queryManipulation.DghQueryManipulation;
import automation.de.dg.database.manipulation.queryManipulation.DghUpdateQueryManipulation;
import automation.de.dg.enumation.BackDays;
import automation.de.dg.utils.config.TestingResource;
import org.testng.Assert;

/**
 * <b>DE.DG : Database/DGH</b> Back-days Customer Data<br>
 * <i>Class functionality:</i><br>
 *  Class is used for back-days customer's data in DGH db
 */

public class SuiteBackDays {

    /**
     * <b>[Test Method]</b> - Back-days of Customer<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs back-days of Customer<br>
     */

    /*public void performBackDays() throws NullPointerException {
        // initialize classes from interest
        TestingResource resource = new TestingResource();
        try {
            System.out.println("#####################################################################################");
            System.out.println("### Changing days Process");
            System.out.println("#####################################################################################");
            Thread.sleep(1000*10);
            // getting Customer ID
            int custId = resource.getCustomerId();
            // getting back-days
            int days = getBackdays().option;
            System.out.println("Starting process of back-days for Customer ID: " + custId);
            System.out.println("Number of change days is " + days);
            if (custId > 0) {
                // updating voice data product
                backdaysVoiceDataProducts(custId, days);

                // update co product
                backdaysProducts(custId, days);

                // update contract
                backdaysContract(custId, days);

                // update subscriber
                backdaysSubscriber(custId, days);
            } else {
                Assert.fail("Backdays process is aborted due to invalid Customer ID" + custId);
            }
        } catch (NullPointerException ne) {
            throw new RuntimeException(ne);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/


    /**
     * <b>[Test Method]</b> - Change days of CO Voice Product<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs changing days of CO Voice Product<br>
     * @param custId int
     * @param days int
     * @throws NullPointerException
     */
    /*public void backdaysVoiceDataProducts(int custId, int days) throws NullPointerException {
        System.out.println("Starting processing date for CO Voice Products");
        try {
            // initialize classes from interest
            DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
            TestingResource resource = new TestingResource();
            // find voice product ids
            getVoiceDataProducts(custId);
            // start changing days
            int[] count = resource.getCoVoiceProductId();
            if (count != null) {
                System.out.println("Number of products: " + count.length);
                for (int i = 0; i < count.length; i++) {
                    System.out.println("Changing date for Product ID: " + count[i]);
                    Thread.sleep(100);
                    dghUpdateQueries.backdaysVoiceData(count[i], days);
                }
            } else {
                //Assert.fail("Backdays process is aborted due to empty list of CO Voice Products for Customer ID" + custId);
                System.out.println("For Customer ID " + custId + ", there are no Product ID in CO Voice Product table");
            }
            System.out.println("Finished processing date for CO Voice Products");
        } catch (NullPointerException ne) {
            throw new RuntimeException(ne);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    /**
     * <b>[Test Method]</b> - Change days of CO Product<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs changing days of CO Product<br>
     * @param custId int
     * @param days int
     * @throws NullPointerException
     */
   /* public void backdaysProducts(int custId, int days) {
        System.out.println("Starting changing date for CO Products");
        try {
            // initialize classes from interest
            DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
            TestingResource resource = new TestingResource();
            // find voice product ids
            getProducts(custId);
            // start changing days
            int[] count = resource.getCoProductId();
            if (count != null) {
                System.out.println("Number of products: " + count.length);
                for (int i = 0; i < count.length; i++) {
                    System.out.println("Changing date for Product ID: " + count[i]);
                    Thread.sleep(100);
                    dghUpdateQueries.backdaysCoProduct(count[i], days);
                }
            } else {
                //Assert.fail("Backdays process is aborted due to empty list of CO Products for Customer ID" + custId);
                System.out.println("For Customer ID " + custId + ", there are no Product ID in CO Product table");
            }
            System.out.println("Finished processing date for CO Products");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    /**
     * <b>[Test Method]</b> - Change days for Contract<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs changing days of customer's Contract<br>
     * @param custId int
     * @param days int
     * @throws NullPointerException
     */
    /*public void backdaysContract(int custId, int days) {
        System.out.println("Starting changing date for Contract");
        try {
            // initialize classes from interest
            DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
            // start changing days
            if (custId > 0) {
                dghUpdateQueries.backdaysContract(custId, days);
            } else {
                Assert.fail("Backdays process is aborted due to invalid Customer ID" + custId);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Finished processing date for Contract");
    }*/

    /**
     * <b>[Test Method]</b> - Change days of Subscriber<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs changing days of customer's Subscriber<br>
     * @param custId int
     * @param days int
     * @throws NullPointerException
     */
    /*public void backdaysSubscriber(int custId, int days) throws NullPointerException {
        System.out.println("Starting processing date for Subscriber");
        try {
            // initialize classes from interest
            DghUpdateQueryManipulation dghUpdateQueries = new DghUpdateQueryManipulation();
            TestingResource resource = new TestingResource();
            // find voice product ids
            getSubscribers(custId);
            // start changing days
            int[] count = resource.getSubscriberList();
            if (count != null) {
                System.out.println("Number of products: " + count.length);
                for (int i = 0; i < count.length; i++) {
                    System.out.println("Changing date for Subscriber ID: " + count[i]);
                    Thread.sleep(100);
                    dghUpdateQueries.backdaysSubscriber(count[i], days);
                }
            } else {
                Assert.fail("Backdays process is aborted due to empty list of Subscribers for Customer ID " + custId);
            }
            System.out.println("Finished processing date for Subscription");
        } catch (RuntimeException re) {
            throw new RuntimeException(re);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

    /**
     * <b>[Test Method]</b> - Get CO Voice Products<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting CO Voice Product<br>
     * @param custId int
     */
    private void getVoiceDataProducts(int custId) {
        System.out.println("Gathering voice data products");
        try {
            // initialize classes from interest
            DghQueryManipulation dghQueries = new DghQueryManipulation();
            // step gathering voice products
            dghQueries.getCoVoiceProducts(custId);
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
    public void getProducts(int custId) {
        System.out.println("Gathering CO products");
        try {
            // initialize classes from interest
            DghQueryManipulation dghQueries = new DghQueryManipulation();
            // step gathering voice products
            dghQueries.getCoProductsForUpdating(custId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Test Method]</b> - Get Subscribers<br>
     * <br>
     * <i>Test Method functionality:</i><br>
     * This functionality performs getting Subscribers<br>
     * @param custId int
     */
    private void getSubscribers(int custId) {
        System.out.println("Gathering Subscriber");
        try {
            // initialize classes from interest
            DghQueryManipulation dghQueries = new DghQueryManipulation();
            // step gathering voice products
            dghQueries.getSubscriberForUpdating(custId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static BackDays days;

    public void setBackdays(BackDays bd) {
        days = bd;
    }

    public BackDays getBackdays() {
        return days;
    }

}

package automation.de.dg.database.manipulation.queryManipulation;

import automation.database.DataBaseConnection;
import automation.de.dg.database.manipulation.queries.DghUpdateQueries;
import automation.de.dg.enumation.CustomerStatuses;
import automation.de.dg.utils.config.TestingResource;
import automation.enumaration.DatabaseNames;
import automation.utilities.TestDataGenerator;

/**
 * <b>DATABASE</b> [Queries]: DGH Update Query Manipulation
 */

public class DghUpdateQueryManipulation {

    /**
     * <b>[Method]</b> - Update Group ID<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality updates Group ID/Project<br>
     * @param custId int
     * @param groupId int
     * @throws RuntimeException exception
     */
    public int updateGroupId(int custId, int groupId) throws RuntimeException {
        try {
            System.out.println("Query for updating:");
            System.out.println(DghUpdateQueries.updateCustomerGroup(custId, groupId));
            // execute query
            int count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerGroup(custId, groupId));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Update UC-Status<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality updates UC-Status<br>
     * @param custId int
     * @param status int
     * @throws RuntimeException exception
     */

    public int updateUcStatus(int custId, int status, int addType) {
        try {
            System.out.println("Query for updating:");
            System.out.println(DghUpdateQueries.updateCustomerUcStatus(custId, status, addType));
            // execute query
            int count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerUcStatus(custId, status, addType));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * <b>[Method]</b> - Activate Products<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality execute procedure<br>
     * that activates customer's products
     * @param contractID int
     * @throws RuntimeException exception
     */

    public int executeProductActivation(int contractID) {
        try {
            System.out.println("Query for updating:");
            System.out.println(DghUpdateQueries.executeActivateProcedure(contractID));
            // execute query
            int count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.executeActivateProcedure(contractID));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Update Contract<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality update contract<br>
     * @param custId int
     * @throws RuntimeException exception
     */

    public int updateContract(int custId) {
        try {
            System.out.println("Query for updating:");
            System.out.println(DghUpdateQueries.updateContract(custId));
            // execute query
            int count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateContract(custId));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Update Customer Status up to AB verschickt<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality updates Customer Status to AB verschickt<br>
     * @param custId int
     */

    public int updateCustomerStatusUpToAbVerschickt(int custId) {
        try {
            int count;
            // execute query
            TestingResource resource = new TestingResource();
            DghQueryManipulation dghQueries = new DghQueryManipulation();

            // get current customer's status
            dghQueries.getCustomer(custId);
            // check if current status is 10000 to upgrade to 10001
            if (resource.getCustomerStatusId() == 10000) {
                count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.Nachbearbeitung.option));
                Thread.sleep(500);
                System.out.println("Number of updated columns: " + count + " for updating to status " + CustomerStatuses.Nachbearbeitung);

            }
            // change status to 10002
            count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.AEB_zu_verschicken.option));
            Thread.sleep(500);
            System.out.println("Number of updated columns: " + count + " for updating to status " + CustomerStatuses.AEB_zu_verschicken);

            // change status to 10003
            count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.AEB_verschickt.option));
            Thread.sleep(500);
            System.out.println("Number of updated columns: " + count + " for updating to status " + CustomerStatuses.AEB_verschickt);

            // change status to 10004
            count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.AB_zu_verschicken.option));
            Thread.sleep(500);
            System.out.println("Number of updated columns: " + count + " for updating to status " + CustomerStatuses.AB_zu_verschicken);

            // change status to 10005
            count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.AB_verschickt.option));
            Thread.sleep(500);
            System.out.println("Number of updated columns: " + count + " for updating to status " + CustomerStatuses.AB_verschickt);

            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Update Customer Status<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality updates Customer Status to Active<br>
     * @param custId int
     * @throws RuntimeException exception
     */

    public int updateCustomerStatus(int custId) {
        try {
            int count;
            // execute query
            // change status to 10004
            /*count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.AB_zu_verschicken.option));
            Thread.sleep(500);
            System.out.println("Number of updated columns: " + count + " for updating to status " + CustomerStatuses.AB_zu_verschicken);

            // change status to 10005
            count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.AB_verschickt.option));
            Thread.sleep(500);
            System.out.println("Number of updated columns: " + count + " for updating to status " + CustomerStatuses.AB_verschickt);
*/
            // change status to 10008
            count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.Portierung_Ready.option));
            Thread.sleep(500);
            System.out.println("Number of updated columns: " + count + " for updating to status " + CustomerStatuses.Portierung_Ready);

            // change status to 10021
            count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustomerStatus(custId, CustomerStatuses.Aktiv.option));
            Thread.sleep(500);
            System.out.println("Number of updated columns: " + count + " for updating to status " + CustomerStatuses.Aktiv);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Update Email Validation<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality updates email validation in Customer Address<br>
     * @param custId int
     * @param addType int
     * @throws RuntimeException exception
     */

    public int updateCustAddressEmailValidation(int custId, int addType) {
        try {
            System.out.println("Query for updating:");
            System.out.println(DghUpdateQueries.updateCustAddEmailValidation(custId, addType));
            // execute query
            int count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.updateCustAddEmailValidation(custId, addType));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Activate Phone Number<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality updates phone number to become active<br>
     * @param subsId int
     * @throws RuntimeException exception
     */

    public int updatePhoneActivation(int subsId) {
        try {
            // generate random number
            int random = TestDataGenerator.generateRandomNumber(0, 999999999);
            System.out.println("Query for updating:");
            System.out.println(DghUpdateQueries.activatePhone(subsId, random));

            System.out.println("Random number is: +49 211 " + random);
            // execute query
            int count = DataBaseConnection.updateQuery(DataBaseConnection.getDatabaseName(), DghUpdateQueries.activatePhone(subsId, random));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Deliver Router<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality performs router delivery<br>
     * @param productId int
     * @throws RuntimeException exception
     */

    public int deliverRouter(int productId) {
        try {
            // execute query
            int count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.deliverRouter(productId));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Activate CO Product<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality performs CO Product activation<br>
     * @param productId int
     * @throws RuntimeException exception
     */

    public int activateCoProduct(int productId) {
        try {
            // execute query
            int count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.activateCoProduct(productId));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Activate CO Product<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality performs CO Product activation<br>
     * @param productId int
     * @throws RuntimeException exception
     */

    public int activateCoVoiceProduct(int productId) {
        try {
            // execute query
            int count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.activateCovProduct(productId));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Update Campaign<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality updates customer's campaign<br>
     * @param custId int
     * @param campId int
     * @throws RuntimeException exception
     */

    public int updateCustCampaign(int custId, int campId) {
        try {
            // execute query
            int count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.updateCustCampaign(custId, campId));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                                    Backdays
    //----------------------------------------------------------------------------------------------------------------//

    public int backdaysVoiceData(int productId, int days) {
        try {
            // execute query
            int count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.backdaysVoiceProduct(productId, days));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int backdaysCoProduct(int productId, int days) {
        try {
            // execute query
            int count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.backdaysCoProduct(productId, days));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int backdaysContract(int customerId, int days) {
        try {
            // execute query
            int count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.backdaysContract(customerId, days));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int backdaysSubscriber(int subsId, int days) {
        try {
            // execute query
            int count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.backdaysSubscription(subsId, days));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //----------------------------------------------------------------------------------------------------------------//
    //                                                One time products
    //----------------------------------------------------------------------------------------------------------------//

    public int changingDateOneTimeCoProduct(int productId) {
        try {
            // execute query
            int count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.changeOneTimeCoProduct(productId));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int changingDateOneTimeCovProduct(int productId) {
        try {
            // execute query
            int count = DataBaseConnection.updateQuery(DatabaseNames.DGH, DghUpdateQueries.changeOneTimeCovProduct(productId));
            System.out.println("Number of updated columns: " + count);

            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

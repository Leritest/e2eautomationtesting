package automation.utilities;

import automation.de.dg.enumation.CustomerTypes;
import automation.de.dg.enumation.Portfolios;
import automation.de.dg.enumation.RouterTypes;
import automation.de.dg.enumation.WaipuTypes;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * <b>PAGES : UTILITIES</b> [Generator]: Data Generator
 */

public class TestDataGenerator {

    public static String[] houseIds = {"12345ABC-6226-", "23816DEU-2-", "24211BEI-9-", "30900AVX-87-A", "41069JTG-16-1-", "48727FKI-21-C", "49808ANZ-6-A",
            "52152EHJ-3-", "59320DEG-1-B", "63329XXT-4-", "63329ADH-26-", "76767AAA-10-",  "85665BIK-4-"};

    public static String[] firstName = {"James", "John", "Robert", "Michael", "William", "David", "Richard", "Charles", "Joseph", "Thomas", "Christopher",
            "Daniel", "Paul", "Mark", "Donald", "George", "Kenneth", "Steven", "Edward", "Brian", "Ronald", "Anthony", "Kevin", "Jason", "Jeff"};

    public static String[] lastName = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez",
            "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson", "White", "Harris"};


    /**
     * <b>[Method]</b> - Return Random number<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns random number<br>
     * from desired column and row<br>
     *
     * @param low int
     * @param high int
     */

    public static int generateRandomNumber(int low, int high) {
        Random random = new Random();
        int number;
        number = random.nextInt((high - low) + 1) + low;
        return number;
    }

    /**
     * <b>[Method]</b> - Return Current Date<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns current date<br>
     */

    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        String currentDate = dtf.format(now);
        return currentDate;
    }

    /**
     * <b>[Method]</b> - Return Random First Name<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns random first name<br>
     */
    public static String getRandomFirstName() {
        int num = generateRandomNumber(0, firstName.length - 1);
        return firstName[num];
    }

    /**
     * <b>[Method]</b> - Return Random Last Name<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns random last name<br>
     */
    public static String getRandomLastName() {
        int num = generateRandomNumber(0, lastName.length - 1);
        return lastName[num];
    }
    
}

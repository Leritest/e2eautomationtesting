package automation.testbase;

import automation.enumaration.Timer;
import automation.listeners.ExtentListeners;
import automation.utilities.Log;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

/**
 * <b>PAGES : TEST BASE</b> [Page]: Page
 */
public class Page {
    public static WebDriver driver;
    public static Properties config = new Properties();
    public static FileInputStream fis;
    public static WebDriverWait wait;
    public static String browser;
    public static TopMenu topMenu;

    /**
     * <b>[Constructor]</b> - Page<br>
     * <br>
     * <i>Constructor functionality:</i><br>
     * This constructor initializes class attribute<br>
     * <i>All initialization is done in this class <b>[WebDriver, Properties, FileInput, ExcelReader, WebDriverWait, Browser, Menu, WebElement]</b></i><br>
     */
    public Page() {
        if (driver == null) {
            try {
                //Load Config.properties file
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
                config.load(fis);

            } catch (IOException e) {
                e.printStackTrace();
            }

            //Jenkins Browser filter configuration
            if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
                browser = System.getenv("browser");
            } else {
                browser = config.getProperty("browser");
            }

            config.setProperty("browser", browser);
            // mark browser as Edge if executing suite is Service Now
            try {
                if (getExecutingClassName().contains("servicenow")) {
                    browser = "edge";
                }
            } catch (Exception e) {
                browser = config.getProperty("browser");
            }

            //Run the relevant web driver for relevant specified browser as specified in Config.properties file
            if (browser.equals("firefox")) {
                driver = new FirefoxDriver();
            } else if (browser.equals("chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                options.addArguments("--remote-debugging-port=9222");
                options.addArguments("--incognito");
                //options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver = new ChromeDriver(options);
            } else if (browser.equals("edge")){
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--disable-notifications");
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver = new EdgeDriver(options);
            }
        }
    }

    public void setUpDriver() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        topMenu = new TopMenu(driver);
    }

    /**
     * <b>[Method]</b> - Return Driver<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality returns active driver<br>
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * <b>[Method]</b> - Quit Driver<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality Quits opened Driver<br>
     */
    public static void quit() {
        driver.quit();
        driver = null;
    }

    /*-----COMMON KEYWORDS: click, type, getText, getElementsTexts, getElements, waitForPageToLoad-----*/

    /**
     * <b>[Method]</b> - Click on Element<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clicks on element located on page.
     * Has logic to find the element based on the locator that this method receives as input parameter<br>
     *
     * @param elementBy By
     * @throws StaleElementReferenceException   exception
     * @throws ElementClickInterceptedException exception
     * @throws TimeoutException                 exception
     */

    public static void click(By elementBy) throws StaleElementReferenceException, ElementClickInterceptedException, TimeoutException {
        String element = elementBy.toString();
        String[] locator = element.split("\\.");
        String[] locatorType = locator[1].split("[:]");
        int attempts = 0;
        boolean success = false;

        try {
            while (!success && attempts < 3) {
                switch (locatorType[0]) {
                    case "cssSelector":
                        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                        driver.findElement(elementBy).click();
                        ExtentListeners.test.log(Status.PASS, "Clicking on element: " + elementBy);
                        success = true;
                        break;

                    case "xpath":
                        waitForPageToLoad();
                        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                        driver.findElement(elementBy).click();
                        ExtentListeners.test.log(Status.PASS, "Clicking on element: " + elementBy);
                        success = true;
                        break;

                    case "id":
                        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                        driver.findElement(elementBy).click();
                        ExtentListeners.test.log(Status.PASS, "Clicking on element: " + elementBy);
                        success = true;
                        break;

                    default:
                        attempts++;
                }
            }
        } catch (ElementClickInterceptedException ce) {
            throw new RuntimeException(ce);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Send Value to Element<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality sends value to element on page.
     * Has logic to find the element based on the locator that this method receives as input parameter
     * and to pass the value received through second input parameter to the element<br>
     *
     * @param elementBy By
     * @param value     String
     * @throws StaleElementReferenceException   exception
     * @throws ElementClickInterceptedException exception
     * @throws TimeoutException                 exception
     */
    public static void type(By elementBy, String value) throws StaleElementReferenceException, ElementClickInterceptedException, TimeoutException {
        String element = elementBy.toString();
        String[] locator = element.split("\\.");
        String[] locatorType = locator[1].split("[:]");
        int attempts = 0;
        boolean success = false;

        try {
            while (!success && attempts < 3) {
                switch (locatorType[0]) {
                    case "cssSelector":
                        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                        driver.findElement(elementBy).sendKeys(value);
                        ExtentListeners.test.log(Status.PASS, "Populating field: " + elementBy + " with value: " + value);
                        success = true;
                        break;

                    case "xpath":
                        waitForPageToLoad();
                        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                        driver.findElement(elementBy).sendKeys(value);
                        ExtentListeners.test.log(Status.PASS, "Populating field: " + elementBy + " with value: " + value);
                        success = true;
                        break;

                    case "id":
                        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                        driver.findElement(elementBy).sendKeys(value);
                        ExtentListeners.test.log(Status.PASS, "Populating field: " + elementBy + " with value: " + value);
                        success = true;
                        break;

                    default:
                        attempts++;
                }
            }
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * <b>[Method]</b> - Get Text from Element<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get text from element on page.
     * Has logic to find the element based on the locator that this method receives as input parameter,
     * and retrieves value which this element has as text<br>
     *
     * @param elementBy By
     * @throws StaleElementReferenceException   exception
     * @throws ElementClickInterceptedException exception
     * @throws TimeoutException                 exception
     */
    public static String getText(By elementBy) {

        return getElement(elementBy).getText();
    }

    /**
     * <b>[Method]</b> - Get Element<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality provides locating an element on page.<br>
     * Has logic to find the element based on the locator that this method receives as input parameter,<br>
     * and retrieve an element<br>
     *
     * @param elementBy By
     * @throws StaleElementReferenceException   exception
     * @throws ElementClickInterceptedException exception
     * @throws TimeoutException                 exception
     */
    public static WebElement getElement(By elementBy) throws StaleElementReferenceException, ElementClickInterceptedException, TimeoutException {
        String elementString = elementBy.toString();
        String[] locator = elementString.split("\\.");
        String[] locatorType = locator[1].split("[:]");
        int attempts = 0;
        boolean success = false;
        WebElement element = null;

        while (!success && attempts < 3) {
            switch (locatorType[0]) {
                case "cssSelector":
                    wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                    element = driver.findElement(elementBy);
                    ExtentListeners.test.log(Status.PASS, "Taking the element: " + elementBy);
                    success = true;
                    break;

                case "xpath":
                    wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                    element = driver.findElement(elementBy);
                    ExtentListeners.test.log(Status.PASS, "Taking the element: " + elementBy);
                    success = true;
                    break;

                case "id":
                    wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                    element = driver.findElement(elementBy);
                    ExtentListeners.test.log(Status.PASS, "Taking the element: " + elementBy);
                    success = true;
                    break;

                default:
                    attempts++;
            }
        }
        return element;
    }

    /**
     * <b>[Method]</b> - Get Elements<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality provides locating multiple elements which have same locater on page.
     * Has logic to find the elements based on the locator that this method receives as input parameter,
     * and retrieves an element list<br>
     *
     * @param elementBy By
     * @throws StaleElementReferenceException   exception
     * @throws ElementClickInterceptedException exception
     * @throws TimeoutException                 exception
     */
    public static List<WebElement> getElements(By elementBy) throws StaleElementReferenceException, ElementClickInterceptedException, TimeoutException {
        String elementString = elementBy.toString();
        String[] locator = elementString.split("\\.");
        String[] locatorType = locator[1].split("[:]");
        int attempts = 0;
        boolean success = false;
        List<WebElement> elements = new ArrayList<>();

        while (!success && attempts < 3) {
            switch (locatorType[0]) {
                case "cssSelector":
                    wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                    elements = driver.findElements(elementBy);
                    ExtentListeners.test.log(Status.PASS, "Taking the elements: " + elementBy);
                    success = true;
                    break;

                case "xpath":
                    wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                    elements = driver.findElements(elementBy);
                    ExtentListeners.test.log(Status.PASS, "Taking the elements: " + elementBy);
                    success = true;
                    break;

                case "id":
                    wait.until(ExpectedConditions.elementToBeClickable(elementBy));
                    elements = driver.findElements(elementBy);
                    ExtentListeners.test.log(Status.PASS, "Taking the elements: " + elementBy);
                    success = true;
                    break;

                default:
                    attempts++;
            }
        }
        return elements;
    }

    /**
     * <b>[Method]</b> - Get Texts from Elements<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality gets texts from elements on page.
     * Has logic to find the elements based on the locator that this method receives as input parameter,
     * and provides the elements texts as a String List<br>
     *
     * @param elementBy By
     */
    public static List<String> getElementsText(By elementBy) {

        List<String> textList = new ArrayList<>();

        getElements(elementBy).stream().forEachOrdered(element -> {
            if (element.getText().trim().equals("")) {
                textList.add(element.getAttribute("innerText").trim());
            } else {
                textList.add(element.getText().trim());
            }
        });
        ExtentListeners.test.log(Status.PASS, "Elements texts collected");
        return textList;
    }

    /**
     * <b>[Method]</b> - Wait For Page To Load<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality waiting until page loaded.
     * Has logic to expect document ready state till timOut duration<br>
     */
    public static void waitForPageToLoad(/*long timeOut*/) {
//        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver driver) {
//                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
//            }
//        };
//        try {
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
//            wait.until(expectation);
//        } catch (Throwable error) {
//            error.printStackTrace();
//        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        // This loop will rotate for 10 times to check If page Is ready after every 1 second.
        // You can replace your if you want to Increase or decrease wait time.
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                ExtentListeners.test.log(Status.FAIL, e);
            }
            // To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                break;
            }
        }
    }

    /**
     * <b>[Method]</b> - Is element visible<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality checks if specific element is still visible<br>
     */
    public Boolean isElementVisible(By by) {
        try {
            driver.findElement(by).isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    /**
     * <b>[Method]</b> - Is element clickable<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality checks if specific element is still clickable<br>
     */
    public Boolean isElementClickable(By by) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            return element != null && element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * <b>[Method]</b> - Is element present<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality checks if specific element is still present<br>
     */
    public boolean isElementPresent(By by) {
        return !driver.findElements(by).isEmpty();
    }

    /**
     * <b>[Method]</b> - Clear field<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality clears a specific input field<br>
     */
    public void clearField(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement toClear = driver.findElement(by);
        driver.findElement(by).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        toClear.clear();
    }

    /*--------------------------------------->Validation<-----------------------------------------------*/

    /**
     * <b>[Method]</b> - Validate Page Title<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality validates page title<br>
     * Has logic to validate actual page title with expected page title<br>
     * It takes expected page title as parameter<br>
     * //    * @param pageTitle String
     *
     * @throws TimeoutException
     * @throws AssertionError
     */
    public static void validatePageTitle(String title) {
        try {
            assertEquals(driver.getTitle(), title);
            ExtentListeners.test.log(Status.PASS, "User successfully accessed to \"" + driver.getTitle() + "\" page");
        } catch (TimeoutException | AssertionError e) {
            ExtentListeners.test.log(Status.FAIL, e.getMessage());
            throw e;
        }
    }


    /**
     * <b>[Method]</b> - Fluent Wait<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality set Fluet Wait<br>
     * @return wait FluentWait
     */
    public FluentWait<WebDriver> fluentWaitWebDriver(long time) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(time))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return wait;
    }

    /**
     * <b>[Method]</b> - Validate WebElement Attribute<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality validates webelement attribute<br>
     * Has logic to validate web element's attribute<br>
     * and return boolean value according to presence of it<br>
     *
     * @return condition Boolean
     */
    public boolean validateElementAttribute(Timer timer, WebElement we, String att, String value) {
        FluentWait<WebDriver> wait = fluentWaitWebDriver(timer.option);
        Boolean condition;
        try {
            final WebElement element = we;
            final String attribute = att;
            final String equalValue = value;
            wait.until((ExpectedCondition<Boolean>) d -> element.getAttribute(attribute).contains(equalValue)
            );
            condition = true;
        } catch (StaleElementReferenceException se) {
            condition = false;
        } catch (java.util.NoSuchElementException ne) {
            condition = false;
        } catch (TimeoutException e) {
            condition = false;
        }
        return condition;
    }

    /**
     * <b>[Method]</b> - Validate Page Title<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality validates Page Title<br>
     * Has logic to validate page's title<br>
     * and return boolean value according to presence of it<br>
     *
     * @return condition Boolean
     */
    public boolean validatePageTitleWithFluentWait(Timer timer, String text) {
        fluentWaitWebDriver(timer.option);
        Boolean condition;
        try {
            final String nextPage = text;
            final WebDriver d = driver;
            wait.until(
                    (ExpectedCondition<Boolean>) d1 -> d1.getTitle().contains(nextPage)
            );
            condition = true;
        } catch (TimeoutException e) {
            condition = false;
        }
        return condition;
    }

    /**
     * <b>[Method]</b> - Validate WebElement Exists<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality validates webelement exists<br>
     * Has logic to validate if list of web element's<br>
     * exist on the page<br>
     *
     * @return condition Boolean
     */
    public boolean validateElementExist(Timer timer, By by) {
        FluentWait<WebDriver> wait = fluentWaitWebDriver(timer.option);
        Boolean condition;
        try {
            final By nextBy = by;
            wait.until((ExpectedCondition<Boolean>) driver -> (driver.findElements(nextBy).size() > 0));
            condition = true;
        } catch (StaleElementReferenceException se) {
            condition = false;
        } catch (java.util.NoSuchElementException ne) {
            condition = false;
        } catch (TimeoutException e) {
            condition = false;
        }
        return condition;
    }

    /**
     * <b>[Method]</b> - Validate WebElement Does not Exist<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality validates webelement not exist<br>
     * Has logic to validate if list of web element's<br>
     * is size zero on the page<br>
     *
     * @return condition Boolean
     */
    public boolean validateElementSizeIsZero(By by, Timer time) {
        FluentWait<WebDriver> wait = fluentWaitWebDriver(time.option);
        Boolean value;
        try {
            final By nextBy = by;
            wait.until((ExpectedCondition<Boolean>) driver -> (driver.findElements(nextBy).size() == 0));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement findElementInsideShadow(WebElement we, By desiredEl) {
        WebElement shadow;
        try {
            SearchContext shadowRoot = we.getShadowRoot();
            shadow = shadowRoot.findElement(desiredEl);
        } catch (Exception e) {
            Log.error(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        return shadow;
    }


    static String className;

    /**
     * <b>[Method]</b> - Set Executing Class Name<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality set class name<br>
     * that is used during executing test scenario<br>
     *
     * @param name String
     */
    public static void setExecutingClassName(String name) {
        className = name;
    }
    /**
     * <b>[Method]</b> - Get Executing Class Name<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality get class name<br>
     * that is used for mark preferred web browser<br>
     * during web driver initialization
     *
     * @return className String
     */
    public static String getExecutingClassName() {
        return className;
    }

}
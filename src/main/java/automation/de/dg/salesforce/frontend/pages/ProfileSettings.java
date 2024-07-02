package automation.de.dg.salesforce.frontend.pages;

import automation.testbase.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/**
 * <b>PAGES :</b> [ProfileSettings]: Profile Settings
 */
public class ProfileSettings extends Page {

    By userProfile = By.xpath("//span[contains(@class, 'photoContainer forceSocialPhoto')]/div/span");
    By einstellungen = By.xpath("//div//a[contains(text(),'Einstellungen')]");
    By settings = By.xpath("//div//a[contains(text(),'Settings')]");
    By spracheZeitzone = By.xpath("//div//a[contains(text(),'Sprache & Zeitzone')]");
    By languageAndTimeZone = By.xpath("//div//a[contains(text(),'Language & Time Zone')]");
    By languageTimezoneEnvironmentLang = By.xpath("//select[contains(@id, 'LanguageAndTimeZoneSetup:editPage:editPageBlock:languageLocaleKey')]");
    By languageTimezoneLocaleLand = By.xpath("//select[contains(@id, 'LanguageAndTimeZoneSetup:editPage:editPageBlock:localeSidKey')]");
    By languageTimezoneSaveButton = By.xpath("//input[@type='button' and (@title='Speichern' or @title='Save')]");

    /**
     * <b>[Method]</b> - Change env language<br>
     * <br>
     * <i>Method functionality:</i><br>
     * This functionality navigates to profile settings and changes the environmental language<br>
     * @param envLanguage String
     */
    public void changeEnvironmentalLangTo(String envLanguage) {
        click(userProfile);
        waitForPageToLoad();

        if(envLanguage.equals("English") && driver.findElements(settings).size() < 1) {

            click(einstellungen);
            wait.until(ExpectedConditions.visibilityOfElementLocated(spracheZeitzone));
            click(spracheZeitzone);
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
            waitForPageToLoad();
            wait.until(ExpectedConditions.visibilityOfElementLocated(languageTimezoneEnvironmentLang));

            Select language = new Select(
                    driver.findElement(languageTimezoneEnvironmentLang));
            language.selectByVisibleText("English");

            Select locale = new Select(
                    driver.findElement(languageTimezoneLocaleLand));
            locale.selectByVisibleText("Deutsch (Deutschland, EURO)");

            click(languageTimezoneSaveButton);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(languageTimezoneEnvironmentLang));

        }

        if(envLanguage.equals("Deutsch") && driver.findElements(einstellungen).size() < 1) {

            click(settings);
            wait.until(ExpectedConditions.visibilityOfElementLocated(languageAndTimeZone));
            click(languageAndTimeZone);
            driver.switchTo().defaultContent();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
            wait.until(ExpectedConditions.visibilityOfElementLocated(languageTimezoneEnvironmentLang));

            Select language = new Select(
                  driver.findElement(languageTimezoneEnvironmentLang));
            language.selectByVisibleText("Deutsch");

            Select locale = new Select(
                  driver.findElement(languageTimezoneLocaleLand));
            locale.selectByVisibleText("German (Germany, EURO)");

            click(languageTimezoneSaveButton);
            waitForPageToLoad();

        }
    }
}

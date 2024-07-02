package automation.de.dg.salesforce.elements;

import automation.testbase.Page;
import org.openqa.selenium.By;

import java.util.LinkedHashMap;
import java.util.Map;

public class AppLauncherMenuElements extends Page {

    /**
     * This map contains the type of element (either 'element' or 'category') that each element that is shown to the All
     * applications menu may be. It is used together with the @createPageElement to create the correct Xpath for each elements.
     */
    public static final Map<String, String> APP_LAUNCHER_ELEMENT_TYPE = new LinkedHashMap<>() {{
        put("Salesforce Chatter",             "category");
        put("Vertrieb",                       "category");
        put("Servicekonsole",                 "category");
        put("Knowledge Console",              "category");
        put("Accounts",                       "element");
        put("Angebote",                       "element");
        put("Article Feedback",               "element");
        put("Assigned To Me(Thor)",           "element");
        put("Aufgaben",                       "element");
        put("Aufträge",                       "element");
        put("Orders",                         "element");
        put("Berichte",                       "element");
        put("Cases",                          "element");
        put("DGProjekte",                     "element");
        put("Dashboards",                     "element");
        put("DocuSign-Setup",                 "element");
        put("Document Template Designer",     "element");
        put("Großstörungen",                  "element");
        put("Kalender",                       "element");
        put("Kampagnen",                      "element");
        put("Knowledge",                      "element");
        put("Kontakte",                       "element");
        put("Leads",                          "element");
        put("Opportunities",                  "element");
        put("Orchestrierungsarbeitselemente", "element");
        put("Orchestrierungsausfürhungen",    "element");
        put("Startseite",                     "element");
    }};

    /**
     * This is an auxiliary method in order to create dynamically the xpath of the page element of interest, when the user is
     * already in the View All Applications view.
     *
     * @param pageElement The page element we want to create, as a String value.
     * @return The corresponding xpath, as a By selenium element.
     */
    public static By createPageElement(String pageElement, String pageElementType) {

        return pageElementType.equals("element") ?
                By.xpath("//*[@data-label='" + pageElement + "']/..") :
                By.xpath("//div[@data-name='" + pageElement + "']");
    }
}

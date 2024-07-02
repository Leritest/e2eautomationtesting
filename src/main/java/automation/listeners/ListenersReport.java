package automation.listeners;

import automation.utilities.ProjectUtils;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Map;

/**
 * This is a class which using listeners generates report about the result of running the test cases.
 */
public class ListenersReport implements IReporter {

    /**
     * Generate a report for the given suites into the specified output directory.
     *
     * @param xmlSuites       The list of <code>XmlSuite</code>
     * @param suites          The list of <code>ISuite</code>
     * @param outputDirectory The output directory
     */
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        ITestContext testContext = null;
        String suiteName = null;

        for (ISuite suite : suites) {
            suiteName = suite.getName();
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult suiteResult : suiteResults.values()) {
                testContext = suiteResult.getTestContext();
            }
        }

        try {
            ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
            ProjectUtils projectUtils = new ProjectUtils();
            ExchangeCredentials credentials = new WebCredentials("TelcoTest@seavus.com", "d24ezVnp$XrJLj!G");
            service.setCredentials(credentials);
            //service.autodiscoverUrl("TelcoTest@seavus.com", service);
            service.autodiscoverUrl("ratko.zekic@seavus.com", service);

            EmailMessage message = new EmailMessage(service);
            message.setSubject("Mail with report for the test suite: " + suiteName);
            // Set body
            assert testContext != null;
            message.setBody(MessageBody.getMessageBodyFromText(
                    "<b>After running suite following report is:</b> <br/> <br/> <b>Total tests run: </b>"
                            + testContext.getAllTestMethods().length
                            + "<br/> <br/> <font color=green>Passed tests: </font>"
                            + testContext.getPassedTests().getAllResults().size()
                            + "<br/> <br/> <font color=red>Failed tests: </font>"
                            + testContext.getFailedTests().getAllResults().size()
                            + "<br/> <br/> <font color=#CBD92A>Re-run tests: </font>"
                            + testContext.getSkippedTests().getAllResults().size()));

            String file = ".\\target\\surefire-reports\\html\\";

            File directoryPath = new File(file);

            // Creating filter for html or jpg files
            FilenameFilter Filefilter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    ExtentListeners listener = new ExtentListeners();
                    // check if name is contains string used for ExtentListener and ExtentApiListener or contains image file
                    return name.startsWith(ExtentListeners.fileName) || name.endsWith(".jpg") || name.startsWith("RestAssured_Extent_Report");
                }
            };

            String[] imageFilesList = directoryPath.list(Filefilter);
            assert imageFilesList != null;
            for (String fileName : imageFilesList) {
                message.getAttachments().addFileAttachment(".\\target\\surefire-reports\\html\\" + fileName);
            }

            // add recipient
            //message.getToRecipients().add("jelena.poptrpeva@seavus.com");
            //message.getToRecipients().add("TelcoTest@seavus.com");
            message.getToRecipients().add("ratko.zekic@seavus.com");

            message.send();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
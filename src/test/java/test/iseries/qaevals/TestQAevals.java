package test.iseries.qaevals;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.config.IConstants;
import com.excel.Xls_Reader;
import com.google.common.io.Resources;
import com.utilities.TestUtil;

import dweb.test.templates.TestTemplate;
import dweb.test.templates.TestTemplateMethodLevelInit;
import pages.iseries.loginpage.LoginPage;
import pages.iseries.qaevals.QaEvalsPage;

public class TestQAevals extends TestTemplateMethodLevelInit {

	@DataProvider(name = "QAEvals", parallel = false)
	protected Object[][] getGMEdataFromExcel() throws URISyntaxException {
		URL urlFilePath = Resources
				.getResource(String.format("%s/%s", IConstants.TEST_DATA_LOCATION, IConstants.TEST_DATA_EXCEL_FILE));
		String filePath = Paths.get(urlFilePath.toURI()).toFile().getAbsolutePath();
		Xls_Reader xlsReader = new Xls_Reader(filePath);
		Object[][] objMetrics = TestUtil.getData("QAEvalsTestData", xlsReader, "QAEvals");
		return objMetrics;
	}
	
	@Test(dataProvider = "QAEvals", description = "Validate application navigating to QA Evals from Clients page")
	public void testC939941(ITestContext testContext) {
		// Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		QaEvalsPage qaEvalsPage = new QaEvalsPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);

		// Logging in to application
		loginPage.loginToFoxApplicationAndDivision(testContext);

		// Navigating QAEvals page
		String clientName = qaEvalsPage.navigateToQAevalsFromClient();

		// Login to QAEvals
		String userName = this.getTestParameter(testContext, "vpnUserName");
		String password = this.getTestParameter(testContext, "vpnpassword");
		loginPage.qaEvalsLogin(userName, password);

		// Get title and store to actual
		String actual = TestQAevals.threadLocalWebDriver.get().getTitle();

		// Validating Expected and Actual Text
		qaEvalsPage.validateTextEquals(actual, "Client: " + clientName);

	}

	@Test(description = "Validate application navigating to QA Evals from provider page")
	public void testC939942(ITestContext testContext) {
		// Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		QaEvalsPage qaEvalsPage = new QaEvalsPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);

		// Logging in to application
		loginPage.loginToFoxApplicationAndDivision(testContext);

		// Navigating QAEvals page
		String providerName = qaEvalsPage.navigateToQAevalsFromProvider();

		// Login to QAEvals
		String userName = this.getTestParameter(testContext, "vpnUserName");
		String password = this.getTestParameter(testContext, "vpnpassword");
		loginPage.qaEvalsLogin(userName, password);

		// Get title and store to actual
		String actual = TestQAevals.threadLocalWebDriver.get().getTitle();

		// Validating Expected and Actual Text
		try {
			if ((actual.replaceAll("[\r\n]+", " ")).contains("Provider")) {
				TestQAevals.testReport.logSuccess("Validate Text Present",
						"Expected Test is " + "Provider: " + providerName + "and Actual Text is " + actual);
			} else {
				TestQAevals.testReport.logFailure("Validate Text Present",
						"Expected Test is " + "Provider: " + providerName + "and Actual Text is " + actual,
						this.getScreenShotName());
			}
		} catch (Exception e) {
			TestQAevals.testReport.logFailure("Validate Text Present", e.getMessage().toString(),
					this.getScreenShotName());
		}

	}

	@Test(description = "Verify that users can access QA Evals from assignments page in FOX")
	public void testC939943(ITestContext testContext) {
		// Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		QaEvalsPage qaEvalsPage = new QaEvalsPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);

		// Logging in to application
		loginPage.loginToFoxApplicationAndDivision(testContext);

		// Navigating QAEvals page
		String assignmentName = qaEvalsPage.navigateToQAevalsFromAssignment();

		// Login to QAEvals
		String userName = this.getTestParameter(testContext, "vpnUserName");
		String password = this.getTestParameter(testContext, "vpnpassword");
		loginPage.qaEvalsLogin(userName, password);

		// Get title and store to actual
		String actual = TestQAevals.threadLocalWebDriver.get().getTitle();

		// Validating Expected and Actual Text
		this.softAssert.assertEquals(actual.replaceAll("[\r\n]+", " "), "QA Eval " + assignmentName);
		qaEvalsPage.validateTextEquals(actual, "QA Eval " + assignmentName);
		
		// Assert All
		this.softAssert.assertAll();
	}
	
	@Test(description = "Verify Creating Client Eval and Provider Eval from Assignment QA Evals")
	public void testC939946(ITestContext testContext) {
		// Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		QaEvalsPage qaEvalsPage = new QaEvalsPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);

		// Logging in to application
		loginPage.loginToFoxApplicationAndDivision(testContext);

		// Navigating QAEvals page
		qaEvalsPage.navigateToQAevalsFromAssignment();

		// Login to QAEvals
		String userName = this.getTestParameter(testContext, "vpnUserName");
		String password = this.getTestParameter(testContext, "vpnpassword");
		loginPage.qaEvalsLogin(userName, password);

		//Create Client QA EVAL
		String qaEvalTypeInClientQaEvals = qaEvalsPage.createClientEval().trim();
		
		//Create provider QA Eval
		String qaEvalTypeInProviderQaEvals = qaEvalsPage.createProviderEval().trim();
		
		// Validating Expected and Actual Text
		this.softAssert.assertEquals(qaEvalTypeInClientQaEvals.replaceAll("[\r\n]+", " "), "Client");
		
		//Validation client eval created
		qaEvalsPage.validateTextEquals(qaEvalTypeInClientQaEvals, "Client");

		//Validate provider Eval created
		qaEvalsPage.validateTextEquals(qaEvalTypeInProviderQaEvals, "Provider");
		
		// Assert All
		this.softAssert.assertAll();
	}
	
	@Test(description = "Verify that users can access all the links on the Assignemnt QA Evals page")
	public void testC939947(ITestContext testContext) {
		// Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		QaEvalsPage qaEvalsPage = new QaEvalsPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		//AppValidations commonPage = new AppValidations();

		// Logging in to application
		loginPage.loginToFoxApplicationAndDivision(testContext);

		// Navigating QAEvals page
		qaEvalsPage.navigateToQAevalsFromAssignment();

		// Login to QAEvals
		String userName = this.getTestParameter(testContext, "vpnUserName");
		String password = this.getTestParameter(testContext, "vpnpassword");
		loginPage.qaEvalsLogin(userName, password);

		//Create Client QA EVAL
		qaEvalsPage.createClientEval().trim();
		
		//Create provider QA Eval
		qaEvalsPage.createProviderEval().trim();
		
		//Click on all the links available and get the page titles
		Map<String, String> actualText = qaEvalsPage.clickLinksPresentInQAEvalsAndGetTitles();
		
		//Validating Edit link
		softAssert.assertEquals(actualText.get("editTitle").replaceAll("[\r\n]+", " "), "Report a technical problem");
		qaEvalsPage.validateTextEquals(actualText.get("editTitle"), "Report a technical problem");
		
		//Validating More link
		softAssert.assertEquals(actualText.get("moreValue").replaceAll("[\r\n]+", " "), "View History");
		qaEvalsPage.validateTextEquals(actualText.get("moreValue"), "View History");
		
		//Validating assignment name link
		softAssert.assertEquals(actualText.get("asgTitle").replaceAll("[\r\n]+", " "), "QA Evals " + actualText.get("asgName"));
		qaEvalsPage.validateTextEquals(actualText.get("asgTitle"), "QA Evals " + actualText.get("asgName"));

		//Validating client name link
		softAssert.assertEquals(actualText.get("clientTitle").replaceAll("[\r\n]+", " "), "Client QA Evals " + actualText.get("clientName"));
		qaEvalsPage.validateTextEquals(actualText.get("clientTitle"), "Client QA Evals " + actualText.get("clientName"));
		
		//Validating provider name link
		softAssert.assertEquals(actualText.get("providerTitle").replaceAll("[\r\n]+", " "), "Provider QA Evals " + actualText.get("providerName"));
		qaEvalsPage.validateTextEquals(actualText.get("providerTitle"), "Provider QA Evals " + actualText.get("providerName"));
		
		//Return values stored in map
		testContext.setAttribute("providerName", actualText.get("providerName"));
		testContext.setAttribute("clientName", actualText.get("clientName"));
		
		//Assert All
		this.softAssert.assertAll();		
	}
	
	@Test(dependsOnMethods = {"testC939947"}, description = "Verify that users can access all the links on the Provider QA Evals page")
	public void testC939945(ITestContext testContext) {
		// Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		QaEvalsPage qaEvalsPage = new QaEvalsPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		Object values = testContext.getAttribute("providerName");
		
		// Logging in to application
		loginPage.loginToFoxApplicationAndDivision(testContext);

		// Navigating QAEvals page
		qaEvalsPage.navigateToQAevalsFromProvider(values.toString());

		// Login to QAEvals
		String userName = this.getTestParameter(testContext, "vpnUserName");
		String password = this.getTestParameter(testContext, "vpnpassword");
		loginPage.qaEvalsLogin(userName, password);
		
		//Click on all the links available and get the page titles
		Map<String, String> actualText = qaEvalsPage.clickLinksPresentInClientQAEvalsAndGetTitles();
		
		//Validating Edit link
		softAssert.assertEquals(actualText.get("editTitle").replaceAll("[\r\n]+", " "), "Report a technical problem");
		qaEvalsPage.validateTextEquals(actualText.get("editTitle"), "Report a technical problem");
		
		//Validating More link
		softAssert.assertEquals(actualText.get("moreValue").replaceAll("[\r\n]+", " "), "View History");
		qaEvalsPage.validateTextEquals(actualText.get("moreValue"), "View History");
		
		//Validating assignment name link
		softAssert.assertEquals(actualText.get("asgTitle").replaceAll("[\r\n]+", " "), "QA Evals " + actualText.get("asgName"));
		qaEvalsPage.validateTextEquals(actualText.get("asgTitle"), "QA Evals " + actualText.get("asgName"));

		//Validating client name link
		softAssert.assertEquals(actualText.get("clientTitle").replaceAll("[\r\n]+", " "), "Client QA Evals " + actualText.get("clientName"));
		qaEvalsPage.validateTextEquals(actualText.get("clientTitle"), "Client QA Evals " + actualText.get("clientName"));
		
		//Validating provider name link
		softAssert.assertEquals(actualText.get("providerTitle").replaceAll("[\r\n]+", " "), "Provider QA Evals " + actualText.get("providerName"));
		qaEvalsPage.validateTextEquals(actualText.get("providerTitle"), "Provider QA Evals " + actualText.get("providerName"));
		
		//Assert All
		this.softAssert.assertAll();
	}
	
	@Test(description = "Validate application navigating to QA Evals from Assignment page")
	public void testC939948(ITestContext testContext) {
		// Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		QaEvalsPage qaEvalsPage = new QaEvalsPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);

		// Logging in to application
		loginPage.loginToFoxApplicationAndDivision(testContext);

		// Navigating QAEvals page
		String clientName = qaEvalsPage.navigateToQAevalsFromClient();

		// Login to QAEvals
		String userName = this.getTestParameter(testContext, "vpnUserName");
		String password = this.getTestParameter(testContext, "vpnpassword");
		loginPage.qaEvalsLogin(userName, password);

		// Get title and store to actual
		String actual = TestQAevals.threadLocalWebDriver.get().getTitle();

		// Validating Expected and Actual Text
		this.softAssert.assertEquals(actual.replaceAll("[\r\n]+", " "), "Client: " + clientName);
		
		qaEvalsPage.validateTextEquals(actual.replaceAll("[\r\n]+", " "), "Client: " + clientName);

		qaEvalsPage.switchToWindowUsingTitle("Client: Z_CHSD_Automation_Client_UAT ~ Salesforce - Unlimited Edition");
		
		// Navigating QAEvals page
		String providerName = qaEvalsPage.navigateToQAevalsFromProvider();

		// Get title and store to actual
		String actual1 = TestQAevals.threadLocalWebDriver.get().getTitle();

		// Validating Expected and Actual Text
		this.softAssert.assertEquals(actual1.replaceAll("[\r\n]+", " "), "QA Eval " + providerName);
			
		try {
			if ((actual1.replaceAll("[\r\n]+", " ")).contains("Provider")) {
				TestQAevals.testReport.logSuccess("Validate Text Present",
						"Expected Test is " + "Provider: " + providerName + "and Actual Text is " + actual1);
			} else {
				TestQAevals.testReport.logFailure("Validate Text Present",
						"Expected Test is " + "Provider: " + providerName + "and Actual Text is " + actual1,
							this.getScreenShotName());
			}
		} catch (Exception e) {
			TestQAevals.testReport.logFailure("Validate Text Present", e.getMessage().toString(),
					this.getScreenShotName());
		}
			
		qaEvalsPage.switchToWindowUsingTitle("Provider or Contact: Dr. CHSTestProvider3 Test3 ~ Salesforce - Unlimited Edition");
				
		// Navigating QAEvals page
		String assignmentName = qaEvalsPage.navigateToQAevalsFromAssignment();
		
		// Get title and store to actual
		String actual2 = TestQAevals.threadLocalWebDriver.get().getTitle();

		// Validating Expected and Actual Text
		this.softAssert.assertEquals(actual2.replaceAll("[\r\n]+", " "), "QA Eval " + assignmentName);
		qaEvalsPage.validateTextEquals(actual2, "QA Eval " + assignmentName);
		
		// Assert All
		this.softAssert.assertAll();
	}
	
	@Test(dependsOnMethods = {"testC939947"}, description = "Verify that Summary report and filter evals works as expected on client evals page")
	public void testC939944(ITestContext testContext) {
		// Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		QaEvalsPage qaEvalsPage = new QaEvalsPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		Object values = testContext.getAttribute("clientName");
		
		// Logging in to application
		loginPage.loginToFoxApplicationAndDivision(testContext);

		// Navigating QAEvals page
		qaEvalsPage.navigateToQAevalsFromClient(values.toString());

		// Login to QAEvals
		String userName = this.getTestParameter(testContext, "vpnUserName");
		String password = this.getTestParameter(testContext, "vpnpassword");
		loginPage.qaEvalsLogin(userName, password);
		
		//Get current Date
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		
		//Get previous year date
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.YEAR, -1);
		SimpleDateFormat isoFormat = new SimpleDateFormat("MM/dd/yyyy");
		String previousYearDate = isoFormat.format(instance.getTime());
		
		//Enter dates and click on search
		String clientName = qaEvalsPage.searchForClientEvals(previousYearDate, dateFormat.format(date));
		
		//Validations
		this.softAssert.assertEquals(clientName, values.toString());
		qaEvalsPage.validateTextEquals(clientName, values.toString());
		
		//Assert everything
		this.softAssert.assertAll();
	}
}

package test.iseries.qaevals;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import dweb.test.templates.TestTemplate;
import dweb.test.templates.TestTemplateMethodLevelInit;
import pages.iseries.loginpage.LoginPage;
import pages.iseries.qaevals.QaEvalsPage;

public class TestQAevals extends TestTemplateMethodLevelInit{

	/**
	 * @param testContext
	 * Validate application navigating to QA Evals from Clients page
	 */
	//@Test
	public void testC939941(ITestContext testContext)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		QaEvalsPage qaEvalsPage = new QaEvalsPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		loginPage.loginToFoxApplicationAndDivision(testContext);
		
		//Navigating QAEvals page
		String clientName = qaEvalsPage.navigateToQAevalsFromClient();
		
		//Login to QAEvals
		loginPage.qaEvalsLogin(testContext);
		
		//Get title and store to actual
		String actual = TestQAevals.threadLocalWebDriver.get().getTitle();
		
		//Validating Expected and Actual Text
		try{
			if((actual.replaceAll("[\r\n]+", " ")).equalsIgnoreCase("Client: "+clientName))
			{
				TestQAevals.testReport.logSuccess("Validate Text Present", "Expected Test is "+"Client: "+clientName+"and Actual Text is "+actual);
			}
			else
			{
				TestQAevals.testReport.logFailure("Validate Text Present", "Expected Test is "+"Client: "+clientName+"and Actual Text is "+actual, this.getScreenShotName());
			}
		}catch(Exception e)
		{
			TestQAevals.testReport.logFailure("Validate Text Present", e.getMessage().toString(), this.getScreenShotName());
		}

	}
	
	/**
	 * @param testContext
	 * Validate application navigating to QA Evals from provider page
	 */
	@Test
	public void testC939942(ITestContext testContext)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		QaEvalsPage qaEvalsPage = new QaEvalsPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		loginPage.loginToFoxApplicationAndDivision(testContext);
		
		//Navigating QAEvals page
		String providerName = qaEvalsPage.navigateToQAevalsFromProvider();
		
		//Login to QAEvals
		loginPage.qaEvalsLogin(testContext);
		
		//Get title and store to actual
				String actual = TestQAevals.threadLocalWebDriver.get().getTitle();
				
		//Validating Expected and Actual Text
		try{
			if((actual.replaceAll("[\r\n]+", " ")).contains("Provider"))
			{
				TestQAevals.testReport.logSuccess("Validate Text Present", "Expected Test is "+"Provider: "+providerName+"and Actual Text is "+actual);
			}
			else
			{
				TestQAevals.testReport.logFailure("Validate Text Present", "Expected Test is "+"Provider: "+providerName+"and Actual Text is "+actual, this.getScreenShotName());
			}
		}catch(Exception e)
		{
			TestQAevals.testReport.logFailure("Validate Text Present", e.getMessage().toString(), this.getScreenShotName());
		}

	}
}

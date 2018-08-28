package test.iseries.qaevals;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import dweb.test.templates.TestTemplate;
import dweb.test.templates.TestTemplateMethodLevelInit;
import pages.iseries.loginpage.LoginPage;
import pages.iseries.qaevals.QaEvalsPage;

public class TestQAevals extends TestTemplateMethodLevelInit{

	@Test(description = "Validate application navigating to QA Evals from Clients page")
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
	

	@Test(description = "Validate application navigating to QA Evals from provider page")
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
	
	@Test(description = "Verify that users can access QA Evals from assignments page in FOX")
	public void testC939943(ITestContext testContext)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		QaEvalsPage qaEvalsPage = new QaEvalsPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		loginPage.loginToFoxApplicationAndDivision(testContext);
		
		//Navigating QAEvals page
		String assignmentName = qaEvalsPage.navigateToQAevalsFromAssignment();
		
		//Login to QAEvals
		loginPage.qaEvalsLogin(testContext);
		
		//Get title and store to actual
		String actual = TestQAevals.threadLocalWebDriver.get().getTitle();
				
		//Validating Expected and Actual Text
		this.softAssert.assertEquals(actual.replaceAll("[\r\n]+", " "), "QA Eval " + assignmentName);
		try{
			if((actual.replaceAll("[\r\n]+", " ")).equalsIgnoreCase("QA Eval " + assignmentName))
			{
				TestQAevals.testReport.logSuccess("Validate Text Present", "Expected Test is "+"QA Eval "+assignmentName+" and Actual Text is "+actual);
			}
			else
			{
				TestQAevals.testReport.logFailure("Validate Text Present", "Expected Test is "+"QA Eval "+assignmentName+" and Actual Text is "+actual, this.getScreenShotName());
			}
		}catch(Exception e)
		{
			TestQAevals.testReport.logFailure("Validate Text Present", e.getMessage().toString(), this.getScreenShotName());
		}
		
		//Assert All
		this.softAssert.assertAll();
	}
}

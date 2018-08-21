package test.iseries.jdevendorsearch;

import org.testng.annotations.Test;

import dweb.test.templates.TestTemplate;
import dweb.test.templates.TestTemplateMethodLevelInit;
import pages.iseries.jdevendorsearch.JdeVendorSearchPage;
import pages.iseries.loginpage.LoginPage;

public class TestVendorSearch extends TestTemplateMethodLevelInit{

	@Test
	public void testC939958()
	{
		//Initialization
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Validating login info
		jdeVendorSearchPage.validateInformationPresentOnLoginPage();

	}
	
	@Test
	public void testC939922()
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin();
		
		//Validating is user logged in to application successfully.
		jdeVendorSearchPage.validateLoggedInToApplication();
	}
	
	@Test
	public void testC939923()
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin();
				
		//Validate list of fields exist in search form
		jdeVendorSearchPage.validateFieldsPresentInSearchForm();
	}
}

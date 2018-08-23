package test.iseries.jdevendorsearch;

import java.util.Hashtable;

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
	
	/**
	 * description : Login Info
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939922(Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin();
		
		//Validating is user logged in to application successfully.
		jdeVendorSearchPage.validateLoggedInToApplication();
	}
	
	/**
	 * description : Validate fields present in search form
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939923(Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin();
				
		//Validate list of fields exist in search form
		jdeVendorSearchPage.validateFieldsPresentInSearchForm();
	}
	
	/**
	 * description : Search for a vendor using vendor name
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939924(Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin();
				
		//Validate list of fields exist in search form
		jdeVendorSearchPage.searchForAVendorAndValidate(data.get("VendorName"));
	}
	
	/**
	 * description : Search for a vendor using vendor state
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939931(Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin();
				
		//search with state and validate record
		jdeVendorSearchPage.searchForAVendorWithStateAndValidate(data.get("State"));
	}
	
	/**
	 * description : Search for a vendor using vendor zip
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939932(Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin();
				
		//search with zip and validate record
		jdeVendorSearchPage.searchForAVendorWithZipAndValidate(data.get("ZIP"));
	}
	
	/**
	 * description : Search for a vendor using vendor number
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939933(Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin();
				
		//search with Vendor Number and validate record
		jdeVendorSearchPage.searchForAVendorWithNumberAndValidate(data.get("Number"));
	}
	
	/**
	 * description : Search for a vendor by filling all the fields in vendor search form
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939934(Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
			
		//Logging in to application
		loginPage.jdeVendorSearchLogin();
					
		//search with Vendor Number and validate record
		jdeVendorSearchPage.searchForAVendorWithAllFieldsAndValidate(data.get("VendorName"), data.get("State"), data.get("ZIP"), data.get("Number"));
	}
}

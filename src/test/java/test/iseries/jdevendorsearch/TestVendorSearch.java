package test.iseries.jdevendorsearch;

import java.util.Hashtable;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import dweb.test.templates.TestTemplate;
import dweb.test.templates.TestTemplateMethodLevelInit;
import pages.iseries.jdevendorsearch.JdeVendorSearchPage;
import pages.iseries.loginpage.LoginPage;

public class TestVendorSearch extends TestTemplateMethodLevelInit{

	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939958(Hashtable<String, String> data)
	{
		//Initialization
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
		
		//Validating login info
		jdeVendorSearchPage.validateInformationPresentOnLoginPage(data.get("informationtex"), data.get("alertText"), data.get("loginStatus"));
		this.softAssert.assertAll();
	}
	
	/**
	 * description : Login Info
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939922(ITestContext testContext, Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin(testContext);
		
		//Validating is user logged in to application successfully.
		jdeVendorSearchPage.validateLoggedInToApplication(data.get("HomePageTitle"));
		this.softAssert.assertAll();
	}
	
	/**
	 * description : Validate fields present in search form
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939923(ITestContext testContext, Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin(testContext);
				
		//Validate list of fields exist in search form
		jdeVendorSearchPage.validateFieldsPresentInSearchForm();
		this.softAssert.assertAll();
	}
	
	/**
	 * description : Search for a vendor using vendor name
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939924(ITestContext testContext, Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin(testContext);
				
		//Validate list of fields exist in search form
		jdeVendorSearchPage.searchForAVendorAndValidate(data.get("VendorName"));
		this.softAssert.assertAll();
	}
	
	/**
	 * description : Search for a vendor using vendor state
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939931(ITestContext testContext, Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin(testContext);
				
		//search with state and validate record
		jdeVendorSearchPage.searchForAVendorWithStateAndValidate(data.get("State"));
		this.softAssert.assertAll();
	}
	
	/**
	 * description : Search for a vendor using vendor zip
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939932(ITestContext testContext, Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin(testContext);
				
		//search with zip and validate record
		jdeVendorSearchPage.searchForAVendorWithZipAndValidate(data.get("ZIP"));
		this.softAssert.assertAll();
	}
	
	/**
	 * description : Search for a vendor using vendor number
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939933(ITestContext testContext, Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin(testContext);
				
		//search with Vendor Number and validate record
		jdeVendorSearchPage.searchForAVendorWithNumberAndValidate(data.get("Number"));
		this.softAssert.assertAll();
	}
	
	/**
	 * description : Search for a vendor by filling all the fields in vendor search form
	 */
	@Test(dataProvider = "getVendorDataFromExcel")
	public void testC939934(ITestContext testContext, Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
			
		//Logging in to application
		loginPage.jdeVendorSearchLogin(testContext);
					
		//search with Vendor Number and validate record
		jdeVendorSearchPage.searchForAVendorWithAllFieldsAndValidate(data.get("VendorName"), data.get("State"), data.get("ZIP"), data.get("Number"));
		this.softAssert.assertAll();
	}
	
	@Test(dataProvider = "getVendorDataFromExcel", description = "Validate clicking on Vendor number from search results")
	public void testC939925(ITestContext testContext, Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin(testContext);
				
		//search with Vendor Number and validate record
		jdeVendorSearchPage.searchForAVendorWithNumberAndValidate(data.get("Number"));
		
		//Click on Vendor Number
		Map<String, String> resultSet = jdeVendorSearchPage.clickOn1stVendorNumberDisplayedInSearchResults();
		
		//Validation
		this.softAssert.assertEquals(resultSet.get("vendorNumberInChecks"), data.get("Number"));
		jdeVendorSearchPage.validateTextEquals(resultSet.get("vendorNumberInChecks"), data.get("Number"));

		this.softAssert.assertAll();
	}
	
	@Test(dataProvider = "getVendorDataFromExcel", description = "Verify that user can navigate to next page on Vendor Check section")
	public void testC939926(ITestContext testContext, Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
		
		//Logging in to application
		loginPage.jdeVendorSearchLogin(testContext);
				
		//Navigate vendor and click on next button in pagination
		Map<String, String> resultSet = jdeVendorSearchPage.naviagtingToNextPageInPagination(data.get("Number"));
		
		//Validation
		this.softAssert.assertEquals(resultSet.get("classNameOf2ndPage"), data.get("ClassOfDisabledPagination"));
		jdeVendorSearchPage.validateTextEquals(resultSet.get("classNameOf2ndPage"), data.get("ClassOfDisabledPagination"));

		this.softAssert.assertAll();
	}
	
	@Test(dataProvider = "getVendorDataFromExcel", description = "Verify that user can change number of enteries to display on Vendor Check section")
	public void testC939927(ITestContext testContext, Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
				
		//Logging in to application
		loginPage.jdeVendorSearchLogin(testContext);
						
		//search with Vendor Number and validate record
		jdeVendorSearchPage.searchForAVendorWithNumberAndValidate(data.get("Number"));
				
		//Click on Vendor Number
		jdeVendorSearchPage.clickOn1stVendorNumberDisplayedInSearchResults();
		
		//update show entries values
		Map<String, String> resultSet = jdeVendorSearchPage.updateShowEntries(data.get("Entry1"));
				
		//Validation
		this.softAssert.assertEquals(resultSet.get("attributeValue"), data.get("Selected"));
		this.softAssert.assertEquals(resultSet.get("noOfRecordsDisplayed"), data.get("Entry1"));
		
		jdeVendorSearchPage.validateTextEquals(resultSet.get("attributeValue"), data.get("Selected"));
		jdeVendorSearchPage.validateTextEquals(resultSet.get("noOfRecordsDisplayed"), data.get("Entry1"));

		// update show entries values
		Map<String, String> resultSet1 = jdeVendorSearchPage.updateShowEntries(data.get("Entry2"));

		// Validation
		this.softAssert.assertEquals(resultSet1.get("attributeValue"), data.get("Selected"));
		this.softAssert.assertEquals(resultSet1.get("noOfRecordsDisplayed"), data.get("Entry2"));

		jdeVendorSearchPage.validateTextEquals(resultSet1.get("attributeValue"), data.get("Selected"));
		jdeVendorSearchPage.validateTextEquals(resultSet1.get("noOfRecordsDisplayed"), data.get("Entry2"));

		// update show entries values
		Map<String, String> resultSet2 = jdeVendorSearchPage.updateShowEntries(data.get("Entry3"));

		// Validation
		this.softAssert.assertEquals(resultSet2.get("attributeValue"), data.get("Selected"));
		jdeVendorSearchPage.validateTextEquals(resultSet2.get("attributeValue"), data.get("Selected"));

		// update show entries values
		Map<String, String> resultSet3 = jdeVendorSearchPage.updateShowEntries(data.get("Entry4"));

		// Validation
		this.softAssert.assertEquals(resultSet3.get("attributeValue"), data.get("Selected"));
		jdeVendorSearchPage.validateTextEquals(resultSet3.get("attributeValue"), data.get("Selected"));

		// update show entries values
		Map<String, String> resultSet4 = jdeVendorSearchPage.updateShowEntries(data.get("Entry5"));

		// Validation
		this.softAssert.assertEquals(resultSet4.get("attributeValue"), data.get("Selected"));
		jdeVendorSearchPage.validateTextEquals(resultSet4.get("attributeValue"), data.get("Selected"));

		// update show entries values
		Map<String, String> resultSet5 = jdeVendorSearchPage.updateShowEntries(data.get("Entry6"));
		
		// Validation
		this.softAssert.assertEquals(resultSet5.get("attributeValue"), data.get("Selected"));
		jdeVendorSearchPage.validateTextEquals(resultSet5.get("attributeValue"), data.get("Selected"));

		this.softAssert.assertAll();
	}
	
	@Test(dataProvider = "getVendorDataFromExcel", description = "Verify that clicking on Check/Voucher Number on vendor checks displays related details under Voucher Details section")
	public void testC939928(ITestContext testContext, Hashtable<String, String> data)
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeVendorSearchPage jdeVendorSearchPage = new JdeVendorSearchPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
				
		//Logging in to application
		loginPage.jdeVendorSearchLogin(testContext);
						
		//search with Vendor Number and validate record
		jdeVendorSearchPage.searchForAVendorWithNumberAndValidate(data.get("Number"));
				
		//Click on Vendor Number
		jdeVendorSearchPage.clickOn1stVendorNumberDisplayedInSearchResults();
		
		//Click on Voucher Number
		Map<String, String> resultSet = jdeVendorSearchPage.navigateTo1stVoucherDisplayed();
		
		// Validation
		this.softAssert.assertEquals(resultSet.get("voucherNumberInDetails"), resultSet.get("voucherNumberClicked"));
		jdeVendorSearchPage.validateTextEquals(resultSet.get("voucherNumberInDetails"), resultSet.get("voucherNumberClicked"));

		this.softAssert.assertAll();
	}
}

package test.iseries.phonebook;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import dweb.test.templates.TestTemplate;
import dweb.test.templates.TestTemplateMethodLevelInit;
import pages.iseries.phonebook.PhoneBookPage;

public class TestPhoneBook extends TestTemplateMethodLevelInit{

	@Test(dataProvider = "getDataFromExcel")
	public void testPhoneBookSearch(Hashtable<String, String> data)
	{
		SoftAssert softAssert = new SoftAssert();
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", data.get("FirstName"));
		int iCountCheckBoxes = 0;
		try
		{
			iCountCheckBoxes = phoneBookPage.searchPhoneBook(mapIdSearchCriteria);	
		}
		catch(Exception ex)
		{
			//Exception in Search Phone Book
			TestTemplate.testReport.logFailure("Phone Book Search", "Phone Book Search Fails", this.getScreenShotName());
			TestTemplate.testReport.logException(ex);
			return;
		}
		//validate search works
		
		softAssert.assertTrue(iCountCheckBoxes > 0, "Validation For Successful Search");
		TestTemplate.testReport.logInfo(String.format("Total Records Found = %d", iCountCheckBoxes));
		if(iCountCheckBoxes > 0)		{
			
			TestTemplate.testReport.logSuccess("Phone Book Search", "Phone Book Search Succeeds", this.getScreenShotName());
		}
		else
		{			
			TestTemplate.testReport.logFailure("Phone Book Search", "Phone Book Search Fails", this.getScreenShotName());
		}
		
		softAssert.assertAll();		
	}
	
	/**
	 * description="Validate Email letter opens up if user check check-box and clicked on Email all Checked Link"
	 */
	@Test(dataProvider = "getDataFromExcel")
	public void testC939913(Hashtable<String, String> data)
	{
		
		String firstName=data.get("FirstName"),lastName=data.get("LastName");
		//initialization
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Adding test data and locators of required fields
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName",firstName);
		mapIdSearchCriteria.put("searchLastName",lastName);
		
		phoneBookPage.verifyAnEmailLetterOpensUp(mapIdSearchCriteria);
			
	}
	
	/**
	 * description="Validate message alert if user does not check check-box and clicked on Email all Checked Link"
	 */
	@Test(dataProvider = "getDataFromExcel")
	public void testC939914(Hashtable<String, String> data) 
	{
		
		String firstName=data.get("FirstName"),lastName=data.get("LastName");
		//initialization
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Adding test data and locators of required fields
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", firstName);
		mapIdSearchCriteria.put("searchLastName",lastName);
		
		phoneBookPage.verifyMessageAlertWhenCheckboxIsUnchecked(mapIdSearchCriteria);
		
		//Verify the alert text
		String alertText = phoneBookPage.getTextFromAlert();	
				
		if(alertText.contains("No people have been selected for emails."))			
		{	
			TestTemplate.testReport.logSuccess("Email All Checked", "Email All Checked Alert Text Succeeds");
		}
		else
		{			
			TestTemplate.testReport.logFailure("Email All Checked", "Email All Checked Alert Text Fails", this.getScreenShotName());
		}
		
	}
	
	/**
	 * description="Validate CHG contact Info when user clicks on name link in search result"
	 */
	@Test(dataProvider = "getDataFromExcel")
	public void testC939915(Hashtable<String, String> data) 
	{
		
		String firstName=data.get("FirstName"),lastName=data.get("LastName");
		//initialization
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Adding test data and locators of required fields
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", firstName);
		mapIdSearchCriteria.put("searchLastName", lastName);
		
		phoneBookPage.verifyNameLinkInSearchResultIsEnableAndClick(mapIdSearchCriteria);
		
		
		//Get contact Title
		String values[] = phoneBookPage.getTextContactTitle();
		if(values[0].contains("CHG"))			
		{	
			TestTemplate.testReport.logSuccess("PhoneBookLogo", "Logo Text Succeeds");
		}
		if(values[1].contains("Phone Book"))			
		{	
			TestTemplate.testReport.logSuccess("PhoneBookTitle", "Title Text Succeeds");
		}
		//Get contact Info
		List<WebElement> Info = phoneBookPage.getTextContactInfo();
		String[] PhoneBookdetails = {"Aaron Cook" , "(801) 930-4029 x4029","aaron.cook@chghealthcare.com","Mgr II IT Project Mgmt","Midvale, UT ","IS Project Management"};
		
		for(int i=0; i<Info.size();i++)
		{
		if(Info.get(i).getText().equals(PhoneBookdetails[i]))
		{
			System.out.println("Phonbook Deatils" + Info.get(i).getText() +"is matched with test data " +PhoneBookdetails[i]);
		}
		else
		{
			System.out.println("Phonbook Deatils" + Info.get(i).getText() +"is mismatched with test data " +PhoneBookdetails[i]);
		}
		}
	}
	
	/**
	 * description="Validate Call,Email and Return Buttons on CHG contact Info"
	 */
	
	@Test(dataProvider = "getDataFromExcel")
	public void testC939916(Hashtable<String, String> data) 
	{
		
		String firstName=data.get("FirstName"),lastName=data.get("LastName");
		//initialization
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", firstName);
		mapIdSearchCriteria.put("searchLastName", lastName);
		
		phoneBookPage.verifyNameLinkInSearchResultIsEnableAndClick(mapIdSearchCriteria);
		
		//Get contact Title
		String values[] = phoneBookPage.getTextContactTitle();
				
		if(values[1].contains("Phone Book"))			
		{	
		TestTemplate.testReport.logSuccess("PhoneBookTitle", "Title Text Succeeds");
		}
			
		//Verifying Buttons In Phone book
		phoneBookPage.verifyButtonsInDisplayedContactInfo();
		
	}
	
	
	@Test(dataProvider = "getDataFromExcel")
	public void testC939917(Hashtable<String, String> data) 
	{
		
		String firstName=data.get("FirstName"),lastName=data.get("LastName");
		//initialization
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", firstName);
		mapIdSearchCriteria.put("searchLastName", lastName);
		
		phoneBookPage.verifyNameLinkInSearchResultIsEnableAndClick(mapIdSearchCriteria);
		
		//Get contact Title
		String values[] = phoneBookPage.getTextContactTitle();
						
		if(values[1].contains("Phone Book"))			
		{	
		TestTemplate.testReport.logSuccess("PhoneBookTitle", "Title Text Succeeds");
		}
		
		/*//Clicking on Call Button In Phone book
		phoneBookPage.clickCallButtonInPhoneBook();
		
		//Verify the alert text
		String alertText = phoneBookPage.getTextFromAlert();
		System.out.println(""+alertText);
		phoneBookPage.acceptAlert();*/
		
		
		//Clicking on Email Button In Phone book
		phoneBookPage.clickEmailButtonInPhoneBook();
		
		//Switch to New Window
		phoneBookPage.switchtoWindow();
		
		//Switch back to Default
		phoneBookPage.switchtoDefaultWindow();
				
		//Clicking  on Return Button In Phone book
		phoneBookPage.clickReturnButtonInPhoneBook();
	
		//Verifying Return To Phone Book Page
		boolean returnValidation = phoneBookPage.VerifyReturnToPhoneBookPage();
		if(returnValidation)
		{
			TestTemplate.testReport.logSuccess("ReturnValidation", "Return to phonebook page Succeeds");
		}
		else
		{
			TestTemplate.testReport.logFailure("ReturnValidation", "Return to phonebook page Fails" + getScreenShotName());
		}
	

	}  

	
	/**
	 * description="Validate phone number link displays the ability to make a call(Via Skype) when user clicks in search result"
	 */
	@Test(dataProvider = "getDataFromExcel")
	public void testC939918(Hashtable<String, String> data)
	{
		
		String firstName=data.get("FirstName"),lastName=data.get("LastName");
		//initialization
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", firstName);
		mapIdSearchCriteria.put("searchLastName", lastName);
		
		//Searching for the record
		phoneBookPage.verifyPhoneNumberLinkIsEnableAndClick(mapIdSearchCriteria);
		
			
	}
	
	/**
	 * description="Validate email link displays an email letter when user clicks in search result"
	 */
	@Test(dataProvider = "getDataFromExcel")
	public void testC939919(Hashtable<String, String> data) 
	{
		
		String firstName=data.get("FirstName"),lastName=data.get("LastName");
		//initialization
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", firstName);
		mapIdSearchCriteria.put("searchLastName",lastName);
		
		//Searching for the record
		phoneBookPage.verifyEmailLinkIsEnableAndClick(mapIdSearchCriteria);
		
	}
	
	/**
	 * description="Validate all field are reset if user clicks on reset button"
	 */
	@Test(dataProvider = "getDataFromExcel",description="Validate the record in PhoneBook on filling all the fields")
	public void testC939920(Hashtable<String, String> data)
	{
		String firstName=data.get("FirstName"),lastName=data.get("LastName"),phoneNo=data.get("Phone"),office=data.get("Office"),
				email=data.get("Email"),title=data.get("JobTitle"),team=data.get("Team");
		
		
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", firstName);
		mapIdSearchCriteria.put("searchLastName", lastName);
		mapIdSearchCriteria.put("searchPhone", phoneNo);
		mapIdSearchCriteria.put("searchOffice", office);
		mapIdSearchCriteria.put("searchTitle", title);
		mapIdSearchCriteria.put("searchEmail", email);
		mapIdSearchCriteria.put("searchTeam", team);	
		
		
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Validate all the fields in Phone Book page are reset
		 phoneBookPage.verifyPhoneBookFiledsReset(mapIdSearchCriteria);		
								
	}
	
	@Test(dataProvider = "getDataFromExcel",description="Validate the record in PhoneBook on filling all the fields")
	public void testC939912(Hashtable<String, String> data)
	{
		String firstName=data.get("FirstName"),lastName=data.get("LastName"),phoneNo=data.get("Phone"),office=data.get("Office"),
				email=data.get("Email"),title=data.get("JobTitle"),team=data.get("Team");
		
		
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", firstName);
		mapIdSearchCriteria.put("searchLastName", lastName);
		mapIdSearchCriteria.put("searchPhone", phoneNo);
		mapIdSearchCriteria.put("searchOffice", office);
		mapIdSearchCriteria.put("searchTitle", title);
		mapIdSearchCriteria.put("searchEmail", email);
		mapIdSearchCriteria.put("searchTeam", team);	
		
		
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		// Fill all the fields in Phone Book page and click on search
		String[] iSearchDetails = phoneBookPage.verifyPhoneBookFileds(mapIdSearchCriteria);		
		
		//Validate all details in a record
		phoneBookPage.validateTextEquals(firstName+" "+lastName,iSearchDetails[0]);	
		phoneBookPage.validateTextEquals(phoneNo,iSearchDetails[1]);		
		phoneBookPage.validateTextEquals(email,iSearchDetails[2]);		
		phoneBookPage.validateTextEquals(title+" "+office,iSearchDetails[3]);					
		phoneBookPage.validateTextEquals(team,iSearchDetails[4]);		
		
	}
	
	@Test(dataProvider="getTitleFromExcel",description="Verify CHG Phonebook page is displayed")
	public void testC939955(Hashtable<String, String> data)
	{
		//Get Page Title
		String title=this.threadLocalWebDriver.get().getTitle();
		
		//Validate the CHG Phone book title
		if(title.equals(data.get("Title"))){
			
			TestTemplate.testReport.logSuccess("Phone Book page", "Phone Book page is successfully displayed.", this.getScreenShotName());
		}
		else
		{			
			TestTemplate.testReport.logFailure("Phone Book page", "Phone Book page is not displayed.", this.getScreenShotName());
		}
		
	}
}
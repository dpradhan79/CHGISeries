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
	
	@Test
	public void testC939913()
	{
		//initialization
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Adding test data and locators of required fields
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", "Aaron");
		mapIdSearchCriteria.put("searchLastName", "Cook");
		
		//Searching for the record
		phoneBookPage.searchingForARecord(mapIdSearchCriteria);
		
		//Check 1st Record
		phoneBookPage.check1StRecordInSearchResults();
		
		//Click on Email all checked link
		phoneBookPage.clickOnEmailAllCheckedlink();		
	}
	
	
	@Test
	public void testC939914() throws InterruptedException
	{
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", "Aaron");
		mapIdSearchCriteria.put("searchLastName", "Cook");
		
		//Searching for the record
		phoneBookPage.searchingForARecord(mapIdSearchCriteria);
				
		//Check 1st Record
		phoneBookPage.uncheck1stRecordIfChecked(mapIdSearchCriteria);
				
		//Click on Email all checked link
		phoneBookPage.clickOnEmailAllCheckedlink();	
		
		//Verify the alert text
		String alertText = phoneBookPage.getTextFromEmailAllCheckedAlert();	
				
		if(alertText.contains("No people have been selected for emails."))			
		{	
			TestTemplate.testReport.logSuccess("Email All Checked", "Email All Checked Alert Text Succeeds");
		}
		else
		{			
			TestTemplate.testReport.logFailure("Email All Checked", "Email All Checked Alert Text Fails", this.getScreenShotName());
		}
		
	}
	
	@Test
	public void testC939915() throws InterruptedException
	{
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", "Aaron");
		mapIdSearchCriteria.put("searchLastName", "Cook");
		
		//Searching for the record
		phoneBookPage.searchingForARecord(mapIdSearchCriteria);
		
		//Verifying record is active link
	
		phoneBookPage.verifyRecordIsEnabled();
		
		//Clicking on the 1st record
		phoneBookPage.clickOn1StRecordLink();
		
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
	
	@Test
	public void testC939916() throws InterruptedException
	{
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", "Aaron");
		mapIdSearchCriteria.put("searchLastName", "Cook");
		
		//Searching for the record
		phoneBookPage.searchingForARecord(mapIdSearchCriteria);
		
		//Verifying record is active link
	
		phoneBookPage.verifyRecordIsEnabled();
		
		//Clicking on the 1st record
		phoneBookPage.clickOn1StRecordLink();
		
		//Get contact Title
		String values[] = phoneBookPage.getTextContactTitle();
				
		if(values[1].contains("Phone Book"))			
		{	
		TestTemplate.testReport.logSuccess("PhoneBookTitle", "Title Text Succeeds");
		}
			
		//Verifying Call Button In Phone book
		phoneBookPage.verifyCallButtonInPhoneBook();
		
		//Verifying Email Button In Phone book
		phoneBookPage.verifyEmailButtonInPhoneBook();
				
		//Verifying Return Button In Phone book
		phoneBookPage.verifyReturnButtonInPhoneBook();
		
	}
	@Test
	public void testC939917() throws InterruptedException
	{
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", "Aaron");
		mapIdSearchCriteria.put("searchLastName", "Cook");
		
		//Searching for the record
		phoneBookPage.searchingForARecord(mapIdSearchCriteria);
		
		//Verifying record is active link
	
		phoneBookPage.verifyRecordIsEnabled();
		
		//Clicking on the 1st record
		phoneBookPage.clickOn1StRecordLink();
		
		//Get contact Title
		String values[] = phoneBookPage.getTextContactTitle();
						
		if(values[1].contains("Phone Book"))			
		{	
		TestTemplate.testReport.logSuccess("PhoneBookTitle", "Title Text Succeeds");
		}
			
		//Clicking on Call Button In Phone book
		phoneBookPage.clickCallButtonInPhoneBook();
		
		//Clicking on Email Button In Phone book
		phoneBookPage.clickEmailButtonInPhoneBook();
		
		
		
		//Switch to New Window
		phoneBookPage.switchtoWindow();
		
		System.out.println("Switching to New Window");

		
		//Switch back to Default
		phoneBookPage.switchtoDefaultWindow();
		
		System.out.println("Window is switching back to default");
		
				
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

	@Test
	public void testC939918() throws InterruptedException
	{
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", "Aaron");
		mapIdSearchCriteria.put("searchLastName", "Cook");
		
		//Searching for the record
		phoneBookPage.searchingForARecord(mapIdSearchCriteria);
		
		//Verifying phone book is active link
		phoneBookPage.verifyPhoneNumberIsEnabled();
		
		//Click phone book is active link
		phoneBookPage.clickOnPhoneNumber();
		
			
	}
	@Test
	public void testC939919() throws InterruptedException
	{
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", "Aaron");
		mapIdSearchCriteria.put("searchLastName", "Cook");
		
		//Searching for the record
		phoneBookPage.searchingForARecord(mapIdSearchCriteria);
		
		//Verifying email is active link
		phoneBookPage.verifyEmailIsEnabled();
		
		//Click email is active link
		phoneBookPage.clickOnEmail();		
			
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
	
	@Test(description="Verify CHG Phonebook page is displayed")
	public void testC939955()
	{
		//Get Page Title
		String title=this.threadLocalWebDriver.get().getTitle();
		
		//Validate the CHG Phone book title
		if(title.equals("Phone Book")){
			
			TestTemplate.testReport.logSuccess("Phone Book page", "Phone Book page is successfully displayed.", this.getScreenShotName());
		}
		else
		{			
			TestTemplate.testReport.logFailure("Phone Book page", "Phone Book page is not displayed.", this.getScreenShotName());
		}
		
	}
}
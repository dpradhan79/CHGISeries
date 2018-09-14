package pages.iseries.rejectedpractitioners;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.asserts.SoftAssert;

import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;

public class RejectedPractitionersPage extends PageTemplate {

	private By userName									= By.xpath("//input[contains(@name,'j_username')]");
	private By password 								= By.xpath("//input[contains(@name,'password')]");
	private By loginButton 								= By.xpath("//input[contains(@value,'Login')]");
	private By searchCOMPHEALTHButton					= By.xpath("//input[@value='SEARCH COMPHEALTH']");
	private By searchRejectedPractitionersLink		 	= By.xpath("//a[text()='Search Rejected Practitioners']");
	private By rejectedPractitionersHomePage		 	= By.xpath("//form[contains(@action,'RejectedPractitionerSearch')]");
	private By byLastName 								= By.xpath("//input[@name='lastName']");
    private By byFirstName								= By.xpath("//input[@name='firstName']");
    private By byDateOfBirth 							= By.xpath("//input[@name='dob']");
    private By bySocialSecurity							= By.xpath("//input[@name='ssn']");
    private By byResetbtn 								= By.xpath("//input[@value='RESET']");

    private SoftAssert softAssert = null;
	public RejectedPractitionersPage(WebDriver webDriver, IReporter testReport) {
		super(webDriver, testReport);
		this.softAssert = null;
	}
	
	public RejectedPractitionersPage(WebDriver webDriver, IReporter testReport, SoftAssert softAssert) {
		super(webDriver, testReport);
		this.softAssert = softAssert;
	}
	
	/**
	 * Login to rejectedPractitioners Login application
	 */
	public void rejectedPractitionersLogin(ITestContext testContext)
	{
		//Wait until user name displayed then enter user name
		this.waitUntilElementIsVisible(userName);
		this.sendKeys(userName, testContext.getCurrentXmlTest().getParameter("userName"));
		//Enter password
		this.sendKeys(password, testContext.getCurrentXmlTest().getParameter("password"));
		
		//Click on login button
		this.click(loginButton);
	}
	
	/**
	 * Click on Search COMPHEALTH button
	 */
	private void clickOnSearchCompHealthButtonk() 
	{
		this.waitUntilElementIsClickable(searchCOMPHEALTHButton);
		this.click(searchCOMPHEALTHButton);
	}

	/**
	 * Click on Search Rejected Practitioners Link
	 */
	private void clickOnRejectedPractitionersLink() 
	{
		this.waitUntilElementIsClickable(searchRejectedPractitionersLink);
		this.click(searchRejectedPractitionersLink);
	}

	
	/**
	 * Click on Search Rejected Practitioners Link
	 */
	private void VerifyRejectedPractitionersText() 
	{
		this.waitUntilElementIsVisible(rejectedPractitionersHomePage);
		
		WebElement element = this.wd.findElement(rejectedPractitionersHomePage);
		
		if (element.isDisplayed()) {

            this.testReport.logSuccess("User Successfully back to to search page onclicking on Search Rejected Practioners link ");

     } else {

            this.testReport.logFailure("User Failed back to search page onclicking on Search Rejected Practioners link " + rejectedPractitionersHomePage,

                         this.getScreenShotName());

     }
		
	}

	
   public void verifyUserBackToSearchPage(ITestContext testContext) {
		// TODO Auto-generated method stub
		
		// click on Search COMPHEALTH button
		
		clickOnSearchCompHealthButtonk();
		
		// click on Search Rejected Practitioners Link
		
		clickOnRejectedPractitionersLink();
		
		// Verify that clicking on Search Rejected Practioners link takes user to search page
		
		VerifyRejectedPractitionersText();
	}
	
   /**

    * @param map

    *            contains all the data from Excel

    *

     *            Fill all the fields on Rejected Practitioner search page

    */

    public void fillAllFieldsInRejectedPractioners(Map<String, String> map) {



           // Fill all the fields

           for (Map.Entry<String, String> entrySet : map.entrySet()) {

                  String searchCriteria = entrySet.getKey();

                  String searchValue = entrySet.getValue();



                  switch (searchCriteria) {

                  case "LastName":

                        this.sendKeys(byLastName, searchValue);

                        break;

                  case "FirstName":

                        this.sendKeys(byFirstName, searchValue);

                        break;

                  case "DateOfBirth":

                        this.sendKeys(byDateOfBirth, searchValue);

                        break;

                  case "SocialSecurity":

                        this.sendKeys(bySocialSecurity, searchValue);

                        break;



                  }



           }



    }



    /**

    * Click on Reset and Validate fields are Reseted

    */



    public void resetFieldsInRejectedPractioners() {



           // Click on Reset button

           this.click(byResetbtn);



           // Validating fields are Reset

           this.isEmptyField(byLastName);

           this.isEmptyField(byFirstName);

           this.isEmptyField(byDateOfBirth);

           this.isEmptyField(bySocialSecurity);



    }



    /**

    * * @param By locator of WebElement Verify the field is Empty

    */



    private void isEmptyField(By by) {



           WebElement e = this.wd.findElement(by);



           this.softAssert.assertTrue(e.getText().isEmpty());



           if (e.getText().isEmpty()) {

                  this.testReport.logSuccess("Rejected Practitioner page",

                               "Field with Locator :" + by + " is Successfully Reseted");

           } else {

                  this.testReport.logFailure("Rejected Practitioner page", "Unable To Reset field with locator:" + by,

                               this.getScreenShotName());

           }

    }
    

    /**
    * Validated rejectedPractitioners Login application
    */
    public void validateRejectedPractitionersLogin()
    {
    String title = this.wd.getTitle();
    this.softAssert.assertEquals(title, "Rejected Practitioner Search");
    if(title.equals("Rejected Practitioner Search"))
    {
    this.testReport.logSuccess("Validate Page Title", "Expected Test is "+"Rejected Practitioner Search"+" Actual Text is "+title);
    }
    else
    {
    this.testReport.logFailure("Validate Page Title", "Expected Test is "+"Rejected Practitioner Search"+" Actual Text is "+title, this.getScreenShotName());
    }
    }
	

}
	

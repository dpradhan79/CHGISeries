package pages.iseries.rejectedpractitioners;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.asserts.SoftAssert;

import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;

public class RejectedPractitionersPage extends PageTemplate {

	private By userName									= By.xpath("//input[contains(@name,'j_username')]");
	private By password 								= By.xpath("//input[contains(@name,'password')]");
	private By loginButton 								= By.xpath("//input[contains(@value,'Login')]");
	private By searchCOMPHEALTHButton					= By.xpath("//input[@value='SEARCH COMPHEALTH']");
	private By searchWeatherByButton					= By.xpath("//input[@value='SEARCH WEATHERBY']");
	private By searchDestinationLocumTenensButton		= By.xpath("//input[@value='SEARCH DESTINATION LOCUM TENENS']");
	private By searchfmsButton							= By.xpath("//input[@value='SEARCH FMS']");
	private By searchRejectedPractitionersLink		 	= By.xpath("//a[text()='Search Rejected Practitioners']");
	private By rejectedPractitionersHomePage		 	= By.xpath("//form[contains(@action,'RejectedPractitionerSearch')]");
	private By byLastName 								= By.xpath("//input[@name='lastName']");
    private By byFirstName								= By.xpath("//input[@name='firstName']");
    private By byDateOfBirth 							= By.xpath("//input[@name='dob']");
    private By bySocialSecurity							= By.xpath("//input[@name='ssn']");
    private By byResetbtn 								= By.xpath("//input[@value='RESET']");
    private By searchResultNameLink                     = By.xpath("(//font[contains(text(),'Name')]/../../following-sibling::tr//a)[1]");
    private By searchRejectedPractitionersPage          = By.xpath("//b[contains(text(),'Rejected Practitioners')]");

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
	 * Click on Search WeatherBy Button
	 */
	public void clickOnSearchWeatherByButtonk() 
	{
		this.waitUntilElementIsClickable(searchWeatherByButton);
		this.click(searchWeatherByButton);
	}

	/**
	 * Click on Search DestinationLocumTenensButton button
	 */
	public void clickOnSearchDestinationLocumTenensButtonButton() 
	{
		this.waitUntilElementIsClickable(searchDestinationLocumTenensButton);
		this.click(searchDestinationLocumTenensButton);
	}
	
	
	/**
	 * Click on Search FMS Button
	 */
	public void clickOnSearchfmsButtonk() 
	{
		this.waitUntilElementIsClickable(searchfmsButton);
		this.click(searchfmsButton);
	}
	
	
	/**
	 * Click on Search COMPHEALTH button
	 */
	public void clickOnSearchCompHealthButtonk() 
	{
		this.waitUntilElementIsClickable(searchCOMPHEALTHButton);
		this.click(searchCOMPHEALTHButton);
	}
	
	/**
	 * Click on Search Rejected Practitioners Link
	 */
	public void clickOnRejectedPractitionersLink() 
	{
		this.waitUntilElementIsClickable(searchRejectedPractitionersLink);
		this.click(searchRejectedPractitionersLink);
	}

	
	/**
	 * User Successfully back to to search page on clicking on Search Rejected Practioners link
	 */
	private void VerifyRejectedPractitionersText() 
	{
		this.waitUntilElementIsVisible(rejectedPractitionersHomePage);
		
		WebElement element = this.wd.findElement(rejectedPractitionersHomePage);
		
		if (element.isDisplayed()) {

            this.testReport.logSuccess("User Successfully back to to search page on clicking on Search Rejected Practioners link ");

     } else {

            this.testReport.logFailure("User Failed back to search page onclicking on Search Rejected Practioners link " + rejectedPractitionersHomePage,

                         this.getScreenShotName());
            this.softAssert.fail();

     }
		
	}

	/**
	 * Verify that clicking on Search Rejected Practioners link takes user to search page
	 */
	
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
	 * Verify that Application is not slow when user clicks on Search COMPHEALTHButton
	 */
	
  public void verifyAppPerformanceSEARCHCOMPHEALTHButton(ITestContext testContext) {
		// TODO Auto-generated method stub
		
		// click on Search COMPHEALTH button
		
		clickOnSearchCompHealthButtonk();
		
		this.isElementIsVisible(searchRejectedPractitionersPage);
			
	}
  
  
	/**
	 * Verify that Application is not slow when user clicks on Search WEATHERBY Button
	 */
	
  public void verifyAppPerformanceSEARCHWEATHERBYButton(ITestContext testContext) {
		// TODO Auto-generated method stub
		
		// click on Search WEATHERBY button
		
		clickOnSearchWeatherByButtonk();
		
		this.isElementIsVisible(searchRejectedPractitionersPage);
			
	}

  
	/**
	 * Verify that Application is not slow when user clicks on Search FMS Button
	 */
	
  public void verifyAppPerformanceSEARCHFMSButton(ITestContext testContext) {
	
		
		// click on Search FMS button
		
		clickOnSearchfmsButtonk();
		
		this.isElementIsVisible(searchRejectedPractitionersPage);
			
	}
  
	/**
	 * Verify that Application is not slow when user clicks on Search FMS Button
	 */
	
  public void verifyAppPerformanceSEARCHDestinationLocumTenensButton(ITestContext testContext) {
	
		
		// click on Search FMS button
		
	  	clickOnSearchDestinationLocumTenensButtonButton();
		
		this.isElementIsVisible(searchRejectedPractitionersPage);
					
	}
  
  // is Element Visible
  
	private boolean isElementIsVisible(By byLocator) {
		boolean isSuccess = false;
		try {
			
			WebDriverWait wait = new WebDriverWait(this.wd, this.implicitWaitInSecs);
			wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
			this.testReport.logSuccess("waitUntilElementIsVisible",
					String.format("Element visible - (By - %s)", byLocator));
			isSuccess = true;
		} catch (Exception ex) {
			isSuccess = false;
			this.testReport.logFailure("waitUntilElementIsVisible",
			String.format("Exception Encountered - %s, StackTrace - %s", ex.getMessage(), ex.getStackTrace(),
							this.getScreenShotName()));
			}
		return isSuccess;
	}
  
  	/**
	 * click on Rejected Practitioners Search Name Link
	 */
	private void clickOnRejectedPractitionersNameLink() 
	{
		this.waitUntilElementIsClickable(searchResultNameLink);
		this.click(searchResultNameLink);
	}
	
	/**
	 * Verify the comment window
	 */
	private void verifyCommentWindow() 
	{
			String title = this.wd.getTitle();
		    this.softAssert.assertEquals(title, "Comments");
		    if(title.equals("Comments"))
		    {
		    this.testReport.logSuccess("Validate Page Title", "Expected Test is "+"Comments"+" Actual Text is "+title);
		    }
		    else
		    {
		    this.testReport.logFailure("Validate Page Title", "Expected Test is "+"Comments"+" Actual Text is "+title, this.getScreenShotName());
		    }
	}
	
	/**
	 * Verify the search name link and comment window
	 * @throws InterruptedException 
	 */
	
	public void verifyNameLinkAndCommentWindow() throws InterruptedException {
		// TODO Auto-generated method stub
		
		Thread.sleep(5000);
		// click on Rejected Practitioners Search Name Link
		
		clickOnRejectedPractitionersNameLink();
		
		switchtoWindow();
		
		// Verify the comment window
		verifyCommentWindow();
		
		closeWindow();
	
		switchtoWindow();
		
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
	 * Switch To Window
	 */
	private void switchtoWindow() 
	{
		for(String windowName : wd.getWindowHandles())	
		{
		 wd.switchTo().window(windowName);
		}
	
		}
	/**
	 * Switch To Default Window
	 */
	private void switchtoDefaultWindow() 
	{	
		 wd.switchTo().defaultContent();
	
	}
	
	/**
	 * Close Window
	 */
	private void closeWindow() 
	{	
		 wd.close();
	
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
	

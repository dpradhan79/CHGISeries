package pages.iseries.loginpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.asserts.SoftAssert;

import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;


public class LoginPage extends PageTemplate {

	private SoftAssert softAssert = null;
	public LoginPage(WebDriver webDriver, IReporter testReport) {
		super(webDriver, testReport);
		this.softAssert = new SoftAssert();
	}
	
	private By userName = By.xpath("//input[contains(@name,'username')]");
	private By password = By.xpath("//input[contains(@name,'password')]");
	private By loginButton = By.xpath("//span[contains(text(),'Login')]");
	private By foxUserName = By.xpath("//*[@id='username']");
	private By foxPassword = By.xpath("//*[@id='password']");
	private By foxLoginButton = By.xpath("//*[@id='Login']");
	private By titleAfterFoxLogin = By.xpath("//*[contains(text(),'CHG Healthcare Services Internal')]");
	private By foxSearchField = By.xpath("//*[@id='phSearchInput']");
	private By arrowIcon = By.xpath("//b[contains(@class,'selectArrow')]/parent::a[@id='moderatorMutton']");
	private By userDetail = By.xpath("//*[@id='USER_DETAIL']/span");
	private By userDetailLogin = By.xpath("(//input[@title='Login'])[1]");
	private By qaEvalsUserName = By.xpath("//*[@id='j_username']");
	private By qaEvalsPassword = By.xpath("//*[@id='j_password']");
	private By qaEvalsSubmitButton = By.xpath("//*[@id='submit']");
	
	/**
	 * Login to JDE vendor search application
	 */
	public void jdeVendorSearchLogin(ITestContext testContext)
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
	 * @param testContext
	 * login to fox application and division
	 */
	public void loginToFoxApplicationAndDivision(ITestContext testContext)
	{
		//Wait until user name displayed and enter user name
		this.waitUntilElementIsClickable(foxUserName);
		this.sendKeys(foxUserName, testContext.getCurrentXmlTest().getParameter("userName")+testContext.getCurrentXmlTest().getParameter("environment"));
		
		//Enter password
		this.sendKeys(foxPassword, testContext.getCurrentXmlTest().getParameter("password"));
		
		//Click on login button
		this.click(foxLoginButton);
		
		//Wait until application logged in
		this.waitUntilElementIsVisible(titleAfterFoxLogin);
		
		//Wait until search field displayed and enter division name
		this.waitUntilElementIsClickable(foxSearchField);
		this.sendKeys(foxSearchField, testContext.getCurrentXmlTest().getParameter("division"));
		
		//wait until division displayed search results and click on division
		this.waitUntilElementIsClickable(By.xpath("//strong[text()='"+testContext.getCurrentXmlTest().getParameter("division")+"']"));
		this.implicitwait(2);
		this.click(By.xpath("//strong[text()='"+testContext.getCurrentXmlTest().getParameter("division")+"']"));
		
		//Click on division details arrow
		this.waitUntilElementIsClickable(arrowIcon);
		this.implicitwait(3);
		this.click(arrowIcon);
		
		//Click on user details
		this.waitUntilElementIsClickable(userDetail);
		this.implicitwait(2);
		this.click(userDetail);
		
		//Click on login button
		this.waitUntilElementIsClickable(userDetailLogin);
		this.implicitwait(3);
		this.click(userDetailLogin);
		
		//wait until division login
		this.waitUntilElementIsVisible(By.xpath("//*[contains(text(),'Logged in as "+testContext.getCurrentXmlTest().getParameter("division")+"')]"));
	}

	/**
	 * @param testContext
	 * Login to qa evals
	 */
	public void qaEvalsLogin(String userName, String password)
	{
		//Wait until user name displayed and enter user name
		this.waitUntilElementIsClickable(qaEvalsUserName);
		this.sendKeys(qaEvalsUserName, userName);
			
		//Enter password
		this.sendKeys(qaEvalsPassword, password);
				
		//Click on login button
		this.click(qaEvalsSubmitButton);
		this.implicitwait(2);
				
		//Wait until application logged in
		//this.waitUntilElementIsVisible(titleAfterFoxLogin);
	}
}

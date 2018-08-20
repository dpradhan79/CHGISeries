package pages.iseries.loginpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;


public class LoginPage extends PageTemplate {

	private SoftAssert softAssert = null;
	public LoginPage(WebDriver webDriver, IReporter testReport) {
		super(webDriver, testReport);
		this.softAssert = new SoftAssert();
	}
	
	By userName = By.xpath("//input[contains(@name,'username')]");
	By password = By.xpath("//input[contains(@name,'password')]");
	By loginButton = By.xpath("//span[contains(text(),'Login')]");
	By vendorSearchTitle = By.xpath("//div[@class='pageTitle' and contains(text(),'Vendor Search Form')]");
	
	
	/**
	 * Login to JDE vendor search application
	 */
	public void jdeVendorSearchLogin()
	{
		this.waitUntilElementIsVisible(userName);
		this.sendKeys(userName, "chstest");
		this.sendKeys(password, "!L0veMyJ0b");
		this.click(loginButton);
	}

}

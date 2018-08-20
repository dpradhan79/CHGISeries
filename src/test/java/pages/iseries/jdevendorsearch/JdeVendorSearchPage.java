package pages.iseries.jdevendorsearch;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;

public class JdeVendorSearchPage extends PageTemplate {

	By informationText	 	= By.xpath("//*[contains(text(),'You have tried')]");
	By alertText 			= By.xpath("//*[contains(text(),'Do not')]/parent::font");
	By auth 				= By.xpath("//div[@class='header']/div[3]");
	private SoftAssert softAssert = null;
	public JdeVendorSearchPage(WebDriver webDriver, IReporter testReport) {
		super(webDriver, testReport);
		this.softAssert = new SoftAssert();
	}

	public void validateTextPresent(By locator, String expecxtedText)
	{
		try{
			String actualText = this.getText(locator);
			if((actualText.replaceAll("[\r\n]+", " ")).equalsIgnoreCase(expecxtedText))
			{
				this.testReport.logSuccess("Validate Text Present", "Expected Test is "+expecxtedText+" Actual Text is "+actualText);
			}
			else
			{
				this.testReport.logFailure("Validate Text Present", "Expected Test is "+expecxtedText+" Actual Text is "+actualText, this.getScreenShotName());
			}
		}catch(Exception e)
		{
			this.testReport.logFailure("Validate Text Present", e.getMessage().toString(), this.getScreenShotName());
		}
	}
	
	public void validateInformationPresentOnLoginPage()
	{
		validateTextPresent(informationText, "You have tried to access a secure area. Please enter your user name and password to gain access.");
		validateTextPresent(alertText, "Do not bookmark this page. Doing so will cause an error on your next visit. The correct procedure is to bookmark the page that appears after you click \"login\".");
		validateTextPresent(auth, "Not logged in");
	}
}

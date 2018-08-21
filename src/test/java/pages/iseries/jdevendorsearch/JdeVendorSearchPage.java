package pages.iseries.jdevendorsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;

public class JdeVendorSearchPage extends PageTemplate {

	By informationText	 			= By.xpath("//*[contains(text(),'You have tried')]");
	By alertText 					= By.xpath("//*[contains(text(),'Do not')]/parent::font");
	By auth 						= By.xpath("//div[@class='header']/div[3]");
	By vendorSearchTitle 			= By.xpath("//div[@class='pageTitle' and contains(text(),'Vendor Search Form')]");
	By fieldsPresentInSearchForm	= By.xpath("//form[@id='vendorSearchForm']//tr//input/../parent::tr/td[1][not(contains(@class,'button'))]");
	
	
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
	
	public void validateLoggedInToApplication()
	{
		this.waitUntilElementIsVisible(vendorSearchTitle);
		validateTextPresent(vendorSearchTitle, "Vendor Search Form");
	}
	
	public void validateFieldsPresentInSearchForm()
	{
		List<String> expectedList = new ArrayList<>(Arrays.asList("Vendor Name:", "Vendor State (abbrev):", "Vendor Zip:", "Vendor Number:"));
		List<WebElement> fieldsExist = this.wd.findElements(fieldsPresentInSearchForm);
		for(int i=0; i<fieldsExist.size(); i++)
		{
			if(fieldsExist.get(i).getText().equalsIgnoreCase(expectedList.get(i)))
			{
				this.testReport.logSuccess("Validate Text Present", "Expected Test is "+expectedList.get(i)+" Actual Text is "+fieldsExist.get(i).getText());
			}
			else
			{
				this.testReport.logFailure("Validate Text Present", "Expected Test is "+expectedList.get(i)+" Actual Text is "+fieldsExist.get(i).getText(), this.getScreenShotName());
			}
		}
	}
}

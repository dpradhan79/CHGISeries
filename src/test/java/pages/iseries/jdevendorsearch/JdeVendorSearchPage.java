package pages.iseries.jdevendorsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;

public class JdeVendorSearchPage extends PageTemplate {

	private By informationText	 			= By.xpath("//*[contains(text(),'You have tried')]");
	private By alertText 					= By.xpath("//*[contains(text(),'Do not')]/parent::font");
	private By auth 						= By.xpath("//div[@class='header']/div[3]");
	private By vendorSearchTitle 			= By.xpath("//div[@class='pageTitle' and contains(text(),'Vendor Search Form')]");
	private By fieldsPresentInSearchForm	= By.xpath("//form[@id='vendorSearchForm']//tr//input/../parent::tr/td[1][not(contains(@class,'button'))]");
	private By serachJDEButton				= By.xpath("//input[@value='Search JDE']");
	private By resetButton					= By.xpath("//input[@value='Reset']");
	private By vendorNameInput				= By.xpath("//td[text()='Vendor Name:']/parent::tr//input");
	private By vendorNameof1stRecord		= By.xpath("//*[text()='Vendor Name']/../parent::tr/../parent::table//tbody/tr[1]/td[2]");
	private By vendorStateInput				= By.xpath("//td[text()='Vendor State (abbrev):']/parent::tr//input");
	private By vendorZipInput				= By.xpath("//td[text()='Vendor Zip:']/parent::tr//input");
	private By vendorNumberInput			= By.xpath("//td[text()='Vendor Number:']/parent::tr//input");
	private By vendorTableHeaders			= By.xpath("//table[@id='VendorList']//th[@role='columnheader']/div");
	private By vendorSearchResults1stDetail	= By.xpath("//table[@id='VendorList']/tbody/tr[1]/td");
	
	private SoftAssert softAssert = null;
	public JdeVendorSearchPage(WebDriver webDriver, IReporter testReport) {
		super(webDriver, testReport);
		this.softAssert = new SoftAssert();
	}

	/**
	 * @param locator
	 * @param expecxtedText
	 * Validates text in locator and expected test is equals
	 */
	private void validateTextPresent(By locator, String expecxtedText)
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
	
	/**
	 * @param expected
	 * @param actual
	 * Validated expected and actual texts are equal
	 */
	private void validateTextEquals(String expected, String actual)
	{
		try{
			if((actual.replaceAll("[\r\n]+", " ")).equalsIgnoreCase(expected))
			{
				this.testReport.logSuccess("Validate Text Present", "Expected Test is "+expected+" Actual Text is "+actual);
			}
			else
			{
				this.testReport.logFailure("Validate Text Present", "Expected Test is "+expected+" Actual Text is "+actual, this.getScreenShotName());
			}
		}catch(Exception e)
		{
			this.testReport.logFailure("Validate Text Present", e.getMessage().toString(), this.getScreenShotName());
		}
	}
	
	/**
	 * Validates information present on Login page
	 */
	public void validateInformationPresentOnLoginPage()
	{
		validateTextPresent(informationText, "You have tried to access a secure area. Please enter your user name and password to gain access.");
		validateTextPresent(alertText, "Do not bookmark this page. Doing so will cause an error on your next visit. The correct procedure is to bookmark the page that appears after you click \"login\".");
		validateTextPresent(auth, "Not logged in");
	}
	
	/**
	 * Validates is application logged in successfully 
	 */
	public void validateLoggedInToApplication()
	{
		this.waitUntilElementIsVisible(vendorSearchTitle);
		validateTextPresent(vendorSearchTitle, "Vendor Search Form");
	}
	
	/**
	 * Validates list fields present in the search form
	 */
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
		
		//Verify search button displayed
		verifyElementPresent(serachJDEButton, "Search JDE Button");
		
		//Verify reset button displayed
		verifyElementPresent(resetButton, "Reset Button");
	}
	
	/**
	 * @param locator
	 * @param elementTitle
	 * Verifies is element present on screen
	 */
	private void verifyElementPresent(By locator, String elementTitle)
	{
		try
		{
			this.wd.findElement(locator);
			this.testReport.logSuccess("Verify element Present", " "+elementTitle+" displayed on screen");
		}
		catch(Exception e)
		{
			this.testReport.logFailure("Verify element Present", " "+elementTitle+" displayed on application "+e.getMessage().toString(), this.getScreenShotName());
		}
	}
	
	/**
	 * @param vendorName
	 * Search for a vendor using vendor name
	 */
	public void searchForAVendorAndValidate(String vendorName)
	{
		this.sendKeys(vendorNameInput, vendorName);
		this.click(serachJDEButton);
		this.implicitwait(3);
		validateTextPresent(vendorNameof1stRecord, vendorName);
	}
	
	/**
	 * @param state
	 * Search for a vendor using vendor State
	 */
	public void searchForAVendorWithStateAndValidate(String state)
	{
		//Enter state into the field
		this.sendKeys(vendorStateInput, state);
		
		//Click on seach button
		this.click(serachJDEButton);
		this.implicitwait(3);
		
		//Get the values and store to a Map
		List<WebElement> vendorTableColumns = this.wd.findElements(vendorTableHeaders);
		List<WebElement> vendorResults1stRowDetails = this.wd.findElements(vendorSearchResults1stDetail);		
		Map<String, String> details = new HashMap<String, String>();
		for(int i=0; i<vendorTableColumns.size(); i++)
		{
			details.put(vendorTableColumns.get(i).getText(), vendorResults1stRowDetails.get(i).getText());
		}

		//Validation
		validateTextEquals(state, details.get("State"));
	}
	
	/**
	 * @param zip
	 * Search for a vendor using vendor Zip
	 */
	public void searchForAVendorWithZipAndValidate(String zip)
	{
		//Enter zip into the field
		this.sendKeys(vendorZipInput, zip);
		
		//Click on search button
		this.click(serachJDEButton);
		this.implicitwait(3);

		//Get the values and store to a Map
		List<WebElement> vendorTableColumns = this.wd.findElements(vendorTableHeaders);
		List<WebElement> vendorResults1stRowDetails = this.wd.findElements(vendorSearchResults1stDetail);
		Map<String, String> details = new HashMap<String, String>();
		for(int i=0; i<vendorTableColumns.size(); i++)
		{
			details.put(vendorTableColumns.get(i).getText(), vendorResults1stRowDetails.get(i).getText());
		}

		//Validation
		validateTextEquals(zip, details.get("Zip"));
	}
	
	/**
	 * @param vendorNumber
	 * Search for a vendor using vendor number
	 */
	public void searchForAVendorWithNumberAndValidate(String vendorNumber)
	{
		//Enter vendor number in field
		this.sendKeys(vendorNumberInput, vendorNumber);
		
		//Click on search button
		this.click(serachJDEButton);
		this.implicitwait(3);

		//Get the values and store to a Map
		List<WebElement> vendorTableColumns = this.wd.findElements(vendorTableHeaders);
		List<WebElement> vendorResults1stRowDetails = this.wd.findElements(vendorSearchResults1stDetail);
		Map<String, String> details = new HashMap<String, String>();
		for(int i=0; i<vendorTableColumns.size(); i++)
		{
			details.put(vendorTableColumns.get(i).getText(), vendorResults1stRowDetails.get(i).getText());
		}

		//Validation
		validateTextEquals(vendorNumber, details.get("Vendor Number"));
	}
	
	/**
	 * @param vendorName
	 * @param state
	 * @param zip
	 * @param number
	 * Search for a vendor by giving all the fields
	 */
	public void searchForAVendorWithAllFieldsAndValidate(String vendorName, String state, String zip, String number)
	{
		//Type in values in all fields
		this.sendKeys(vendorNameInput, vendorName);
		this.sendKeys(vendorStateInput, state);
		this.sendKeys(vendorZipInput, zip);
		this.sendKeys(vendorNumberInput, number);
		
		//Click on search button
		this.click(serachJDEButton);
		this.implicitwait(3);

		//Get the values and store to a Map
		List<WebElement> vendorTableColumns = this.wd.findElements(vendorTableHeaders);
		List<WebElement> vendorResults1stRowDetails = this.wd.findElements(vendorSearchResults1stDetail);		
		Map<String, String> details = new HashMap<String, String>();
		for(int i=0; i<vendorTableColumns.size(); i++)
		{
			details.put(vendorTableColumns.get(i).getText(), vendorResults1stRowDetails.get(i).getText());
		}

		//Validations
		validateTextEquals(number, details.get("Vendor Number"));
		validateTextEquals(zip, details.get("Zip"));
		validateTextEquals(state, details.get("State"));
		validateTextEquals(vendorName, details.get("Vendor Name"));
	}
}

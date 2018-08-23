package pages.iseries.phonebook;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;

public class PhoneBookPage extends PageTemplate {

	private By byFirstNameTextBox = By.id("searchFirstName");
	private By byLastNameTextBox = By.id("searchLastName");
	private By bySearchButton = By.xpath("//input[@value='Search']");
	private By bySearchListCheckBox = By.xpath("//input[@type='checkbox']");
	private By byEmailAllCheckedLink = By.xpath("//a[@id='emailCheckedLink']");
	private By by1StRecordLink = By.xpath("(//span[@class='DropPhone']//a)[1]");
	private By byPhoneBookTitle = By.xpath("//td[@class='Title']");
	private By byPhoneBookLogo = By.xpath("//img[@alt='CHG']");
	private By byCallButton = By.xpath("//input[@value='Call']");
	private By byEmailButton = By.xpath("//input[@value='Email']");
	private By byReturnButton = By.xpath("//input[@value='Return']");
	private By byPhoneNumberLink = By.xpath("(//span[@class='DropPhone']//a)[2]");
	private By byEmailLink = By.xpath("(//span[@class='DropPhone']//a)[3]");
	private By byResetBtn = By.xpath("//input[@value='Reset']");
	private By byPhoneTextBox = By.id("searchPhone");
	private By byOfficeDropdown = By.id("searchOffice");
	private By byEmailBox = By.id("searchEmail");
	private By byJobTitleDropdown = By.id("searchTitle");
	private By byTeamDropdown = By.id("searchTeam");
	private By bySearchListCheckBox_Name = By.xpath("(//span[@class='DropPhone']/a)[1]");
	private By bySearchListCheckBox_PhoneNo = By.xpath("(//span[@class='DropPhone']/a)[2]");
	private By bySearchListCheckBox_Email = By.xpath("//a[contains(text(),'.com')]");
	private By bySearchListCheckBox_Team = By.xpath("(//*[@class='Value odd'])[4]/span");
	private By bySearchListCheckBox_TitleAndOffice = By.xpath("(//*[@class='Value odd'])[3]/span");
	
	private SoftAssert softAssert = null;
	private Object retuen;
	public PhoneBookPage(WebDriver webDriver, IReporter testReport) {
		super(webDriver, testReport);
		this.softAssert = new SoftAssert();
	}
	//interface for search phone book
	public int searchPhoneBook(Map<String, String>mapIdSearchTextBoxes)
	{
		for(Map.Entry<String, String> entrySet : mapIdSearchTextBoxes.entrySet())
		{
			String searchCriteria = entrySet.getKey();
			String searchValue = entrySet.getValue();
			
		    switch(searchCriteria)
		    {
			    case "searchFirstName":
			    this.sendKeys(byFirstNameTextBox, searchValue);			    
			    break;
			    
		    }
		}
		this.click(bySearchButton);
		int iCountCheckBoxes = this.wd.findElements(bySearchListCheckBox).size();
		return iCountCheckBoxes;
		
	}
	
	/**
	 * Clicking on Search button 
	 */
	public void clickOnSearchButton()
	{
		this.waitUntilElementIsClickable(bySearchButton);
		this.click(bySearchButton);
	}
	
	/**
	 * Searching For Record
	 */
	public void searchingForARecord(Map<String, String>mapIdSearchTextBoxes)
	{
		for(Map.Entry<String, String> entrySet : mapIdSearchTextBoxes.entrySet())
		{
			String searchCriteria = entrySet.getKey();
			String searchValue = entrySet.getValue();
			
		    switch(searchCriteria)
		    {
			    case "searchFirstName":
			    this.wd.findElement(byFirstNameTextBox).sendKeys(searchValue);
			    break;
			    
			    case "searchLastName":
				this.wd.findElement(byLastNameTextBox).sendKeys(searchValue);
				break;
			    
		    }
		}
		clickOnSearchButton();
	}
	
	/**
	 * Check 1st record check-box in search results 
	 */
	public void check1StRecordInSearchResults()
	{
		this.waitUntilElementIsClickable(bySearchListCheckBox);
		this.click(bySearchListCheckBox);
	}
	
	/**
	 * Click on Email all Checked link
	 */
	public void clickOnEmailAllCheckedlink()
	{
		this.waitUntilElementIsClickable(byEmailAllCheckedLink);
		this.click(byEmailAllCheckedLink);
	}
	
	/**
	 * Uncheck 1st Record If Checked
	 */
	public void uncheck1stRecordIfChecked(Map<String, String>mapIdSearchTextBoxes) throws InterruptedException
	{
		WebElement checkBox = this.wd.findElement(bySearchListCheckBox);

		// Wait until element is clickable
		this.waitUntilElementIsClickable(bySearchListCheckBox);

		// Click on check-box
		if (checkBox.isSelected()) {
			this.click(bySearchListCheckBox);
		} else {
			System.out.println("check-box un-checked");
		}			
	}
	
	/**
	 * Get text from Email all Checked alert
	 */
	public String getTextFromEmailAllCheckedAlert()
	{
		Alert alert = wd.switchTo().alert();
		String alertText = alert.getText();
		return alertText;
	}
	/**
	 * Verify Record is Enabled
	 */
	public void verifyRecordIsEnabled() 
	{
		assertTrue(wd.findElement(by1StRecordLink).isEnabled());			
	}
	
	/**
	 * Click on the 1st Record
	 */
	public void clickOn1StRecordLink() 
	{
		this.waitUntilElementIsClickable(by1StRecordLink);
		this.click(by1StRecordLink);
	}
	
	/**
	 * Verify Call Button In Phone book
	 */
	public void verifyCallButtonInPhoneBook() 
	{
		this.waitUntilElementIsVisible(byCallButton);
	}
	
	/**
	 * Verify Email Button In Phone book
	 */
	public void verifyEmailButtonInPhoneBook() 
	{
		this.waitUntilElementIsVisible(byEmailButton);
	}
	
	/**
	 * Verify Return Button In Phone book
	 */
	public void verifyReturnButtonInPhoneBook() 
	{
		this.waitUntilElementIsVisible(byReturnButton);
	}
	
	/**
	 * Click on  Call Button In Phone book
	 */
	public void clickCallButtonInPhoneBook() 
	{
		this.waitUntilElementIsVisible(byCallButton);
		this.click(byCallButton);
	}
	
	/**
	 * Click on Email Button In Phone book
	 */
	public void clickEmailButtonInPhoneBook() 
	{
		this.waitUntilElementIsVisible(byEmailButton);
		this.click(byEmailButton);
	}
	
	/**
	 * Click on Return Button In Phone book
	 */
	public void clickReturnButtonInPhoneBook() 
	{
		this.waitUntilElementIsVisible(byReturnButton);
		this.click(byReturnButton);
	}
	
	/**
	 * getTextContactInfo
	 */
	public String[] getTextContactTitle()
	{
		String PhoneBookTitle = wd.findElement(byPhoneBookTitle).getText();
		String PhoneBookLogo = wd.findElement(byPhoneBookLogo).getAttribute("alt");
		return new String[] { PhoneBookLogo , PhoneBookTitle };
		
	}
	/**
	 * getTextContactInfo
	 * @return 
	 */
	public List<WebElement> getTextContactInfo() 
	{
		List<WebElement> columnTitles = this.wd.findElements(By.xpath("//td[@class='DetailBold']"));
		 
		List<WebElement> columnvalues = this.wd.findElements(By.xpath("//div[@class='Detail']"));
		Map<String, String> details = new HashMap<String, String>();
		
		for(int i=0;i<columnTitles.size();i++)
		{
			details.put(columnTitles.get(i).getText(), columnvalues.get(i).getText());
		}
		
		System.out.println(details.keySet());
		System.out.println(details.values());
		
		return columnvalues;
	}
	
	/**
	 * Verify Return To Phone Book Page
	 */
	public boolean VerifyReturnToPhoneBookPage() 
	{
		boolean value = wd.findElement(by1StRecordLink).isEnabled();
		return value;
	}
	
	/**
	 * Switch To Window
	 */
	public void switchtoWindow() 
	{
		for(String windowName : wd.getWindowHandles())	
		{
		 wd.switchTo().window(windowName);
		}
	
		}
	/**
	 * Switch To Default Window
	 */
	public void switchtoDefaultWindow() 
	{	
		 wd.switchTo().defaultContent();
	
	}
	
	/**
	 * Close Window
	 */
	public void closeWindow() 
	{	
		 wd.close();
	
	}
	/**
	 * Verify PhoneNumber is Enabled
	 */
	public void verifyPhoneNumberIsEnabled() 
	{
		assertTrue(wd.findElement(byPhoneNumberLink).isEnabled());			
	}
	
	/**
	 * click on PhoneNumber
	 */
	public void clickOnPhoneNumber() 
	{
		this.click(byPhoneNumberLink);
	}
	
	/**
	 * Verify Email is Enabled
	 */
	public void verifyEmailIsEnabled() 
	{
		assertTrue(wd.findElement(byEmailLink).isEnabled());			
	}
	
	/**
	 * click on Email
	 */
	public void clickOnEmail() 
	{
		this.click(byEmailLink);
	}
	
	/**
	 * click on Reset button
	 */
	private void clickOnReset() 
	{
		this.click(byResetBtn);
	}
	
	/**
     * Verify all fields are Reset in the Phone book page
     */
	
	public void verifyPhoneBookFiledsReset(Map<String, String>mapIdSearchTextBoxes)
	{
		//Filling all fields in Phone book page
		
		FillPhoneBookFileds(mapIdSearchTextBoxes);
		
		// Click on Reset button.
		
		clickOnReset();
		
		String firstname = this.wd.findElement(byFirstNameTextBox).getAttribute("value");
		
		List<WebElement> textfields = wd.findElements(By.xpath("//table[@class='SearchField']"));
		List<WebElement> pickupfields = wd.findElements(By.xpath("//table[@class='SearchField DropTable']"));
		
	    for(int i=0; i<textfields.size(); i++)
	    {
	    	if(textfields.get(i).getAttribute("value").isEmpty())
			{
				this.testReport.logSuccess("Validate Field", "Expected Test is Null" + "Actual Text is "+ textfields.get(i).getText());
	        }
	        else
	        {
	              this.testReport.logFailure("Validate Field", "Expected Test is Null" + "Actual Text is "+ textfields.get(i).getText(), this.getScreenShotName());
	        }
			
	    }
		
	    for(int i=0; i<pickupfields.size(); i++)
	    {
	    	if(pickupfields.get(i).getAttribute("value").isEmpty())
			{
				this.testReport.logSuccess("Validate Field", "Expected Test is Null" + "Actual Text is "+ pickupfields.get(i).getText());
	        }
	        else
	        {
	              this.testReport.logFailure("Validate Field", "Expected Test is Null" + "Actual Text is "+ pickupfields.get(i).getText(), this.getScreenShotName());
	        }
			
	    }
		
	}
	
	/**
     * Fill all the fields in the Phone book
     */
	
	private void FillPhoneBookFileds(Map<String, String>mapIdSearchTextBoxes)
	{
		for(Map.Entry<String, String> entrySet : mapIdSearchTextBoxes.entrySet())
		{
			String searchCriteria = entrySet.getKey();
			String searchValue = entrySet.getValue();
						
		    switch(searchCriteria)
		    {
			    case "searchFirstName":
			    	 this.sendKeys(byFirstNameTextBox, searchValue);
			    break;
			    case "searchLastName":
			    	 this.sendKeys(byLastNameTextBox, searchValue);
				    break;	
			    case "searchPhone":
				    this.sendKeys(byPhoneTextBox, searchValue);
				    break;
			    case "searchOffice":
				    this.SelectDropDownByText(byOfficeDropdown, searchValue);
				    break;
			    case "searchEmail":
				    this.sendKeys(byEmailBox, searchValue);
				    break;
			    case "searchTitle":
				    this.SelectDropDownByText(byJobTitleDropdown, searchValue);
				    break;
			    case "searchTeam":
				    this.SelectDropDownByText(byTeamDropdown, searchValue);
				    break;
				   
		    }
		 			    
		}
	}
	
	/**
     * Fill all the fields in the Phone book and click on search btn and verify the record
     */
	
	public String[] verifyPhoneBookFileds(Map<String, String>mapIdSearchTextBoxes)
	{
		FillPhoneBookFileds(mapIdSearchTextBoxes);
		String[] recordDetails = new String[5];
		
		this.click(bySearchButton);
		
		int iCountCheckBoxes = this.wd.findElements(bySearchListCheckBox).size();
		softAssert.assertTrue(iCountCheckBoxes == 1, "Validation for Successful Search Of Single Record");
		
		recordDetails[0]=this.getText(bySearchListCheckBox_Name);				
		recordDetails[1]=this.getText(bySearchListCheckBox_PhoneNo);
		recordDetails[2]=this.getText(bySearchListCheckBox_Email);
		recordDetails[3]=this.getText(bySearchListCheckBox_TitleAndOffice);
		recordDetails[4]=this.getText(bySearchListCheckBox_Team);
		
		return recordDetails;
	}
	
	/**
     * @param expected
     * @param actual
     * Validated expected and actual texts are equal
     */
	
	
	/**
     * @param expected
     * @param actual
     * Validated expected and actual texts are equal
     */
     public void validateTextEquals(String expected, String actual)
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
}
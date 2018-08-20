package pages.iseries.phonebook;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;

public class PhoneBookPage extends PageTemplate {

	private By byFirstNameTextBox = By.id("searchFirstName");
	private By bySearchButton = By.xpath("//input[@value='Search']");
	private By bySearchListCheckBox = By.xpath("//input[@type='checkbox']");
	private SoftAssert softAssert = null;
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
			    this.wd.findElement(byFirstNameTextBox).sendKeys(searchValue);
			    break;
		    }
		}
		this.wd.findElement(bySearchButton).click();
		int iCountCheckBoxes = this.wd.findElements(bySearchListCheckBox).size();
		return iCountCheckBoxes;
		
	}
}

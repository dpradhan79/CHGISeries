package test.iseries.phonebook;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import dweb.test.templates.TestTemplate;
import dweb.test.templates.TestTemplateMethodLevelInit;
import pages.iseries.phonebook.PhoneBookPage;

public class TestPhoneBook extends TestTemplateMethodLevelInit{

	@Test
	public void testPhoneBookSearch()
	{
		SoftAssert softAssert = new SoftAssert();
		PhoneBookPage phoneBookPage = new PhoneBookPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		Map<String, String> mapIdSearchCriteria = new HashMap<String, String>();
		mapIdSearchCriteria.put("searchFirstName", "Joe");
		int iCountCheckBoxes = phoneBookPage.searchPhoneBook(mapIdSearchCriteria);		//validate search works
		
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
}

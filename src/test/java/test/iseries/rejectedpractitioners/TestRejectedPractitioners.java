package test.iseries.rejectedpractitioners;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.config.IConstants;
import com.excel.Xls_Reader;
import com.google.common.io.Resources;
import com.utilities.TestUtil;

import pages.iseries.rejectedpractitioners.RejectedPractitionersPage;
import dweb.test.templates.TestTemplate;
import dweb.test.templates.TestTemplateMethodLevelInit;

public class TestRejectedPractitioners extends TestTemplateMethodLevelInit {
	

    @DataProvider(name = "getRejectedPractitionersDataFromExcel", parallel = false)

    protected Object[][] getGMEdataFromExcel() throws URISyntaxException {

           URL urlFilePath = Resources.getResource(String.format("%s/%s", IConstants.TEST_DATA_LOCATION, IConstants.TEST_DATA_EXCEL_FILE));

           String filePath = Paths.get(urlFilePath.toURI()).toFile().getAbsolutePath();

           Xls_Reader xlsReader = new Xls_Reader(filePath);

           Object[][] objMetrics = TestUtil.getData("RejectedPractitionersData", xlsReader, "RejectedPractitioners");

           return objMetrics;

    }
	
    // description = "Verify that clicking on Search Rejected Practioners link takes user to search page"

	@Test(dataProvider = "getRejectedPractitionersDataFromExcel")
	public void testC863584(Hashtable<String, String> data, ITestContext testContext)
	{
		//Initialization
		RejectedPractitionersPage rejectedPractitionersPage = new RejectedPractitionersPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);
		
		
	    // Logging in to Rejected Practitioners

        rejectedPractitionersPage.rejectedPractitionersLogin(testContext);
        
        
        // Enter Data into first Name field
        
        Map<String, String> mapidsSerachCriteria = new HashMap<String, String>();
		
		mapidsSerachCriteria.put("FirstName", data.get("FirstName"));
		
		rejectedPractitionersPage.fillAllFieldsInRejectedPractioners(mapidsSerachCriteria);
		
		//Verify that clicking on Search Rejected Practioners link takes user to search page
		
		rejectedPractitionersPage.verifyUserBackToSearchPage(testContext);
		

		this.softAssert.assertAll();
	}
	
	  @Test(dataProvider = "getRejectedPractitionersDataFromExcel", description = "Reset Rejected Practitioners fields")

      public void testC863583(ITestContext testContext, Hashtable<String, String> data) {

             // Data

             Map<String, String> map = new HashMap<String, String>();

             map.put("LastName", data.get("LastName"));

             map.put("FirstName", data.get("FirstName"));

             map.put("DateOfBirth", data.get("DateOfBirth"));

             map.put("SocialSecurity", data.get("SocialSecurity"));

             // Initialization

             RejectedPractitionersPage rejectedPractitionersPage = new RejectedPractitionersPage(

                          TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);

             // Logging in to Rejected Practitioners

             rejectedPractitionersPage.rejectedPractitionersLogin(testContext);

             // Fill all the fields in Rejected Practitioners Search

             rejectedPractitionersPage.fillAllFieldsInRejectedPractioners(map);

             // Verify fields are Reseted

             rejectedPractitionersPage.resetFieldsInRejectedPractioners();

             this.softAssert.assertAll();

      }
	
	
}

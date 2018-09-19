package test.iseries.grossmarginestimator;

import java.awt.AWTException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.config.IConstants;
import com.excel.Xls_Reader;
import com.google.common.io.Resources;
import com.utilities.TestUtil;

import dweb.test.templates.TestTemplate;
import dweb.test.templates.TestTemplateMethodLevelInit;
import pages.iseries.grossmarginestimator.GrossMarginEstimatorPage;

public class TestGrossMarginEstimator extends TestTemplateMethodLevelInit {

	@DataProvider(name = "getGMEdataFromExcel", parallel = false)
	protected Object[][] getGMEdataFromExcel() throws URISyntaxException {
		URL urlFilePath = Resources
				.getResource(String.format("%s/%s", IConstants.TEST_DATA_LOCATION, IConstants.TEST_DATA_EXCEL_FILE));
		String filePath = Paths.get(urlFilePath.toURI()).toFile().getAbsolutePath();
		Xls_Reader xlsReader = new Xls_Reader(filePath);
		Object[][] objMetrics = TestUtil.getData("LoginGrossMarginEstimator", xlsReader, "GrossMarginEstimator");
		return objMetrics;
	}

	@Test(dataProvider = "getGMEdataFromExcel", description = "Login into Gross Margin Estimator")
	public void testC939887(Hashtable<String, String> data) throws InterruptedException, AWTException {

		// Credentials
		Map<String, String> mapIdLoginData = new HashMap<String, String>();
		mapIdLoginData.put("username", data.get("UserName"));
		mapIdLoginData.put("password", data.get("Password"));

		// Initialization
		GrossMarginEstimatorPage grossMarginEstimatorPage = new GrossMarginEstimatorPage(
				TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);

		// Login To Gross Margin Estimator And Validate
		grossMarginEstimatorPage.loginGrossMarginEstimator(mapIdLoginData);

		softAssert.assertAll();

	}

		@Test(dataProvider = "getGMEdataFromExcel", description = "Validating page layout for Gross Margin Estimator  ")
	public void testC939888(Hashtable<String, String> data)
			throws URISyntaxException, InterruptedException, AWTException {
		// Credentials
		Map<String, String> mapIdLoginData = new HashMap<String, String>();
		mapIdLoginData.put("username", data.get("UserName"));
		mapIdLoginData.put("password", data.get("Password"));

		// Initialization
		GrossMarginEstimatorPage grossMarginEstimatorPage = new GrossMarginEstimatorPage(
				TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);

		// Login To Gross Margin Estimator And Validate
		grossMarginEstimatorPage.loginGrossMarginEstimator(mapIdLoginData);

		// Get All Labels Form Excel
		Map<String, String> labels = new HashMap<>();
		labels = grossMarginEstimatorPage.getColElementsFromExcel(0, "Label");

		// Validating all labels on Gross Margin Estimator page
		grossMarginEstimatorPage.validateFields(labels, "label");

		// Get All Buttons Fields Form Excel
		Map<String, String> buttons = new HashMap<>();
		buttons = grossMarginEstimatorPage.getColElementsFromExcel(2, "Buttons");

		// Validating All Buttons on Gross Margin Estimator page
		grossMarginEstimatorPage.validateFields(buttons, "button");

		softAssert.assertAll();
	}

	@Test(dataProvider = "getGMEdataFromExcel", description = "Logout from Gross Margin Estimator")
	public void testC939890(Hashtable<String, String> data) throws InterruptedException, AWTException {

		// Credentials
		Map<String, String> mapIdLoginData = new HashMap<String, String>();
		mapIdLoginData.put("username", data.get("UserName"));
		mapIdLoginData.put("password", data.get("Password"));

		// Initialization
		GrossMarginEstimatorPage grossMarginEstimatorPage = new GrossMarginEstimatorPage(
				TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);

		// Login To Gross Margin Estimator And Validate
		grossMarginEstimatorPage.loginGrossMarginEstimator(mapIdLoginData);

		// Logout form Gross Margin Estimator And Validate
		grossMarginEstimatorPage.logoutGrossMarginEstimator();

		softAssert.assertAll();

	}

	
	@Test(dataProvider = "getGMEdataFromExcel", description = "Calculate Gross Margin")
	public void testC939889(Hashtable<String, String> data) throws InterruptedException, AWTException {

		// Credentials
		Map<String, String> mapIdLoginData = new HashMap<String, String>();
		mapIdLoginData.put("username", data.get("UserName"));
		mapIdLoginData.put("password", data.get("Password"));
		mapIdLoginData.put("worksiteState", data.get("WorksiteState"));
		mapIdLoginData.put("specialty", data.get("Specialty"));
		mapIdLoginData.put("team", data.get("Team"));
		mapIdLoginData.put("clientname", data.get("ClientName"));
		mapIdLoginData.put("fromDate", data.get("FromDate"));
		mapIdLoginData.put("toDate", data.get("ToDate"));
		mapIdLoginData.put("malPractice",data.get("MalPractice"));
		mapIdLoginData.put("rate", data.get("Rate"));
		mapIdLoginData.put("overTime", data.get("OverTime"));
		mapIdLoginData.put("callDay", data.get("CallDay"));
		mapIdLoginData.put("callNight", data.get("CallNight"));
		mapIdLoginData.put("ratePayInfo", data.get("RatePayInfo"));
		
		
		
		// Initialization
		GrossMarginEstimatorPage grossMarginEstimatorPage = new GrossMarginEstimatorPage(
				TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport, this.softAssert);

		// Login To Gross Margin Estimator And Validate
		grossMarginEstimatorPage.loginGrossMarginEstimator(mapIdLoginData);
		grossMarginEstimatorPage.calculateGrossMargin(mapIdLoginData);
		softAssert.assertAll();

	}
}

package pages.iseries.grossmarginestimator;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import com.config.IConstants;
import com.excel.Xls_Reader;
import com.google.common.io.Resources;
import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;

public class GrossMarginEstimatorPage extends PageTemplate {

	private By byLoginUserName = By.id("username");
	private By byLoginPassword = By.id("password");
	private By byLoginbtn = By.xpath("//input[@class='btn-submit']");
	private By byHeadingGrossMarginEstimator = By.xpath("//span[text()='Gross Margin Estimator']");
	private By byInclusiveRadiobtn = By
			.xpath("(//*[contains(text(),'Contract Type')]/../../../../following-sibling::tr//table//td)[1]");
	private By byBillBackRadiobtn = By
			.xpath("(//*[contains(text(),'Contract Type')]/../../../../following-sibling::tr//table//td)[3]");
	private By byWorkUnits = By.xpath("//input[@id='B' or @id='P']");
	private By byMealsCarsHousingetc = By.xpath("//*[contains(text(),'Meals')]");
	private By byBasedOnConsecutiveDays = By.xpath("//*[contains(text(),'consecutive days')]");
	private By byTo = By.xpath("//input[@id='toDate']");
	
	private By byElementNameGME;
	private By bylogout = By.xpath("//a[@href='logout']");
	private SoftAssert softAssert = null;
	private By byWorksiteState = By.xpath("(//td[contains(text(),'Worksite State:')]/following-sibling::td//select)[1]");
	private By byTeam = By.xpath("//span[contains(text(),'Team:')]/..//select");
	private By bySpecialty = By.xpath("//span[contains(text(),'Specialty:')]/..//select");
	private By byClientName = By.xpath("(//span[contains(text(),'Client Name:')]/../following-sibling::td//input)[1]");
	private By byCalculatorbtn = By.xpath("//input[@id='calculate']");
	private By byErrorHeadingGrossMargin = By.xpath("((//td[contains(.,'Errors')])[3]//div)[1]");
	private By byFromDate = By.xpath("(//span[contains(text(),'Client Name:')]/../following-sibling::td//input)[2]");
	private By byToDate = By.xpath("(//span[contains(text(),'Client Name:')]/../following-sibling::td//input)[3]");
	private By byMalpractice = By.xpath("//td[contains(text(),'Malpractice/Day:')]/following-sibling::td//input");
	private By byRate = By.xpath("(//td[contains(text(),'Rate:')]/following-sibling::td//input)[1]");
	private By byRatePayInfo = By.xpath("(//td[contains(text(),'Pay Info')]/../following-sibling::tr//td[2]//input)[1]");
	private By byOvertime = By.xpath("(//td[contains(text(),'OverTime:')]/following-sibling::td//input)[1]");
	private By byCallDay = By.xpath("(//td[contains(text(),'OnCall Day:')]/following-sibling::td//input)[1]");
	private By byCallNight = By.xpath("(//td[contains(text(),'OnCall Night:')]/following-sibling::td//input)[1]");
	private By byProjectedGrossMargin = By.xpath("//*[contains(text(),'Projected Gross Margin:')]/../following-sibling::td//font");
	
	
	public GrossMarginEstimatorPage(WebDriver webDriver, IReporter testReport) {
		super(webDriver, testReport);
		this.softAssert = null;
	}

	public GrossMarginEstimatorPage(WebDriver webDriver, IReporter testReport, SoftAssert softAssert) {
		super(webDriver, testReport);
		this.softAssert = softAssert;
	}

	/**
	 * @param Visisble
	 *            text of WebElement
	 * @param Index
	 *            Of WebElemet ,Create x-path of WebElement using visible text
	 */
	private By byElementByVisibleText(String value, int index) {
		byElementNameGME = By.xpath("(//*[contains(text(),'" + value + "')])[" + index + "]");
		return byElementNameGME;
	}

	/**
	 * @param Visisble
	 *            text of WebElement ,Create x-path of WebElement using visible
	 *            text
	 * 
	 */
	private By byElementByVisibleText(String value) {
		byElementNameGME = By.xpath("//*[contains(text(),'" + value + "')]");
		return byElementNameGME;
	}

	/**
	 * @param value
	 *            attribute of WebElement
	 * @param Index
	 *            for WebElemet ,Create x-path of WebElement using value
	 *            attribute
	 */
	private By byElementByValueText(String value, int index) {
		byElementNameGME = By.xpath("(//*[@value='" + value + "'])[" + index + "]");
		return byElementNameGME;
	}

	/**
	 * @param value
	 *            attribute of WebElement ,Create x-path of WebElement using
	 *            value attribute
	 */
	private By byElementByValueText(String value) {
		byElementNameGME = By.xpath("//*[@value='" + value + "']");
		return byElementNameGME;
	}

	/**
	 * @param username
	 * @param password
	 *            Login into gross margin estimator
	 */
	public void loginGrossMarginEstimator(Map<String, String> mapIdSearchTextBoxes) {

		for (Map.Entry<String, String> entrySet : mapIdSearchTextBoxes.entrySet()) {
			String searchCriteria = entrySet.getKey();
			String searchValue = entrySet.getValue();

			switch (searchCriteria) {
			case "username":
				this.sendKeys(byLoginUserName, searchValue);
				break;
			case "password":
				this.sendKeys(byLoginPassword, searchValue);
				break;

			}

		}
		this.click(byLoginbtn);
		Boolean b = isElementVisible(byHeadingGrossMarginEstimator);

		// Validate for Successful Login
		if (b) {
			this.testReport.logSuccess("Gross Margin Estimator page", "Login To Gross Margin Estimator is Successfull",
					this.getScreenShotName());
		} else {
			this.testReport.logFailure("Gross Margin Estimator page", "Unable To Login Gross Margin Estimator",
					this.getScreenShotName());

		}
	}
	
	/**
	 * @param worksiteState
	 * @param specialty
	 * @param team
	 * @param clientname
	 *            calculate gross margin
	 */
	public void calculateGrossMargin(Map<String, String> mapIdSearchTextBoxes) {
		
		this.click(byCalculatorbtn);
		Boolean b = isElementVisible(byErrorHeadingGrossMargin);
		String errorText=getText(byErrorHeadingGrossMargin);
		String errorTextLine =errorText.replace("\n", "");
		this.softAssert.assertEquals(errorTextLine, "From Date and To Date must be filled.Malpractice Rate must be entered.");
		    if(errorTextLine.equals("From Date and To Date must be filled.Malpractice Rate must be entered."))
		    {
		    this.testReport.logSuccess("Validate Error Msg", "Expected Test is "+"From Date and To Date must be filled.Malpractice Rate must be entered."+" Actual Text is "+errorTextLine);
		    }
		    else
		    {
		    this.testReport.logFailure("Validate Error Msg", "Expected Test is "+"From Date and To Date must be filled.Malpractice Rate must be entered."+" Actual Text is "+errorTextLine, this.getScreenShotName());
		    }
		
		for (Map.Entry<String, String> entrySet : mapIdSearchTextBoxes.entrySet()) {
		String searchCriteria = entrySet.getKey();
		String searchValue = entrySet.getValue();

		switch (searchCriteria) {
		case "worksiteState":
			this.selectByVisibleText(byWorksiteState, searchValue);
			break;
		case "specialty":
			this.selectByVisibleText(bySpecialty, searchValue);
			break;
		case "team":
			this.selectByVisibleText(byTeam, searchValue);
			break;
		case "fromDate":
			this.sendKeys(byFromDate, searchValue);
			break;
		case "toDate":
			this.sendKeys(byToDate, searchValue);
			break;
		case "malPractice":
			this.sendKeys(byMalpractice, searchValue);
			break;
		case "rate":
			this.sendKeys(byRate, searchValue);
			break;
		case "clientname":
			this.sendKeys(byClientName, searchValue);
			break;
		case "overTime":
			this.sendKeys(byOvertime, searchValue);
			break;
		case "callDay":
			this.sendKeys(byCallDay, searchValue);
			break;
		case "callNight":
			this.sendKeys(byCallNight, searchValue);
			break;
		case "ratePayInfo":
			this.sendKeys(byRatePayInfo, searchValue);
			break;
			
			}
		}
		this.click(byCalculatorbtn);
		Boolean b1 = isElementVisible(byProjectedGrossMargin);
		String value=getText(byProjectedGrossMargin);	
		this.softAssert.assertEquals(value, "($156.00)");
	    if(value.equals("($156.00)"))
	    {
	    this.testReport.logSuccess("Validate value", "Expected Test is "+"($156.00)"+" Actual Text is "+value);
	    }
	    else
	    {
	    this.testReport.logFailure("Validate value", "Expected Test is "+"($156.00)"+" Actual Text is "+value, this.getScreenShotName());
	    }
	}

	/**
	 * logout form gross margin estimator
	 */
	public void logoutGrossMarginEstimator() {
		this.click(bylogout);

		String title = this.wd.getTitle();

		// validation for Successful logout
		softAssert.assertEquals(title, "CHG – Authentication Service");

		if (title.equalsIgnoreCase("CHG – Authentication Service")) {
			this.testReport.logSuccess("Gross Margin Estimator page", "Login To Gross Margin Estimator is Successfull",
					this.getScreenShotName());
		} else {
			this.testReport.logFailure("Gross Margin Estimator page", "Unable To Login Gross Margin Estimator",
					this.getScreenShotName());

		}

	}

	/**
	 * @param label
	 *            visible text
	 * @param index
	 *            of label
	 * @param Elementtype
	 *            Verify the presence of element on webpage
	 */
	private boolean isElementDisplayed(String labelname, int index, String elemetType) {

		Boolean b = null;
		switch (elemetType) {

		case "label": {
			if (this.wd.findElement(byElementByVisibleText(labelname, index)).isDisplayed()) {
				softAssert.assertTrue(this.wd.findElement(byElementByVisibleText(labelname, index)).isDisplayed());

				b = true;
			} else
				b = false;
			break;
		}
		case "button": {
			if (this.wd.findElement(byElementByValueText(labelname, index)).isDisplayed()) {
				softAssert.assertTrue(this.wd.findElement(byElementByValueText(labelname, index)).isDisplayed());

				b = true;
			} else
				b = false;
			break;
		}
		}
		return b;

	}

	/**
	 * @param Map
	 * @param Elementtype
	 *            Validating all element of a map on webpage
	 */
	public void validateFields(Map<String, String> map, String elemetType) {
		for (Map.Entry<String, String> e : map.entrySet()) {
			String b_key = e.getKey().toString();
			String b_value = e.getValue().toString();
			List<WebElement> we = null;

			switch (elemetType) {

			case "label":
				we = this.wd.findElements(byElementByVisibleText(b_key));
				break;

			case "button":
				we = this.wd.findElements(byElementByValueText(b_key));
				break;

			}

			int size = we.size();

			if (size >= Integer.parseInt(b_value)) {

				softAssert.assertTrue(size >= Integer.parseInt(b_value));

				if (Integer.parseInt(b_value) > 1) {
					for (int i = 1; i < Integer.parseInt(b_value); i++) {
						this.isElementDisplayed(b_key, i, elemetType);
					}

				} else {
					this.isElementDisplayed(b_key, 1, elemetType);

				}

				this.testReport.logSuccess("Gross Margin Estimator page",
						"Element " + b_key + " is verified successfully on Gross Margin Estimator",
						this.getScreenShotName());

			} else {

				we = specialfiledsGME(b_key, b_value);

			}
		}

	}

	/**
	 * @param Key-field
	 *            name
	 * @param Value-field
	 *            count ,Verify some special fields in GME page
	 */
	private List<WebElement> specialfiledsGME(String b_key, String b_value) {
		List<WebElement> we = null;
		try {
			switch (b_key) {

			case "To:":
				we = (List<WebElement>) this.wd.findElements(byTo);
				softAssert.assertTrue(this.wd.findElement(byTo).isDisplayed());
				break;
			case "Inclusive":
				we = (List<WebElement>) this.wd.findElements(byInclusiveRadiobtn);
				softAssert.assertTrue(this.wd.findElement(byInclusiveRadiobtn).isDisplayed());
				break;

			case "Bill Back":
				we = (List<WebElement>) this.wd.findElements(byBillBackRadiobtn);
				softAssert.assertTrue(this.wd.findElement(byBillBackRadiobtn).isDisplayed());
				break;
			case "(Meals, Cars, Housing, etc)":
				we = (List<WebElement>) this.wd.findElements(byMealsCarsHousingetc);
				softAssert.assertTrue(this.wd.findElement(byMealsCarsHousingetc).isDisplayed());
				break;
			case "Work Unit:":
				we = (List<WebElement>) this.wd.findElements(byWorkUnits);
				softAssert.assertTrue(this.wd.findElement(byWorkUnits).isDisplayed());
				break;
			case "Based on consecutive days (Car Days)":
				we = (List<WebElement>) this.wd.findElements(byBasedOnConsecutiveDays);
				softAssert.assertTrue(this.wd.findElement(byBasedOnConsecutiveDays).isDisplayed());
				break;

			}
			if (we.size() >= 1) {
				this.testReport.logSuccess("Gross Margin Estimator page",
						"Element " + b_key + " is verified successfully on Gross Margin Estimator",
						this.getScreenShotName());
			}

		} catch (Exception e) {
			this.testReport.logFailure("Gross Margin Estimator page",
					"Elements " + b_key + " is not present on Gross Margin Estimator", this.getScreenShotName());
			System.out.println("xpath+++++++++++++++++++++++++++++++++" + we);
		}

		return we;
	}

	/**
	 * @param columnNo.form
	 * @param ColHeadingExcel
	 *            Create map of Element of two columns form excel starting
	 *            form @param column no
	 */
	public Map<String, String> getColElementsFromExcel(int columnnumber, String testname) throws URISyntaxException {

		URL urlFilePath = Resources
				.getResource(String.format("%s/%s", IConstants.TEST_DATA_LOCATION, IConstants.TEST_DATA_EXCEL_FILE));
		String filePath = Paths.get(urlFilePath.toURI()).toFile().getAbsolutePath();
		Xls_Reader xlsReader = new Xls_Reader(filePath);

		int rowCount = xlsReader.getRowCount("GrossMarginEstimator");

		Map<String, String> map = new HashMap<>();
		for (int i = 0; i <= rowCount; i++) {
			if (xlsReader.getCellData("GrossMarginEstimator", columnnumber, i).equalsIgnoreCase(testname)) {
				for (int j = i; j <= rowCount; j++) {
					if (!(xlsReader.getCellData("GrossMarginEstimator", columnnumber, j + 1).isEmpty())) {

						map.put(xlsReader.getCellData("GrossMarginEstimator", columnnumber, j + 1),
								xlsReader.getCellData("GrossMarginEstimator", columnnumber + 1, j + 1));
					} else {
						break;
					}
				}
			}
		}

		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey().toString();
			String value = entry.getValue().toString();
			System.out.println("key, " + key + " value " + value);
		}

		return map;
	}
	
	public void selectByVisibleText(By by,String text)
	{
		try {
		Select select=new Select(this.wd.findElement(by));
		select.selectByVisibleText(text);
		this.testReport.logSuccess("Gross Margin Estimator page",
				"Element "+text+" is  successfully selected for Webelement "+by+"on Gross Margin Estimator");
		
		}
		catch(Exception e){
			this.testReport.logFailure("Gross Margin Estimator page",
					"Element "+text+" cannot be selected for Webelement "+by+"on Gross Margin Estimator",this.getScreenShotName());
			
		}
	}
	

}

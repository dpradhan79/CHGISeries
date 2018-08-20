package pages.iseries.jdevendorsearch;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;

public class JdeVendorSearchPage extends PageTemplate {

	private SoftAssert softAssert = null;
	public JdeVendorSearchPage(WebDriver webDriver, IReporter testReport) {
		super(webDriver, testReport);
		this.softAssert = new SoftAssert();
	}

}

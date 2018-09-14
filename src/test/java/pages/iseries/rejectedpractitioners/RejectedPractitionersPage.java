package pages.iseries.rejectedpractitioners;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import com.testreport.IReporter;
import dweb.aut.pages.templates.PageTemplate;

public class RejectedPractitionersPage extends PageTemplate {

	
	private SoftAssert softAssert = null;
	public RejectedPractitionersPage(WebDriver webDriver, IReporter testReport) {
		super(webDriver, testReport);
		this.softAssert = null;
	}
	
	public RejectedPractitionersPage(WebDriver webDriver, IReporter testReport, SoftAssert softAssert) {
		super(webDriver, testReport);
		this.softAssert = softAssert;
	}

		
	}
	
	

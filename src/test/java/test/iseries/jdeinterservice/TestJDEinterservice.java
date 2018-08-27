package test.iseries.jdeinterservice;

import org.testng.annotations.Test;

import dweb.test.templates.TestTemplate;
import dweb.test.templates.TestTemplateMethodLevelInit;
import pages.iseries.jdeinterservice.JdeInterservicePage;
import pages.iseries.loginpage.LoginPage;

public class TestJDEinterservice extends TestTemplateMethodLevelInit{

	@Test
	public void testC939958()
	{
		//Initialization
		LoginPage loginPage = new LoginPage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		JdeInterservicePage jdeInterServicePage = new JdeInterservicePage(TestTemplate.threadLocalWebDriver.get(), TestTemplate.testReport);
		
		//Logging in to application
		//loginPage.jdeVendorSearchLogin();

	}
}

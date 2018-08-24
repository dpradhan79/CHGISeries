package pages.iseries.qaevals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.testreport.IReporter;

import dweb.aut.pages.templates.PageTemplate;

public class QaEvalsPage extends PageTemplate {

	private By clientsTab 					= By.xpath("//ul[@id='tabBar']//a[@title='Clients Tab']");
	private By clientsPage 					= By.xpath("//h1[text()='Clients']");
	private By availableClientInCLientsTab 	= By.xpath("//th[text()='Client Name']/../parent::tbody/tr[2]//a");
	private By clientDetailText 			= By.xpath("//h2[text()='Client Detail']");
	private By qaEvalsButton 				= By.xpath("//input[@title='QA Evals']");
	private By providersAndContactsTab		= By.xpath("//ul[@id='tabBar']//a[@title='Providers and Contacts Tab']");
	private By availableProviderTab 		= By.xpath("//th[text()='Name']/../parent::tbody/tr[2]/td[2][text()='Provider']/parent::tr//a");
	private By providersPage 				= By.xpath("//h1[text()='Providers and Contacts']");
	private By providerDetailText 			= By.xpath("//h2[text()='Provider or Contact Detail']");
	
	public QaEvalsPage(WebDriver webDriver, IReporter testReport) {
		super(webDriver, testReport);
	}
	
	private void navigateToClientsTab()
	{
		//Wait until clients tab is clickable and click on clients tab
		this.waitUntilElementIsClickable(clientsTab);
		this.click(clientsTab);
		
		//Wait until clients page displayed
		this.waitUntilElementIsVisible(clientsPage);
	}
	
	private String clickOn1stClientAvailable()
	{
		//Click on client available
		this.waitUntilElementIsClickable(availableClientInCLientsTab);
		String clientName = this.getText(availableClientInCLientsTab);
		this.click(availableClientInCLientsTab);
		
		//Wait until it navigates to client details
		this.waitUntilElementIsVisible(clientDetailText);
		return clientName;
	}
	
	private void clickOnQAevalsButton()
	{
		//click on QA Evals button
		this.waitUntilElementIsClickable(qaEvalsButton);
		this.click(qaEvalsButton);
	}
	
	private void switchToWindowUsingTitle(String title)
	{
		String currentWindow = this.wd.getWindowHandle();  //will keep current window to switch back
		for(String winHandle : this.wd.getWindowHandles()){
		   if (this.wd.switchTo().window(winHandle).getTitle().equals(title)) {
		     //This is the one you're looking for
		     break;
		   } 
		   else {
			   this.wd.switchTo().window(currentWindow);
		   } 
		}
	}
	public String navigateToQAevalsFromClient()
	{
		//Navigate to clients tab
		this.navigateToClientsTab();
		
		//Click on clients tab
		String ClientName = this.clickOn1stClientAvailable();
		
		//click on QA Evals button
		this.clickOnQAevalsButton();
		
		//Switch to window
		this.implicitwait(3);
		this.switchToWindowUsingTitle("Login Page");
		return ClientName;
	}
	
	private void navigateToProvidersTab()
	{
		//Wait until providers tab is clickable and click on clients tab
		this.waitUntilElementIsClickable(providersAndContactsTab);
		this.click(providersAndContactsTab);
		
		//Wait providers clients page displayed
		this.waitUntilElementIsVisible(providersPage);
	}
	
	private String clickOn1stProviderAvailable()
	{
		//Click on client available
		this.waitUntilElementIsClickable(availableProviderTab);
		String providerName = this.getText(availableProviderTab);
		this.click(availableProviderTab);
		
		//Wait until it navigates to client details
		this.waitUntilElementIsVisible(providerDetailText);
		return providerName;
	}
	
	public String navigateToQAevalsFromProvider()
	{
		//Navigate to clients tab
		this.navigateToProvidersTab();
		
		//Click on clients tab
		String ClientName = this.clickOn1stProviderAvailable();
		
		//click on QA Evals button
		this.clickOnQAevalsButton();
		
		//Switch to window
		this.implicitwait(3);
		this.switchToWindowUsingTitle("Login Page");
		return ClientName;
	}
}

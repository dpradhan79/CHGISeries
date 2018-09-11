package pages.iseries.qaevals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

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
	private By assignmentsTab				= By.xpath("//ul[@id='tabBar']//a[@title='Assignments Tab']");
	private By assignmentsPage 				= By.xpath("//h1[text()='Assignments']");
	private By availableAssignment			= By.xpath("//a[contains(text(),'ASG-')]");
	private By assignmentDetailText			= By.xpath("//h2[text()='Assignment Detail']");
	private By createClientEval				= By.xpath("//input[contains(@value,'Create Client Eval')]");
	private By createProviderEval			= By.xpath("//input[contains(@value,'Create Provider Eval')]");
	private By evalTypeInClientQAEvals		= By.xpath("//div[text()='Client Evals']/following-sibling::div[1]//tr[contains(@class,'status_CREATED')]/td[2]");
	private By evalTypeInProviderQAEvals	= By.xpath("//div[text()='Provider Evals']/following-sibling::div[1]//tr[contains(@class,'status_CREATED')]/td[2]");
	private By providerEvalsEditLink		= By.xpath("//div[text()='Provider Evals']/following-sibling::div[1]//tr[contains(@class,'status_CREATED')]/td[1]/a[text()='Edit']");
	private By providerEvalsPDFLink			= By.xpath("//div[text()='Provider Evals']/following-sibling::div[1]//tr[contains(@class,'status_CREATED')]/td[1]/a[text()='PDF']");
	private By providerEvalsMoreLink		= By.xpath("//div[text()='Provider Evals']/following-sibling::div[1]//tr[contains(@class,'status_CREATED')]/td[1]/a[contains(text(),'More')]");
	private By providerEvalsAsgNameLink		= By.xpath("//a[contains(@href,'assignmentSummary')]");
	private By providerEvalsClientNameLink	= By.xpath("//td[contains(@class,'clientName')]/a");
	private By providerEvalsProviderNameLink= By.xpath("//td[contains(@class,'providerName')]/a");
	private By editPageText					= By.xpath("//a[contains(text(),'Report a technical')]");
	private By linkAfterClikingMore			= By.xpath("//a[text()='View History']");
	private By PageTitle					= By.xpath("//div[contains(@class,'headerFrame')]/h1");
	private By searchField					= By.xpath("//*[@id='phSearchInput']");
	private By searchButton					= By.xpath("//*[@id='phSearchButton']");
	private By providerLinkInSearchResults	= By.xpath("//a[text()='Provider Status']/../parent::tr/following-sibling::tr/th/a");
	private By clientEvalsEditLinkInProvider	= By.xpath("//tr[contains(@class,'status_CREATED')]/td[1]/a[text()='Edit']");
	private By clientEvalsPDFLinkInProvider		= By.xpath("//tr[contains(@class,'status_CREATED')]/td[1]/a[text()='PDF']");
	private By clientEvalsMoreLinkInProvider	= By.xpath("//tr[contains(@class,'status_CREATED')]/td[1]/a[contains(text(),'More')]");
	private By clientLinkInSearchResult			= By.xpath("//a[text()='Client Type']/../parent::tr/following-sibling::tr/th/a");
	private By summaryReport					= By.xpath("//input[@value='Summary Report']");
	private By fromDateEvalCreated				= By.xpath("//*[@id='fromDate']");
	private By toDateEvalCreated				= By.xpath("//*[@id='toDate']");
	private By searchButtonQAEvals				= By.xpath("//input[@value='Search']");
	private By clientNameInClientQAEVals		= By.xpath("//tr[contains(@class,'CREATED')]/td[@class='clientName']/a");

	
	
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
		//click on QAEvals button
		this.waitUntilElementIsClickable(qaEvalsButton);
		this.click(qaEvalsButton);
	}
	
	public void switchToWindowUsingTitle(String title)
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
		
		//click on QAEvals button
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
		
		//click on QAEvals button
		this.clickOnQAevalsButton();
		
		//Switch to window
		this.implicitwait(3);
		this.switchToWindowUsingTitle("Login Page");
		return ClientName;
	}
	
	public String navigateToQAevalsFromAssignment()
	{
		//Navigate to clients tab
		this.navigateToAssignementTab();
		
		//Click on clients tab
		String assignmentName = this.clickOn1stAssignmentsAvailable();
		
		//click on QAEvals button
		this.clickOnQAevalsButton();
		
		//Switch to window
		this.implicitwait(3);
		this.switchToWindowUsingTitle("Login Page");
		return assignmentName;
	}
	
	private void navigateToAssignementTab()
	{
		//Wait until providers tab is clickable and click on clients tab
		this.waitUntilElementIsClickable(assignmentsTab);
		this.click(assignmentsTab);
		
		//Wait providers clients page displayed
		this.waitUntilElementIsVisible(assignmentsPage);
	}
	
	private String clickOn1stAssignmentsAvailable()
	{
		//Click on client available
		this.waitUntilElementIsClickable(availableAssignment);
		String assignmentName = this.getText(availableAssignment);
		this.click(availableAssignment);
		
		//Wait until it navigates to client details
		this.waitUntilElementIsVisible(assignmentDetailText);
		return assignmentName;
	}
	
	public String createClientEval() {
		// Click on create client eval button
		try {
			this.wd.findElement(evalTypeInClientQAEvals);
		} catch (NoSuchElementException e) {
			this.click(createClientEval);
			this.implicitwait(3);
		}
		// Get Eval type from Created record
		String evalType = this.getText(evalTypeInClientQAEvals);

		// Return the value
		return evalType;
	}
	
	public String createProviderEval() {
		// Click on create client eval button
		try {
			this.wd.findElement(evalTypeInProviderQAEvals);
		} catch (NoSuchElementException e) {
			this.click(createProviderEval);
			this.implicitwait(3);
		}

		// Get Eval type from Created record
		String evalType = this.getText(evalTypeInProviderQAEvals);

		// Return the value
		return evalType;
	}
	
	public Map<String, String> clickLinksPresentInQAEvalsAndGetTitles()
	{
		Map<String, String> titles = new HashMap<String, String>();
		
		//Click on Edit link and get title
		this.clickWithJavascript(providerEvalsEditLink);
		this.waitUntilElementIsVisible(editPageText);
		String editTitle = this.getText(editPageText).trim();
		this.wd.navigate().back();
		
		//Click on More and get link present
		this.waitUntilElementIsClickable(providerEvalsMoreLink);
		this.click(providerEvalsMoreLink);
		String moreValue = this.getText(linkAfterClikingMore).trim();
		
		//Click on assignment name and get title
		String asgName = this.getText(providerEvalsAsgNameLink);
		this.clickWithJavascript(providerEvalsAsgNameLink);
		this.waitUntilElementIsVisible(PageTitle);
		String asgTitle = this.getText(PageTitle).trim();
		this.wd.navigate().back();
		
		//Click on client name and get title
		this.waitUntilElementIsClickable(providerEvalsClientNameLink);
		String clientName = this.getText(providerEvalsClientNameLink);
		this.click(providerEvalsClientNameLink);
		String clientTitle = this.getText(PageTitle).trim();
		this.wd.navigate().back();
		
		//Click on client name and get title
		this.waitUntilElementIsClickable(providerEvalsProviderNameLink);
		String providerName = this.getText(providerEvalsProviderNameLink);
		this.click(providerEvalsProviderNameLink);
		String providerTitle = this.getText(PageTitle).trim();
		this.wd.navigate().back();
		
		//Click on PDF link
		this.waitUntilElementIsClickable(providerEvalsPDFLink);
		this.click(providerEvalsPDFLink);
		
		//Adding values to a Map
		titles.put("editTitle", editTitle);
		titles.put("moreValue", moreValue);
		titles.put("asgName", asgName);
		titles.put("asgTitle", asgTitle);
		titles.put("clientName", clientName);
		titles.put("clientTitle", clientTitle);
		titles.put("providerName", providerName);
		titles.put("providerTitle", providerTitle);
		return titles;
	}
	
	public String navigateToQAevalsFromProvider(String provider)
	{	
		//Search for the provider
		this.searchForAText(provider);
		
		//Click on provider
		this.waitUntilElementIsClickable(providerLinkInSearchResults);
		this.implicitwait(2);
		this.click(providerLinkInSearchResults);
		
		//Wait until navigates provider details
		this.waitUntilElementIsVisible(providerDetailText);
		
		//click on QAEvals button
		this.clickOnQAevalsButton();
		
		//Switch to window
		this.implicitwait(3);
		this.switchToWindowUsingTitle("Login Page");
		return provider;
	}
	
	private void searchForAText(String valueToSearch)
	{
		this.sendKeys(searchField, valueToSearch);
		this.click(searchButton);
		this.implicitwait(3);
	}
	
	public Map<String, String> clickLinksPresentInClientQAEvalsAndGetTitles()
	{
		Map<String, String> titles = new HashMap<String, String>();
		
		//Click on Edit link and get title
		this.click(clientEvalsEditLinkInProvider);
		this.waitUntilElementIsVisible(editPageText);
		String editTitle = this.getText(editPageText).trim();
		this.wd.navigate().back();
		
		//Click on More and get link present
		this.waitUntilElementIsClickable(clientEvalsMoreLinkInProvider);
		this.click(clientEvalsMoreLinkInProvider);
		String moreValue = this.getText(linkAfterClikingMore).trim();
		
		//Click on assignment name and get title
		String asgName = this.getText(providerEvalsAsgNameLink);
		this.click(providerEvalsAsgNameLink);
		this.waitUntilElementIsVisible(PageTitle);
		String asgTitle = this.getText(PageTitle).trim();
		this.wd.navigate().back();
		
		//Click on client name and get title
		this.waitUntilElementIsClickable(providerEvalsClientNameLink);
		String clientName = this.getText(providerEvalsClientNameLink);
		this.click(providerEvalsClientNameLink);
		String clientTitle = this.getText(PageTitle).trim();
		this.wd.navigate().back();
		
		//Click on client name and get title
		this.waitUntilElementIsClickable(providerEvalsProviderNameLink);
		String providerName = this.getText(providerEvalsProviderNameLink);
		this.click(providerEvalsProviderNameLink);
		String providerTitle = this.getText(PageTitle).trim();
		this.wd.navigate().back();
		
		//Click on PDF link
		this.waitUntilElementIsClickable(clientEvalsPDFLinkInProvider);
		this.click(clientEvalsPDFLinkInProvider);
		
		//Adding values to a Map
		titles.put("editTitle", editTitle);
		titles.put("moreValue", moreValue);
		titles.put("asgName", asgName);
		titles.put("asgTitle", asgTitle);
		titles.put("clientName", clientName);
		titles.put("clientTitle", clientTitle);
		titles.put("providerName", providerName);
		titles.put("providerTitle", providerTitle);
		return titles;
	}
	
	public String navigateToQAevalsFromClient(String client)
	{	
		//Search for the provider
		this.searchForAText(client);
		
		//Click on provider
		this.waitUntilElementIsClickable(clientLinkInSearchResult);
		this.implicitwait(2);
		this.click(clientLinkInSearchResult);
		
		//Wait until navigates provider details
		this.waitUntilElementIsVisible(clientDetailText);
		
		//click on QAEvals button
		this.clickOnQAevalsButton();
		
		//Switch to window
		this.SwitchToQAEvalsLoginPage();
		
		return client;
	}
	
	private void SwitchToQAEvalsLoginPage()
	{
		//Switch to window
		this.implicitwait(3);
		this.switchToWindowUsingTitle("Login Page");
	}
	
	public String searchForClientEvals(String fromDate, String toDate)
	{
		this.sendKeys(fromDateEvalCreated, fromDate);
		this.sendKeys(toDateEvalCreated, toDate);
		this.click(searchButtonQAEvals);
		this.implicitwait(3);
		String clientName = this.getText(clientNameInClientQAEVals);
		return clientName;
	}
	
	public void clickSummaryReport() {
		this.implicitwait(3);
		Set<String> windowHandles = this.wd.getWindowHandles();
		this.click(summaryReport);
		this.implicitwait(2);
		Set<String> windowHandlesAfter = this.wd.getWindowHandles();
		for (String s : windowHandlesAfter) {
			if (windowHandles.toString().contains(s)) {

			} else {
				this.wd.switchTo().window(s);
				this.wd.get("https://v_atalas:!Mpr0veMent4@bi.mychg.com");
				System.out.println("Switched to window");
			}
		}
	}
}

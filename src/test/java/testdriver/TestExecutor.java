package testdriver;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class TestExecutor {

	public static void main(String[] args) {
		TestNG testNG = new TestNG();
		List<String> suiteList = new ArrayList<String>();
		suiteList.add("testngPhoneBook.xml");
		suiteList.add("testngVendorSearch.xml");
		testNG.setTestSuites(suiteList);
		testNG.run();
	}
}

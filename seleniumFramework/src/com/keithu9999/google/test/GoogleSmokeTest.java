package com.keithu9999.google.test;

import org.testng.annotations.Test;

import com.keithu9999.google.pages.GoogleMainPage;
import com.keithu9999.selenium.test.SeleniumTest;

/**
 * Simple smoke test that attempts to navigate to the main Google search page, 
 * verifies that the page loads, and enters a value in the search field. 
 * 
 * @author keithu9999
 */
public class GoogleSmokeTest extends SeleniumTest {
	
	@Test(description="Navigate to the main Google page and enter a search string")
	public void navigateTest() {
		GoogleMainPage page = new GoogleMainPage(getDriver());
		page.getPage();
		
		page.setSearchText("Pittsburgh");
	}
}

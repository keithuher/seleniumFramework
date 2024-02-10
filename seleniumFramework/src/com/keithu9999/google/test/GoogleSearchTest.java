package com.keithu9999.google.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.keithu9999.google.pages.GoogleMainPage;
import com.keithu9999.google.pages.GoogleSearchResultsPage;
import com.keithu9999.selenium.test.SeleniumTest;

/**
 * Simple smoke test that attempts to navigate to the main
 * Google search page and verifies that the page loads.
 * 
 * @author keithu9999
 */
@Test(groups={"GoogleSearchTest", "regression"})
public class GoogleSearchTest extends SeleniumTest {
	
	@Test(description="Execute a Google search and verify the results page")
	public void searchTest() {
		
		String search = "Pittsburgh";
		
		GoogleMainPage page = new GoogleMainPage(getDriver());
		page.getPage();
		
		GoogleSearchResultsPage resultsPage = page.executeSearch(search);
		Assert.assertEquals(resultsPage.getSearchTitle(), search, "Did not find expected search results page");
	}
}

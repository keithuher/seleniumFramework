package com.keithu9999.google.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.keithu9999.selenium.pages.PageObject;

/**
 * Page Object encapsulates the Home Page
 */
public class GoogleSearchResultsPage extends PageObject {

	private static final Logger LOG = LoggerFactory.getLogger(GoogleSearchResultsPage.class);
	
	private static final By RESULT_TITLE_BY = By.cssSelector("div[data-attrid='title']");
	private String search;
	private String pageUrl;
	
	public GoogleSearchResultsPage(WebDriver driver, String search) {
		super(driver);
		this.search = search;
	}
	
	public String getSearchTitle() {
		return waitForVisible(RESULT_TITLE_BY).getText();
	}

	@Override
	public PageObject getPage() {
		LOG.info("Navigating to search results page for " + search);
		waitForVisible(RESULT_TITLE_BY);
		pageUrl = driver.getCurrentUrl();
		String partialUrl = "google.com/search?q=" + search;
		Assert.assertTrue(pageUrl.contains(partialUrl), "Did not find expected string in URL: " + partialUrl + ", found: " + pageUrl);
		return new GoogleSearchResultsPage(driver, search);
	}

}

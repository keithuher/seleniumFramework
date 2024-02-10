package com.keithu9999.google.pages;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.keithu9999.selenium.pages.PageObject;

/**
 * Page Object encapsulates the Home Page
 */
public class GoogleSearchResultsPage extends PageObject {

	private Logger logger = Logger.getLogger("GoogleSearchResultsPage");
	
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
		logger.info("Navigating to search results page for " + search);
		waitForVisible(RESULT_TITLE_BY);
		pageUrl = driver.getCurrentUrl();
		String partialUrl = "google.com/search?q=" + search;
		Assert.assertTrue(pageUrl.contains(partialUrl), "Did not find expected string in URL: " + partialUrl + ", found: " + pageUrl);
		return new GoogleSearchResultsPage(driver, search);
	}

}

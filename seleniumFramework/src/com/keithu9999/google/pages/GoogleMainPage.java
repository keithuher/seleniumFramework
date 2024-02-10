package com.keithu9999.google.pages;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.keithu9999.selenium.pages.PageObject;

/**
 * Page Object encapsulates the Home Page
 */
public class GoogleMainPage extends PageObject {

	private Logger logger = Logger.getLogger("GoogleMainPage");
	
	private static final By GMAIL_LINK_BY = By.cssSelector("a[href^='https://mail.google.com']");
	private static final By SEARCH_TEXT_BY = By.cssSelector("textarea[title='Search']");
	private static final By SEARCH_BUTTON_BY = By.cssSelector("input[value='Google Search']");
	
	public GoogleMainPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Sets the Google search field to the specified value.
	 * @param search The search string to type into the search field.
	 */
	public void setSearchText(String search) {
		waitForVisible(SEARCH_TEXT_BY).sendKeys(search + Keys.TAB);
	}
	
	public GoogleSearchResultsPage executeSearch(String search) {
		setSearchText(search);
		getAllElementsDisplayed(SEARCH_BUTTON_BY, 1, Duration.ofSeconds(2), Duration.ofSeconds(10)).get(0).click();
		return (GoogleSearchResultsPage) new GoogleSearchResultsPage(driver, search).getPage();
	}

	@Override
	public PageObject getPage() {
		String navUrl = "https://google.com";
		String expectedPageUrl = "https://www.google.com/";
		logger.info("Navigating to " + navUrl);
		driver.get(navUrl);
		waitForVisible(GMAIL_LINK_BY);
		Assert.assertEquals(driver.getCurrentUrl(), expectedPageUrl, "Did not find expected page");
		return new GoogleMainPage(driver);
	}

}

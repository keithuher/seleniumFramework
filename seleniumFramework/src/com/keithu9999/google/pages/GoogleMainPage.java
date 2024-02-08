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
	
	private static final By GOGGLE_IMAGE_BY = By.cssSelector("img[alt='Google']");
	private static final By SEARCH_TEXT_BY = By.cssSelector("textarea[title='Search']");
	private static final By SEARCH_BUTTON_BY = By.cssSelector("input[value='Google Search']");
	
	public GoogleMainPage(WebDriver driver) {
		super(driver);
	}
	
	public void setSearchText(String search) {
		waitForVisible(SEARCH_TEXT_BY).sendKeys(search + Keys.TAB);
	}
	
	public void googleSearch(String search) {
		setSearchText(search);
		getAllElementsDisplayed(SEARCH_BUTTON_BY, 1, Duration.ofSeconds(3), Duration.ofSeconds(10)).get(0).click();
	}

	@Override
	public PageObject getPage() {
		String navUrl = "https://google.com";
		String pageUrl = "https://www.google.com/";
		logger.info("Fetching " + navUrl);
		driver.get(navUrl);
		waitForVisible(GOGGLE_IMAGE_BY);
		Assert.assertEquals(driver.getCurrentUrl(), pageUrl, "Did not find expected page");
		return new GoogleMainPage(driver);
	}

}

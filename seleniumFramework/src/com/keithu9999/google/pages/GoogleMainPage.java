package com.keithu9999.google.pages;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.keithu9999.selenium.pages.PageObject;

/**
 * Page Object encapsulates the Home Page
 */
public class GoogleMainPage extends PageObject {

	private Logger logger = Logger.getLogger("GoogleMainPage");
	private static final By GOGGLE_BY = By.cssSelector("img[alt='Google']"); 
	
	public GoogleMainPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public PageObject getPage() {
		String navUrl = "https://google.com";
		String pageUrl = "https://www.google.com/";
		logger.info("Fetching " + navUrl);
		driver.get(navUrl);
		waitForVisible(GOGGLE_BY, 10);
		Assert.assertEquals(driver.getCurrentUrl(), pageUrl, "Did not find expected page");
		return new GoogleMainPage(driver);
	}

}

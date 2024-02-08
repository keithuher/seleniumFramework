package com.keithu9999.google.test;

import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.keithu9999.google.pages.GoogleMainPage;
import com.keithu9999.selenium.test.SeleniumTest;

public class GoogleSearchTest extends SeleniumTest {
	
	private Logger logger = Logger.getLogger("GoogleSearchTest");
	
	@Test(priority=1)
	public void navigateTest() {
		
		GoogleMainPage page = new GoogleMainPage(getDriver());
		page.getPage();
		
		page.googleSearch("Fantastic Things");
		
		logger.info("Here!");
		
	}
}

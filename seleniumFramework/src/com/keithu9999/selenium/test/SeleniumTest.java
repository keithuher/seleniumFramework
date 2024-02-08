package com.keithu9999.selenium.test;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.keithu9999.selenium.infra.BrowserType;
import com.keithu9999.selenium.infra.SeleniumDriver;

public abstract class SeleniumTest {

	private SeleniumDriver sd;
	private WebDriver driver;
	private String browser;
	private static final Logger logger = Logger.getLogger("SeleniumTest");
	
	@Parameters({ "browser" })
    @BeforeTest
    public void beforeTest(String browser) {
    	sd = new SeleniumDriver(getBrowserType(browser), "--remote-allow-origins=*");
    	driver = sd.getDriver();
    }

    @AfterTest
    public void afterTest() {
    	driver.quit();
    	logger.info("Quit WebDriver for " + browser);
    }
    
    public WebDriver getDriver() {
    	return driver;
    }
    
    private BrowserType getBrowserType(String browser) {
    	
    	this.browser = browser;
    	logger.info("Initializing WebDriver for " + browser);
    	
    	switch (browser)
    	{
    	     case "Chrome":
    	    	 return BrowserType.CHROME;
    	     default:
    	    	 Assert.fail("This browser is not currently supported " + browser);
    	    	 return null;
    	}
    }
}
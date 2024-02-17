package com.keithu9999.selenium.test;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.keithu9999.selenium.infra.BrowserType;
import com.keithu9999.selenium.infra.GridType;
import com.keithu9999.selenium.infra.SeleniumDriver;

public abstract class SeleniumTest {

	private SeleniumDriver sd;
	private WebDriver driver;
	private String browser;
	private String grid;
	private static final Logger logger = Logger.getLogger("SeleniumTest");
	
	@Parameters({ "browser", "grid" })
    @BeforeTest(alwaysRun=true)
    public void beforeTest(String browser, String grid) {
    	sd = new SeleniumDriver(getBrowserType(browser), getGridType(grid), "--remote-allow-origins=*");
    	driver = sd.getDriver();
    }

    @AfterTest(alwaysRun=true)
    public void afterTest() {
    	driver.quit();
    	logger.info("Quit WebDriver for " + browser);
    }
    
    public WebDriver getDriver() {
    	return driver;
    }
    
    private BrowserType getBrowserType(String browser) {
    	
    	this.browser = browser;
    	logger.info("Initializing WebDriver for " + this.browser);
    	
    	switch (browser)
    	{
    	     case "Chrome":
    	    	 return BrowserType.CHROME;
    	     default:
    	    	 Assert.fail("This browser is not currently supported " + browser);
    	    	 return null;
    	}
    }
    
	private GridType getGridType(String grid) {
		this.grid = grid;
		logger.info("Setting up properties for grid " + this.grid);

		switch (grid) {
			case "local":
				return GridType.LOCAL;
			case "desktop":
				return GridType.DESKTOP;
			default:
				Assert.fail("This grid is not currently supported " + grid);
				return null;
		}
	}
}
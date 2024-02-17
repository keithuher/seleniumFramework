package com.keithu9999.selenium.infra;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class SeleniumDriver {
	
	private static final Logger logger = Logger.getLogger("SeleniumDriver");

	//private WebDriver driver;
	private WebDriver driver;
	private BrowserType browserType;
	private GridType gridType;
	private String[] options;

	public SeleniumDriver(BrowserType browser, GridType grid, String ... options) {
		this.gridType = grid;
		this.browserType = browser;
		this.options = options;
	}

	public WebDriver getDriver() {
		System.out.println("Setting up a WebDriver for browser " + browserType.getName() + " using grid " + gridType.getName());
		
		switch (browserType) {
			case CHROME:
				// Standard properties
				System.setProperty("webdriver.chrome.driver", browserType.getDriverPath());
				ChromeOptions opts = new ChromeOptions();
				for(String option : options)
					opts.addArguments(option);
				
				// Grid setup
				if(gridType == GridType.DESKTOP) {
					DesiredCapabilities capabilities = new DesiredCapabilities();
			        capabilities.setCapability(ChromeOptions.CAPABILITY, opts);
			        try {
			        	driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
			        } catch (MalformedURLException ex) {
			        	logger.warning(ex.toString());
			        }
				}
				else
					driver = new ChromeDriver(opts);

				break;
			
			case FIREFOX:
				System.out.println("Handling Mozilla Firefox...");
				driver = new FirefoxDriver();
				break;
			default:
				Assert.fail("Unsupported driver: " + browserType.getName());
				break;
		}
		
		// Set any global driver properties here
		
		return driver;
	}

}

package com.keithu9999.infra;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class SeleniumDriver {

	private WebDriver driver;
	private BrowserType browserType;
	private String[] options;

	public SeleniumDriver(BrowserType browser, String ... options) {
		this.browserType = browser;
		this.options = options;
	}

	public WebDriver getDriver() {
		System.out.println("Setting up a WebDriver for " + browserType.getName());

		switch (browserType) {
			case CHROME:
				System.setProperty("webdriver.chrome.driver", browserType.getDriverPath());
				ChromeOptions opts = new ChromeOptions();
				for(String option : options)
					opts.addArguments(option);
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

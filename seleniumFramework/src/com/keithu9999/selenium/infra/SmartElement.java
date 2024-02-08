package com.keithu9999.selenium.infra;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public abstract class SmartElement {
	
	private WebDriver driver;
		
	public SmartElement(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement waitForVisible(By by, int seconds) {
		
		WebElement we = new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofMillis(500))
			.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOfElementLocated(by));
		
		return we;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}

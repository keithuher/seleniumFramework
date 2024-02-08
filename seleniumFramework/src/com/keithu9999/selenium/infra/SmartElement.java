package com.keithu9999.selenium.infra;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public abstract class SmartElement {
	
	private static final int STANDARD_TEN_SECOND_WAIT = 10;
	
	private WebDriver driver;
		
	public SmartElement(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement waitForVisible(By by, int seconds) {
		return new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(seconds)).pollingEvery(Duration.ofMillis(500))
			.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public WebElement waitForVisible(By by) {
		return waitForVisible(by, STANDARD_TEN_SECOND_WAIT);
	}
	
	public List<WebElement> getAllElementsDisplayed(By locator, int retries, Duration interval, Duration timeout) {
		
		List<WebElement> visibleElements = new ArrayList<>();
		
		try {
			new ElementPoller() {

				@Override
				public String conditionDescription() {
					return "at least one expected element";
				}

				@Override
				public boolean conditionSatisfied() {
					List<WebElement> elements = getDriver().findElements(locator);
					for(WebElement element : elements) {
						if(element != null && element.isDisplayed())
							visibleElements.add(element);
					}
					
					return visibleElements.size() > 0;
				}
				
			}.poll(interval, timeout);
		}
		catch(StaleElementReferenceException stale) {
			return getAllElementsDisplayed(locator, retries-1);
		}
		catch(Exception ex) {
			throw(ex);
		}
			
		return visibleElements;
		
	}
	
	public List<WebElement> getAllElementsDisplayed(By locator, int retries) {
		return getAllElementsDisplayed(locator, retries, Duration.ofSeconds(0), Duration.ofSeconds(0));
	}
	
	
	/*
	 * Pauses (sleeps) the test for the specified number of seconds. This method will
	 * fail if you attempt to pause for more than 60 seconds. In general, you should only 
	 * use this method when every other wait strategy fails.
	 */
	public void pauseForSeconds(long seconds) {
		Assert.assertTrue(seconds <= 60, "You shouldn't be pausing for more than 60 seconds. Consider another approach.");
		pauseFor(seconds*1000);
	}
	
	private void pauseFor(long milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}

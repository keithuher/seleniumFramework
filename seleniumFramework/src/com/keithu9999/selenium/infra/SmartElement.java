package com.keithu9999.selenium.infra;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public abstract class SmartElement {
	
	private static final int STANDARD_TEN_SECOND_WAIT = 10;
	private static final int POLL_FREQUENCY_MILLIS = 500;
	
	private WebDriver driver;
		
	public SmartElement(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Waits for up to the default number of seconds (STANDARD_TEN_SECOND_WAIT) for 
	 * a single element associated with the specified By to be visible.
	 * @param by The By object used to find the element.
	 * @return the WebElement that you are waiting for.
	 */
	public WebElement waitForVisible(By by) {
		return waitForVisible(by, STANDARD_TEN_SECOND_WAIT);
	}
	
	/**
	 * Waits for up to the specified number of seconds for a single element
	 * associated with the specified By to be visible.
	 * @param by The By object used to find the element.
	 * @param seconds The number of seconds to wait for the element to be visible.
	 * @return the WebElement that you are waiting for.
	 */
	public WebElement waitForVisible(By by, int seconds) {
		return waitFor(by, seconds, ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	/**
	 * Waits for up to the default number of seconds (STANDARD_TEN_SECOND_WAIT) for 
	 * a single element associated with the specified By to be present in the DOM.
	 * @param by The By object used to find the element.
	 * @return the WebElement that you are waiting for.
	 */
	public WebElement waitForPresence(By by) {
		return waitForPresence(by, STANDARD_TEN_SECOND_WAIT);
	}
	
	/**
	 * Waits for up to the specified number of seconds for a single element
	 * associated with the specified By to be present in the DOM.
	 * @param by The By object used to find the element.
	 * @param seconds The number of seconds to wait for the element to be present.
	 * @return the WebElement that you are waiting for.
	 */
	public WebElement waitForPresence(By by, int seconds) {
		return waitFor(by, seconds, ExpectedConditions.presenceOfElementLocated(by));
	}
	
	/**
	 * Waits for up to the default number of seconds (STANDARD_TEN_SECOND_WAIT) for 
	 * a single element associated with the specified By to be clickable.
	 * @param by The By object used to find the element.
	 * @return the WebElement that you are waiting for.
	 */
	public WebElement waitForClickable(By by) {
		return waitForClickable(by, STANDARD_TEN_SECOND_WAIT);
	}
	
	/**
	 * Waits for up to the specified number of seconds for a single element
	 * associated with the specified By to be clickable.
	 * @param by The By object used to find the element.
	 * @param seconds The number of seconds to wait for the element to be clickable.
	 * @return the WebElement that you are waiting for.
	 */
	public WebElement waitForClickable(By by, int seconds) {
		return waitFor(by, seconds, ExpectedConditions.elementToBeClickable(by));
	}
	
	
	// Base method used to wait for a single element for various expected conditions
	private WebElement waitFor(By by, int seconds, ExpectedCondition<WebElement> expectedCondition) {
		return new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(seconds))
			.pollingEvery(Duration.ofMillis(POLL_FREQUENCY_MILLIS))
			.ignoring(StaleElementReferenceException.class)
			.until(expectedCondition);
	}
	
	/**
	 * Gets all displayed WebElements associated with the specified By using the polling 
	 * interval and total polling timeout. If no WebElements are returned, the method will retry 
	 * up to the specified number.
	 * @param locator The By used to find the target WebElements.
	 * @param retries The number of times to retry the method if no WebElements are returned.
	 * @param interval The polling interval.
	 * @param timeout The total polling timeout.
	 * @return the displayed WebElements found, which could be 0 if none are found
	 */
	public List<WebElement> getAllElementsDisplayed(By locator, int retries, Duration interval, Duration timeout) {
		
		List<WebElement> visibleElements = new ArrayList<>();
		
		try {
			new ConditionPoller() {

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
	
	// Gets all WebElements associated with the By, retrying the specified number of times
	// with a 0 polling interval and total timeout.
	private List<WebElement> getAllElementsDisplayed(By locator, int retries) {
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

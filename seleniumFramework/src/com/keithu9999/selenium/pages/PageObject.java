package com.keithu9999.selenium.pages;

import org.openqa.selenium.WebDriver;

import com.keithu9999.selenium.infra.SmartElement;

/**
 * Root page object class inherited by all concrete page object classes
 * used within this framework.
 * 
 * @author keithu9999
 */
public abstract class PageObject extends SmartElement {

	public WebDriver driver;
	
	public PageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	/**
	 * @return an instance of the page object class
	 */
	public abstract PageObject getPage();

}

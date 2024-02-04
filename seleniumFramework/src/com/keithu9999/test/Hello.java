package com.keithu9999.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.keithu9999.infra.BrowserType;
import com.keithu9999.infra.SeleniumDriver;
import com.keithu9999.infra.SmartElement;

public class Hello extends SmartElement {
	
	public Hello(WebDriver driver) {
		super(driver);
	}

	public static void main(String[] args) {
		
		SeleniumDriver sd = new SeleniumDriver(BrowserType.CHROME, "--remote-allow-origins=*");
		Hello hi = new Hello(sd.getDriver());

		hi.getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");
		hi.getDriver().getTitle();
        
        WebElement textBox = hi.waitForVisible(By.cssSelector("#my-text-id"), 10);
        WebElement submitButton = hi.waitForVisible(By.cssSelector("button.btn-outline-primary"), 10);

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = hi.waitForVisible(By.cssSelector("#message"), 10);
        message.getText();

        hi.getDriver().quit();
    }

}

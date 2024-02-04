package com.keithu9999.test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class SeleniumTest {

    @BeforeTest
    public void beforeTest() {
        // No-op for now
    }

    @AfterTest
    public void afterTest() {
    	// No-op for now
    }
}
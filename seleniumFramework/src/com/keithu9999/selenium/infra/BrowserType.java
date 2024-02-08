package com.keithu9999.selenium.infra;

public enum BrowserType {
	
    CHROME("Chrome", "webdriver.chrome.driver", "C:\\Users\\keith\\git\\drivers\\chrome\\chromedriver.exe"),
    FIREFOX("Firefox", "webdriver.chrome.driver", "Mozilla");
    
    private String name;
    private String driverPath;
    private String driverProperty;

    BrowserType(String name, String driverProperty, String driverPath) {
        this.name = name;
        this.driverPath = driverPath;
        this.driverProperty = driverProperty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }
    
    public String getDriverProperty() {
        return driverProperty;
    }

    public void setDriverPropery(String driverProperty) {
        this.driverProperty = driverProperty;
    }
}

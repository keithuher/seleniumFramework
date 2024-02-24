package com.keithu9999.selenium.infra;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public enum GridType {
	
    LOCAL("local", null),
    DESKTOP("desktop", "http://127.0.0.1:4444");
    
    private String name;
    private URL url;
    
    private final Logger logger = Logger.getLogger("SeleniumDriver");

    GridType(String name, String url) {
        this.name = name;
        try {
			this.url = new URL(url);
		} catch (MalformedURLException ex) {
			logger.warning(ex.getStackTrace().toString());
		}
    }

    public String getName() {
        return name;
    }
    
    public URL getURL() {
        return url;
    }
}

package com.keithu9999.selenium.infra;

import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum GridType {
	
    LOCAL("local", null),
    DESKTOP("desktop", "http://127.0.0.1:4444");
    
    private String name;
    private URL url;
    
    private final Logger LOG = LoggerFactory.getLogger(GridType.class);

    GridType(String name, String url) {
        this.name = name;
        try {
			this.url = new URL(url);
		} catch (MalformedURLException ex) {
			LOG.warn(ex.getStackTrace().toString());
		}
    }

    public String getName() {
        return name;
    }
    
    public URL getURL() {
        return url;
    }
}

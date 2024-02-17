package com.keithu9999.selenium.infra;

public enum GridType {
	
    LOCAL("local"),
    DESKTOP("desktop");
    
    private String name;

    GridType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

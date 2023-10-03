package org.example.enums;

public enum BrowserType {
    CHROME("chrome"),
    EXPLORER("internet explorer"),
    FIREFOX("firefox");


    private final String value;

    BrowserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.banking.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import com.banking.automation.enums.BrowserType;

public class ConfigurationManager {
	private static final String CONFIG_FILE = "config/config.properties";
	private static final String BROWSER_KEY = "browser";
    private static final String ENVIRONMENT_KEY = "environment";
    private static final String BASE_URL_KEY = "base.url";
    private static final String EXPLICIT_WAIT_KEY = "explicit.wait";
    private static final String PAGE_LOAD_TIMEOUT_KEY = "page.load.timeout";
    
    private final Properties properties;
    public ConfigurationManager() {
    	this.properties = loadProperties();
    }
    
    private Properties loadProperties() {
    	Properties loadedProperties = new Properties();
    	
    	try (InputStream inputStream = getClass()
    			.getClassLoader()
    			.getResourceAsStream(CONFIG_FILE)){
    		if(Objects.isNull(inputStream)) {
    			throw new IllegalStateException(
    					"COnfiguration file was not on the classpath: "+CONFIG_FILE);
    		}
    		loadedProperties.load(inputStream);
    		return loadedProperties;
    	} catch (IOException e) {
    		throw new IllegalStateException(
    				"Failed to load configuration file: "+ CONFIG_FILE, e);
    	}
    	
    }
    
    //Browser
    public BrowserType getBrowser() {
    	String browser = getRequiredProperty(BROWSER_KEY);
    	
    	try {
    		return BrowserType.valueOf(browser.trim().toUpperCase());
    	} catch (IllegalArgumentException e) {
    		throw new IllegalStateException("Unsupported browser configuration: "+ browser, e);
    	}
    }
    
    //Environment
    public String getEnvironment() {
    	return getRequiredProperty(ENVIRONMENT_KEY);
    }
    
    //Base URL
    public String getBaseURL() {
    	return getRequiredProperty(BASE_URL_KEY);
    }
    
    //Explicit Wait
    public int getExplicitWait() {
    	return getPositiveIntegerProperty(EXPLICIT_WAIT_KEY);
    }
    
    //Page Load Timeout
    public int getPageLoadTimeout() {
    	return getPositiveIntegerProperty(PAGE_LOAD_TIMEOUT_KEY);
    }
    
    //Helper Methods
    private String getRequiredProperty(String key) {
    	String systemProperty = System.getProperty(key);
    	if (systemProperty != null && !systemProperty.isBlank()) {
    		return systemProperty.trim();
    	}
    	
    	String fileProperty = properties.getProperty(key);
    	if(fileProperty == null || fileProperty.isBlank()) {
    		throw new IllegalStateException(
    				"Required configuration property is missing: "+key);
    	}
    	
    	return fileProperty.trim();
    }
    
    private int getPositiveIntegerProperty(String key) {
    	String value = System.getProperty(key);
    	
    	try {
    		int parseValue = Integer.parseInt(value);
    		
    		if(parseValue <= 0) {
    			throw new IllegalStateException(
    					"Configuration property must be greater than zero: "+key+" = "+value);
    		}
    		
    		return parseValue;
    		
    	} catch (NumberFormatException e) {
    		throw new IllegalStateException(
    				"Configuration property must be valid integer: "+key+" = "+value,
    				e);
    	}
    }
    
}

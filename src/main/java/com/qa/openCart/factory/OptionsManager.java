package com.qa.openCart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private Properties prop;
	
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	
	//Constructor
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====running script in headless mode, headless = true in properties file.====");
			co.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("====running script in incognito mode, incognito = true in properties file.====");
			co.addArguments("--incognito");
		}
		
		return co;
	}
	
	
	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====running script in headless mode, headless = true in properties file.====");
			fo.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("====running script in incognito mode, incognito = true in properties file.====");
			fo.addArguments("--incognito");
		}
		
		return fo;
	}
	
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("====running script in headless mode, headless = true in properties file.====");
			eo.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("====running script in incognito mode, incognito = true in properties file.====");
			eo.addArguments("--inPrivate");
		}
		
		return eo;
	}
	
	
	

}

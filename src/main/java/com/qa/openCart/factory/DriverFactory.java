package com.qa.openCart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.errors.AppError;
import com.qa.openCart.exceptions.BrowserException;
import com.qa.openCart.exceptions.FrameworkException;

  
public class DriverFactory {

	WebDriver driver;
	Properties prop;
	FileInputStream file = null;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This is init the driver on the besis on given browser name
	 * 
	 * @param browserName
	 */
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		System.out.println("Browser Name Is " + browserName);
		
		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		default:
			System.out.println("please pass correct browser..." + browserName);
			throw new BrowserException(AppError.BROSER_NOT_FOUND);
		}
		
         // used for ThreadLocal driver
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		// Used for driver 
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.get(prop.getProperty("url"));

		return getDriver();
		//return driver;  // used for driver
	}

	/**
	 * Get the local thread copy of driver
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	
	/**
	 * This is use to init properties from .properties file
	 * 
	 * @return this return properties (prop)
	 */
	public Properties initProp() {

		// mvn clean install -Denv="qa" -->this is a command which we have to run from
		// cmd
		String envName = System.getProperty("env");
		System.out.println("running test suite on env-->" + envName);
		prop = new Properties();
		try {

			if (envName == null) {
				System.out.println("env name is null.. running the qa env by default.." + envName);
				file = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
			} else {

				switch (envName.trim().toLowerCase()) {
				case "qa":
					file = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
					break;
				case "stage":
					file = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
					break;
				case "dev":
					file = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
					break;
				case "uat":
					file = new FileInputStream(AppConstants.CONFIG_UAT_FILE_PATH);
					break;
				case "prod":
					file = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
					break;

				default:
					System.out.println("please pass the right env name..." + envName);
					throw new FrameworkException("===WRONG ENV NAME PASSED===");

				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;

		
		//OR - only single env
		
//		prop = new Properties();
//		
//		try {
//			FileInputStream file = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
//			prop.load(file);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return prop;
	}
	
	//screen shot method
//	public static  String getScreenshot(String methodName) {
//		
//		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
//		
//		String path = System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
//		
//		File destination = new File(path);
//		
//		//srcFile.renameTo(destination);
//		//Or
//		try {
//			org.openqa.selenium.io.FileHandler.copy(srcFile, destination);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return path;
//	}
	
	
	//OR this method generte screenshot folder automaticall if not avalable
	
	public static String getScreenshot(String methodName) {

	    File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

	    String path = System.getProperty("user.dir") 
	            + "/screenshots/" 
	            + methodName + "_" 
	            + System.currentTimeMillis() + ".png";

	    File folder = new File(System.getProperty("user.dir") + "/screenshots");

	    if (!folder.exists()) {
	        folder.mkdir();   // create folder if not present
	    }

	    try {
	    	org.openqa.selenium.io.FileHandler.copy(src, new File(path));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return path;
	}
	
	
	
	

}

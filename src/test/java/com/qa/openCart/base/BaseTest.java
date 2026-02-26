package com.qa.openCart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.openCart.factory.DriverFactory;
import com.qa.openCart.pages.AccountsPage;
import com.qa.openCart.pages.LoginPage;
import com.qa.openCart.pages.RegistrationPage;
import com.qa.openCart.pages.SearchResultPage;
import com.qa.openCart.pages.productInfoPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	
	protected LoginPage loginpage;
	protected AccountsPage accPage;
	protected SearchResultPage searchResultPage;
	protected productInfoPage productInfoPage;
	protected RegistrationPage regPage;
	
	protected SoftAssert softAssert;
	
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional("chrome") String browserName){
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!= null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
		softAssert = new SoftAssert();
		
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}

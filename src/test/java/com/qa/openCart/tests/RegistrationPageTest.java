package com.qa.openCart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.openCart.base.BaseTest;
import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.errors.AppError;
import com.qa.openCart.listeners.ExtendReportListener;
import com.qa.openCart.listeners.TestAllureListener;
import com.qa.openCart.utils.ExcelUtil;
import com.qa.openCart.utils.StringUtils;

@Listeners({ExtendReportListener.class, TestAllureListener.class})
public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetUp(){
		regPage = loginpage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] userRegTestData() {
		
		return new Object[][] {
			{"arti", "mar", "9876548656", "Arti@123,", "yes"},
			{"Shubham", "adsod", "9876098656", "Shubh@123,", "no"},
			{"pratik", "dafade", "9876548609", "Pratik@123,", "yes"},
		};
		
	}
	
	@DataProvider
	public Object[][] useRegTestDataFromSheet() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	
	
	@Test(dataProvider = "userRegTestData")
	public void userRegistrationTest(String firstName, String lastName, String teliPhone, String password,
			       String subcribe ) {
		
		Boolean flag = regPage.userRegister(firstName, lastName, StringUtils.getRandomEmailId(), teliPhone,
				   password, subcribe);
		Assert.assertTrue(flag, AppError.USER_REG_NOT_DONE);
		
	}

}

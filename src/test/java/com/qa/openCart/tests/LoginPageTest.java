package com.qa.openCart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.openCart.base.BaseTest;
import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.errors.AppError;
import com.qa.openCart.listeners.ExtendReportListener;
import com.qa.openCart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Stories;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

@Epic("Epic 100 : Designe open cart application with shoppig workflow.")
@Story("User story 101 : Designe login page for open cart application.")

@Listeners({ExtendReportListener.class, TestAllureListener.class})
public class LoginPageTest extends BaseTest {
	
	@Description("Checking login page title...") //imp
	@Severity(SeverityLevel.MINOR) //imp
	@Feature("login page title features") // imp
	@Owner("Shubbham Adsod")
	@Issue("Bug-200")
	@TmsLink("wwww.teams")
	@Link("www.opencart.com")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	@Description("Checking login page URL...")
	@Feature("login page URL features")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginpage.getLoginPageUrl();
		Assert.assertTrue(actUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
	}
	
	@Description("Checking forgot password link exist on login page...")
	@Feature("forgot password link exist features")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPswLinkExistTest() {
		Assert.assertTrue(loginpage.checkForgotPwdExist(), AppError.ELEMENT_NOT_FOUND);
	}
	
	@Description("Checking user is able to login successfully...")
	@Feature("Login page features")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void doLoginTest() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE, AppError.TITLE_NOT_FOUND);

	}

}

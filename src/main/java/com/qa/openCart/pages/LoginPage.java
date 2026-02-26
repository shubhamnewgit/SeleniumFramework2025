package com.qa.openCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;
import com.qa.openCart.utils.TimeUtils;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. page object : By Locators

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.xpath("//input[@value='Login']");
	private By forgotPswlink = By.linkText("Forgotten Password");
	private By Register = By.linkText("Register");

	// 2. public constructor of the page

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	// 3. public page actions/methods

	@Step("Getting login page title...")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtils.DEFAULT_TIME);
		System.out.println("login page title is : " + title);
		return title;
	}

	@Step("Getting login page URL...")
	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtils.DEFAULT_TIME);
		System.out.println("Login Page Url :" + url);
		return url;
	}

	@Step("Getting a state of forgotvpassword link exist...")
	public boolean checkForgotPwdExist() {
		return eleUtil.doIsDisplay(forgotPswlink);
	}

	@Step("Login to application with username : {0} and password : {1}")
	public AccountsPage doLogin(String userName, String pwd) {
		eleUtil.doSendkeys(emailId, userName, TimeUtils.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendkeys(password, pwd);
		eleUtil.doClick(loginbtn, TimeUtils.DEFAULT_MEDIUM_TIME);
		return new AccountsPage(driver);

	}
	
	@Step("Navigated to the user registration page...")
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.doClick(Register, TimeUtils.DEFAULT_TIME);
		return new RegistrationPage(driver);
	}

}

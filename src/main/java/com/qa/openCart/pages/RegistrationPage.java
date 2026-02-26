package com.qa.openCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;
import com.qa.openCart.utils.TimeUtils;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	//locators
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	
	//constructor
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
	
	
	public boolean userRegister(String firstName, String lastName, String email, String teliphone, 
			 String password, String subscribe) {
		 eleUtil.doSendkeys(this.firstName, firstName, TimeUtils.DEFAULT_TIME);
		 eleUtil.doSendkeys(this.lastName, lastName);
		 eleUtil.doSendkeys(this.email, email);
		 eleUtil.doSendkeys(this.telephone, teliphone);
		 eleUtil.doSendkeys(this.password, password);
		 eleUtil.doSendkeys(this.confirmpassword, password);
		 
		 if(subscribe.equalsIgnoreCase("yes")) {
			 eleUtil.doClick(subscribeYes);
		 }else {
			 eleUtil.doClick(subscribeNo);
		 }
		 
		 eleUtil.doClick(agreeCheckBox);
		 eleUtil.doClick(continueButton);
		 
		 String successMsg = eleUtil.waitForElementVisible(successMessg, TimeUtils.DEFAULT_TIME).getText();
		 System.out.println(successMsg);
		 
		 if(successMsg.contains(AppConstants.USER_REGISTER_SUCCESS_MSG)) {
			 eleUtil.doClick(logoutLink);
			 eleUtil.doClick(registerLink);
			 return true;
		 }else {
			 return false;
		 }
		 
		
	}

}

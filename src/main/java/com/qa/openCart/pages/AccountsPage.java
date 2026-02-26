package com.qa.openCart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;
import com.qa.openCart.utils.TimeUtils;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1 define constructor
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 1 locators

	//private By passwordLink = By.linkText("Password");
	//private By passwordLink = By.xpath("//aside[@id='column-right']//a[text()='Password']");
	private By logo = By.xpath("//div[@id='logo']//img[@alt='naveenopencart']");
	private By headers = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");

	// Methods

	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNT_PAGE_TITLE, TimeUtils.DEFAULT_TIME);
		System.out.println("Account Page title : " + title);
		return title;
	}

	public String getAccountPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_FRACTION_URL, TimeUtils.DEFAULT_TIME);
		System.out.println("Account Page Url : " + url);
		return url;
	}

//	public boolean ispasswordLinkExist() {
//		return eleUtil.doIsDisplay(passwordLink);
//		
//	}

	public boolean isAccountPageLogoExist() {
		return eleUtil.doIsDisplay(logo);
	}

	public List<String> getAccPageHeaders() {
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElementsLocated(headers, TimeUtils.DEFAULT_MEDIUM_TIME);
		
		List<String> headersListName = new ArrayList<String>();
		for (WebElement e : headersList) {
			String headerName = e.getText();
			System.out.println("HEADER NAME ARE : " + headerName);
			headersListName.add(headerName);
		}

		return headersListName;

	}

	public boolean isSearchExist() {
		return eleUtil.doIsDisplay(search);
	}

	
	public SearchResultPage doSearch(String searchKye) {
		System.out.println("search key is :" + searchKye);

		if (isSearchExist()) {
			eleUtil.doSendkeys(search, searchKye);
			eleUtil.doClick(searchIcon);
			return new SearchResultPage(driver);
		} else {
			System.out.println("search field is not present on the page...");
			return null;
		}

	}

}

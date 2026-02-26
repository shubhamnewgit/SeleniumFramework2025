package com.qa.openCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.openCart.utils.ElementUtil;
import com.qa.openCart.utils.TimeUtils;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//locators
	private By searchResult = By.cssSelector("div.product-thumb");
	
	
	
	public SearchResultPage (WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	//methods 
	public int getSearchresultsCount() {
		int searchResultCount = 
			eleUtil.waitForVisibilityOfElementsLocated(searchResult, TimeUtils.DEFAULT_MEDIUM_TIME).size();
		System.out.println("search result count is : " + searchResultCount);
		return searchResultCount;
	}
	
	public productInfoPage selectProduct(String productName) {
		eleUtil.doClick(By.linkText(productName),TimeUtils.DEFAULT_MEDIUM_TIME);
		return new productInfoPage(driver);
	}

}

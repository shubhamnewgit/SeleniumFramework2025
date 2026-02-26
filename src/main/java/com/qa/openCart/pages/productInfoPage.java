package com.qa.openCart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.openCart.utils.ElementUtil;
import com.qa.openCart.utils.TimeUtils;

public class productInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	//locators
	private By productHeader = By.cssSelector("div#content h1");
	private By imgCounter = By.cssSelector("ul.thumbnails li a");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By producpriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> productMap;
	
	
	
	
	// 1 define constructor
	public productInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public String getProductHeader() {
		String productHeadername = eleUtil.doGetText(productHeader);
		System.out.println("product header name is ====" + productHeadername);
		return productHeadername;
	}
	
	public int getImgCount() {
		int imgCount  = 
				eleUtil.waitForVisibilityOfElementsLocated(imgCounter, TimeUtils.DEFAULT_MEDIUM_TIME).size();
		System.out.println("total image count is =====" + imgCounter);
		return imgCount;
	}
	
	
	public Map<String, String> getProductInfoMap() {
		productMap = new HashMap<String, String>();
		//productMap = new LinkedHashMap<String, String>();
		//productMap = new TreeMap<String, String>();
		
		productMap.put("productName", getProductHeader());
		productMap.put("productImgsCount", String.valueOf(getImgCount()));
		
		getProductMetadata();
		getProductPriceData();
		return productMap;
			
	}
	
//Brand: Apple
//Product Code: Product 18
//Reward Points: 800
//Availability: Out Of Stock
	
	private void getProductMetadata() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		for(WebElement e : metaList) {
			String metaDataText = e.getText();
			String meta [] = metaDataText.split(":");
			String metaKey = meta[0].trim();
			String metavalue = meta[1].trim();
			productMap.put(metaKey, metavalue);
		}
	}
	
//$2,000.00
//Ex Tax: $2,000.00
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(producpriceData);
		String productPrice = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice",productPrice);
		productMap.put("exTaxPrice",exTaxPrice);
		
	}

}

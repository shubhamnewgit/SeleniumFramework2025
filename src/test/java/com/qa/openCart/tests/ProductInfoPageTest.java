package com.qa.openCart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.openCart.base.BaseTest;
import com.qa.openCart.errors.AppError;
import com.qa.openCart.listeners.ExtendReportListener;
import com.qa.openCart.listeners.TestAllureListener;

@Listeners({ExtendReportListener.class, TestAllureListener.class})
public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void productInfoSetUp() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object[][] getProductdata() {
		return new Object[][] {
			{"macbook", "MacBook Pro"},
			{"imac", "iMac"},
			{"samsung", "Samsung SyncMaster 941BW"},
			{"samsung", "Samsung Galaxy Tab 10.1"},
			{"canon", "Canon EOS 5D"}
		};
	}
	
	@Test(dataProvider = "getProductdata")
	public void productHeaderTest(String searchKey, String productName) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName, AppError.HEADER_NOT_FOUND);
	}
	
	
	@DataProvider
	public Object[][] getProductImgData() {
		return new Object[][] {
			{"macbook", "MacBook Pro", 4},
			{"imac", "iMac", 3},
			{"samsung", "Samsung SyncMaster 941BW", 1},
			{"samsung", "Samsung Galaxy Tab 10.1", 7},
			{"canon", "Canon EOS 5D", 3}
		};
	}
	@Test(dataProvider = "getProductImgData")
	public void productImgTest(String searchKey, String productName, int imgsCount) {
		searchResultPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getImgCount(), imgsCount, AppError.IMAGES_COUNT_MISMATCHED);
		
	}
	
	@Test
	public void productInfoTest() {
		searchResultPage = accPage.doSearch("macbook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String> productInfoMap = productInfoPage.getProductInfoMap();
		System.out.println("======PRODUCT INFO MAP=====");
		System.out.println(productInfoMap);
		
		softAssert.assertEquals(productInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"), "800");
		softAssert.assertEquals(productInfoMap.get("exTaxPrice"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("productImgsCount"), "4");
		softAssert.assertEquals(productInfoMap.get("productPrice"), "$2,000.00");
		
		softAssert.assertAll();
	}

}

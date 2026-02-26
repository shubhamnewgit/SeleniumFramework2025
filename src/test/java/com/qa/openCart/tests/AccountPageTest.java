package com.qa.openCart.tests;

import java.util.List;

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

@Listeners({ExtendReportListener.class, TestAllureListener.class})
public class AccountPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test
	public void accPageTitleTest() {
		String actAccPageTitle = accPage.getAccountPageTitle();
		Assert.assertEquals(actAccPageTitle, AppConstants.ACCOUNT_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	@Test
	public void accPageUrlTest() {
		String actAccUrl = accPage.getAccountPageUrl();
		Assert.assertTrue(actAccUrl.contains(AppConstants.ACC_PAGE_FRACTION_URL), AppError.URL_NOT_FOUND);
	}
	
//	@Test
//	public void accPagePasswordLinkTest() {
//		Assert.assertTrue(accPage.ispasswordLinkExist(), AppError.ELEMENT_NOT_FOUND);
//		
//	}
	
	@Test
	public void accPageLogoTest() {
		Assert.assertTrue(accPage.isAccountPageLogoExist(), AppError.ELEMENT_NOT_FOUND);
	}
	
	@Test
	public void accpageHeadersListTest() {
		List<String> accPageHeadersList = accPage.getAccPageHeaders();
		Assert.assertEquals(accPageHeadersList, AppConstants.ACC_PAGE_HEADERS_LIST, AppError.LIST_IS_NOT_MATCH);
	}
	
	@DataProvider
	public Object[][] getSearchdata() {
		return new Object[][] {
			{"macbook", 3},
			{"imac", 1},
			{"samsung", 2},
			{"airtel", 0}
		};
	}
	
	@Test(dataProvider="getSearchdata")
	public void searchTest(String searchKey, int expResultCount) {
		searchResultPage = accPage.doSearch(searchKey);
		Assert.assertEquals(searchResultPage.getSearchresultsCount(), expResultCount, AppError.RESULT_COUNT_MISTMATCH);
	}
	
	

}

package com.qa.openkarat.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Open_cart.constant.AppConstants;
import com.qa.openkarat.base.BaseTest;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNNT_PAGE_TITLE_VALUE);
	}

	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNNT_PAGE_URL_FRACTION_VALUE));
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}

	@Test
	public void isSearchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}

	@Test
	public void accPAgeHeadersCountTest() {
		List<String> acctualAccPageHeaderslList = accPage.getAccountHeadersList();
		System.out.println("Acc page Headers List: " + acctualAccPageHeaderslList);
		Assert.assertEquals(acctualAccPageHeaderslList.size(), AppConstants.ACCOUNNT_PAGE_URL_HEADERS_COUNT);
	}

	@Test
	public void accPAgeHeadersValueTest() {
		List<String> acctualAccPageHeaderslList = accPage.getAccountHeadersList();
		System.out.println("Actual Acc page Headers List: " + acctualAccPageHeaderslList);
		System.out.println("Expected Acc page Headers List: " + AppConstants.EXPECTED_ACCOUNT_PAGE_HEADERS_LIST);
		Assert.assertEqualsNoOrder(acctualAccPageHeaderslList, AppConstants.EXPECTED_ACCOUNT_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook"},
			{"iMac"},
			{"Apple"},
			{"Samsung"}
		};
	}

	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchkey) {
		searchPage = accPage.performSearch(searchkey);
		Assert.assertTrue(searchPage.getSearchProductCount() > 0);

	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Apple","Apple Cinema 30\""},
			{"Samsung", "Samsung SyncMaster 941BW" },
			{"Samsung", "Samsung Galaxy Tab 10.1"}
		};
	}

	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String productName) {
		searchPage = accPage.performSearch(searchKey);
		if (searchPage.getSearchProductCount() > 0) {
			productInfoPage = searchPage.selectproduct(productName);
			String actProductHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProductHeader, productName);
		}

	}

}

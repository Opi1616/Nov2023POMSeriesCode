package com.qa.openkarat.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.openkarat.base.BaseTest;

public class ProductPageInfoTest extends BaseTest {
	@BeforeClass
	public void ProductInfoPageTest() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro",4},
			{"iMac", "iMac", 3},
			{"Apple","Apple Cinema 30\"",6},
			{"Samsung", "Samsung SyncMaster 941BW",1},
		};
	}

	@Test(dataProvider="getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectproduct(productName);
		int actImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}
	
	@Test
	public void productInfoTest() {
		searchPage = accPage.performSearch("MacBook");
		productInfoPage = searchPage.selectproduct("MacBook Pro");
		Map<String, String> actuProductInfoMap = productInfoPage.getProductInfo();
	//	System.out.println(actuProductInfoMap);
		softAssert.assertEquals(actuProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actuProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actuProductInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actuProductInfoMap.get("productPrice"), "$2,000.00");
	
		softAssert.assertAll();
	
	}
	
	//assert vs verify(soft assertion)
	@Test
	public void addToCartTest() {
		searchPage = accPage.performSearch("MacBook");
		productInfoPage = searchPage.selectproduct("MacBook Pro");
		productInfoPage.enterQuantity(2);
	String actCartMesg=	productInfoPage.addProductToCart();
	//Success: You have added MacBook Pro to your shopping cart!
	softAssert.assertTrue(actCartMesg.indexOf("Success")>=0);
	softAssert.assertTrue(actCartMesg.indexOf("MacBook Pro")>=0);
	softAssert.assertEquals(actCartMesg, "Success: You have added MacBook Pro to your shopping cart!");
	softAssert.assertAll();
	}
	
	
	

}

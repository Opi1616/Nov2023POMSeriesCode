package com.qa.openkarat.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Open_cart.constant.AppConstants;
import com.qa.openkarat.base.BaseTest;

import co.qa.openCart.Utils.ExcelUtil;

public class RegPageTest extends BaseTest {

	@BeforeClass
	public void regpageSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	public String getRandomEmail() {
		Random random = new Random();
		// String email = "automationN" + random.nextInt(8000) + "@gmail.com";
		String email = "automationN" + System.currentTimeMillis() + "@gmail.com";
		return email;
	}

	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getRegTestData")
	public void userRegTest(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		Assert.assertTrue(
				registerPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}

}

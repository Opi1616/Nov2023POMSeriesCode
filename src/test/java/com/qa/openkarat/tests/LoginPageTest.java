package com.qa.openkarat.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.Open_cart.constant.AppConstants;
import com.qa.openkarat.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC -100: design for open cart app")
@Story("US-Login: 101:design loginpage features for open cart")
public class LoginPageTest extends BaseTest {
	@Severity(SeverityLevel.TRIVIAL)
	@Description("...checking the title of the page... tester:Naveen")
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitile = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitile, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("...checking  the url of the page... tester:Naveen")
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("..checking forgot pwd link exist... tester:Naveen")
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("..checking user is able to login to app with correct username and password... tester:Naveen")
	@Test(priority = 4)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}

}

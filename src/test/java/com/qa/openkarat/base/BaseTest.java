package com.qa.openkarat.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.openkarat.factory.DriverFractory;
import com.qa.openkarat.pages.AccountsPage;
import com.qa.openkarat.pages.LoginPage;
import com.qa.openkarat.pages.ProductInfoPage;
import com.qa.openkarat.pages.RegisterPage;
import com.qa.openkarat.pages.SearchPage;

public class BaseTest {

	DriverFractory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage registerPage;

	@BeforeTest
	public void setup() {
		df = new DriverFractory();
		prop = df.initProp();
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);

		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}

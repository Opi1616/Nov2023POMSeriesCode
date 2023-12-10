package com.qa.openkarat.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.Open_cart.constant.AppConstants;

import co.qa.openCart.Utils.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By fName = By.id("input-firstname");
	private By lName = By.xpath("//input[@name='lastname']");
	private By emailId = By.id("input-email");
	private By Telephone = By.id("input-telephone");
	private By pwd = By.id("input-password");
	private By cpwd = By.id("input-confirm");

	private By agree = By.xpath("//input[@name='agree']");
	private By submit = By.xpath("//input[@value='Continue']");
	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");

	private By registerSuccessMesg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean registerUser(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		eleUtil.waitForElementVisible(fName, AppConstants.DEFAULT_SHORT_TIME_OUT).sendKeys(firstName);
		eleUtil.doSendKeys(lName, lastName);
		eleUtil.doSendKeys(emailId, email);
		eleUtil.doSendKeys(Telephone, telephone);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doSendKeys(cpwd, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}

		eleUtil.doClick(agree);
		eleUtil.doClick(submit);

		String SuccessMsg = eleUtil.waitForElementVisible(registerSuccessMesg, AppConstants.DEFAULT_MEDIUM_TIME_OUT)
				.getText();
		System.out.println("user reg success messg: " + SuccessMsg);

		if (SuccessMsg.contains(AppConstants.USER_REG_SUCCESS_MESSG)) {

			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

}

package com.pom.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class ForgetPasswordPage {

	private static final Logger log = Logger.getLogger(ForgetPasswordPage.class.getName());
	private WebDriver driver;

	@CacheLookup
	@FindBy(id = "forgotEmail")
	private WebElement forgotEmail;

	@CacheLookup
	@FindBy(xpath = "//button[contains(text(),'Reset')]")
	private WebElement reset;

	@CacheLookup
	@FindBy(className = "error-msg")
	private WebElement errMessage;

	public ForgetPasswordPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void typeForgetEmail(String email) {
		forgotEmail.sendKeys(email);
		log("entered email:-" + email + "and object is " + forgotEmail.toString());
	}

	public void waitFor2Second() throws InterruptedException {
		Thread.sleep(2000);
	}

	public void clickOnResetbutton() throws InterruptedException {
		reset.click();
		log("clicked on Reset button and object is:- " + reset.toString());
	}

	public void resetPassword(String email) throws InterruptedException {
		waitFor2Second();
		typeForgetEmail(email);
		waitFor2Second();
		clickOnResetbutton();
	}

	public String getInvalidForgetPasswordErrorMessage() {
		log("error message is:- " + errMessage.getText());
		return errMessage.getText();
	}

	public void verifyInvalidForgetPasswordErrorMessage(String errorMessage) {
		Assert.assertEquals(getInvalidForgetPasswordErrorMessage(), (errorMessage));
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}
}

package com.pom.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class LoginPage {

	private static final Logger log = Logger.getLogger(LoginPage.class.getName());
	private WebDriver driver;

	@CacheLookup
	@FindBy(id = "loginEmail")
	private WebElement loginEmail;

	@CacheLookup
	@FindBy(id = "loginPassword")
	private WebElement loginPassword;

	@CacheLookup
	@FindBy(xpath = "//button[contains(text(),'Log in')]")
	private WebElement loginButton;

	@CacheLookup
	@FindBy(id = "rememberme")
	private WebElement rememberMe;

	@CacheLookup
	@FindBy(xpath = "//a[@class='forgot-btn']")
	private WebElement forgetPassword;

	@CacheLookup
	@FindBy(id = "alertMessage")
	private WebElement errMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clearFields() {
		loginEmail.clear();
		loginPassword.clear();
	}

	public void typeEmail(String email) {
		loginEmail.sendKeys(email);
		log("entered email:- " + email + " and object is " + loginEmail.toString());
	}

	public void typePassword(String password) {
		loginPassword.sendKeys(password);
		log("entered email:- " + password + " and object is " + loginPassword.toString());
	}

	public void clickOnLoginButton() {
		loginButton.click();
		log("clicked on Login button and object is:- " + loginButton.toString());
	}

	public void clickOnForgetPasswordLink() throws InterruptedException {
		waitFor2Second();
		forgetPassword.click();
		log("clicked on Forget Password link and object is:- " + forgetPassword.toString());
	}

	public void loginToApplication(String email, String password) throws InterruptedException {
		waitFor2Second();
		typeEmail(email);
		waitFor2Second();
		typePassword(password);
		waitFor2Second();
		clickOnLoginButton();
	}

	public String getInvalidLoginErrorMessage() {
		log("error message is:- " + errMessage.getText());
		return errMessage.getText();
	}

	public void verifyInvalidLoginErrorMessage(String errorMessage) {
		Assert.assertEquals(getInvalidLoginErrorMessage(), (errorMessage));
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

	public void waitFor2Second() throws InterruptedException {
		Thread.sleep(2000);
	}

}
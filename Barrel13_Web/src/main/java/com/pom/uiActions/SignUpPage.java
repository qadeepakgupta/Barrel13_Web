package com.pom.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class SignUpPage {

	private static final Logger log = Logger.getLogger(SignUpPage.class.getName());
	private WebDriver driver;

	/**
	 * Already a Seller On Amazon page elements
	 */
	
	@CacheLookup
	@FindBy(id = "signupName")
	private WebElement fullName;

	@CacheLookup
	@FindBy(id = "signupGst")
	private WebElement gstNo;

	@CacheLookup
	@FindBy(id = "firmName")
	private WebElement firmName;

	@CacheLookup
	@FindBy(id = "signupEmail")
	private WebElement email;

	@CacheLookup
	@FindBy(xpath = "//input[@id='signupPassword']")
	private WebElement password;

	@CacheLookup
	@FindBy(xpath = "//div[@class='new-users']//button[@type='button'][contains(text(),'Sign up')]")
	private WebElement signUpAlreadySelller;

	@CacheLookup
	@FindBy(xpath = "//div[@class='old-users']//button[@type='button'][contains(text(),'Sign up')]")
	private WebElement signUpNewSelller;

	@CacheLookup
	@FindBy(className = "error-msg")
	private WebElement errMessage;

	@CacheLookup
	@FindBy(xpath = "//*[contains(text(),'We have sent a verification link to your email. Kindly verify the link.')]")
	private WebElement infoMessage;

	/**
	 * New Seller On Amazon page elements
	 */

	@CacheLookup
	@FindBy(id = "localStoreName")
	private WebElement localStoreName;

	@CacheLookup
	@FindBy(id = "localStoreAddress")
	private WebElement localStoreAddress;

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void typeFullName(String enterFullName) {
		fullName.sendKeys(enterFullName);
		log("entered full name :- " + enterFullName + " and object is " + fullName.toString());
	}

	public void typeGSTNo(String enterGSTNo) {
		gstNo.sendKeys(enterGSTNo);
		log("entered GST no :- " + enterGSTNo + " and object is " + gstNo.toString());

	}

	public void typeFirmName(String enterFirmName) {
		firmName.sendKeys(enterFirmName);
		log("entered Firm Name :- " + enterFirmName + " and object is " + firmName.toString());
	}

	public void typeEmail(String enterEmail) {
		email.sendKeys(enterEmail);
		log("entered Email :- " + enterEmail + " and object is " + email.toString());
	}

	public void typePassword(String enterPassword) {
		password.sendKeys(enterPassword);
		log("entered Password :- " + enterPassword + " and object is " + password.toString());
	}

	public void typeLocalStoreName(String storeName) {
		localStoreName.sendKeys(storeName);
		log("entered Local Store Name :- " + storeName + " and object is " + localStoreName.toString());
	}

	public void typeLocalStoreAddress(String storeAddress) {
		localStoreAddress.sendKeys(storeAddress);
		log("entered Local Store Address :- " + storeAddress + " and object is " + localStoreAddress.toString());

	}

	public void clickOnSignUpAlreadySellerButton() {
		scrollDown(signUpAlreadySelller);
		log("Scroll Down till to sign Up button and object is:- " + signUpAlreadySelller.toString());
		signUpAlreadySelller.click();
		log("clicked on Sign Up button and object is:- " + signUpAlreadySelller.toString());
	}

	public void clickOnSignUpNewSellerButton() {
		scrollDown(signUpNewSelller);
		log("Scroll Down till to sign Up button and object is:- " + signUpNewSelller.toString());
		signUpNewSelller.click();
		log("clicked on Sign Up button and object is:- " + signUpNewSelller.toString());
	}

	public void signUpUserByAlreadySellerOnAmazon(String enterFullName, String enterGSTNo, String enterFirmName,
			String enterEmail, String enterPassword) {

		typeFullName(enterFullName);
		typeGSTNo(enterGSTNo);
		typeFirmName(enterFirmName);
		typeEmail(enterEmail);
		typePassword(enterPassword);
	}

	public void waitFor2Second() throws InterruptedException {
		Thread.sleep(2000);
	}

	public void signUpUserByAlreadySellerOnAmazonSubmit(String enterFullName, String enterGSTNo, String enterFirmName,
			String enterEmail, String enterPassword) throws InterruptedException {
		waitFor2Second();
		typeFullName(enterFullName);
		waitFor2Second();
		typeGSTNo(enterGSTNo);
		waitFor2Second();
		typeFirmName(enterFirmName);
		waitFor2Second();
		typeEmail(enterEmail);
		waitFor2Second();
		typePassword(enterPassword);
		waitFor2Second();
		clickOnSignUpAlreadySellerButton();
		waitFor2Second();
	}

	public void signUpUserByNewSellerOnAmazon(String enterFullName, String enterGSTNo, String storeName, String storeAddress,
			String enterEmail, String enterPassword) {

		typeFullName(enterFullName);
		typeGSTNo(enterGSTNo);
		typeLocalStoreName(storeName);
		typeLocalStoreAddress(storeAddress);
		typeEmail(enterEmail);
		typePassword(enterPassword);
	}

	public void signUpUserByNewSellerOnAmazonSubmit(String enterFullName, String enterGSTNo, String storeName,
			String storeAddress, String enterEmail, String enterPassword) throws InterruptedException {

		waitFor2Second();
		typeFullName(enterFullName);
		waitFor2Second();
		typeGSTNo(enterGSTNo);
		waitFor2Second();
		typeLocalStoreName(storeName);
		waitFor2Second();
		typeLocalStoreAddress(storeAddress);
		waitFor2Second();
		typeEmail(enterEmail);
		waitFor2Second();
		typePassword(enterPassword);
		waitFor2Second();
		clickOnSignUpNewSellerButton();
		waitFor2Second();
	}

	public void scrollUp(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollDown(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

		// This will scroll down the page by 1000 pixel vertical
		// js.executeScript("window.scrollBy(0,1000)");

		// This will scroll the web page till end.
		// js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		// This will scroll the page Horizontally till the element is found
		// js.executeScript("arguments[0].scrollIntoView();", Element);

	}

	public void waitForTextToBePresentInElement(WebElement element, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public String getInvalidSignUpErrorMessage() {
		log("error message is:- " + errMessage.getText());
		return errMessage.getText();
	}

	public void verifyInvalidSignUpErrorMessage(String errorMessage) {
		Assert.assertEquals(getInvalidSignUpErrorMessage(), (errorMessage));
	}

	public String getSignUpInfoMessage() {
		log("info message is:- " + infoMessage.getText());
		return infoMessage.getText();
	}

	public void verifySignUpInfoMessage(String validInfoMessage) {
		Assert.assertEquals(getSignUpInfoMessage(), (validInfoMessage));
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

}
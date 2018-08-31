package com.pom.uiActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class HomePage {

	private WebDriver driver;
	private static final Logger log = Logger.getLogger(HomePage.class.getName());

	@CacheLookup
	@FindBy(xpath = "//a[@href='http://barrel13.com/']//img")
	private WebElement logo;

	@CacheLookup
	@FindBy(xpath = "//a[contains(text(),'Products')]")
	private WebElement productsLink;

	@CacheLookup
	@FindBy(xpath = "//a[contains(text(),'Tour')]")
	private WebElement tourLink;

	@CacheLookup
	@FindBy(xpath = "//a[contains(text(),'Pricing')]")
	private WebElement pricingLink;

	@CacheLookup
	@FindBy(xpath = "//a[contains(text(),'About')]")
	private WebElement aboutLink;

	@CacheLookup
	@FindBy(xpath = "//a[@class='popup-btn common-btn']")
	private WebElement loginLink;

	@CacheLookup
	@FindBy(xpath = "//a[contains(@class,'btn-fill')]")
	private WebElement signUpLink;

	@CacheLookup
	@FindBy(partialLinkText = "Already a Seller")
	private WebElement alreadySellerOnAmazonLink;

	@CacheLookup
	@FindBy(partialLinkText = "New Seller on Amazon")
	private WebElement newSellerOnAmazonLink;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

	public void clickOnLogo() {
		logo.click();
		log("clicked on login link and object is:- " + logo.toString());
	}

	public void clickOnProductsLink() {
		productsLink.click();
		log("clicked on Products link and object is:- " + productsLink.toString());
	}

	public void clickOnTourLink() {
		tourLink.click();
		log("clicked on Tour link and object is:- " + tourLink.toString());
	}

	public void clickOnPricingLink() {
		pricingLink.click();
		log("clicked on Pricing link and object is:- " + pricingLink.toString());
	}

	public void clickOnAboutLink() {
		aboutLink.click();
		log("clicked on About link and object is:- " + aboutLink.toString());
	}

	public void clickOnLoginLink() throws InterruptedException {
		waitFor2Second();
		loginLink.click();
		log("clicked on Login link and object is:- " + loginLink.toString());
	}

	public void clickSignUpLink() {
		waitForElementClickable(signUpLink);
		signUpLink.click();
		log("clicked on Sign Up link and object is:- " + signUpLink.toString());
	}

	public void waitFor2Second() throws InterruptedException {
		Thread.sleep(2000);
	}

	public void waitForElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void clickOnAlreadyASellerOnAmazon() throws InterruptedException {
		waitFor2Second();
		clickSignUpLink();
		waitForElementClickable(alreadySellerOnAmazonLink);
		alreadySellerOnAmazonLink.click();
		log("clicked on Already A Seller On Amazon link and object is:- " + alreadySellerOnAmazonLink.toString());
	}

	public void clickOnNewSellerOnAmazon() throws InterruptedException {
		waitFor2Second();
		clickSignUpLink();
		waitForElementClickable(newSellerOnAmazonLink);
		newSellerOnAmazonLink.click();
		log("clicked on New Seller On Amazon link and object is:- " + newSellerOnAmazonLink.toString());
	}

}

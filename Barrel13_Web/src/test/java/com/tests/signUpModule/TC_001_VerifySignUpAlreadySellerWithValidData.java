package com.tests.signUpModule;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pom.testBase.TestBase;
import com.pom.uiActions.HomePage;
import com.pom.uiActions.SignUpPage;

public class TC_001_VerifySignUpAlreadySellerWithValidData extends TestBase {

	static final Logger log = Logger.getLogger(TC_001_VerifySignUpAlreadySellerWithValidData.class.getName());
	HomePage homePage;
	SignUpPage signUpPage;

	@BeforeClass
	public void setUp() throws IOException {
		init();
	}

	@Test
	public void testSignUpAlreadySellerOnAmazonWithValidData() throws InterruptedException {
		try {
			log.info("============= Starting testSignUpAlreadySellerOnAmazonWithValidData() Test =============");

			homePage = new HomePage(driver);
			homePage.clickOnAlreadyASellerOnAmazon();

			signUpPage = new SignUpPage(driver);
			signUpPage.signUpUserByAlreadySellerOnAmazonSubmit(OR.getProperty("Full_Name"), OR.getProperty("GST_No"),
					OR.getProperty("Firm_Name"), OR.getProperty("Email"), OR.getProperty("Password"));

			signUpPage.verifySignUpInfoMessage(OR.getProperty("Info_Message_Sign_Up"));

			log.info("============= Finished testSignUpAlreadySellerOnAmazonWithValidData() Test =============");
		} catch (Exception e) {
			log.error("Exception is: " + e.getMessage());
			log.error(e.getCause());
			log.error("============= Finished testSignUpAlreadySellerOnAmazonWithValidData() Test =============");
			e.printStackTrace();
			Assert.fail();
		}
	}
}
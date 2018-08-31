package com.tests.loginModule;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pom.testBase.TestBase;
import com.pom.uiActions.HomePage;
import com.pom.uiActions.LoginPage;

public class TC_001_VerifyLoginWithValidData extends TestBase {

	static final Logger log = Logger.getLogger(TC_001_VerifyLoginWithValidData.class.getName());
	HomePage homePage;
	LoginPage loginPage;

	@BeforeClass
	public void setUp() throws IOException {
		init();
	}

	@Test
	public void testLoginCredentials() {
		try {
			log.info("============= starting testLoginCredentials() Test =============");

			homePage = new HomePage(driver);
			homePage.clickOnLoginLink();

			loginPage = new LoginPage(driver);
			loginPage.loginToApplication(OR.getProperty("Valid_Email"), OR.getProperty("Valid_Password"));

			log.info("============= Finished testLoginCredentials() Test =============");
		} catch (Exception e) {
			log.error("Exception is: " + e.getMessage());
			log.error(e.getCause());
			log.error("============= Finished testLoginCredentials() Test =============");
			e.printStackTrace();
			Assert.fail();
		}
	}
}
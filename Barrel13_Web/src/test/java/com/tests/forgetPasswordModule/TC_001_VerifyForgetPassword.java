package com.tests.forgetPasswordModule;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pom.testBase.TestBase;
import com.pom.uiActions.ForgetPasswordPage;
import com.pom.uiActions.HomePage;
import com.pom.uiActions.LoginPage;

public class TC_001_VerifyForgetPassword extends TestBase {

	static final Logger log = Logger.getLogger(TC_001_VerifyForgetPassword.class.getName());
	HomePage homePage;
	LoginPage loginPage;
	ForgetPasswordPage forgetPasswordPage;

	@BeforeClass
	public void setUp() throws IOException {
		init();
	}

	@Test
	public void testForgetPassword() {

		try {
			log.info("============= starting testForgetPassword() Test =============");

			homePage = new HomePage(driver);
			homePage.clickOnLoginLink();

			loginPage = new LoginPage(driver);
			loginPage.clickOnForgetPasswordLink();

			forgetPasswordPage = new ForgetPasswordPage(driver);
			forgetPasswordPage.resetPassword(OR.getProperty("Email"));

			log.info("============= Finished testForgetPassword() Test =============");
		} catch (Exception e) {
			log.error("Exception is: " + e.getMessage());
			log.error(e.getCause());
			log.error("============= Finished testForgetPassword() Test =============");
			e.printStackTrace();
			Assert.fail();

		}

	}

}

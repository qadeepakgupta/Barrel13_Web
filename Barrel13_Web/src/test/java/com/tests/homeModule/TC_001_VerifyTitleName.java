package com.tests.homeModule;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pom.testBase.TestBase;

public class TC_001_VerifyTitleName extends TestBase {

	public static final Logger log = Logger.getLogger(TC_001_VerifyTitleName.class.getName());

	@BeforeClass
	public void setUp() throws IOException {
		init();
	}

	@Test
	public void testTitleName() throws InterruptedException {
		try {
			log.info("============= Starting testTitleName() Test =============");
			log.info("Current title name is : " + driver.getTitle());
			Assert.assertEquals(driver.getTitle(), "Barrel13");

			log.info("============= Finished testTitleName() Test =============");
		} catch (Exception e) {
			log.error("Exception is: " + e.getMessage());
			log.error(e.getCause());
			log.error("============= Finished testTitleName() Test =============");
			e.printStackTrace();
			Assert.fail();
		}
	}
}
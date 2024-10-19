package com.luru.web.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.luru.web.pages.LuruLoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Site Login")
@Feature("Login Test")
public class LuruLoginTest extends BaseLuruAutomationTest {
	private WebDriver driver = null;
	
	private static final Logger logger = Logger.getLogger(LuruLoginTest.class.getName());

	@BeforeClass
	@Parameters({ "browser", "siteURL" })
	public void initLuruSiteLogin(@Optional("Chrome") String browser, String siteURL) throws Exception {
		logger.info("Starting of initLuruSiteLogin methond");

		this.driver = this.getWebDriver(browser, WEB_DRIVER.LOGIN_DRIVER_TEST);
		this.goToSite(driver, siteURL);
		this.luruLoginPage = new LuruLoginPage(driver);
		logger.info("Ending of initLuruSiteLogin method");
	}

	@Test(priority = 1, description = "Verify User can login to the site")
	@Parameters({ "email", "password" })
	@Description("Test Description:Verify User can login to the site")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify User can login to the site")
	public void testLoginToSiteUI(String strUserName, String strPassword) {
		logger.info("Startitng of testLoginToSiteUI method");

		try {
			luruLoginPage.clickOnLoginLabel();
			luruLoginPage.switchToNewWindow();

			Assert.assertEquals(luruLoginPage.getLuruHeadingText(), expectedAssertionsProp.getProperty("label.luru"));

			luruLoginPage.clickOnSalesForceButton();

			Assert.assertEquals(luruLoginPage.getSalesForceHeadingText(),
					expectedAssertionsProp.getProperty("label.salesforce"));

			luruLoginPage.setUserName(testDataProp.getProperty("login.email.address"));
			luruLoginPage.setPassword(testDataProp.getProperty("login.password"));
			luruLoginPage.clickOnLoginButton();

		} catch (Exception e) {
			Assert.fail("Exception occured while testing LoginTosite: " + e.getMessage());
			logger.error("Error occured while testing the LoginTosite   ", e);
		}

		logger.info("Ending of testLoginToSiteUI method");
	}
	
	@Test(priority = 2, description = "Verify User can logout from site")
	@Description("Test Description:Verify User can logout from site")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify User can logout from site")
	public void testLogOutFromSite() {
		logger.info("Startitng of testLogOutFromSite method");

		try {
			luruLoginPage.clickOnSignOut();

			Assert.assertEquals(luruLoginPage.getLuruHeadingText(), expectedAssertionsProp.getProperty("label.luru"));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing User can logout from site: " + e.getMessage());
			logger.error("Error occured while testing the User can logout from site   ", e);
		}

		logger.info("Ending of testLogOutFromSite method");
	}

	@AfterClass
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			hardWait(3);
			if (this.driver != null) {
				this.quitDriver(this.driver, WEB_DRIVER.LOGIN_DRIVER_TEST);
				logger.debug("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		logger.info("Ending of quitDriver method");
	}
}

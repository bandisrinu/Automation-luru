package com.luru.web.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.luru.web.pages.LuruLoginPage;
import com.luru.web.pages.NotesPage;
import com.luru.web.pages.SettingsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Luru Note")
@Feature("Luru Note Test")
public class SettingsTest extends BaseLuruAutomationTest {
	private WebDriver driver = null;
	private NotesPage notesPage = null;
	private SettingsPage settingsPage = null;
	
	private static final Logger logger = Logger.getLogger(SettingsTest.class.getName());

	@BeforeClass
	@Parameters({ "browser", "siteURL", "email", "password" })
	public void initLuruSiteLogin(String browser, String siteURL, String strUserName, String strPassword)
			throws Exception {
		logger.info("Starting of initLuruSiteLogin methond");

		this.driver = this.getWebDriver(browser, WEB_DRIVER.LURU_SETTINGS_DRIVER_TEST);
		this.goToSite(driver, siteURL);
		this.luruLoginPage = new LuruLoginPage(driver);
		this.notesPage = new NotesPage(driver);
		this.settingsPage = new SettingsPage(driver);
		super.loginToTheLuruSite(strUserName, strPassword);

		logger.info("Ending of initLuruSiteLogin method");
	}

	@Test(priority = 1, description = "Verify that the user can create the new collection")
	@Description("Verify that the user can create the new collection")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Verify that the user can create the new collection")
	public void testCreateCollection() {
		logger.info("Startitng of testCreateCollection method");

		try {
			notesPage.selectDashboardOptions(testDataProp.getProperty("label.settings"));

			Assert.assertEquals(settingsPage.getSettingsLabel(), expectedAssertionsProp.getProperty("label.settings"));

			settingsPage.clickOnAddCollections();

			Assert.assertEquals(settingsPage.getCreateCollectionLabel(),
					expectedAssertionsProp.getProperty("label.create.collection"));

			settingsPage.setCollectionName(testDataProp.getProperty("txt.collection.name"));
			settingsPage.setCollectionDescription(testDataProp.getProperty("txt.collection.description"));
			settingsPage.clickOnFields();
			settingsPage.selectAvailableFileds(testDataProp.getProperty("label.Account"));
			settingsPage.selectAvailableFileds(testDataProp.getProperty("label.Name"));
			settingsPage.clickOnFieldsOkayButton();
			settingsPage.clickOnCreateButton();

			Assert.assertEquals(notesPage.getSuccessMessage(),
					expectedAssertionsProp.getProperty("message.collection.created"));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that the user can create the new collection: " + e.getMessage());
			logger.error("Error occured while testing that the user can create the new collection   ", e);
		}

		logger.info("Ending of testCreateCollection method");

	}

	@Test(priority = 2, description = "Verify that the user can share the created collection")
	@Description("Verify that the user can share the created collection")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify that the user can share the created collection")
	public void testShareCollection() {
		logger.info("Startitng of testShareCollection method");

		try {
			settingsPage.clickOnShareCollection();
			settingsPage.clickOnSaveChanges();

			Assert.assertEquals(notesPage.getSuccessMessage(),
					expectedAssertionsProp.getProperty("message.collection.shared"));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that the user can share and copy the created collection: "
					+ e.getMessage());
			logger.error("Error occured while testing that the user can share and copy the created collection   ", e);
		}
		logger.info("Ending of testShareCollection method");
	}

	@Test(priority = 3, description = "Verify that the user can Edit the Created collection")
	@Description("Verify that the user can Edit the Created collection")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify that the user can Edit the Created collection")
	public void testEditCollection() {
		logger.info("Startitng of testEditCollection method");

		try {
			settingsPage.clickOnEditCollectionIcon();
			settingsPage.setCollectionName(testDataProp.getProperty("txt.edit.collection.name"));
			settingsPage.setCollectionDescription(testDataProp.getProperty("txt.edit.collection.description"));
			settingsPage.clickOnCreateButton();

			Assert.assertEquals(notesPage.getSuccessMessage(),
					expectedAssertionsProp.getProperty("message.collection.edited"));

			Assert.assertTrue(
					settingsPage.isDisplayedCollectionLabel(testDataProp.getProperty("txt.edit.collection.name")));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that the user can create the Created collection: "
					+ e.getMessage());
			logger.error("Error occured while testing that the user can create the Created collection ", e);
		}

		logger.info("Ending of testEditCollection method");

	}

	@Test(priority = 4, description = "Verify that the user can Search the Created collection")
	@Description("Verify that the user can Search the Created collection")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify that the user can Search the Created collection")
	public void testSearchCollection() {
		logger.info("Startitng of testSearchCollection method");

		try {
			settingsPage.searchCollectionName(testDataProp.getProperty("txt.edit.collection.name"));

			Assert.assertTrue(
					settingsPage.isDisplayedCollectionLabel(testDataProp.getProperty("txt.edit.collection.name")));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that the user can Search the Created collection: "
					+ e.getMessage());
			logger.error("Error occured while testing that the user can Search Created collection   ", e);
		}

		logger.info("Ending of testSearchCollection method");

	}

	@Test(priority = 5, description = "Verify that the user can Delete the Created collection")
	@Description("Verify that the user can Delete the Created collection")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify that the user can Delete the Created collection")
	public void testDeleteCollection() {
		logger.info("Startitng of testDeleteCollection method");

		try {
			settingsPage.clickOnDeleteIcon();

			Assert.assertEquals(settingsPage.getDeleteCollectionLabel(),
					expectedAssertionsProp.getProperty("delete.collection.label"));

			settingsPage.clickOnConfirmDeleteButton();

			Assert.assertFalse(
					settingsPage.isDisplayedCollectionLabel(testDataProp.getProperty("txt.edit.collection.name")));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that the user can Delete the Created collection: "
					+ e.getMessage());
			logger.error("Error occured while testing that the user can Delete the Created collection   ", e);
		}

		logger.info("Ending of testDeleteCollection method");

	}

	@AfterClass
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			hardWait(3);
			luruLoginPage.clickOnSignOut();
			if (this.driver != null) {
				this.quitDriver(this.driver, WEB_DRIVER.LURU_SETTINGS_DRIVER_TEST);
				logger.debug("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		logger.info("Ending of quitDriver method");
	}
}

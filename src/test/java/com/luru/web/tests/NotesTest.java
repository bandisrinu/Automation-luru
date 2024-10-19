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
import com.luru.web.pages.NotesPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Luru Note")
@Feature("Luru Note Test")
public class NotesTest extends BaseLuruAutomationTest {
	private WebDriver driver = null;
	private NotesPage notesPage = null;

	private static final Logger logger = Logger.getLogger(NotesTest.class.getName());

	@BeforeClass
	@Parameters({ "browser", "siteURL", "email", "password" })
	public void initLuruSiteLogin(String browser, String siteURL, String strUserName, String strPassword)
			throws Exception {
		logger.info("Starting of initLuruSiteLogin methond");

		this.driver = this.getWebDriver(browser, WEB_DRIVER.LURU_NOTE_DRIVER_TEST);
		this.goToSite(driver, siteURL);
		this.luruLoginPage = new LuruLoginPage(driver);
		this.notesPage = new NotesPage(driver);
		super.loginToTheLuruSite(strUserName, strPassword);

		logger.info("Ending of initLuruSiteLogin method");
	}

	@Test(priority = 1, description = "Verify that the user can create the new luru note")
	@Description("Test Description:Verify that the user can create the new luru note")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Verify that the user can create the new luru note")
	public void testCreateLuruNote() {
		logger.info("Startitng of testCreateLuruNote method");

		try {
			notesPage.clickOnCreateRecordButton();
			notesPage.selectCreateRecordsOptions(testDataProp.getProperty("label.createnote"));
			notesPage.setNoteTitle(testDataProp.getProperty("notepage.title"));
			String beforeTitle = notesPage.getSalesForceHeadingText();
			notesPage.setNoteDescription(testDataProp.getProperty("notepage.description"));
			String afterTitle = notesPage.getCreatedNoteText();

			Assert.assertEquals(beforeTitle, afterTitle);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that user can create the new luru note: " + e.getMessage());
			logger.error("Error occured while testing that user can create the new luru note   ", e);
		}

		logger.info("Ending of testCreateLuruNote method");
	}

	@Test(priority = 2, description = "Verify that the user can search from the existed luru note")
	@Description("Test Description:Verify that the user can search from the existed luru note")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify that the user can search from the existed luru note")
	public void testSearchLuruNote() {
		logger.info("Starting of testSearchLuruNote method");

		try {
			notesPage.clickOnNewLuruNoteIcon();
			notesPage.setNoteTitle(testDataProp.getProperty("notepage.title.one"));
			notesPage.clickOnNewLuruNoteIcon();
			notesPage.setNoteTitle(testDataProp.getProperty("notepage.title.two"));
			notesPage.searchNoteInSearchBar(testDataProp.getProperty("notepage.title.one"));

			Assert.assertTrue(notesPage.isDisplayedSearchedNote());

			notesPage.clickOnDeleteIcon();
		} catch (Exception e) {
			Assert.fail("Exception occured while testing that the user can search from the existed luru note: "
					+ e.getMessage());
			logger.error("Error occured while testing that the user can search from the existed luru note   ", e);
		}
		logger.info("Ending of testSearchLuruNote method");
	}

	@Test(priority = 3, description = "Verify that the user can Link CRM to luru note")
	@Description("Test Description:Verify that the user can Link CRM to luru note")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify that the user can Link CRM to luru note")
	public void testLinkCRMToLuruNote() {
		logger.info("Startitng of testLinkCRMToLuruNote method");

		try {
			notesPage.selectDashboardOptions(testDataProp.getProperty("label.pipeline"));
			String oppurtunityText = notesPage.getOpportunityText();
			notesPage.selectDashboardOptions(testDataProp.getProperty("notes.text"));
			luruLoginPage.clickOnCloseCalendarButton();
			notesPage.clickOnNewLuruNoteIcon();
			notesPage.clickOnLinkToCRMButton();
			notesPage.setCRMRecordLink(oppurtunityText);
			notesPage.clickOnSearchedRecord();

			Assert.assertEquals(notesPage.getCRMTemplateRecordHeader(),
					expectedAssertionsProp.getProperty("crm.template.record.header"));

			notesPage.clickNavigateButton();
		} catch (Exception e) {
			Assert.fail("Exception occured while testing the user can Link CRM to luru note : " + e.getMessage());
			logger.error("Error occured while testing the user can Link CRM  to luru note  ", e);
		}

		logger.info("Ending of testLinkCRMToLuruNote method");
	}

	@Test(priority = 4, description = "Verify that the user can able to remove the Linked CRM")
	@Description("Test Description:Verify that the user can able to remove the Linked CRM")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify that the user can able to remove the Linked CRM")
	public void testRemoveLinkedCRM() {
		logger.info("Startitng of testRemoveLinkedCRM method");

		try {
			notesPage.clickLinkedToCRMButton();

			Assert.assertEquals(notesPage.getRemoveRecordHeader(),
					expectedAssertionsProp.getProperty("remove.recorder.header"));

			notesPage.clickRemoveRecordOkayButton();
		} catch (Exception e) {
			Assert.fail("Exception occured while testing  user can able to remove the CRM: " + e.getMessage());
			logger.error("Error occured while testing user can able to remove the CRM   ", e);
		}

		logger.info("Ending of testRemoveLinkedCRM method");
	}

	@Test(priority = 5, description = "Verify that the user can delete the created luru note")
	@Description("Test Description:Verify that the user can delete the created luru note")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify that the user can delete the created luru note")
	public void testDeleteNote() {
		logger.info("Startitng of testDeleteNote method");

		try {
			notesPage.clickOnThreeDottedMenu();
			notesPage.clickOnDeleteNote();

			Assert.assertEquals(notesPage.getDeleteLabel(), expectedAssertionsProp.getProperty("label.delete"));

			notesPage.clickOnDeleteNoteOkayButton();

			notesPage.hardWait(2);
			Assert.assertEquals(notesPage.getSuccessMessage(),
					expectedAssertionsProp.getProperty("message.note.deleted"));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing that the user can delete the created luru note: "
					+ e.getMessage());
			logger.error("Error occured while testing that the user can delete the created luru note   ", e);
		}

		logger.info("Ending of testDeleteNote method");
	}

	@Test(priority = 6, description = "Failed Test case")
	@Description("Test Description:Failed Test case")
	@Severity(SeverityLevel.NORMAL)
	@Story("Failed Test case")
	public void testSearchNoteNotDisplayed() {
		logger.info("Starting of testSearchNoteNotDisplayed method");

		try {
			notesPage.clickOnNewLuruNoteIcon();
			notesPage.setNoteTitle(testDataProp.getProperty("notepage.title.one"));
			notesPage.clickOnNewLuruNoteIcon();
			notesPage.setNoteTitle(testDataProp.getProperty("notepage.title.two"));
			notesPage.searchNoteInSearchBar(testDataProp.getProperty("notepage.title.one"));

			Assert.assertFalse(notesPage.isDisplayedSearchedNote(), "ASSERTION FAILED:: Search note not displayed ");

			notesPage.clickOnDeleteIcon();
		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing that the user can search note not displyed after search from the existed luru note: "
							+ e.getMessage());
			logger.error(
					"Error occured while testing that the user can search note not displyed after search from the existed luru note   ",
					e);
		}
		logger.info("Ending of testSearchNoteNotDisplayed method");
	}

	@AfterClass
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			hardWait(3);
			luruLoginPage.clickOnSignOut();
			if (this.driver != null) {
				this.quitDriver(this.driver, WEB_DRIVER.LURU_NOTE_DRIVER_TEST);
				logger.debug("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		logger.info("Ending of quitDriver method");
	}
}
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
import com.luru.web.pages.PipeLinePage;
import com.luru.web.tests.BaseLuruAutomationTest.WEB_DRIVER;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class PipeLineTest extends BaseLuruAutomationTest {

	private WebDriver driver = null;
	private PipeLinePage pipelinePage;
	private static final Logger logger = Logger.getLogger(PipeLineTest.class.getName());

	@BeforeClass
	@Parameters({ "browser", "siteURL", "email", "password" })
	public void initLuruSiteLogin(String browser, String siteURL, String strUserName, String strPassword)
			throws Exception {
		logger.info("Starting of initLuruSiteLogin methond");

		this.driver = this.getWebDriver(browser, WEB_DRIVER.PIPE_LINE_DRIVER_TEST);
		this.goToSite(driver, siteURL);
		this.luruLoginPage = new LuruLoginPage(driver);
		this.pipelinePage = new PipeLinePage(driver);
		super.loginToTheLuruSite(strUserName, strPassword);

		logger.info("Ending of initLuruSiteLogin method");
	}

	@Test(priority = 1, description = "Verify User Can Create Lead Pipeline ")
	@Description("Test Description:Verify User Can Create Lead Pipeline")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Verify User Can Create Lead Pipeline")
	public void testCreateLeadPipeline() {
		logger.info("Startitng of testCreateLeadPipeline method");

		try {
			pipelinePage.clickOnPipelineTab();
			pipelinePage.clickOnLead();
			pipelinePage.clickOnCreateAndSearchFiled();
			pipelinePage.clickOnCreateLead();

			// Assertion for Create title
			String lblCreateLead = pipelinePage.getTitleCreateLead();
			Assert.assertEquals(lblCreateLead, expectedAssertionsProp.getProperty("text.create.lead"));

			pipelinePage.setCreateLeadInputFields(testDataProp.getProperty("pipeline.last.names"));
			pipelinePage.clickOnDropdownStatus();
			pipelinePage.clickOnNotContactedButton();
			pipelinePage.clickOnDropdownLeadSource();
			pipelinePage.clickOnWebButton();

			pipelinePage.clickOnCreateButton();

			// Assertion for Created lead successfully message title
			Assert.assertEquals(pipelinePage.getMessageText(),
					expectedAssertionsProp.getProperty("text.created.lead.successfully"));

		} catch (Exception e) {
			Assert.fail("Exception occured while creating lead pipeline  : " + e.getMessage());
			logger.error("Error occured while creating lead pipeline  ", e);
		}

		logger.info("Ending of testCreateLeadPipeline method");
	}

	@Test(priority = 2, description = "Verify User Can Update the created Pipeline From Leads Record")
	@Description("Test Description:Verify User Can Update the created Pipeline From Leads Record")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify User Can Update the created Pipeline From Leads Record")
	public void testUpdateCreatedLeadPipelineFromLeadsRecord() {
		logger.info("Startitng of testUpdateCreatedLeadPipelineFromLeadsRecord method");

		try {
			String companyName = testDataProp.getProperty("edit.company.name");
			driver.navigate().refresh();
			pipelinePage.editCompanyName(companyName);
			Assert.assertEquals(pipelinePage.getEditedCompanyName(), companyName);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing  User Can Update the created Pipeline From Leads Record : "
					+ e.getMessage());
			logger.error("Error occured while testing User Can Update the created Pipeline From Leads Record  ", e);
		}

		logger.info("Ending of testUpdateCreatedLeadPipelineFromLeadsRecord method");
	}

	@Test(priority = 3, description = "Verify User Can Update Lead Pipeline in View Record")
	@Description("Test Description:Verify User Can Update Lead Pipeline in View Record")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify User Can Update Lead Pipeline in View Record")
	public void testUpdateLeadPipelineInViewRecord() {
		logger.info("Startitng of testUpdateLeadPipelineInViewRecord method");

		try {

			this.pipelinePage.updateLead();
			this.pipelinePage.editLastNameInUpdateScreen(testDataProp.getProperty("edit.pipeline.last.names"));
			this.pipelinePage.clickOnUpdateButton();

			// Assertion for Created lead successfully message title
			Assert.assertTrue(pipelinePage.getUpdatedLeadMessage(testDataProp.getProperty("company.name")));

		} catch (Exception e) {
			Assert.fail(
					"Exception occured while testing User Can Update Lead Pipeline in View Record: " + e.getMessage());
			logger.error("Error occured while testing User Can Update Lead Pipeline in View Record  ", e);
		}

		logger.info("Ending of testUpdateLeadPipelineInViewRecord method");
	}

	@AfterClass
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			hardWait(3);
			luruLoginPage.clickOnSignOut();
			if (this.driver != null) {
				this.quitDriver(this.driver, WEB_DRIVER.PIPE_LINE_DRIVER_TEST);
				logger.debug("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		logger.info("Ending of quitDriver method");
	}

}

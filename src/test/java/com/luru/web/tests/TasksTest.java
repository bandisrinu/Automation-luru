package com.luru.web.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.luru.web.pages.LuruLoginPage;
import com.luru.web.pages.PipeLinePage;
import com.luru.web.pages.TaskPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class TasksTest extends BaseLuruAutomationTest {

	private WebDriver driver = null;
	private TaskPage taskPage;
	private static final Logger logger = Logger.getLogger(TasksTest.class.getName());

	@BeforeClass
	@Parameters({ "browser", "siteURL", "email", "password" })
	public void initLuruSiteLogin(String browser, String siteURL, String strUserName, String strPassword)
			throws Exception {
		logger.info("Starting of initLuruSiteLogin methond");

		this.driver = this.getWebDriver(browser, WEB_DRIVER.TASKS_DRIVER_TEST);
		this.goToSite(driver, siteURL);
		this.luruLoginPage = new LuruLoginPage(driver);
		this.taskPage = new TaskPage(driver);
		super.loginToTheLuruSite(strUserName, strPassword);

		logger.info("Ending of initLuruSiteLogin method");
	}

	@Test(priority = 1, description = "Verify User Can Create a new Task")
	@Description("Test Description:Verify User Can Create a new Task")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Verify User Can Create a new Task")
	public void testCreateTask() {
		logger.info("Startitng of testCreateTask method");

		try {
			taskPage.clickOnTaskTab();
			taskPage.clickOnCreateTaskButton();

			Assert.assertEquals(taskPage.getCreateTaskTiltle(), expectedAssertionsProp.getProperty("text.create.task"));

			taskPage.setTitle(testDataProp.getProperty("text.title"));
			taskPage.clickOnLinkToCrmIcon();
			taskPage.clickOnLead();
			taskPage.searchCrmRecord(testDataProp.getProperty("search.lead.name"));
			taskPage.clickOnSelectedLeadName();
			taskPage.setDescription(testDataProp.getProperty("text.task.description"));
			taskPage.clickOnCreateButton();

			Assert.assertEquals(taskPage.getTaskCreatedMessage(),
					expectedAssertionsProp.getProperty("text.task.created.successfully"));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing User Can Create a new Task : " + e.getMessage());
			logger.error("Error occured while testing User Can Create a new Task ", e);
		}

		logger.info("Ending of testCreateTask method");
	}

	@Test(priority = 2, description = "Verify user can mark the Task as Completed")
	@Description("Test Description: Verify user can mark the Task as Completed ")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify user can mark the Task as Completed")
	public void testMarkTaskAsCompleted() {
		logger.info("Startitng of testMarkTaskAsCompleted method");

		try {
			taskPage.clickOnCompleteTaskIcon();

			Assert.assertEquals(taskPage.getTaskCreatedMessage(),
					expectedAssertionsProp.getProperty("text.task.marked.completed"));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing user can mark the Task as Completed : " + e.getMessage());
			logger.error("Error occured while testing user can mark the Task as Completed", e);
		}

		logger.info("Ending of testMarkTaskAsCompleted method");
	}

	@Test(priority = 3, description = "Verify User Can Update the created Task ")
	@Description("Test Description: Verify User Can Update the created Task ")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify User Can Update the created Task")
	public void testUpdateTask() {
		logger.info("Startitng of testUpdateTask method");

		try {
			taskPage.clickOnCreatedTask();

			Assert.assertEquals(taskPage.getUpdateTaskTiltle(), expectedAssertionsProp.getProperty("text.update.task"));

			taskPage.clickOnTaskAssignButton();
			taskPage.clickOnAssignedLead();
			taskPage.clickOnUpdateButton();

			Assert.assertEquals(taskPage.getTaskCreatedMessage(),
					expectedAssertionsProp.getProperty("text.task.updated.successfully"));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing User Can Update the created Task : " + e.getMessage());
			logger.error("Error occured while testing User Can Update the created Task ", e);
		}

		logger.info("Ending of testUpdateTask method");
	}

	@Test(priority = 4, description = "Verify User Can Delete the created Task ")
	@Description("Test Description: Verify User Can Delete the created Task")
	@Severity(SeverityLevel.NORMAL)
	@Story("Verify User Can Delete the created Task")
	public void testDeleteTask() {
		logger.info("Startitng of testDeleteTask method");

		try {
			taskPage.clickOnDeleteIcon();

			// Assertion for Delete Task
			Assert.assertEquals(taskPage.getTaskCreatedMessage(),
					expectedAssertionsProp.getProperty("text.task.deleted"));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing User Can Delete the created Task : " + e.getMessage());
			logger.error("Error occured while testing User Can Delete the created Task ", e);
		}

		logger.info("Ending of testDeleteTask method");
	}

	@AfterClass
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			hardWait(3);
			luruLoginPage.clickOnSignOut();
			if (this.driver != null) {
				this.quitDriver(this.driver, WEB_DRIVER.TASKS_DRIVER_TEST);
				logger.debug("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		logger.info("Ending of quitDriver method");
	}

}

package com.luru.web.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TaskPage extends BaseLuruAutomationPage {

	@FindBy(xpath = "//img[@alt='Tasks']")
	private WebElement mnuTasks;

	@FindBy(xpath = "//section[@data-role='my-open-tasks']//button[contains(text(),'Create task...')]")
	private WebElement btnCreateTask;

	@FindBy(xpath = "//span[text()='Create task']")
	private WebElement lblCreateTask;

	@FindBy(xpath = "//label[text()='Title']//following-sibling::input")
	private WebElement txtTitle;

	@FindBy(xpath = "//img[@alt='Link to CRM record']")
	private WebElement icnLinkCrm;

	@FindBy(xpath = "//li[@data-filter='Lead']")
	private WebElement btnLead;

	@FindBy(xpath = "//input[@placeholder='Search for CRM record']")
	private WebElement txtSearchCrmRecord;

	@FindBy(xpath = "//li[@data-sor-record-name='Qwerty Qwerty']")
	private WebElement txtLeadName;

	@FindBy(xpath = "//div[contains(@class,'TaskView_taskViewProperty')]//textarea")
	private WebElement txtDescription;

	@FindBy(xpath = "//button[@title='Create task']")
	private WebElement btnCreate;

	@FindBy(xpath = "//span[@id='luruNotification']")
	private WebElement msgTaskCreated;

	@FindBy(xpath = "//img[@alt='Delete task']")
	private WebElement icnDeleteTask;

	@FindBy(xpath = "//img[@alt='Complete task']")
	private WebElement icnCompleteTask;

	@FindBy(xpath = "//button[contains(@class,'Tasks_infoButton__mn9wH Tasks_dueDate__VKmRK Tasks_noduedate')]")
	private WebElement btnCalender;

	@FindBy(xpath = "//input[@type='date']")
	private WebElement txtDate;

	@FindBy(xpath = "//div[contains(@class,'Tasks_taskTitleBox')]")
	private WebElement lblTask;

	@FindBy(xpath = "//span[text()='Update task']")
	private WebElement lblUpdateTask;

	@FindBy(xpath = "//img[@alt='Linked to User']")
	private WebElement icnTaskAssignedTo;

	@FindBy(xpath = "//li/div/div[contains(@class,'ToolTip_parent__coei1 ToolTip_right__vFdfZ ToolTip_bottom')]/../following-sibling::div[contains(text(),'Chatter Expert')]")
	private WebElement btnSelectLead;

	@FindBy(xpath = "//button[@title='Update task']")
	private WebElement btnUpdate;

	private static final Logger logger = Logger.getLogger(TaskPage.class.getName());

	public TaskPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of LuruLoginPage method");
		PageFactory.initElements(driver, this);
		logger.info("Ending of LuruLoginPage method");
	}

	public void clickOnTaskTab() {
		logger.info("Starting of clickOnTaskTab method");

		try {
			this.clickOnWebElement(mnuTasks);
		} catch (Exception e) {
			mnuTasks.click();
		}

		logger.info("Ending of clickOnTaskTab method");
	}

	public void clickOnCreateTaskButton() {
		logger.info("Starting of clickOnCreateTaskButton method");

		try {
			this.clickOnWebElement(btnCreateTask);
		} catch (Exception e) {
			btnCreateTask.click();
		}

		logger.info("Ending of clickOnCreateTaskButton method");
	}

	public void setTitle(String strUserName) {
		logger.info("Starting of setTitle method");

		this.txtTitle.click();
		this.txtTitle.sendKeys(strUserName);

		logger.info("Ending of setTitle method");
	}

	public void clickOnLinkToCrmIcon() {
		logger.info("Starting of clickOnLinkToCrmIcon method");

		try {
			this.clickOnWebElement(icnLinkCrm);
		} catch (Exception e) {
			icnLinkCrm.click();
		}

		logger.info("Ending of clickOnLinkToCrmIcon method");
	}

	public void clickOnLead() {
		logger.info("Starting of clickOnLLead method");

		try {
			this.clickOnWebElement(btnLead);
		} catch (Exception e) {
			btnLead.click();
		}

		logger.info("Ending of clickOnLLead method");
	}

	public void setDescription(String strdescription) {
		logger.info("Starting of setDescription method");

		this.txtDescription.click();
		this.txtDescription.sendKeys(strdescription);

		logger.info("Ending of setDescription method");
	}

	public void clickOnCreateButton() {
		logger.info("Starting of clickOnCreateButton method");

		try {
			this.clickOnWebElement(btnCreate);
		} catch (Exception e) {
			btnCreate.click();
		}

		logger.info("Ending of clickOnCreateButton method");
	}

	public void searchCrmRecord(String strlead) {
		logger.info("Starting of searchCrmRecord method");

		try {
			this.clickOnWebElement(txtSearchCrmRecord);
			txtSearchCrmRecord.sendKeys(strlead);
			txtSearchCrmRecord.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			txtSearchCrmRecord.click();
			txtSearchCrmRecord.sendKeys(strlead);
			txtSearchCrmRecord.sendKeys(Keys.ENTER);
		}

		logger.info("Ending of searchCrmRecord method");
	}

	public void clickOnSelectedLeadName() {
		logger.info("Starting of clickOnSelectedLeadName method");

		try {
			hardWait(2);
			this.clickOnWebElement(txtLeadName);
		} catch (Exception e) {
			hardWait(2);
			txtLeadName.click();
		}

		logger.info("Ending of clickOnSelectedLeadName method");
	}

	public String getCreateTaskTiltle() {

		logger.info("Starting of getCreateTaskTiltle method");
		logger.info("Ending of getCreateTaskTiltle method");

		return lblCreateTask.getText();

	}

	public String getTaskCreatedMessage() {

		logger.info("Starting of getTaskCreatedMessage method");

		explicitWait(msgTaskCreated);

		logger.info("Ending of getTaskCreatedMessage method");

		return msgTaskCreated.getText();

	}

	public void clickOnDeleteIcon() {
		logger.info("Starting of clickOnDeleteIcon method");

		try {
			hardWait(5);
			this.mouseHover(icnDeleteTask);
			clickOnWebElement(icnDeleteTask);
		} catch (Exception e) {
			this.mouseHover(icnDeleteTask);
			icnDeleteTask.click();
			hardWait(4);
		}

		logger.info("Ending of clickOnDeleteIcon method");
	}

	public void clickOnCompleteTaskIcon() {
		logger.info("Starting of clickOnCompleteTaskIcon method");

		try {
			hardWait(5);
			this.clickOnWebElement(icnCompleteTask);
		} catch (Exception e) {
			icnCompleteTask.click();
			hardWait(5);
		}

		logger.info("Ending of clickOnCompleteTaskIcon method");
	}

	public void clickOnCreatedTask() {
		logger.info("Starting of clickOnCreatedTask method");

		try {
			hardWait(3);
			this.clickOnWebElement(lblTask);
		} catch (Exception e) {
			lblTask.click();
			hardWait(3);
		}

		logger.info("Ending of clickOnCreatedTask method");
	}

	public String getUpdateTaskTiltle() {

		logger.info("Starting of getUpdateTaskTiltle method");
		logger.info("Ending of getUpdateTaskTiltle method");

		return lblUpdateTask.getText();

	}

	public void clickOnTaskAssignButton() {
		logger.info("Starting of clickOnTaskAssignButton method");

		try {
			hardWait(5);
			this.clickOnWebElement(icnTaskAssignedTo);
		} catch (Exception e) {
			icnTaskAssignedTo.click();
			hardWait(5);
		}

		logger.info("Ending of clickOnTaskAssignButton method");
	}

	public void clickOnAssignedLead() {
		logger.info("Starting of clickOnAssignedLead method");

		try {
			hardWait(3);
			this.clickOnWebElement(btnSelectLead);
		} catch (Exception e) {
			btnSelectLead.click();
			hardWait(3);
		}

		logger.info("Ending of clickOnAssignedLead method");
	}

	public void clickOnCalenderIcon() {
		logger.info("Starting of clickOnCalenderIcon method");

		try {
			hardWait(3);
			this.clickOnWebElement(btnCalender);
		} catch (Exception e) {
			btnCalender.click();
			hardWait(3);
		}

		logger.info("Ending of clickOnCalenderIcon method");
	}

	public void setDate(String strDate) {
		logger.info("Starting of setDate method");

		this.txtDate.click();
		this.txtDate.sendKeys(strDate);

		logger.info("Ending of setDate method");
	}

	public void clickOnUpdateButton() {
		logger.info("Starting of clickOnUpdateButton method");

		try {
			hardWait(4);
			this.clickOnWebElement(btnUpdate);
		} catch (Exception e) {
			btnUpdate.click();
			hardWait(4);
		}

		logger.info("Ending of clickOnUpdateButton method");
	}
}
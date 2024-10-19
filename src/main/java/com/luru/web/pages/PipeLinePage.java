package com.luru.web.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;
import io.qameta.allure.internal.shadowed.jackson.databind.PropertyNamingStrategies.KebabCaseStrategy;

public class PipeLinePage extends BaseLuruAutomationPage {

	@FindBy(xpath = "//img[@alt='Pipeline']")
	private WebElement tabPipeline;

	@FindBy(xpath = "//img[@alt='Drop down']")
	private WebElement drpLead;

	@FindBy(xpath = "//li[@data-item-chooser-key='Leads']")
	private WebElement drpSelectLead;

	@FindBy(xpath = "//span[text()='Create or search record (Ctrl+J)'] ")
	private WebElement txtCreate;

	@FindBy(xpath = " //img[@alt='Lead']")
	private WebElement btnCreateLead;

	@FindBy(xpath = "//span[text()='Create Lead']")
	private WebElement lblCreateLead;

	@FindBy(xpath = "//label[text()='Status']//following-sibling::div")
	private WebElement drpStatus;

	@FindBy(xpath = "//span[text()='Open - Not Contacted'] ")
	private WebElement btnSelectedStatus;

	@FindBy(xpath = "//label[text()='Lead Source']//following-sibling::div")
	private WebElement drpLeadSource;

	@FindBy(xpath = "//span[text()='Web']")
	private WebElement btnSelectedWeb;

	@FindBy(xpath = "//button[text()='Create']")
	private WebElement btnCreate;

	@FindBy(xpath = "//span[@id='luruNotification']")
	private WebElement msgCreatedLead;

	@FindBy(xpath = "(//input[@data-field-name='Company'])[last()]")
	private WebElement lstLeads;

	@FindBy(xpath = "//div[contains(@class,'styles_fieldSet')]//div//div//label//following-sibling::div//input")
	private List<WebElement> lblCreateLeadFields;

	@FindBy(xpath = "//div[contains(@class,'View_omniboxLayer')]/img/preceding-sibling::img")
	private List<WebElement> lblFirstLead;

	@FindBy(xpath = "//div[contains(@class,'View_omniboxLayer')]/following-sibling::span")
	private List<WebElement> lstLeadNames;

	@FindBy(xpath = "//div[contains(@class,'View_omniboxLayer')]/../following-sibling::td[1]/input")
	private List<WebElement> lstCompanyNames;

	@FindBy(xpath = "//button[@data-luru-role='update-crm-record-button']")
	private WebElement btnUpdate;

	@FindBy(xpath = "//div[contains(@class,'styles_edit')]/input")
	private WebElement txtUpdateLastName;

	public PipeLinePage(WebDriver driver) {
		super(driver);
		logger.info("Starting of PilpeLinePage method");

		PageFactory.initElements(driver, this);

		logger.info("Ending of PilpeLinePage method");
	}

	private static final Logger logger = Logger.getLogger(PipeLinePage.class.getName());

	public void clickOnPipelineTab() {
		logger.info("Starting of clickOnPipelineTab method");

		try {
			this.clickOnWebElement(tabPipeline);
			hardWait(10);
		} catch (Exception e) {
			tabPipeline.click();
			hardWait(10);
		}

		logger.info("Ending of clickOnPipelineTab method");
	}

	public void clickOnDropdown() {
		logger.info("Starting of clickOnDropdown method");
		try {

			this.clickOnWebElement(drpLead);
			hardWait(10);
		} catch (Exception e) {
			drpLead.click();
			hardWait(10);

		}
		logger.info("Ending of clickOnDropdown method");
	}

	public void clickOnLead() {
		logger.info("Starting of clickOnLead method");
		try {
			hardWait(5);
			this.clickOnWebElement(drpSelectLead);

		} catch (Exception e) {
			drpSelectLead.click();
			hardWait(10);

		}
		logger.info("Ending of clickOnLead method");
	}

	public void clickOnCreateAndSearchFiled() {
		logger.info("Starting of clickOnCreateAndSearchFiled method");

		try {
			this.clickOnWebElement(txtCreate);
		} catch (Exception e) {
			txtCreate.click();
		}

		logger.info("Ending of clickOnCreateAndSearchFiled method");
	}

	public void clickOnCreateLead() {
		logger.info("Starting of clickOnCreateLead method");
		try {
			this.clickOnWebElement(btnCreateLead);
		} catch (Exception e) {
			btnCreateLead.click();
		}
		logger.info("Ending of clickOnCreateLead method");
	}

	public String getTitleCreateLead() {
		logger.info("Starting of getTitleCreateLead method");
		logger.info("Ending of getTitleCreateLead method");

		return lblCreateLead.getText();
	}

	public void clickOnDropdownStatus() {
		logger.info("Starting of clickOnDropdownStatus method");

		this.drpStatus.click();

		logger.info("Ending of clickOnDropdownStatus method");
	}

	public void clickOnNotContactedButton() {
		logger.info("Starting of clickOnNotContactedButton method");

		this.btnSelectedStatus.click();

		logger.info("Starting of clickOnNotContactedButton method");
	}

	public void clickOnDropdownLeadSource() {
		logger.info("Starting of clickOnDropdownLeadSource method");

		this.drpLeadSource.click();

		logger.info("Ending of clickOnDropdownLeadSource method");
	}

	public void clickOnWebButton() {
		logger.info("Starting of clickOnWebButton method");

		this.btnSelectedWeb.click();

		logger.info("Ending of clickOnWebButton method");
	}

	public void clickOnCreateButton() {
		logger.info("Starting of clickOnCreateButton method");

		this.btnCreate.click();

		logger.info("Ending of clickOnCreateButton method");
	}

	public String getMessageText() {
		logger.info("Starting of getMessageText method");

		explicitWait(msgCreatedLead);

		logger.info("Ending of getMessageText method");
		return msgCreatedLead.getText();

	}

	public boolean getUpdatedLeadMessage(String s) {
		logger.info("Starting of getUpdatedLeadMessage method");
		logger.info("Ending of getUpdatedLeadMessage method");

		try {
			explicitWait(msgCreatedLead);
			return msgCreatedLead.getText().contains(s);
		} catch (Exception e) {
			return false;
		}
	}

	public String getCreatedLeadName(int i) {
		logger.info("Starting of getCreatedLeadName method");
		logger.info("Ending of getCreatedLeadName method");

		return lstLeadNames.get(i).getText();
	}

	public String getCreatedCompanyText() {
		logger.info("Starting of getCreatedCompanyText method");

		hardWait(4);

		logger.info("Ending of getCreatedCompanyText method");
		return lstLeads.getText();
	}

	public String getEditedCompanyName() {
		logger.info("Starting of getEditedCompanyName method");
		logger.info("Ending of getEditedCompanyName method");

		return lstCompanyNames.get(0).getAttribute("value");

	}

	public void setCreateLeadInputFields(String s) {
		logger.info("Starting of setCreateLeadInputFields method");
		hardWait(3);
		String[] ss = s.split(",");
		for (int i = 0; i < lblCreateLeadFields.size(); i++) {
			if (i == 2) {
				lblCreateLeadFields.get(i).sendKeys(ss[i] + this.getUniqueCharactorString(7));

			} else if (i == 3) {
				lblCreateLeadFields.get(i).sendKeys(ss[i] + this.getUniqueMail(ss[i]));

			} else if (i == 4) {
				lblCreateLeadFields.get(i).sendKeys(ss[i] + this.getUniqueMobileNumber(9));

			} else {
				lblCreateLeadFields.get(i).sendKeys(ss[i]);

			}
		}
		logger.info("Ending of setCreateLeadInputFields method");

	}

	public void editCompanyName(String updateName) {
		logger.info("Starting of editCompanyName method");

		hardWait(5);
		System.out.println(lstLeadNames.get(0).getText());
		lstCompanyNames.get(0).click();
		lstCompanyNames.get(0).sendKeys(updateName);

		logger.info("Ending of editCompanyName method");
	}

	public void updateLead() {
		logger.info("Starting of updatelead method");

		hardWait(3);
		this.mouseHover(lblFirstLead.get(0));
		hardWait(3);
		clickOnWebElement(lblFirstLead.get(0));

		logger.info("Ending of updatelead method");
	}

	public void clickOnUpdateButton() {
		logger.info("Starting of clickUpdateButton method");

		try {
			hardWait(2);
			this.clickOnWebElement(btnUpdate);
		} catch (Exception e) {
			hardWait(2);
			btnUpdate.click();
		}

		logger.info("Ending of clickUpdateButton method");
	}

	public void editLastNameInUpdateScreen(String lastName) {
		logger.info("Starting of editLastNameInUpdateScreen method");

		explicitWait(txtUpdateLastName);
		txtUpdateLastName.sendKeys(lastName);

		logger.info("Ending of editLastNameInUpdateScreen method");
	}

	public void setEditLeadInputFields(String inputFields) {
		logger.info("Starting of setEditLeadInputFields method");

		hardWait(3);
		String[] SplittedInputFields = inputFields.split(",");
		for (int i = 0; i < lblCreateLeadFields.size(); i++) {

			lblCreateLeadFields.get(i).sendKeys(SplittedInputFields[i]);
			break;
		}
		
		logger.info("Ending of setEditLeadInputFields method");
	}
}

package com.luru.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class NotesPage extends BaseLuruAutomationPage {

	@FindBy(xpath = "//aside[contains(@class,'Toolbar_toolbar')]//span[contains(text(),'${text}')]")
	private WebElement lblLuruDashboard;

	@FindBy(xpath = " //span[text()='Create or search record (Ctrl+J)']")
	private WebElement btnCreateRecord;

	@FindBy(xpath = "//div[contains(@class,'styles_records')]//li//span[contains(text(),'${text}')]")
	private WebElement lstCreateRecords;

	@FindBy(xpath = "//input[contains(@id,'title')]")
	private WebElement txtNotesTitle;

	@FindBy(xpath = "//td[contains(@class,'NotesEditor_note')]")
	private WebElement txtNotesDescription;

	@FindBy(xpath = "//div[contains(@class,'NewExplorer_primaryLine')]")
	private WebElement txtCreatedNotesTitle;

	@FindBy(xpath = "//div[contains(@class,'RecordLinkControl_parent')]//button[contains(@class,'OvalButton_main')]/img")
	private WebElement btnLinkToCRM;

	@FindBy(xpath = "//input[@value='Burlington Textiles Weaving Plant Generator']")
	private WebElement lblSelectOpportunityName;

	@FindBy(xpath = "//input[@placeholder='Search for CRM record to link']")
	private WebElement txtSearchForCRMLink;

	@FindBy(xpath = "//ul[contains(@class,'RecordLinkControl_loaded')]//div")
	private WebElement txtSearchedRecord;

	@FindBy(xpath = "//span[@id='luruNotification'] ")
	private WebElement msgToast;

	// expected= Linking Burlington Textiles Weaving Plant Generator to note

	@FindBy(xpath = "//div[contains(@class,'ListItemChooserDialog_title')]")
	private WebElement lblCRMTemplateRecordHeader;

	@FindBy(xpath = "//input[@placeholder='Choose template']")
	private WebElement txtChooseTemplate;

	@FindBy(xpath = "//img[@alt='Linked to CRM']")
	private WebElement btnLinkedToCRM;

	@FindBy(xpath = "//div[contains(text(),'Remove connected record')]")
	private WebElement lblRemoveRecordHeader;

	@FindBy(xpath = "//div[contains(@class,'ModalScreen_buttons')]//following-sibling::button")
	private WebElement btnRemoveRecordOkay;

	// expected Deleting note connection

	@FindBy(xpath = "(//div[text()='↑' and contains(., ' or ') and contains(., '↓') ])[last()]")
	private WebElement btnNavigate;

	@FindBy(xpath = "//button[contains(@class,'NotesEditorInfoBlock_noSelect')]")
	private WebElement btnThreeDottedMenu;

	@FindBy(xpath = "//div[contains(@class,'PopupMenu_menu__XF-Gp PopupMenu_rightAligned__XPgSP PopupMenu_menuVisible')]//preceding-sibling::li")
	private WebElement btnShareNotes;

	@FindBy(xpath = "//div[contains(text(),'Share Note')]")
	private WebElement lblShareNotes;

	@FindBy(xpath = "//input[@placeholder='Add users to share note - start typing']")
	private WebElement txtShareEmail;

	@FindBy(xpath = "//button[text()='Copy note to clipboard']")
	private WebElement btnCopyNote;

	@FindBy(xpath = "//div[contains(@class,'styles_shareLink')]//button")
	private WebElement lblCopied;

	@FindBy(xpath = "//div[contains(@class,'PopupMenu_menu__XF-Gp PopupMenu_rightAligned__XPgSP PopupMenu_menuVisible')]//following-sibling::li")
	private WebElement btnDeleteNotes;

	@FindBy(xpath = "//div[contains(@class,'ConfirmDialog_title')]")
	private WebElement lblDeleteNotes;

	@FindBy(xpath = "//div[contains(@class,'ConfirmDialog_buttons')]//following-sibling::button")
	private WebElement btnDeleteNoteOkay;

	@FindBy(xpath = "(//div[contains(@class,'ConfirmDialog_buttons')]//button)[last()]")
	private WebElement btnDeleteFromSalesForce;

	// expected deletd notes

	@FindBy(xpath = "//button[contains(@class,'NewExplorer_createButton__7xV7l NewExplorer_actionButton')]//img[contains(@src,'circle.7d49cec8b94a249ea55071547f9aa68e.svg')]")
	private WebElement icnNewLuruNote;

	@FindBy(xpath = "//input[@placeholder='Search all notes']")
	private WebElement txtSearchNotes;

	@FindBy(xpath = "//button[contains(@class,'NewExplorer_deleteButton__dsCUF NewExplorer_actionButton')]")
	private WebElement icnDeleteNote;

	@FindBy(xpath = "//div[contains(@class,'NewExplorer_primaryLine')]")
	private List<WebElement> txtExistedNotesTitle;
	
	public static String dashBoardOptions = "//aside[contains(@class,'Toolbar_toolbar')]//span[contains(text(),'${text}')]";
	public static String createRecordOptions = "//div[contains(@class,'styles_records')]//li//span[contains(text(),'${text}')]";

	private static final Logger logger = Logger.getLogger(NotesPage.class.getName());

	public NotesPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of NotesPage method");

		PageFactory.initElements(driver, this);

		logger.info("Ending of NotesPage method");

	}

	public void selectDashboardOptions(String strOptions) {
		logger.info("Starting of selectDashboardOptions method");

		hardWait(2);
		driver.findElement(By.xpath(dashBoardOptions.replace("${text}", strOptions))).click();

		logger.info("Ending of selectDashboardOptions method");
	}

	public void clickOnCreateRecordButton() {
		logger.info("Starting of clickOnCreateRecordButton method");

		try {
			this.clickOnWebElement(btnCreateRecord);
		} catch (Exception e) {
			btnCreateRecord.click();
		}

		logger.info("Ending of clickOnCreateRecordButton method");
	}

	public void selectCreateRecordsOptions(String strCreateRecordOptions) {
		logger.info("Starting of selectCreateRecordsOptions method");

		driver.findElement(By.xpath(createRecordOptions.replace("${text}", strCreateRecordOptions))).click();

		logger.info("Ending of selectCreateRecordsOptions method");

	}

	public void setNoteTitle(String strTitleName) {
		logger.info("Starting of setNoteTitle method");

		this.txtNotesTitle.clear();
		hardWait(3);
		this.txtNotesTitle.sendKeys(strTitleName);
		this.txtNotesTitle.sendKeys(Keys.ENTER);

		logger.info("Ending of setNoteTitle method");
	}

	public String getSalesForceHeadingText() {
		logger.info("Starting of getSalesForceHeadingText method");
		logger.info("Ending of getSalesForceHeadingText method");

		return txtNotesTitle.getAttribute("value");
	}

	public void setNoteDescription(String strDescriptionName) {
		logger.info("Starting of setNoteDescription method");

		this.txtNotesDescription.clear();
		hardWait(3);
		this.txtNotesDescription.sendKeys(strDescriptionName);
		this.txtNotesDescription.sendKeys(Keys.ENTER);

		logger.info("Ending of setNoteDescription method");
	}

	public String getCreatedNoteText() {
		logger.info("Starting of getCreatedNoteText method");
		logger.info("Ending of getCreatedNoteText method");

		return txtCreatedNotesTitle.getText();
	}

	public void clickOnThreeDottedMenu() {
		logger.info("Starting of clickOnThreeDottedMenu method");

		try {

			this.clickOnWebElement(btnThreeDottedMenu);
		} catch (Exception e) {
			btnThreeDottedMenu.click();
		}

		logger.info("Ending of clickOnThreeDottedMenu method");
	}

	public void clickOnShareNote() {
		logger.info("Starting of clickOnShareNote method");

		try {

			this.clickOnWebElement(btnShareNotes);
		} catch (Exception e) {
			btnShareNotes.click();
		}

		logger.info("Ending of clickOnShareNote method");
	}

	public String getShareLabel() {
		logger.info("Starting of getShareLabel method");

		explicitWait(lblShareNotes);

		logger.info("Ending of getShareLabel method");
		return lblShareNotes.getText();
	}

	public void clickOnCopyNote() {
		logger.info("Starting of clickOnCopyNote method");

		try {
			Actions actions = new Actions(driver);
			actions.click(btnCopyNote).perform();
		} catch (Exception e) {
			this.clickOnWebElement(btnCopyNote);
		}

		logger.info("Ending of clickOnCopyNote method");
	}

	public String getCopyLabel() {
		logger.info("Starting of getCopyLabel method");
		logger.info("Ending of getCopyLabel method");

		return lblCopied.getText();
	}

	public void clickOnDeleteNote() {
		logger.info("Starting of clickOnDeleteNote method");

		try {

			this.clickOnWebElement(btnDeleteNotes);
		} catch (Exception e) {
			btnDeleteNotes.click();
		}

		logger.info("Ending of clickOnDeleteNote method");
	}

	public String getDeleteLabel() {
		logger.info("Starting of getDeleteLabel method");
		logger.info("Ending of getDeleteLabel method");

		return lblDeleteNotes.getText();
	}

	public void clickOnDeleteNoteOkayButton() {
		logger.info("Starting of clickOnDeleteNoteOkayButton method");

		try {

			this.clickOnWebElement(btnDeleteNoteOkay);
		} catch (Exception e) {
			btnDeleteNoteOkay.click();
		}

		logger.info("Ending of clickOnDeleteNoteOkayButton method");
	}

	public String getSuccessMessage() {
		logger.info("Starting of getSuccessMessage method");

		explicitWait(msgToast);

		logger.info("Ending of getSuccessMessage method");
		return msgToast.getText();
	}

	public void clickOnNewLuruNoteIcon() {
		logger.info("Starting of clickOnNewLuruNoteIcon method");

		try {

			this.clickOnWebElement(icnNewLuruNote);
		} catch (Exception e) {
			icnNewLuruNote.click();
		}

		logger.info("Ending of clickOnNewLuruNoteIcon method");
	}

	public void searchNoteInSearchBar(String noteName) {
		logger.info("Starting of searchNoteInSearchBar method");

		hardWait(3);
		txtSearchNotes.sendKeys(noteName);

		logger.info("Ending of searchNoteInSearchBar method");

	}

	public boolean isDisplayedSearchedNote() {
		logger.info("Starting of isDisplayedSearchedNote method");
		logger.info("Ending of isDisplayedSearchedNote method");

		return txtCreatedNotesTitle.isDisplayed();
	}

	public void clickOnDeleteIcon() {
		logger.info("Starting of clickOnDeleteIcon method");

		for (int i = 0; i < txtExistedNotesTitle.size(); i++) {
			hardWait(3);
			mouseHover(icnDeleteNote);
			this.icnDeleteNote.click();
			this.btnDeleteNoteOkay.click();
		}

		logger.info("Ending of clickOnDeleteIcon method");
	}

	public String getOpportunityText() {
		logger.info("Starting of getOpportunityText method");
		logger.info("Ending of getOpportunityText method");
		explicitWait(lblSelectOpportunityName);
		return lblSelectOpportunityName.getAttribute("value");
	}

	public void clickOnLinkToCRMButton() {
		logger.info("Starting of clickOnLinkToCRMButton method");

		explicitWait(btnLinkToCRM);
		btnLinkToCRM.click();

		logger.info("Ending of clickOnLinkToCRMButton method");

	}

	public void setCRMRecordLink(String link) {
		logger.info("Starting of setCRMRecordLink method");

		txtSearchForCRMLink.sendKeys(link);

		logger.info("Ending of setCRMRecordLink method");

	}

	public void clickOnSearchedRecord() {
		logger.info("Starting of clickOnSearchedRecord method");
		
		explicitWait(txtSearchedRecord);
		
		txtSearchedRecord.click();
		logger.info("Ending of clickOnSearchedRecord method");

	}

	public String getCRMTemplateRecordHeader() {
		logger.info("Starting of getCRMTemplateRecordHeader method");

		explicitWait(lblCRMTemplateRecordHeader);

		logger.info("Ending of getCRMTemplateRecordHeader method");
		return lblCRMTemplateRecordHeader.getText();
	}

	public void clickLinkedToCRMButton() {
		logger.info("Starting of clickLinkedToCRMButton method");

		btnLinkedToCRM.click();

		logger.info("Ending of clickLinkedToCRMButton method");

	}

	public String getRemoveRecordHeader() {
		logger.info("Starting of getCRMTemplateRecordHeader method");
		logger.info("Ending of getCRMTemplateRecordHeader method");

		return lblRemoveRecordHeader.getText();
	}

	public void clickRemoveRecordOkayButton() {
		logger.info("Starting of clickRemoveRecordOkayButton method");

		btnRemoveRecordOkay.click();

		logger.info("Ending of clickRemoveRecordOkayButton method");

	}

	public void clickNavigateButton() {
		logger.info("Starting of clickNavigateButton method");

		btnNavigate.click();

		logger.info("Ending of clickNavigateButton method");
	}
}
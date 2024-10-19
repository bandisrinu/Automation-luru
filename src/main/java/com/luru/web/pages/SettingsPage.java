package com.luru.web.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage extends BaseLuruAutomationPage {

	public static String chooseFields = "//li[contains(@class,'styles_fieldItem')]//label[contains(text(),'${text}')]";

	@FindBy(xpath = "//div[contains(@class,'styles_parent')]//h1")
	private WebElement lblSettings;

	@FindBys(@FindBy(xpath = "//li[contains(@class,'styles_fieldItem')]//label"))
	private WebElement lstAvailableFields;

	@FindBy(xpath = "//button[@title='Create a collection']")
	private WebElement btnAddCollections;

	@FindBy(xpath = "//div[contains(text(),'Create Collection')]")
	private WebElement lblCreateCollection;

	@FindBy(xpath = "//label[contains(text(),'Collection name*')]//following-sibling::input")
	private WebElement txtCollectionName;

	@FindBy(xpath = "//label[contains(text(),'Description')]//following-sibling::input")
	private WebElement txtCollectionDescription;

	@FindBy(xpath = "//label[contains(text(),'Fields')]//following-sibling::div/div//input")
	private WebElement drpFields;

	@FindBy(xpath = "(//div[contains(@class,'ModalScreen_buttons')]//following-sibling::button[contains(@class,'FlatButton_main')])[1]")
	private WebElement btnOkay;

	@FindBy(xpath = "//div[contains(@class,'styles_inviteButtons')]//following-sibling::button")
	private WebElement btnCreate;

	@FindBy(xpath = "//button[contains(@class,'styles_shareButton')]")
	private WebElement btnShareCollection;

	@FindBy(xpath = "//button[contains(@class,'styles_saveChangesButton')]")
	private WebElement btnSaveChanges;

	@FindBy(xpath = "//button[contains(@class,'styles_deleteButton')]")
	private WebElement icnDelete;

	@FindBy(xpath = "//button[contains(@class,'FlatButton_main__ex9-h ModalScreen_okButton')]")
	private WebElement btnConfirmDelete;

	@FindBy(xpath = "(//div[contains(text(),'Delete collection')])[last()]")
	private WebElement lblDeleteCollection;

	@FindBy(xpath = "//div[contains(@class,'table-cell styles_collectionName')]")
	private List<WebElement> lblCollectionNames;

	@FindBy(xpath = "//input[@type='search']")
	private WebElement txtSearchCollection;

	@FindBy(xpath = "//button[contains(@class,'styles_editButton')]")
	private WebElement icnEditCollection;

	public void clickOnEditCollectionIcon() {
		logger.info("Starting of clickOnEditCollectionIcon method");

		try {
			hardWait(3);
			this.clickOnWebElement(icnEditCollection);
		} catch (Exception e) {
			icnDelete.click();
		}

		logger.info("Ending of clickOnEditCollectionIcon method");
	}

	public void searchCollectionName(String collectionName) {
		logger.info("Starting of searchCollectionName method");

		this.txtSearchCollection.clear();
		this.txtSearchCollection.sendKeys(collectionName);

		logger.info("Ending of searchCollectionName method");
	}

	private static final Logger logger = Logger.getLogger(SettingsPage.class.getName());

	public SettingsPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of SettingsPage method");

		PageFactory.initElements(driver, this);

		logger.info("Ending of SettingsPage method");
	}

	public String getSettingsLabel() {
		logger.info("Starting of getSettingsLabel method");
		logger.info("Ending of getSettingsLabel method");

		return lblSettings.getText();
	}

	public void selectAvailableFileds(String strFields) {
		logger.info("Starting of selectAvailableFileds method");

		driver.findElement(By.xpath(chooseFields.replace("${text}", strFields))).click();

		logger.info("Ending of selectAvailableFileds method");
	}

	public void clickOnAddCollections() {
		logger.info("Starting of clickOnAddCollections method");

		try {
			this.clickOnWebElement(btnAddCollections);
		} catch (Exception e) {
			btnAddCollections.click();
		}

		logger.info("Ending of clickOnAddCollections method");
	}

	public String getCreateCollectionLabel() {
		logger.info("Starting of getCreateCollectionLabel method");
		logger.info("Ending of getCreateCollectionLabel method");

		return lblCreateCollection.getText();
	}

	public void setCollectionName(String strCollectionName) {
		logger.info("Starting of setCollectionName method");
		explicitWait(txtCollectionName);
		this.txtCollectionName.clear();
		this.txtCollectionName.sendKeys(strCollectionName);

		logger.info("Ending of setCollectionName method");
	}

	public void setCollectionDescription(String strDescriptionName) {
		logger.info("Starting of setCollectionDescription method");

		this.txtCollectionDescription.clear();
		hardWait(3);
		this.txtCollectionDescription.sendKeys(strDescriptionName);

		logger.info("Ending of setCollectionDescription method");
	}

	public void clickOnFields() {
		logger.info("Starting of clickOnFields method");

		try {
			Actions actions = new Actions(driver);
			actions.click(drpFields).perform();
		} catch (Exception e) {
			this.clickOnWebElement(drpFields);
		}

		logger.info("Ending of clickOnFields method");
	}

	public void clickOnFieldsOkayButton() {
		logger.info("Starting of clickOnFieldsOkayButton method");

		try {
			this.clickOnWebElement(btnOkay);
		} catch (Exception e) {
			btnOkay.click();
		}

		logger.info("Ending of clickOnFieldsOkayButton method");
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

	public void clickOnShareCollection() {
		logger.info("Starting of clickOnShareCollection method");

		try {
			this.clickOnWebElement(btnShareCollection);
		} catch (Exception e) {
			btnShareCollection.click();
		}

		logger.info("Ending of clickOnShareCollection method");
	}

	public void clickOnSaveChanges() {
		logger.info("Starting of clickOnSaveChanges method");

		try {
			hardWait(4);
			this.clickOnWebElement(btnSaveChanges);
		} catch (Exception e) {
			btnSaveChanges.click();
		}

		logger.info("Ending of clickOnSaveChanges method");
	}

	public String getDeleteCollectionLabel() {
		logger.info("Starting of getDeleteCollectionLabel method");
		logger.info("Ending of getDeleteCollectionLabel method");

		return lblDeleteCollection.getText();
	}

	public boolean isDisplayedCollectionLabel(String deletedCollectionName) {
		logger.info("Starting of clickOnDeleteIcon method");

		for (int i = 0; i < lblCollectionNames.size(); i++) {
			hardWait(2);
			System.out.println(lblCollectionNames.get(i).getText());
			if ((lblCollectionNames.get(i).getText().equalsIgnoreCase(deletedCollectionName))) {
				return true;
			}
		}

		logger.info("Ending of clickOnDeleteIcon method");
		return false;
	}

	public void clickOnDeleteIcon() {
		logger.info("Starting of clickOnDeleteIcon method");

		try {
			this.clickOnWebElement(icnDelete);
		} catch (Exception e) {
			icnDelete.click();
		}

		logger.info("Ending of clickOnDeleteIcon method");
	}

	public void clickOnConfirmDeleteButton() {
		logger.info("Starting of clickOnConfirmDeleteButton method");

		try {
			this.clickOnWebElement(btnConfirmDelete);
		} catch (Exception e) {
			btnConfirmDelete.click();
		}

		logger.info("Ending of clickOnConfirmDeleteButton method");
	}

}
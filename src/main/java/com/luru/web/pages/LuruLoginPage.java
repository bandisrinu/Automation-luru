package com.luru.web.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LuruLoginPage extends BaseLuruAutomationPage {

	@FindBy(xpath = "//a[contains(text(),'Login')]")
	private WebElement lblLogin;

	@FindBy(xpath = "//div[contains(@class,'styles_header')]//img")
	private WebElement lblLuru;

	@FindBy(xpath = "//button[normalize-space()='Continue with Salesforce']")
	private WebElement btnSalesForce;

	@FindBy(xpath = "//img[@alt='Salesforce']")
	private WebElement lblSalesForce;

	@FindBy(xpath = "//input[@id='username']")
	private WebElement txtUserName;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement txtPassword;

	@FindBy(xpath = "//input[@id='Login']")
	private WebElement btnLogin;

	@FindBy(xpath = "//span[contains(@class,'UserLogo_userLogo')]")
	private WebElement icnUserProfile;

	@FindBy(xpath = "//li[text()='Sign Out']")
	private WebElement btnSignOut;

	@FindBy(xpath = "//div[contains(@class,'Modal_modalClose')]/span")
	private WebElement btnCalendarPopupClose;

	@FindBy(xpath = "//div[@class='uf-eq2gc-actions right']//following-sibling::button")
	private WebElement btnLuruPopupClose;

	private static final Logger logger = Logger.getLogger(LuruLoginPage.class.getName());

	public LuruLoginPage(WebDriver driver) {
		super(driver);
		logger.info("Starting of LuruLoginPage method");
		
		PageFactory.initElements(driver, this);
		
		logger.info("Ending of LuruLoginPage method");
	}

	public void clickOnLoginLabel() {
		logger.info("Starting of clickOnLoginLabel method");

		try {

			this.clickOnWebElement(lblLogin);
			hardWait(10);

		} catch (Exception e) {
			lblLogin.click();
			hardWait(10);

		}

		logger.info("Ending of clickOnLoginLabel method");
	}

	public String getLuruHeadingText() {
		logger.info("Starting of getLuruHeadingText method");
		logger.info("Ending of getLuruHeadingText method");

		return lblLuru.getAttribute("alt");
	}

	public void clickOnSalesForceButton() {
		logger.info("Starting of clickOnSalesForceButton method");

		try {

			this.clickOnWebElement(btnSalesForce);
		} catch (Exception e) {
			btnSalesForce.click();
		}

		logger.info("Ending of clickOnSalesForceButton method");
	}

	public String getSalesForceHeadingText() {
		logger.info("Starting of getSalesForceHeadingText method");
		logger.info("Ending of getSalesForceHeadingText method");

		return lblSalesForce.getAttribute("alt");
	}

	public void setUserName(String strUserName) {
		logger.info("Starting of setUserName method");

		this.txtUserName.clear();
		hardWait(2);
		this.txtUserName.sendKeys(strUserName);

		logger.info("Ending of setUserName method");
	}

	public void setPassword(String strPassword) {
		logger.info("Starting of setPassword method");

		this.txtPassword.clear();
		hardWait(2);
		this.txtPassword.sendKeys(strPassword);

		logger.info("Ending of setPassword method");
	}

	public void clickOnLoginButton() {
		logger.info("Starting of clickOnLoginButton method");

		try {
			this.clickOnWebElement(btnLogin);
		} catch (Exception e) {
			btnLogin.click();
		}

		logger.info("Ending of clickOnLoginButton method");
	}

	public void clickOnSignOut() {
		logger.info("Starting of clickOnSignOut method ");

		this.clickOnWebElement(icnUserProfile);
		this.explicitWait(btnSignOut);
		this.clickOnWebElement(btnSignOut);

		logger.info("Ending of clickOnSignOut method");
	}

	public void clickOnLuruTourButton() {
		logger.info("Starting of clickOnLuruTourButton method");

		try {
			hardWait(2);
			this.clickOnWebElement(btnLuruPopupClose);
		} catch (Exception e) {
			btnLuruPopupClose.click();
		}

		logger.info("Ending of clickOnLuruTourButton method");
	}

	public void clickOnCloseCalendarButton() {
		logger.info("Starting of clickOnCloseCalendarButton method");

		try {
			hardWait(2);
			this.clickOnWebElement(btnCalendarPopupClose);
		} catch (Exception e) {
			btnCalendarPopupClose.click();
		}

		logger.info("Ending of clickOnCloseCalendarButton method");
	}

}

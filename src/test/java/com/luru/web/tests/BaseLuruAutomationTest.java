package com.luru.web.tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.luru.util.AutomationUtil;
import com.luru.util.Constants;
import com.luru.util.ExecutionMode;
import com.luru.util.TestListener;
import com.luru.web.pages.LuruLoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(TestListener.class)
public class BaseLuruAutomationTest {

	protected static final String BASE_DIR = System.getProperty("user.dir");
	protected static final String FILE_SEPARATOR = System.getProperty("file.separator");

	protected static String browserDriverPath = null;
	private static String loginURL = "https:www.luru.app/";
	protected LuruLoginPage luruLoginPage = null;

	protected static Map<String, String> chromeDriverMap = new HashMap<String, String>();
	private static final Logger logger = Logger.getLogger(BaseLuruAutomationTest.class.getName());
	protected static Properties testDataProp = null;
	protected static Properties expectedAssertionsProp = null;

	private static Map<WEB_DRIVER, WebDriver> webDriverPool = new Hashtable<WEB_DRIVER, WebDriver>();
	protected WebDriver childWebDriver = null;

	protected enum WEB_DRIVER {
		LOGIN_DRIVER_TEST, LURU_NOTE_DRIVER_TEST, LURU_SETTINGS_DRIVER_TEST, TASKS_DRIVER_TEST, PIPE_LINE_DRIVER_TEST
	}

	@BeforeSuite
	@Parameters({ "siteURL", "language" })
	public void initTestData1(String siteURL, String lang) {

		if (siteURL != null) {
			loginURL = siteURL;
		}

		if (testDataProp == null) {
			FileReader testDataReader = null;
			FileReader assertionsReader = null;

			try {
				String testDataFile = AutomationUtil.getLanguageFilePath(Constants.TEST_DATA_PROPERTIES, lang);
				logger.debug("Test data properties file " + testDataFile);
				testDataReader = new FileReader(testDataFile);
				testDataProp = new Properties();
				testDataProp.load(testDataReader);

				String expectedAssertionFile = AutomationUtil
						.getLanguageFilePath(Constants.EXPECTED_ASSERTIONS_PROPERTIES, lang);
				logger.debug("Expected assertions properties file " + expectedAssertionFile);
				assertionsReader = new FileReader(expectedAssertionFile);
				expectedAssertionsProp = new Properties();
				expectedAssertionsProp.load(assertionsReader);

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					testDataReader.close();
					assertionsReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		logger.debug("Site URL : " + loginURL);
	}

	protected synchronized void quitDriver(WebDriver driver, WEB_DRIVER webDriver) {
		logger.info("Starting of method quitDriver in BaseLuruAutomationTest ");

		try {
			if (driver != null) {
				// driver.close();
				driver.quit();
				driver = null;
				webDriverPool.remove(webDriver);
				logger.debug(webDriver + " Web driver quit successfully in BaseLuruAutomationTest ");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			driver = null;
		}
		logger.info("Ending of method quitDriver in BaseLuruAutomationTest");
	}

	/**
	 * This method is used for get driver
	 * 
	 * @param webDriver
	 * @return
	 */

	protected synchronized WebDriver getWebDriver(String browser, WEB_DRIVER webDriver) {
		logger.info("Starting of method getWebDriver");

		WebDriver driver = webDriverPool.get(webDriver);

		String osPath = System.getProperty("os.name");

		// Use existing driver
		if (driver != null) {
			logger.debug("Using existing web driver " + webDriver);
			return driver;

		}

		if (osPath.contains("Linux")) {

			if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.setHeadless(true);
				options.addArguments("--no-sandbox");
				driver = new FirefoxDriver(options);
			} else if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();

				options.addArguments("enable-automation");
				options.addArguments("--headless");
				options.addArguments("--no-sandbox");
				// options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				options.addArguments("--remote-allow-origins=*");
				options.setHeadless(true);
				options.addArguments("--headless");
				options.addArguments("--window-size=1920,1080");
				options.addArguments("--disable-gpu");
				options.addArguments("--no-sandbox");

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_settings.popups", 0);
				options.setExperimentalOption("prefs", prefs);

				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("CHROME");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				capabilities.setCapability("applicationCacheEnabled", "true");

				driver = new ChromeDriver(options);

			}
		}

		else {

			if (browser.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("enable-automation");
				// options.addArguments("--headless");
				// options.addArguments("--no-sandbox");
				// options.addArguments("--disable-extensions");
				options.addArguments("--dns-prefetch-disable");
				options.addArguments("--disable-gpu");
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				//options.addExtensions(new File("extension_2_3_164.crx"));
				options.addArguments("allow-file-access-from-files");
				options.addArguments("use-fake-device-for-media-stream");
				options.addArguments("use-fake-ui-for-media-stream");
				driver = new ChromeDriver(options);

			} else if (browser.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();

			} else if (browser.equalsIgnoreCase("Chromium")) {
				WebDriverManager.chromiumdriver().setup();
				driver = new EdgeDriver();

			} else if (browser.equalsIgnoreCase("IEDriverServer")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();

			}
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		logger.info("***** Driver Successfully Created ****** " + driver.getTitle());

		logger.info("End of method getWebDriver");

		webDriverPool.put(webDriver, driver);
		return driver;
	}

	public void siteLogin(String strEmailAddress, String strPassword, WebDriver driver) {
		logger.info("Starting of siteLogin method");

		driver.get(loginURL);

		logger.info("Ending of siteLogin method");
	}

	public void goToSite(WebDriver driver, String siteURL) throws Exception {

		logger.debug("Login URL" + loginURL);

		driver.get(siteURL);
	}

	public void logBrowserConsoleErrors(WebDriver driver) {
		LogEntries logentries = driver.manage().logs().get(LogType.BROWSER);
		for (LogEntry logentrey : logentries) {
			logger.error("===========================");
			logger.error(logentrey);
			logger.error("===========================");
		}
	}

	public WebDriver getChildWebDriver() {
		return this.childWebDriver;
	}

	public void fluentWaitForElement(WebDriver childDriver, String xPath) {

		try {

			// Reference : https://www.guru99.com/implicit-explicit-waits-selenium.html
			Wait<WebDriver> wait = new FluentWait<WebDriver>(childDriver).withTimeout(Duration.ofSeconds(5))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(Exception.class);

			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath(xPath));
				}
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected boolean isHeadless() {

		if (ExecutionMode.HEADLESS.getMode())
			return true;
		else
			return false;

	}

	public List<String> getPropertyList(String name) {
		List<String> list = Arrays.asList(name.toString().split("\\,"));
		System.out.println(list);
		return list;
	}

	public void clickOutside(WebDriver driver) {
		logger.info("Starting of clickOutside method");

		Actions action = new Actions(driver);
		action.moveByOffset(100, 100).click().build().perform();

		logger.info("Ending of clickOutside method");
	}

	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void loginToTheLuruSite(String strUserName, String strPassword) {

		luruLoginPage.clickOnLoginLabel();
		luruLoginPage.switchToNewWindow();
		luruLoginPage.clickOnSalesForceButton();
		luruLoginPage.setUserName(strUserName);
		luruLoginPage.setPassword(strPassword);
		luruLoginPage.clickOnLoginButton();
		luruLoginPage.clickOnCloseCalendarButton();
		luruLoginPage.clickOnLuruTourButton();
	}
}
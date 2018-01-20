package com.rbs.step_definitions;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.rbs.Support.Log;
import com.rbs.Support.TestDataExtractor;
import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	/**********************************************************************************************
	 ********************************* Basic Needed Variables *************************************
	 **********************************************************************************************/

	public static ExtentTest extentedReport;
	public static String appURL;
	public static WebDriver driver;
	public static String scenarioName;
	public static TestDataExtractor testData = new TestDataExtractor();
	public static String xltestDataWorkBook = "com.rbs.testData\\RBS_PoC_TD.xls";
	public static String xltestDataWorkSheet = "MemberCheckout";
	public static int maxPageLoadWait = 15;
	public static ExpectedCondition<Boolean> pageLoad;
	static ChromeOptions opt = new ChromeOptions();
	static DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();

	/**********************************************************************************************
	 ********************************* Basic Needed Variables *************************************
	 **********************************************************************************************/

	static {
		try {
			pageLoad = new ExpectedCondition<Boolean>() {
				public final Boolean apply(final WebDriver driver) {
					final JavascriptExecutor js = (JavascriptExecutor) driver;
					boolean docReadyState = false;
					try {
						docReadyState = (Boolean) js.executeScript(
								"return (function() { if (document.readyState != 'complete') {  return false; } if (window.jQuery != null && window.jQuery != undefined && window.jQuery.active) { return false;} if (window.jQuery != null && window.jQuery != undefined && window.jQuery.ajax != null && window.jQuery.ajax != undefined && window.jQuery.ajax.active) {return false;}  if (window.angular != null && angular.element(document).injector() != null && angular.element(document).injector().get('$http').pendingRequests.length) return false; return true;})();");
					} catch (WebDriverException e) {
						docReadyState = true;
					}
					return docReadyState;

				}
			};

			opt.addArguments("start-maximized");
			opt.addArguments("disable-infobars");
			opt.addArguments("--ignore-certificate-errors");
			opt.addArguments("--disable-bundled-ppapi-flash");
			opt.addArguments("--disable-extensions");
			opt.addArguments("--disable-web-security");
			opt.addArguments("--always-authorize-plugins");
			opt.addArguments("--allow-running-insecure-content");
			opt.addArguments("--test-type");
			chromeCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
			chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, opt);
			chromeCapabilities.setPlatform(Platform.WINDOWS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Comment when running from TestNG
		// Log.startExtentReport();
	}

	@Before
	/**
	 * Initialize Browser Read TestData based on Scenario Name
	 */
	public void beforeTest(Scenario scenario) throws MalformedURLException {
		URL hubUrl = new URL("http://localhost:4444/wd/hub");

		scenarioName = scenario.getName();
		driver = new RemoteWebDriver(hubUrl, chromeCapabilities);

		// Loading the test data from excel using the test case id
		testData.setWorkBookName(xltestDataWorkBook);
		testData.setWorkSheet(xltestDataWorkSheet);
		testData.setFilePathMapping(true);
		testData.setTestCaseId(Hooks.scenarioName);
		testData.readData();
		appURL = testData.get("URL");
		extentedReport = Log.startTestCase(scenarioName);
	}

	@After
	/**
	 * Ends the Test
	 */
	public void afterTest(Scenario scenario) {
		Log.testCaseResultExtentReport(extentedReport, scenario.isFailed());
		driver.quit();
		Log.endTestCase(extentedReport);
		// Comment when running from TestNG
		// Log.endExtentReport();
	}
}
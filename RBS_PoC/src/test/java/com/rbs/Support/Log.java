package com.rbs.Support;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import com.rbs.step_definitions.Hooks;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Log {

	private static ExtentReports extent;
	private static String screenShotFolderPath;
	private static Logger Log = Logger.getLogger(Log.class.getName());
	private static AtomicInteger screenShotIndex = new AtomicInteger(0);

	/**
	 * Static block clears the screenshot folder if any in the output during every
	 * suite execution and also sets up the print console flag for the run
	 */
	static {

		File screenShotFolder = new File("./test-output/");
		screenShotFolderPath = "./test-output/ScreenShot" + File.separator;
		screenShotFolder = new File(screenShotFolderPath);

		if (!screenShotFolder.exists()) {
			screenShotFolder.mkdir();
		}

		File[] screenShots = screenShotFolder.listFiles();

		// delete files if the folder has any
		if (screenShots != null && screenShots.length > 0) {
			for (File screenShot : screenShots) {
				screenShot.delete();
			}
		}

	} // static block

	public static void startExtentReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/RBS_Report.html", true);
		extent.addSystemInfo("Host Name", "PoC RBS").addSystemInfo("Environment", "TA Practice")
				.addSystemInfo("User Name", "Aspire Systems");
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
	}

	public static void endExtentReport() {
		extent.flush();
		extent.close();
	}

	public static void endTestCase(ExtentTest extentReporterTests) {
		extent.endTest(extentReporterTests);
	}

	/**
	 * Overrided with Extent Reports
	 * 
	 * @param extentedReport
	 */
	public static void testCaseResultExtentReport(ExtentTest extentedReport, boolean scriptFailed) {
		if (scriptFailed) {
			extentedReport.log(LogStatus.FAIL, "Test Failed");
		} else {
			extentedReport.log(LogStatus.INFO, "Test Passed");
		}
		extent.endTest(extentedReport);
	}

	public static ExtentTest startTestCase(String scenarioName) {
		ExtentTest extentedReport = extent.startTest(scenarioName, "PoC TestScript");
		extentedReport.assignCategory("QA TA");
		extentedReport.assignAuthor("Aspire Systems");
		return extentedReport;
	}

	public static void info(String description, WebDriver driver, ExtentTest extentedReport,
			Boolean... takeScreenShot) {

		boolean finalDecision = false;

		if (takeScreenShot.length > 0)
			finalDecision = true;

		if (finalDecision) {
			extentedReport.log(LogStatus.INFO,
					description + extentedReport.addScreenCapture("./ScreenShot/" + takeScreenShot(driver)));
		} else {
			extentedReport.log(LogStatus.INFO, description);
		}
	}

	public static void passExtentReport(WebDriver driver, String description, ExtentTest extentedReport) {
		extentedReport.log(LogStatus.PASS,
				description + extentedReport.addScreenCapture("./ScreenShot/" + takeScreenShot(driver)));
	}

	/**
	 * takeScreenShot will take the screenshot by sending driver as parameter in the
	 * log and puts in the screenshot folder
	 * 
	 * depends on system variable isTakeScreenShot status, if true it will take the
	 * screenshot, else return the empty string
	 * 
	 * @param driver
	 *            to take screenshot
	 * @return String take sheet shot path
	 */
	public static String takeScreenShot(WebDriver driver) {
		String inputFile = "";
		inputFile = Hooks.scenarioName + "_" + screenShotIndex.incrementAndGet() + ".png";
		ScreenshotManager.takeScreenshot(driver, screenShotFolderPath + inputFile);
		return inputFile;
	}

	public static void event(String message) {
		Log.info(message);
	}
}
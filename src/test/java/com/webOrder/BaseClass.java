package com.webOrder;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {

	static WebDriver driver;

	// String filePath_failure = "D:\\F Drive\\Selenium Training
	// Data\\workspace\\Maven_Selenium_WebDriver_4\\Screenshot_Failure";
	static String filePath = System.getProperty("user.dir");

	static String Relativepath_failure = filePath + "\\Screenshot_Failure";
	static String Relativepath_success = filePath + "\\Screenshot_Success";

	public static String getScreenshotfailure(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = Relativepath_failure + "//" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static String getScreenshotSuccess(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = Relativepath_success + "//" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static WebDriver crossBrowserTesting(String browser) throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			// WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();

			// Disable Password Manager
			options.addArguments("--disable-save-password-bubble");

			options.setExperimentalOption("prefs", Map.of("credentials_enable_service", false,
					"profile.password_manager_enabled", false, "profile.password_manager_leak_detection", false));

			driver = new ChromeDriver(options);
			// WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			// WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			// WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		return driver;
	}
	
	

	// Method to create report.
//	public static ExtentSparkReporter htmlreporter;
//	public static ExtentReports extent;
//	public static ExtentTest test;

	static ExtentSparkReporter htmlreporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public static void CreateExtentReport(String ReportName, String Browser) {
		String reportDirectory = "./ExtentReport/";
		File reportDir = new File(reportDirectory);
		if (!reportDir.exists()) {
			reportDir.mkdir();
			System.out.println("Created directory: " + reportDirectory);
		}

		String reportPath = reportDirectory + ReportName;
		htmlreporter = new ExtentSparkReporter(reportPath);
		extent = new ExtentReports();
		extent.attachReporter(htmlreporter);

		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Browser", Browser);
		extent.setSystemInfo("QA Name", "Dixit");
		htmlreporter.config().setDocumentTitle("Regression Test");
		htmlreporter.config().setReportName(ReportName);
		htmlreporter.config().setTheme(Theme.DARK);
		htmlreporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		System.out.println("Extent Report created at: " + reportPath);
	}


}
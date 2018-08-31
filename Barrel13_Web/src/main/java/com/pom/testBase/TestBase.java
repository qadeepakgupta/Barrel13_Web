package com.pom.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.pom.excelReader.Excel_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public WebDriver driver;
	public Excel_Reader excel;
	public Properties OR = new Properties();
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/main/java/com/pom/report/Extent_Report_"
				+ formater.format(calendar.getTime()) + ".html", false);
	}

	public void loadData() throws IOException {
		File file = new File(System.getProperty("user.dir") + "/src/main/java/com/pom/config/config.properties");
		FileInputStream f = new FileInputStream(file);
		OR.load(f);

	}

	public void init() throws IOException {
		loadData();
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		selectBrowser(OR.getProperty("browser"));
		getUrl(OR.getProperty("url"));
	}

	public void checkBrowserOS() {
		// Get Browser name and version.
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = caps.getBrowserName();
		String browserVersion = caps.getVersion();

		// Get OS name.
		String os = System.getProperty("os.name").toLowerCase();
		log.info("Operaing System: " + os + ", Browser Name and Version: " + browserName + " " + browserVersion);
	}

	public void selectBrowser(String browser) {
		System.out.println("Browser Name:" + OR.getProperty("browser"));
		if (System.getProperty("os.name").contains("Window")) {
			if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("disable-infobars");
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);
				checkBrowserOS();
			} else if (browser.equals("firefox")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				checkBrowserOS();

			}
		} else if (System.getProperty("os.name").contains("Mac")) {
			if (browser.equals("chrome")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
				driver = new ChromeDriver();
			} else if (browser.equals("firefox")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/drivers/geckodriver");
				driver = new FirefoxDriver();
			}
		}
	}

	public void getUrl(String url) {
		driver.manage().window().maximize();
		log.info("navigating to :-" + url);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public String[][] getData(String excelName, String sheetName) {
		String path = System.getProperty("user.dir") + "/src/main/java/com/pom/data/" + excelName;
		excel = new Excel_Reader(path);
		String[][] data = excel.getDataFromSheet(sheetName, excelName);
		return data;
	}

	public void getScreenShot(String name) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "/src/main/java/com/pom/screenshot/";
			File destFile = new File((String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Iterator<String> getAllWindows() {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		return itr;
	}

	public void getScreenShot(WebDriver driver, ITestResult result, String folderName) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String methodName = result.getName();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/pom/";
			File destFile = new File((String) reportDirectory + "/" + folderName + "/" + methodName + "_"
					+ formater.format(calendar.getTime()) + ".png");

			FileUtils.copyFile(scrFile, destFile);

			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getScreenShotOnSucess(WebDriver driver, ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String methodName = result.getName();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/pom/";
			File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_"
					+ formater.format(calendar.getTime()) + ".png");

			FileUtils.copyFile(scrFile, destFile);

			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "Issue_Screen_Shot";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "/src/main/java/com/pom/screenshot/";
			destFile = new File((String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
		test.log(LogStatus.INFO, data);
	}

	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
			String screen = captureScreen("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) {
		getresult(result);
		test.log(LogStatus.INFO, result.getName() + " test finished");
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() throws InterruptedException {
		closeBrowser();
	}

	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
		log.info("browser closed");
		extent.endTest(test);
		extent.flush();
	}

	// @Parameters("browser")
	// @BeforeTest
	public void launchApp(String browser) throws IOException {

		if (System.getProperty("os.name").contains("Mac")) {
			if (browser.equals("chrome")) {
				// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
				// + "/drivers/chromedriver");
				System.out.println(" Executing on CHROME");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				String Node = "http://localhost:5001/wd/hub";
				driver = new RemoteWebDriver(new URL(Node), cap);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				// Launch website
				loadData();
				getUrl(OR.getProperty("url"));
			} else if (browser.equals("firefox")) {
				// System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
				// "/drivers/geckodriver.exe");
				System.out.println(" Executing on FireFox");
				String Node = "http://172.16.123.130:5000/wd/hub";
				DesiredCapabilities cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				driver = new RemoteWebDriver(new URL(Node), cap);
				loadData();
				getUrl(OR.getProperty("url"));
			} else if (browser.equalsIgnoreCase("ie")) {
				System.out.println(" Executing on IE");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setBrowserName("ie");
				String Node = "http://192.168.0.101:5555/wd/hub";
				driver = new RemoteWebDriver(new URL(Node), cap);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				// Launch website
				loadData();
				getUrl(OR.getProperty("url"));
			} else {
				throw new IllegalArgumentException("The Browser Type is Undefined");
			}
		}
		if (System.getProperty("os.name").contains("Window")) {
			if (browser.equals("chrome")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
				System.out.println(" Executing on CHROME");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				String Node = "http://localhost:5555/wd/hub";
				driver = new RemoteWebDriver(new URL(Node), cap);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				// Launch website
				loadData();
				getUrl(OR.getProperty("url"));
			} else if (browser.equals("firefox")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
				System.out.println(" Executing on FireFox");
				String Node = "http://172.16.123.130:5554/wd/hub";
				DesiredCapabilities cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				driver = new RemoteWebDriver(new URL(Node), cap);
				loadData();
				getUrl(OR.getProperty("url"));
			} else if (browser.equalsIgnoreCase("ie")) {
				System.out.println(" Executing on IE");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setBrowserName("ie");
				String Node = "http://192.168.0.101:5555/wd/hub";
				driver = new RemoteWebDriver(new URL(Node), cap);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				// Launch website
				getUrl(OR.getProperty("url"));
			} else {
				throw new IllegalArgumentException("The Browser Type is Undefined");
			}
		}
	}

	public void clearCookies() {
		log.info("browser cookies clear.");
		driver.manage().deleteAllCookies();
	}

}
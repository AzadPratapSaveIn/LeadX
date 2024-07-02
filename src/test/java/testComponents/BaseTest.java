package testComponents;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.internal.TestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected static WebDriver driver;
	public static String screenshotSubFolderName;
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	public WebDriver initializeDriver(ITestContext context ) throws IOException {
		
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\globalData.properties");
		properties.load(fis);
		System.out.println(System.getProperty("user.dir")+"\\src\\test\\resources\\globalData.properties");
		String browserName = properties.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		}
			
		if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		}
		
		if(browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			
		}

		driver.manage().window().maximize();
		extentTest = extentReport.createTest(context.getName());
		return driver;
		
	}
	
	@BeforeClass(alwaysRun = true)
	public void landingPage(ITestContext context) throws IOException {
		driver = initializeDriver(context);
		driver.get("https://qa.savein.money/");
	}
	
	
	@AfterMethod
	public void captureScreenshots(ITestResult result) throws IOException {
		if(result.getStatus() == TestResult.FAILURE) {
			captureScreenshot(result.getTestContext().getName()+"_"+result.getMethod().getMethodName()+".jpg");
		}
		
	}
	
	
	public String captureScreenshot(String methodName) throws IOException {
		if(screenshotSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		    screenshotSubFolderName = myDateObj.format(myFormatObj);
		}
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/"+ screenshotSubFolderName+"/"+methodName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");
		return destFile.getAbsolutePath();
	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDownDriver() throws IOException {
		driver.quit();
	}
	
	
	@AfterClass
	public void report() throws IOException {
		ExtentReports extentReport = new ExtentReports();
		File file = new File("report.html");
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(file);
		extentReport.attachReporter(extentSparkReporter);
		
		extentReport.flush();
		
		Desktop.getDesktop().browse(file.toURI());
	}
	
	
	@BeforeSuite
	public void initializeExtentReports() {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("AllTest.html");
		extentReport = new ExtentReports();
		extentReport.attachReporter(extentSparkReporter);
		extentReport.setSystemInfo("OS",System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		
	}
	
	
	
	@AfterSuite
	public void generateExtentReport() throws IOException {
		extentReport.flush();
		
		Desktop.getDesktop().browse(new File("AllTest.html").toURI());
	}

	@AfterMethod
	public void checkStatus(Method m, ITestResult iTestResult) throws IOException {
		if(iTestResult.getStatus()==ITestResult.FAILURE) {
			String screenshotPath=null;
			screenshotPath = captureScreenshot(iTestResult.getName()+"_"+iTestResult.getMethod().getMethodName()+".jpg");
			extentTest.addScreenCaptureFromPath(screenshotPath);
			extentTest.fail(iTestResult.getThrowable());
		} else if(iTestResult.getStatus()==ITestResult.SUCCESS) {
			extentTest.pass(m.getName()+"is passed"); 
		}
	}

	
	
	
}

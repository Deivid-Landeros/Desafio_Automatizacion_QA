package ips.utils.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.Status;

import ips.tests.BaseTest;
import ips.utils.logs.Log;
import ips.utils.reports.ExtentManager;
import static ips.utils.reports.ExtentTestManager.getTest;

public class TestListener extends BaseTest implements ITestListener{
	
	private static String nameTest;
	private static String getTestMethodName(ITestResult iTestResult) {
		nameTest = iTestResult.getMethod().getConstructorOrMethod().getName();
        return nameTest;
    }
	
	@Override
    public void onStart(ITestContext iTestContext) {
        Log.info("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is starting.");
    }
    @Override
 	public void onTestSuccess(ITestResult iTestResult) {
     	Log.info(getTestMethodName(iTestResult) + " test is succeed.");
     	//Get driver from BaseTest and assign to local webdriver variable.
     	Object testClass = iTestResult.getInstance();
     	WebDriver driver = ((BaseTest) testClass).getDriver();
         
   	//Take base64Screenshot screenshot for extent reports
     	String base64Screenshot =
         	"data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
     	//ExtentReports log operation for passed tests.
     	getTest().log(Status.PASS, "Test passed",
     	getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
 	}

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is failed.");
        //Get driver from BaseTest and assign to local webdriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
        "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, "Test Failed",
            getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

    public static void registrarPaso(String msg) {
    	try {
        	Thread.sleep(1000);
    	} catch (InterruptedException e) {
        	e.printStackTrace();
    	}
 
    	// Set the window size to a large resolution - solo headless
 /*  	driver.manage().window().setSize(new Dimension(1400, 1050));
 
    	// Scroll to the top of the page
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollTo(0, 0);");
 
 */
    	try {
        	Thread.sleep(1000);
    	} catch (InterruptedException e) {
        	e.printStackTrace();
    	}
 
 
    	File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	Path path = Paths.get("src" + File.separator + "test" + File.separator + "resources" + File.separator + "evidencias" + File.separator + nameTest);
    	try {
        	Files.createDirectories(path);
    	} catch (IOException e) {
        	e.printStackTrace();
    	}
 
    	msg = msg.replace(" ", "_");
    	File destinationFile = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "evidencias" + File.separator + nameTest + File.separator + msg + ".png");
 
 
    	try {
        	FileUtils.copyFile(screenshotFile, destinationFile);
    	} catch (IOException e) {
        	e.printStackTrace();
    	}
 
//    	driver.manage().window().setSize(new Dimension(1400, 1050));  //solo headless
	}


}

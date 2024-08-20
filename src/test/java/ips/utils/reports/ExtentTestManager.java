package ips.utils.reports;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ips.tests.BaseTest;

public class ExtentTestManager extends BaseTest{
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports            extent        = ExtentManager.createExtentReports();
    public static ExtentTest test;
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }
    public static synchronized ExtentTest startTest(String testName, String desc) {
        test = extent.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }
    
  
    
    
  /*  public static synchronized ExtentTest registrarPaso(String msg) {
        String base64Screenshot =
            "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        
        getTest().log(Status.INFO, msg,
            getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    	
        return test;
    }
 */   
    
}

package ips.tests;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ips.utils.logs.Log;
import ips.webpages.HomePage;

public class BaseTest {
	public static WebDriver driver;
	public HomePage homePage;
	
	
    public WebDriver getDriver() {
    	
        return driver;
    }
    
    @BeforeSuite
    public void setupProxy() {

    }
    
    @BeforeClass
    public void classLevelSetup() {
        Log.info("Test empieza!");
        
//        System.setProperty("webdriver.edge.driver", "src\\test\\resources\\driver\\msedgedriver.exe");
//        EdgeOptions edgeOptions = new EdgeOptions();
//        edgeOptions.setAcceptInsecureCerts(true);

//        driver = new EdgeDriver(edgeOptions);
//  		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//  		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
//  		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
//        driver.manage().window().maximize();
        
        ChromeOptions options = new ChromeOptions(); 
        driver = new ChromeDriver(options);
        
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // Configuración para Windows
            System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\driver\\chromedriver.exe");
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Configuración para Linux/Mac
            System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver");
            options.addArguments("headless"); // Activa el modo headless en Linux/Mac
        }
      		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
      		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
      		driver.manage().window().setSize(new Dimension(1920, 1080));
    }
    
    @BeforeMethod
    public void methodLevelSetup() {
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void teardown() {
    	try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Log.info("Test terminado");
        driver.quit();
    }

}

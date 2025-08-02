package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Properties prop;
	public Logger log;
	
	@BeforeClass(groups={"Sanity", "Regression", "DataDriven", "Master" })
	@Parameters({"browser", "os"})
	public void setupDriver(String browser, String os) throws IOException {
		
		log = LogManager.getLogger(this.getClass());
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
		prop = new Properties();
		prop.load(fis);
		
		switch(browser) {
		
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver =  new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default : System.out.println("Invaild browser...");
		return;
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Sanity", "Regression", "DataDriven", "Master"})
	public void tearDown() {
		driver.quit();
	}
	
	@SuppressWarnings("deprecation")
	public String randomPassword() {
		String randomPassword = RandomStringUtils.randomAlphanumeric(8);
		return randomPassword;
	}

	@SuppressWarnings("deprecation")
	public String randomNumber() {
		String randomNumber = RandomStringUtils.randomNumeric(10);
		return randomNumber;
	}

	@SuppressWarnings("deprecation")
	public String randomString() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		return randomString;
	}
	
	public String captureScreenshot(String sName) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"/screenshots/"+sName+"_"+timeStamp+".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}

}

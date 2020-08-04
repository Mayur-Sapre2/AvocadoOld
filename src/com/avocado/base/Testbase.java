package com.avocado.base;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Testbase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentTest test;
	public static ExtentReports extent;
	//public static Logger logger=Logger.getLogger(Log.class.getName());
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public static void LoadConfigProperty() {
		try {
			prop=new Properties();
			FileInputStream fis=new FileInputStream("/home/gslab/eclipse-workspace/Avacado/resources/browser-config.properties");
			prop.load(fis);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static WebDriver initialisation() throws AWTException {
		LoadConfigProperty();
		String browsername=Testbase.prop.getProperty("browser");
		//Chrome
		if(browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","/usr/bin/chromedriver");
			driver=new ChromeDriver();
		//Firefox
			}else if(browsername.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver","/usr/bin/chromedriver");
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(prop.getProperty("baseURL"));
		return driver;	
	}
}
	
//	public static void quit() throws IOException 
//	{
//		Random rand = new Random();
//		String random = rand.toString();
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "//screenshots/" + random + ".png"));
//		Path content = Paths.get(System.getProperty("user.dir") + "//screenshots/" + random + ".png");
//	}
//}

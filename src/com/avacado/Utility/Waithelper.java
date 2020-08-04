package com.avacado.Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avocado.base.Testbase;

public class Waithelper{
	
	public static WebDriver driver;
	
	public Waithelper(WebDriver driver) {
		this.driver=driver;
	}
	
	public static void waitForElement(WebDriver driver,WebElement locator,long timeoutinseconds) {
		new WebDriverWait(driver, timeoutinseconds).ignoring(StaleElementReferenceException.class)
		.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public static void pageLoad(int time) {
		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
	}
	
	public static void waitForSmalltime() throws InterruptedException {
		Testbase.LoadConfigProperty();
		String smalltime;
		smalltime=Testbase.prop.getProperty("SleepTimeSmall");
		long small = Long.parseLong(smalltime);
		Thread.sleep(small);
	}
	
	public static void waitForMediumtime() throws InterruptedException {
		Testbase.LoadConfigProperty();
		String mediumtime;
		mediumtime=Testbase.prop.getProperty("SleepTimeMedium");
		long medium = Long.parseLong(mediumtime);
		Thread.sleep(medium);
	}
	
	public static void waitforLongtime() throws InterruptedException {
		Testbase.LoadConfigProperty();
		String longtime;
		longtime=Testbase.prop.getProperty("SleepTimeLarge");
		long large = Long.parseLong(longtime);
		Thread.sleep(large);
	}
	
	public static void waitforXLongtime() throws InterruptedException {
		Testbase.LoadConfigProperty();
		String xlongtime;
		xlongtime=Testbase.prop.getProperty("SleepTimeLarge");
		long xlarge = Long.parseLong(xlongtime);
		Thread.sleep(xlarge);
	}
}
	
	


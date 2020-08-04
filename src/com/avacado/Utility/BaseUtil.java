package com.avacado.Utility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseUtil {
	
	public WebDriver driver;
	
	public ExtentReports extent;
	
	public static ExtentTest scenarioDef;
	
	public static ExtentTest features;
	
	public static String reportLocation="/home/gslab/eclipse-workspace/Avacado/reports";
}

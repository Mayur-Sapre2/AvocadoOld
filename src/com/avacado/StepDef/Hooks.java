package com.avacado.StepDef;

import java.io.IOException;

import javax.mail.MessagingException;

import org.openqa.selenium.WebDriver;

import com.avacado.Utility.GenericHelper;
import com.avacado.Utility.SendMail;
import com.aventstack.extentreports.ExtentTest;
import com.avocado.listeners.ExtentReport;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks{
	
	private static WebDriver driver;
	private static ExtentReport extentreport;
	private static boolean isReporterRunning; //To check whether extentreport engine started or not
    ExtentTest test;
	@Before
	public void setup(Scenario scenario) {
		System.out.println("----------"+scenario.getName()+"---------");
	}
	
	@Before(order = 1)
	public void beforeScenario(Scenario scenario) {
		if(!isReporterRunning) {
			extentreport=new ExtentReport("/home/gslab/eclipse-workspace/Avacado/reports/Test_Report.html");
			isReporterRunning=true;
		}
	}
	
	@After(order = 1)
	public void afterScenario(Scenario scenario) throws IOException, MessagingException {
		String screenShotFileName = "/home/gslab/eclipse-workspace/Avacado/reports" + scenario.getName().replaceAll(" ", "") + ".jpeg";
		if(scenario.isFailed()){
			GenericHelper.takeScreenShot(screenShotFileName);
			System.out.println("SCenario failed");
		}
		extentreport.createTest(scenario, screenShotFileName);
		//ExtentTest node = test.createNode("Node");
		extentreport.writeToReport();
		if(driver != null){
	    	driver.quit(); // it will close all the window and stop the web driver
	    }
		SendMail.sendMail();
	}
	
	@After
	public void teardown(Scenario scenario) throws IOException{
		System.out.println("------------------");
		System.out.println(scenario.getStatus() +"-----"+ scenario.getName());
		System.out.println("-------------------");
		
	}
	
}

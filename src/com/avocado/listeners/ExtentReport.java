package com.avocado.listeners;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;

import com.avacado.StepDef.AvacadoLoginStepDef;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import cucumber.api.Result;
import cucumber.api.Scenario;

public class ExtentReport {
	
	private ExtentHtmlReporter htmlreport;
	private ExtentReports extentReports;
	static Logger logger = Logger.getLogger(ExtentReport.class);
	
	public ExtentReport(String reportlocation) {
		htmlreport=new ExtentHtmlReporter(new File(reportlocation));
		htmlreport.config().setDocumentTitle("Avocado Automation Report");
		htmlreport.config().setEncoding("utf-8");
		htmlreport.config().setReportName("Avocado Automation Report");
		
		extentReports=new ExtentReports();
		extentReports.attachReporter(htmlreport);	
		
		extentReports.setSystemInfo("Application","Avocado");
		extentReports.setSystemInfo("Opearating System",System.getProperty("os.name"));
		extentReports.setSystemInfo("Hostname",System.getProperty("user.name"));
		extentReports.setSystemInfo("Tester Name","Mayur Sapre");
		
	}
	
	public void createTest(Scenario scenario,String screenshotfile) throws IOException {
		if(scenario!=null) {
			String testname=getScenarioTitle(scenario);
			switch(scenario.getStatus()) {
				case PASSED:
					extentReports.createTest(testname).pass("Passed").assignCategory("Unit");
					break;
					
				case FAILED:
					String errorMsg=getErrorMessage(scenario);
					extentReports.createTest(testname).fail(errorMsg).addScreenCaptureFromPath(getScreenShotLocation(screenshotfile));
					break;
					
				default:
					extentReports.createTest(testname).skip("Skipped");
					break;
			}
		}
	}

	public String getErrorMessage(Scenario scenario) {
		List<Result> testResultList = null;
		List<Result> failedStepList = null;
		
		try {
			 Field stepResults = scenario.getClass().getDeclaredField("stepResults");
			 stepResults.setAccessible(true);
			 testResultList = (List<Result>)stepResults.get(scenario);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		if(testResultList != null && !testResultList.isEmpty()){
			failedStepList = testResultList.stream().filter((x) ->{
				return x.getErrorMessage() != null;
			}).collect(Collectors.toList());
		}
		
		if(failedStepList != null && !failedStepList.isEmpty()){
			return failedStepList.get(0).getErrorMessage();
		}
		return "";
	}
		
	
	//*******Write all event back to report******//
	public void writeToReport() {
		if(extentReports != null) {
			extentReports.flush();
		}
	}
	
	private String getScreenShotLocation(String aLocation) {
		return "file:///" + aLocation.replace("\\", "//");
	}
	
	private String getScenarioTitle(Scenario scenario){
		return scenario.getName().replaceAll(" ", "");
	}
	
	public String getStepname(ITestResult result) {
		return result.getMethod().getMethodName();
	}
	
	
	
}

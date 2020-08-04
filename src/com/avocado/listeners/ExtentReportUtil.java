package com.avocado.listeners;

import com.avacado.Utility.BaseUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil extends BaseUtil {

	String filename= reportLocation + "extentreport.html";
	
	public void ExtentReport() {
		
		extent=new ExtentReports();
		
		ExtentHtmlReporter htmlreporter= new ExtentHtmlReporter(filename);
		htmlreporter.config().setTheme(Theme.DARK);
		htmlreporter.config().setDocumentTitle("Avocado Automation Report");
		htmlreporter.config().setEncoding("utf-8");
		htmlreporter.config().setReportName("Avocado Automation Report");
		extent.attachReporter(htmlreporter);
	}
	
	public void ExtentReportScreenshot() {
		
	}
}

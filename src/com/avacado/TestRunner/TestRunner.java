package com.avacado.TestRunner;


import org.testng.annotations.BeforeClass;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;

	@CucumberOptions(
			features = "/home/gslab/eclipse-workspace/Avacado/feature/Setting.feature",
			glue = {"com.avacado.StepDef"},
			tags = {"@plugin_installation"},
			plugin = {"pretty",
					"html:target/site/cucumber-pretty",
					"json:target/cucumber.json","com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
			monochrome = true,dryRun=true)

	public class TestRunner {
	    private TestNGCucumberRunner testNGCucumberRunner;
	    
	    @BeforeClass(alwaysRun = true)
	    public void setUpClass() throws Exception {
	        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	    }
	}
	    
package com.avacado.StepDef;

import java.awt.AWTException;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.avacado.Utility.CommonUtil;
import com.avacado.Utility.Waithelper;
import com.avocado.base.Testbase;
import com.avocado.pageobjects.Settings;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class AvacadoLoginStepDef extends Testbase{
	
	CommonUtil util=new CommonUtil();
	static Logger logger = Logger.getLogger(AvacadoLoginStepDef.class);
	
	
		@Given("Open chrome browser and enter url")
		public void open_chrome_browser_and_enter_url() throws InterruptedException, AWTException, ClassNotFoundException {	
			logger.info("**********Opening Browser and Entering Url**********");
			Testbase.initialisation();	
		}
		
		@When("Title of page is Avocado")
		public void title_of_page_is_Avocado() {
				String title=driver.getTitle();
				Assert.assertEquals(title, "Avocado");
				logger.info("*****Title Verified as Avocado********");
		}
		
		@Then("Enter username and password")
		public void enter_username_and_password() throws Exception {
			logger.info("****Entering Username and Password*****");
			util.enterNameInTextboxById("username", Testbase.prop.getProperty("username"));
		    util.enterNameInTextboxById("password", Testbase.prop.getProperty("password"));
		}

		@And("Click on login button")
		public void click_on_login_button() throws Exception {
		   logger.info("****Clicking on Login Button*****");
		   util.clickOnButtonById("loginBtn");
		   Waithelper.waitForSmalltime();
		}
		
		//Setting Page Step Definition
		@Then("Click on Setting menu")
		public void click_on_Setting_menu() throws InterruptedException {
			logger.info("****Clicking on Settings Menu*****");
			new Settings(driver).navigateToSetting();
		}
		
		@Then("Set orchestrator url")
		public void set_orchestrator_url() throws Exception {
		   logger.info("****Setting Orchestrator Url*****");
		   util.enterNameInTextFieldByPlaceHolder("Type orchestrator URL here", Testbase.prop.getProperty("orchestrator_url"));
		   util.clickOnButtonById("btn_save");
		}

		@Then("Set pico segment and server per pico segment")
		public void set_pico_segment_and_server_per_pico_segment() throws Exception {
			logger.info("****Setup Picosegment*****");
			Waithelper.waitForSmalltime();
		   util.clearTextFromTextFieldByPlaceHolder("Type maximum no. of pico segments here");
		   util.enterNameInTextFieldByPlaceHolder("Type maximum no. of pico segments here",Testbase.prop.getProperty("pico_segment"));
		   //util.verifyErrorMessage(message,Testbase.prop.getProperty("expected_msg"))
		   util.clearTextFromTextFieldByPlaceHolder("Type maximum servers per pico segments here");
		   util.enterNameInTextFieldByPlaceHolder("Type maximum servers per pico segments here",Testbase.prop.getProperty("server_pico_segment"));
		   util.clickOnButtonById("btn_save_picoSegment");
		}
		
		@Then("Verify error message for \"([^\"]*)\"")
		public void verify_error_message_for_pico_segment(String message) throws Exception {
		    Waithelper.waitForSmalltime();
			util.verifyErrorMessage(message, Testbase.prop.getProperty("expected_msg"));
		}	

		@Then("Set polling interval and polling interval for ui")
		public void set_polling_interval_and_polling_interval_for_ui() throws Exception {
			   util.clearTextFromTextFieldByPlaceHolder("Type polling interval here");
			   util.enterNameInTextFieldByPlaceHolder("Type polling interval here",Testbase.prop.getProperty("polling_interval"));
			   util.clearTextFromTextFieldByPlaceHolder("Type polling interval for UI here");
			   util.enterNameInTextFieldByPlaceHolder("Type polling interval for UI here",Testbase.prop.getProperty("polling_interval_for_ui"));
			   util.clickOnButtonById("btn_save_prop");
		}

		@Then("Set application discovery only mode")
		public void set_application_discovery_only_mode() throws Exception {
			new Settings(driver).setApplicationDiscoveryMode(Testbase.prop.getProperty("application_discovery_mode"));
		}

		@Then("Click on {string} button")
		public void click_on_button(String button) throws Exception {
			Waithelper.waitForMediumtime();
		    util.clickOnButtonByText("Configure \"Service Now\"");
		}
		
		@Given("Enter service url,config name,password and confirm password")
		public void enter_service_url_config_name_password_and_confirm_password() throws Exception {
			   util.clickOnButtonById("btn_create_config");
			   util.clearTextFromTextFieldByPlaceHolder("Type URL here");
			   util.enterNameInTextFieldByPlaceHolder("Type URL here",Testbase.prop.getProperty("service_url"));
			   util.clearTextFromTextFieldByPlaceHolder("Type userid for the user here");
			   util.enterNameInTextFieldByPlaceHolder("Type userid for the user here",Testbase.prop.getProperty("userid"));
			   
			   util.clearTextFromTextFieldByPlaceHolder("Type config namehere");
			   util.enterNameInTextFieldByPlaceHolder("Type config namehere",Testbase.prop.getProperty("config_name"));
			   
			   util.enterTextInTextboxByText("PASSWORD","Type password for the user here", Testbase.prop.getProperty("config_password"));
			   util.enterTextInTextboxByText("CONFIRM PASSWORD","Type password for the user here", Testbase.prop.getProperty("confirm_password"));
		}

		@Given("Select domain access and sub-domain access")
		public void select_domain_access_and_sub_domain_access() throws Exception {
			 Waithelper.waitForMediumtime();
			 //util.setDomain("All");
			 new Settings(driver).setDomain("All");
			 Waithelper.waitForMediumtime();
			 new Settings(driver).setSubDomain("All");
			 //util.setSubDomain("All");
		}
		
		@Then("Click on \"([^\"]*)\" Button")
		public void click_on_Button(String text) throws Exception {
			Waithelper.waitForSmalltime();
		    util.clickOnButtonByText(text);
		    util.scrollpage("DOWN");
		}
		
		@Then("click {string} Button")
		public void click_Button(String string) throws Exception {
			Waithelper.waitForMediumtime();
		    util.clickOnButtonById("btn_save_config");
		    Waithelper.waitForMediumtime();
		    util.scrollpage("DOWN");
		}
		
		@Then("Verify configuration name in configuration list")
		public void verify_configuration_name_in_configuration_list() throws Exception {
		    Settings.verifyConfigList(Testbase.prop.getProperty("config_name"));
		}
		
		@Then("Right click on configuration name")
		public void right_click_on_configuration_name() throws Exception {
		
			Waithelper.waitForSmalltime();
			util.rightClickOnElementByLabel(Testbase.prop.getProperty("config_name"));
		}

		@Then("delete service")
		public void delete_service() throws Exception {
			 util.clickOnButtonById("delete");
			 util.clickOnConfirmationboxAndPerformOperation("Are you sure you want to delete this configuration ? ", "DELETE");
		}
		
		@Then("Navigate to plugin installation")
		public void navigate_to_plugin_installation() {
			new Settings(driver).navigateToPlugininstallation();
		}
		
		@Then("Click register new plugin Button")
		public void click_register_new_plugin_Button() throws Exception {
			Waithelper.waitForSmalltime();
		    util.clickOnButtonById("btn_generate_credentials");
		}
		
		@Given("Enter plugin related details")
		public void enter_plugin_related_details() throws Exception {
		  util.enterNameInTextboxById("plugin_name",Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Pluginname"));
		  new Settings(driver).selectRole(Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Role"));  
		}
		
		@Given("Set domain and subdomain")
		public void set_domain_and_subdomain() throws Exception {
			 new Settings(driver).setDomain(Testbase.prop.getProperty("Settings_General_Setting_Domain"));
			 new Settings(driver).setSubDomain(Testbase.prop.getProperty("Settings_General_Setting_Subdomain"));
			 util.scrollpage("down");
		}
		
		@Given("Set plugin execution mode")
		public void set_plugin_execution_mode() throws Exception {
		    new Settings(driver).setPluginExecutionMode();
		    new Settings(driver).setDomainForPLuginExecutionMode(Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Plugin_Domain"));
			new Settings(driver).setSubDomainForPluginExecution(Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Plugin_Subdomain"));
		}

		@Given("Set log file properties")
		public void set_log_file_properties() throws Exception {
			new Settings(driver).setLogFileProperties();
		}
		
		@Then("Verify newly created plugin")
		public void verify_newly_created_plugin() throws Exception {
		   new Settings(driver).verifyPluginCreation(Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Role"));
		   CommonUtil.screenshot();
		   System.out.println("Plugin : "+Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Pluginname")+" is created successfully......!");
		}

		//**********************Upload New Package***********************//
		
		@Given("Click on {string} tab")
		public void click_on_tab(String string) throws Exception {
		   util.clickOnTab("Packages List");
		}

		@Given("Set Operating System Type")
		public void set_Operating_System_Type() throws Exception {
			util.clickOnButtonById("drp_operating_system");
		    util.selectOptionFromDropdownByName(Testbase.prop.getProperty("Plugin_Installation_Upload_Package_Operating_System_Type"));
		}

		@Given("Set Opearting System Name")
		public void set_Opearting_System_Name() throws Exception {
		    util.clickOnButtonById("drp_os_name");
		    util.selectOptionFromDropdownByName(Testbase.prop.getProperty("Plugin_Installation_Upload_Package_Operating_System_Name"));
		}

		@Given("Set Operating System Version")
		public void set_Operating_System_Version() throws Exception {
		    util.enterNameInTextboxById("os_version",Testbase.prop.getProperty("Plugin_Installation_Upload_Package_Operating_System_Version"));
		}

		@Given("Upload Plugin Package File")
		public void upload_Plugin_Package_File() throws Exception {
		    util.uploadFile("pkg_file",Testbase.prop.getProperty("Plugin_Installation_Upload_Package_File_Name"));
		}

		@Given("Upload Installer File")
		public void upload_Installer_File() throws Exception {
			Waithelper.waitForMediumtime();
			util.uploadFile("installer_file", Testbase.prop.getProperty("Plugin_Installation_Upload_Package_Installer_File_Name"));		
		}

		@Then("Verify uploaded package")
		public void verify_uploaded_package() throws Exception {
		   new Settings(driver).verifyPackageName(Testbase.prop.getProperty("Plugin_Installation_Upload_Package_Operating_System_Version"));
		}

}

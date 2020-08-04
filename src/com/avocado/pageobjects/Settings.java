package com.avocado.pageobjects;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.avacado.Utility.CommonUtil;
import com.avacado.Utility.Waithelper;
import com.avocado.base.Testbase;

public class Settings extends Testbase {
	
//	WebDriver driver;
	//PropertyFIleReader obj= new PropertyFIleReader();
	//Properties properties=obj.getProperty();
	public static Waithelper helper;
	CommonUtil util=new CommonUtil(); 
	public static Logger logger = Logger.getLogger(Settings.class);
	
	public Settings(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);		
	}
	
	@FindBy(xpath="//*[@class='nav-div']//*[contains(@src,'settings')]")
	public WebElement navigationMenu;
	
	@FindBy(xpath="//*[@class='nav-menu-lable' and text()='Settings']")
	public WebElement settingsMenu;
	
	@FindBy(xpath="//*[@class='nav-expand-direction'")
	public WebElement settingDirection;
	
	@FindBy(xpath="//*[contains(@class,'sub-menu')]//*[text()='General Settings']")
	public WebElement generalSettingsOption;
	
	@FindBy(xpath="//*[contains(@class,'sub-menu')]//*[text()='Plugin Installation']")
	public WebElement pluginInstallationOption;
	
	//Orchestrator Address
	@FindBy(xpath="//*[text()='ORCHESTRATOR URL']/..//input")
	public WebElement orchestratorUrlTxtbox;
	
	@FindBy(xpath="//button[@id='btn_save']")
	public WebElement saveBtn;
	
	//Service Account
	@FindBy(xpath="//*[contains(@placeholder,'no. of pico segments')]")
	public WebElement maxPicosegmentsTxtbox;
	
	@FindBy(xpath="//*[contains(@placeholder,'servers')]")
	public WebElement maxServersTxtbox;
	
	@FindBy(xpath="//button[@id='btn_save_picoSegment']")
	public WebElement savePicoSegBtn;

	//Management Properties
	@FindBy(xpath="//*[@placeholder='Type polling interval here']")
	public WebElement pollingIntervalTxtbox;

	@FindBy(xpath="//*[@placeholder='Type polling interval for UI here']")
	public WebElement pollingIntervalUiTxtbox;

	@FindBy(xpath="//*[@class='radioPad__wrapper radioPad__wrapper--selected']//input[@id='ON']")
	public WebElement monitorApplicationHealthOn;

	@FindBy(xpath="//*[@class='radioPad__wrapper radioPad__wrapper--selected']//input[@id='OFF']")
	public WebElement monitorApplicationHealthOff;
	
	@FindBy(xpath="//*[@id='btn_save_prop']")
	public WebElement saveManagemntProperties;

	//Aplication Discovery Only Mode Pending
	
	//Configure Service Now
	@FindBy(xpath="//*[contains(text(),'Configure')]//*[@class='down-arrow mapping']")
	public WebElement configureServiceNowOption;

	@FindBy(xpath="//*[@id='btn_create_config']")
	public WebElement createNewConfigBtn;
	
	//Create New Config
	@FindBy(xpath="//*[@placeholder='Type URL here']")
	public WebElement serviceUrlTxtbox;
	
	@FindBy(xpath="//*[@placeholder='Type userid for the user here']")
	public WebElement serviceUserIdTxtbox;
	
	@FindBy(xpath="//*[@placeholder='Type config namehere']")
	public WebElement serviceConfignameTxtbox;
	
	@FindBy(xpath="//input[(contains(@minlength, '8'))]")
	public WebElement serviceConfigPasswordTxtbox;
	
	@FindBy(xpath="//*[@class='label mandate' and text()='CONFIRM PASSWORD']//following-sibling::input")
	public WebElement serviceConfigConfirmPasswordTxtbox;
	
	@FindBy(xpath="//*[@class='checkmark']")
	public WebElement activateServiceChkbox;
	
	@FindBy(xpath="//*[contains(@class,'sub-domain-field')]//following::div[@class='domain-select']")
	public WebElement domainDropdown;
	
	@FindBy(xpath="//*[contains(@class,'sub-domain-field')]//following::div[@class='domain-select']")
	public WebElement subdomainDropdown;

	@FindBy(xpath="//*[text()='ADD']")
	public WebElement addBtn;
	
	@FindBy(xpath="//*[@class='btn_cancel']")
	public WebElement cancelBtn;
	
	@FindBy(xpath="//*[@id='btn_continue']")
	public WebElement serviceSaveBtn;
	
	@FindBy(xpath="//*[contains(@class,'styles_closeButton')]")
	public WebElement closeBtn;

	@FindBy(xpath="//*[@class='rt-tr-group']")
	private WebElement configlist;
	
	@FindBy(id="drp_operating_system")
	private WebElement operatingsystemdropdown;
	
	@FindBy(id="drp_package")
	private WebElement packagedropdown;
	
	@FindBy(id="host_ip")
	private WebElement hostiptxtbox;
	
	@FindBy(id="user_id")
	private WebElement useridremotemachinetxtbox;
	
	@FindBy(id="in_password")
	private WebElement remotemachinepasswordtxtbox;
	
	@FindBy(id="chk_adpl_logs")
	private WebElement adplogcheckbox;
	
	@FindBy(xpath="//*[text()='INSPECTION']")
	private WebElement inspectionmode;
	
	public void navigateToSetting() throws InterruptedException {
		Waithelper.waitForElement(driver,navigationMenu,70);
		Actions act=new Actions(driver);
		act.moveToElement(navigationMenu).click().perform();
		Waithelper.waitForElement(driver,settingsMenu,70);
		settingsMenu.click();
		generalSettingsOption.click();
		Thread.sleep(1000);
	}
	
	public void navigateToPlugininstallation() {
		Waithelper.waitForElement(driver,navigationMenu,70);
		Actions act=new Actions(driver);
		act.moveToElement(navigationMenu).click().perform();
		Waithelper.waitForElement(driver,settingsMenu,70);
		settingsMenu.click();
		pluginInstallationOption.click();
	}
	
	public void setApplicationDiscoveryMode(String status) throws Exception {	
		if(status.equalsIgnoreCase("ON")) {
			Waithelper.waitForMediumtime();
			driver.findElement(By.xpath(("//*[@class='label mandate' and text()='APPLICATION DISCOVERY MODE']//parent::td//following::div//label[text()='"+status+"']"))).click();
			util.enterNameInTextFieldByPlaceHolder("Enter User ID of the first user",Testbase.prop.getProperty("firstuser"));
			util.enterNameInTextboxById("in_password",Testbase.prop.getProperty("firstuserpassword"));
			util.enterNameInTextFieldByPlaceHolder("Enter User ID of the second user",Testbase.prop.getProperty("seconduser"));
			util.enterNameInTextboxById("cnfrm_password",Testbase.prop.getProperty("seconduserpassword"));
			Waithelper.waitForElement(driver, serviceSaveBtn, 30);
			serviceSaveBtn.click();
		}else if(status.equalsIgnoreCase("OFF")) {
			Waithelper.waitforLongtime();
			driver.findElement(By.xpath(("//*[@class='label mandate' and text()='APPLICATION DISCOVERY MODE']//parent::td//following::div//label[text()='"+status+"']"))).click();
			Waithelper.waitForMediumtime();
			util.clickOnButtonByText("YES");
		}
	}
	
	public void selectRole(String rolename) throws Exception {
		util.clickOnButtonById("drp_role");		
		util.selectOptionFromDropdownByName(Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Role"));
		if(rolename.equalsIgnoreCase("SSO-Client")){
			Waithelper.waitForSmalltime();
			util.clickOnButtonById("drp_signAlgo");
			util.selectOptionFromDropdownByName(Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Algorithmname"));
			util.enterNameInTextboxById("verfication_key","121898dnhdhhd");
		}
		else if(rolename.equalsIgnoreCase("Appmanager")){
			Waithelper.waitForSmalltime();
			util.clickOnButtonById("drp_operating_system");
			
			//Select OS
			Waithelper.waitForElement(driver, operatingsystemdropdown, 20);
			util.selectOptionFromDropdownByName(Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Operating_System"));
			
			//Select Package
			Waithelper.waitForElement(driver, packagedropdown, 20);
			util.clickOnButtonById("drp_package");
			util.selectOptionFromDropdownByName(Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Package"));
			util.scrollpage("down");
			
			//Set Host IP
			Waithelper.waitForElement(driver, hostiptxtbox, 20);
			util.enterNameInTextboxById("host_ip",Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Host_IP"));
			
			//Set userid for the remote machine
			Waithelper.waitForElement(driver, useridremotemachinetxtbox, 20);
			util.enterNameInTextboxById("user_id",Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Remote_Machine_Userid"));
			
			//Set remote machine password
			Waithelper.waitForElement(driver, remotemachinepasswordtxtbox, 20);
			util.enterNameInTextboxById("in_password",Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Remote_Machine_Password"));
		
			util.clickOnButtonById("add_package");
			
			verifyHostname(Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Host_IP"));
			util.scrollpage("down");
		}	
//		else if(rolename.equalsIgnoreCase("Automation-Client") || rolename.equalsIgnoreCase("UI")) {
//			//setDomain(Testbase.prop.getProperty("domain"));
//			//setSubDomain(Testbase.prop.getProperty("subdomain"));
//			//util.clickOnButtonById("btn_show_access_list");
//		}
	}
	
	public void verifyHostname(String ip) throws Exception{
		
		String my_xpath="//*[@class='rt-td']//*[text()='"+ip+"']";
		
		if(driver.findElements(By.xpath(my_xpath)).size()!=0) {
			String ipname=driver.findElement(By.xpath(my_xpath)).getText();
			Assert.assertEquals(ipname, Testbase.prop.getProperty("host_ip"));
			System.out.println("Hostname availbale:"+ip);
		}else {
			throw new Exception("Hostname not availbale "+ ip +" in list");
		}
	}
	
	
	public void setDomain(String domain) throws Exception {
		Waithelper.waitForElement(driver, domainDropdown, 20);
		util.clickOnButtonById("drp_domain");
		util.selectOptionFromDropdownByName(Testbase.prop.getProperty("Settings_General_Setting_Domain"));
		}
	
	public void setDomainForPLuginExecutionMode(String domain) throws Exception {
		//driver.findElement(By.xpath("//*[@class='domain-select' and not(contains(text(),'sub'))]")).click();
		Waithelper.waitForElement(driver, domainDropdown, 20);
		util.clickOnButtonById("drp_execution_domain");
		util.selectOptionFromDropdownByName(Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Plugin_Domain"));
		}
	
	
	public void setSubDomain(String subdomain) throws Exception {
		Waithelper.waitForElement(driver, subdomainDropdown, 20);
		util.clickOnButtonById("drp_sub_domain");
		util.selectOptionFromDropdownByName(Testbase.prop.getProperty("Settings_General_Setting_Subdomain"));
		util.clickOnButtonById("btn_show_access_list");
	}
	
	public void setSubDomainForPluginExecution(String subdomain) throws Exception {
		Waithelper.waitForElement(driver, subdomainDropdown, 20);;
		util.clickOnButtonById("execution_sub_domain");
		util.selectOptionFromDropdownByName(Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Plugin_Subdomain"));
		util.scrollpage("down");
	}
	
	public void setSubDomainForPluginExecutionMode(String subdomain) throws Exception {
		driver.findElement(By.xpath("//*[@class='domain-select' and contains(text(),'sub-domain')]")).click();
		Waithelper.waitForElement(driver, subdomainDropdown, 20);
		util.selectOptionFromDropdownByName(Testbase.prop.getProperty("subdomain"));
	}
	
	public void setSignAlgorithm(String algoname) throws Exception {
		driver.findElement(By.id("drp_signAlgo")).click();
		Waithelper.waitForSmalltime();
		List<WebElement> alogoriths=driver.findElements(By.className("dd-list-item"));
		System.out.println(alogoriths);
		Iterator<WebElement> itr = alogoriths.iterator();
		while (itr.hasNext()) {
			WebElement click = itr.next();
			String text = click.getText().trim();
			if (text.equals(algoname)) {
				click.click();
				break;
		}else {
			throw new Exception("Algorithm "+ algoname +" not selected");
		}
	}
}

	public static void verifyConfigList(String configname) throws Exception {
		
		String xpath="//*[@class='rt-tr-group']//*[text()='"+configname+"']";
		
		if ((driver.findElements(By.xpath(xpath)).size()!=0)){
			String text=driver.findElement(By.xpath(xpath)).getText();
			System.out.println("Configname is "+configname+"");
		}else {
			throw new Exception("Unbale to find configname "+configname+" ");
		}
	}
	
	public void setPluginExecutionMode() throws Exception {
		Waithelper.waitForMediumtime();
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",inspectionmode);
		util.scrollpage("Down");
		//util.clickOnButtonById("INSPECTION");
	}
	
	public void setLogFileProperties() throws Exception {
		Waithelper.waitForSmalltime();
		//util.clickOnButtonById("chk_adpl_logs");
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",adplogcheckbox);
		util.enterNameInTextboxById("max_file_size", Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_Max_File_Size"));
		util.enterNameInTextboxById("file_prefix", Testbase.prop.getProperty("Plugin_Installation_Register_Plugin_File_Prefix"));
		util.clickOnButtonById("btn_install");	
	}
	
	public void verifyPluginCreation(String role) throws Exception {
		if(role.equalsIgnoreCase(Testbase.prop.getProperty("role")))
		{
			util.checkValidationPopUp("Password will only be display on this screen, please note the password for future reference.");
			closeBtn.click();
			verfiyPlugin(Testbase.prop.getProperty("pluginname"));
		}else {
			System.out.println("Appmanager Plugin : "+Testbase.prop.getProperty("pluginname")+" Created successfully");
		}
	}
	
	public static void verfiyPlugin(String pluginname) throws Exception {
		String my_xpath="//*[@class='rt-td']//*[text()='"+pluginname+"']";
		
		if(driver.findElements(By.xpath(my_xpath)).size()!=0) {
			driver.findElement(By.xpath(my_xpath)).getText();
			System.out.println("Newly Created Plugin:" +pluginname);
		}else {
			throw new Exception("PLugin "+pluginname+" not created");
		}
	}
	
	public static void verfiyPluginForAppmanager(String pluginname) throws Exception {
		String my_xpath="//*[@class='rt-td']//*[text()='"+pluginname+"']";
		
		if(driver.findElements(By.xpath(my_xpath)).size()!=0) {
			driver.findElement(By.xpath(my_xpath)).getText();
			System.out.println("Newly Created Plugin:" +pluginname);
		}else {
			throw new Exception("Plugin "+pluginname+" not created");
		}
	}
	
	public void verifyPackageName(String packagename) throws Exception {
		
		String my_xpath="//*[@class='rt-tbody']//*[text()='"+packagename+"']";
		
		if(driver.findElements(By.xpath(my_xpath)).size()!=0) {
			driver.findElement(By.xpath(my_xpath)).getText();
			System.out.println("Newly created package with operrating system version as "+packagename+" created");
		}else {
			throw new Exception("Operating system version as "+packagename+" not available");
		}
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


package com.avocado.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avacado.Utility.CommonUtil;
import com.avocado.base.Testbase;

public class Loginpage extends Testbase {
	
	CommonUtil util=new CommonUtil(); 
	
	public Loginpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);		
	}

	@FindBy(xpath="//*[@id='username']")
	public WebElement usernameTxt;
	
	@FindBy(xpath="//*[@id='password']")
	public WebElement passwordTxt;
	
	@FindBy(xpath="//*[@id='loginBtn']")
	public WebElement loginBtn;
	
	public String validateLoginpageTitle() {
		return driver.getTitle();
	}
	
	public void setUsername(String uname) {
		usernameTxt.clear();
		usernameTxt.sendKeys(uname);
	}
	
	public void setPassword(String passwd) {
		passwordTxt.clear();
		passwordTxt.sendKeys(passwd);
	}

	public void login() throws Exception {
	util.enterNameInTextFieldByPlaceHolder("Username", Testbase.prop.getProperty("username"));
    util.enterNameInTextFieldByPlaceHolder("Password", Testbase.prop.getProperty("password"));
	}

}

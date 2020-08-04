package com.avacado.Utility;

import java.awt.AWTException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.avocado.base.Testbase;

public class CommonUtil extends Testbase{
	
	static Logger logger = Logger.getLogger(CommonUtil.class);
	Waithelper helper=new Waithelper(driver);
	
	/**
	 * @Description:- Used to enter value in text field by placeholder
	 * @Used_In:- Login Page UserName and Password
	 * @author Mayur
	 * @throws Exception 
	 */
	
	public void enterNameInTextFieldByPlaceHolder(String placeholder, String text) throws Exception {
		String myxpath = "//*[@placeholder='" + placeholder + "']";
		if ((driver.findElements(By.xpath(myxpath))).size() != 0) {
			Waithelper.waitForMediumtime();
			clearTextFromTextFieldByPlaceHolder(placeholder);
			logger.info("Entering text into field with placeholder "+ placeholder);
			driver.findElement(By.xpath(myxpath)).click();
			driver.findElement(By.xpath(myxpath)).sendKeys(text);			
		} else {
			throw new Exception("Unable to locate text field with placeholder " + placeholder);
		}
		
	}

	/**
	 * @Description:- Used to clear value in text field by placeholder
	 * @Used_In:- Login Page UserName and Password
	 * @author Mayur
	 * @throws Exception 
	 */

	public void clearTextFromTextFieldByPlaceHolder(String placeholder) throws Exception {
		
		String myxpath = "//*[@placeholder='" + placeholder + "']";
		
		
		if ((driver.findElements(By.xpath(myxpath))).size() != 0) {
			logger.info("Clearing text from field with placeholder "+ placeholder);
			driver.findElement(By.xpath("//*[@placeholder='" + placeholder + "']")).clear();
		} else {
			throw new Exception("Unable to locate text field with placeholder " + placeholder);
		}
		
	}
	
	/**
	 * @Description:- Used to clear value in text field by id
	 * @Used_In:- All Test Cases
	 * @author Mayur
	 * @throws Exception 
	 */

	public void clearTextFromTextFieldById(String id) throws Exception {
		
		String myxpath = "//*[@id='" + id + "']";
		
		
		if ((driver.findElements(By.xpath(myxpath))).size() != 0) {
			
			logger.info("Clearing text from field with placeholder "+ id);
			driver.findElement(By.xpath("//*[@id='" + id + "']")).clear();
			
		} else {

			throw new Exception("Unable to locate text field with id " + id);
		}
		
	}
	
	
	/**
	 * @throws Exception 
	 * @function: This method is used to click on button by button-text
	 * @Author: Mayur
	 * @Version: 1.0
	 */
	public void clickOnButtonByText(String text) throws Exception {

		String myxpath = "//*[text()='" + text + "']";
		
		if ((driver.findElements(By.xpath(myxpath))).size() != 0) {
			
			driver.findElement(By.xpath(myxpath)).click();
			logger.info("Clicked on " + text + " button");
		} else {

			throw new Exception("Unable to locate the "+ text +" button");
		}

	}
	
	/**
	 * @throws Exception 
	 * @function: This method is used to click on button by id
	 * @Author: Mayur
	 * @Version: 1.0
	 */
	
	public void clickOnButtonById(String id) throws Exception {
		
		String my_xpath="//*[@id='"+id+"']";
		
		if((driver.findElements(By.xpath(my_xpath)).size()!=0)){
			Waithelper.waitForSmalltime();
			driver.findElement(By.xpath(my_xpath)).click();
			logger.info("Clicked on "+id+" buttton");
		}else {
			throw new Exception("Unable to locate the "+id+" button");
		}
	}
	

	/**
	 * @throws Exception 
	 * @function: This method is used to enter text in textbox with respective to text(name=username,lastname=surname)
	 * @Author: Mayur
	 * @Version: 1.0
	 */
	
	public void enterTextInTextboxByText(String name,String placeholder,String text) throws Exception {
		
		String my_xpath="//*[@class='label mandate' and text()='"+name+"']//following-sibling::input[@placeholder='"+placeholder+"']";
		
		if((driver.findElements(By.xpath(my_xpath)).size()!=0)){
			logger.info("Entering text into field with name "+ name);
			driver.findElement(By.xpath(my_xpath)).clear();
			driver.findElement(By.xpath(my_xpath)).sendKeys(text);
		}else {
			throw new Exception("Unable to locate element with "+name+" and placeholder as "+placeholder+"");
		}
	}
			
	public void checkValidationPopUp(String validString) throws Exception {
		
		String my_xpath="//*[@class='delete-header']//*[text()='"+validString+"']";
		
		if ((driver.findElements(By.xpath(my_xpath)).size()!=0)){
			//Waithelper.waitForSmalltime();
			String text=driver.findElement(By.xpath(my_xpath)).getText();
			logger.info("Validation of "+validString+" popup done successfully");
		}
		else {
			throw new Exception("Unable to display "+validString+" popup");
		}
	}
	
	/**
	 * @throws Exception 
	 * @function: This method is used to take screenshot with scenario name
	 * @Author: Mayur
	 * @Version: 1.0
	 */
	
	public static void screenshot()
	{
	try {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    //FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "//screenshots/" + random + ".png"));
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	    FileUtils.copyFile(source, new File("./ScreenShots/"+timeStamp+".png"));
	    System.out.println("ScreenShot Taken");
	} 
	catch (Exception e) 
	{
	    System.out.println("Exception while taking ScreenShot "+e.getMessage());
	}
	}
	
	/**
	 * @Description:- Used to scroll page up or down
	 * @Used_In:- All Testcases	
	 * @author Mayur
	 * @throws Exception
	 */

	public void scrollpage(String direction) throws Exception {		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		if(direction.equalsIgnoreCase("UP"))
		{	
			js.executeScript("window.scrollBy(0,-250);", "");
			
		}else if(direction.equalsIgnoreCase("DOWN")) {
			
			js.executeScript("window.scrollBy(0,250);", "");
		}
	}
	
	/**
	 * @function This method is used to right click on element by label
	 * @UsedIn:- Configuration Service Edit/Delete
	 * @author: Mayur
	 * @throws Exception 
	 */
	public void rightClickOnElementByLabel(String label) throws Exception {
		
		String myxpath ="//*[@class='rt-td']//*[text()='"+label+"']";
		
		if (driver.findElements(By.xpath(myxpath)).size() != 0) {
			
			Actions action = new Actions(driver);
			WebElement rightClickElement= driver.findElement(By.xpath(myxpath));
	        action.contextClick(rightClickElement).build().perform();
	        logger.info("Right click on Element by "+ label);
		}else {
			
			throw new Exception("Unable to locate element with label "+ label);
		}
		
	}
	
	/**
	 * @Description:- Used to click on confirmation box and related operations of same
	 * @Used_In:- All test cases
	 * @author Mayur
	 * @throws Exception 
	 */
	
	public void clickOnConfirmationboxAndPerformOperation(String operation,String option) throws Exception {
		
		String my_xpath="//*[@class='delete-header']//*[text()='"+operation+"']//following::div//button[text()='"+option+"']";
		
		if((driver.findElements(By.xpath(my_xpath)).size()!=0)){
			Waithelper.waitForSmalltime();
			driver.findElement(By.xpath(my_xpath)).click();
		}else {
			throw new Exception("Confirmation box "+operation+" not found and "+option+" not perform");
		}
	}
	
	/**
	 * @Description:- Used to Click on Navigation bar links such as Dashboard,Policy,Applications,Container Management etc.
	 * @Used_In:- All test cases
	 * @author Mayur
	 * @throws Exception 
	 */

	public void clickOnNavBarLinkByText(String text) throws Exception {
		
		String xpath = "//*[@class='nav-menu-lable' and text()='"+text+"']";
		
		if (driver.findElements(By.xpath(xpath)).size() != 0) {
			
			logger.info("Clicking on link with label "+ text);
			driver.findElement(By.xpath(xpath)).click();
		}else {

			throw new Exception("Unable to locate button with label " + text);
		}
		
	}
	
	/**
	 * @Description:- Used to hover on mosue on elements
	 * @Used_In:- All test cases
	 * @author Mayur
	 * @throws Exception 
	 */
	
	public void mouseHoverOnElement(WebElement element) throws Exception {
		Actions act=new Actions(driver);
		if(element.isDisplayed()==true && element.isEnabled()==true) {
		act.moveToElement(element).click().perform();
		}else {
			throw new Exception("Mosue not hover on element:"+element);
		}
	}
	
	/**
	 * @Description:- Used to verify error message
	 * @Used_In:- All test cases
	 * @author Mayur
	 * @throws Exception 
	 */
	
	public void verifyErrorMessage(String message,String expected_msg) throws Exception {
		
		String actual_messsage="//*[@class='error-div' and text()="+message+"]";	
		String expected_message=expected_msg;
		
		if(driver.findElements(By.xpath(actual_messsage)).size()!=0) {
			Assert.assertEquals(actual_messsage,expected_message);
			System.out.println("Error message validation sucessful");
		}else {
			throw new Exception("Error message validation unsuccessful"+actual_messsage);
		}
	}
	
	/**
	 * @Description:- Used to enter values in textbox by id
	 * @Used_In:- All test cases
	 * @author Mayur
	 * @throws Exception 
	 */
	
	public void enterNameInTextboxById(String id,String text) throws Exception {
		
		String my_xpath="//*[@id='"+id+"']";
		
		if((driver.findElements(By.xpath(my_xpath)).size()!=0)){
			logger.info("Entering text into field with id "+ id);
			Waithelper.waitForSmalltime();
			clearTextFromTextFieldById(id);
			driver.findElement(By.xpath(my_xpath)).sendKeys(text);
		}else {
			throw new Exception("Textbox with id: "+ id +" not available");
		}
	}
	
	/**
	 * @Description:- Used to enter select value from dropdown
	 * @Used_In:- All test cases
	 * @author Mayur
	 * @throws Exception 
	 */
	
	public void selectOptionFromDropdownByName(String name) throws Exception {
		
		String myxpath = "//*[@class='dd-list-item' and text()='"+name+"']";
		
		if(driver.findElements(By.xpath(myxpath)).size() != 0) {
			Waithelper.waitForMediumtime();
			driver.findElement(By.xpath(myxpath)).click();
			logger.info("Clicked on " + name + " in dropdown");
		}else {
			throw new Exception("Unable to the locate the " + name + " option in dropdown");
		}
	}
	
	/**
	 * @throws Exception 
	 * @function: This method is used to click on tab menu
	 * @Author: Mayur
	 * @Version: 1.0
	 */
	
	public void clickOnTab(String tabname) throws Exception {
		
		String my_xpath="//*[@class='tab']//*[text()='"+tabname+"']";
		
		if((driver.findElements(By.xpath(my_xpath)).size()!=0)){
			Waithelper.waitForSmalltime();
			driver.findElement(By.xpath(my_xpath)).click();
			logger.info("Clicked on "+tabname+" tab");
		}else {
			throw new Exception("Tab name: "+tabname+" not available");
		}
	}
	/**
	 * @throws AWTException 
	 * @throws InterruptedException 
	 * @throws Exception 
	 * @function: This method is used to upload file
	 * @Author: Mayur
	 * @Version: 1.0
	 */
	
	public void uploadFile(String buttonid,String filename) throws AWTException, InterruptedException {
		Waithelper.waitForSmalltime();
		WebElement file_input=driver.findElement(By.id(buttonid));
	    file_input.sendKeys(filename);
	    logger.info("File "+filename+" uploaded successfully..!!");
	}
}

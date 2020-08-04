package com.avacado.Utility;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.avocado.base.Testbase;

public class WindowPopup extends Testbase {
	
	public WebDriver driver;
	public static Properties prop;
	
	Robot robot;

	Runnable mlauncher = () -> {
	    try {

	      driver.get(prop.getProperty("baseURL"));
	     } catch (Exception e) {
	          e.printStackTrace();
	       }
	    };
	   
	    public void handleWindowPopup(){
	    	 try {

	    	   Thread mthread = new Thread(mlauncher);
	    	   mthread.start();

	    	  robot.keyPress(KeyEvent.VK_ENTER);
	    	  robot.keyRelease(KeyEvent.VK_ENTER);
	    	 } catch (Exception e1) {
	    	          e1.printStackTrace();
	    	       }
}
}

package com.avacado.Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	private static WebDriver driver;
	private Screenshot screenshot;

	public static String capture(String screenshotname) throws IOException {
		File source=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dest=System.getProperty("user.dir")+"/ErrorScreenShot/"+screenshotname+".png";
		File destination=new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
	}
	
	
}

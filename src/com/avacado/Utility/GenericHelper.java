package com.avacado.Utility;


	import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.avocado.base.Testbase;

	public class GenericHelper {
		
		Testbase test;
		
		public static String takeScreenShot(String aPath) {
			File screenshot = ((TakesScreenshot)Testbase.driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File(aPath));
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
			return aPath;
		}
		
	}


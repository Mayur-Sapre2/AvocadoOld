package com.avacado.Utility;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class PropertyFIleReader {
	
	public Properties getProperty()
	{
		FileInputStream inputStream=null;
		Properties property=new Properties();
		try {
			property.load(new FileInputStream("resources/browser-config.properties"));
		}catch(Exception e) {
			System.out.println("Exception:"+ e);
		}
		return property;
	}
}
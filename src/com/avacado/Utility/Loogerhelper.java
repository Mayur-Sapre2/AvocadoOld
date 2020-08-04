package com.avacado.Utility;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Loogerhelper {
	
	private static boolean root=false;
	
	public static Logger getLogger(Class cls) {
		if(root) {
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure("/home/gslab/eclipse-workspace/Avacado/log4j.properties");
		root=true;
		return Logger.getLogger(cls);
	}
}

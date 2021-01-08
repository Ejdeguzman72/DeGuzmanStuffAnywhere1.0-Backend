package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class MasterLogger {

	public static boolean append = true;
	
	public final static Logger masterLogger = Logger.getLogger(ClassName.class.getName());
	
	public static FileHandler masterLoggerHandler;
	
	public static String path = ".\\log\\master\\master-logger.log";
	
	public void createLog() throws SecurityException, IOException  {
		File masterLogDirectory = new File(path);
		if(!masterLogDirectory.exists()) {
			masterLogDirectory.mkdirs();
			System.out.println("Created directory" + " " + masterLogDirectory);  
		}
		
		masterLoggerHandler = new FileHandler(path,append);
		masterLogger.addHandler(masterLoggerHandler);
	}
}

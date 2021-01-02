package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class UtilityInfoLogger {

	public static boolean append = true;
	
	public final static Logger utilityInfoLogger = Logger.getLogger(ClassName.class.getName(),null);
	
	public final static String path = "C:\\EJ-Projects\\Web-Applications\\DeGUzmanFamilyAPI-Backend\\log\\utility-logs\\utility-info.log";
	
	public static FileHandler utilityFileHandler;
	
	public static void createLog() throws SecurityException, IOException {
		
		File utilityDirectory = new File("C:\\EJ-Projects\\Web-Applications\\DeGUzmanFamilyAPI-Backend\\log\\utility-logs");
		
		if (!utilityDirectory.exists()) {
			utilityDirectory.mkdirs();
			System.out.println("Created directory: " + utilityDirectory);
		}
		
		utilityFileHandler = new FileHandler(path,append);
		
		utilityInfoLogger.addHandler(utilityFileHandler);
	}
}

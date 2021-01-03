package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class AdminPortalLogger {

	public static boolean append = true;
	
	public final static Logger adminPortalLogger = Logger.getLogger(ClassName.class.getName());
	
	public final static String path = "C:\\EJ-Projects\\DeGuzmanFamilyAPI-Backend\\log\\admin-portal-logs\\admin-portal.log";
	
	static FileHandler adminPortalFileHandler;
	
	public static void createLog() throws SecurityException, IOException {
		
		File logDirectory = new File("C:\\\\EJ-Projects\\\\DeGuzmanFamilyAPI-Backend\\\\log\\\\admin-portal-logs");
		
		if (!logDirectory.exists()) {
			logDirectory.mkdirs();
			System.out.println("Created log directory " + " " + logDirectory);
		}
		
		adminPortalFileHandler = new FileHandler(path,append);
		
		adminPortalLogger.addHandler(adminPortalFileHandler);
		
	}
	
}

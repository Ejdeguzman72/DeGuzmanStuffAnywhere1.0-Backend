package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class RunTrackerLogger {
	
	public static final boolean append = true;
	
	public static final Logger runTrackerLogger = Logger.getLogger(ClassName.class.getName());
	
	public static FileHandler runTrackerFileHandler;
	
	public static String path = "C:\\EJ-Projects\\DeGuzmanFamilyAPI-Backend\\log\\run-tracker-logs\\run-tracker.log";
	
	public static void createLog() {
		
		File runTrackerDirectory = new File("C:\\EJ-Projects\\DeGuzmanFamilyAPI-Backend\\log\\run-tracker-logs");
		
		if(!runTrackerDirectory.exists()) {
			runTrackerDirectory.mkdirs();
			System.out.println("Crated Directory: " + runTrackerDirectory);
		}
	}
}

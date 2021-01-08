package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class ExternalFileLogger {

	public static final boolean append = true;
	
	public final static Logger externalFileLogger = Logger.getLogger(ClassName.class.getName());
	
	public static FileHandler externalFileFileHandler;
	
	public static String path = ".\\logs\\external-file-logs\\external-file.log";
	
	public static void createLog() throws SecurityException, IOException {
		File logDirectory = new File(path);
		if (!logDirectory.exists()) {
			logDirectory.mkdirs();
			System.out.println("created directory " + logDirectory);
		}
		externalFileFileHandler =  new FileHandler(path,append);
		externalFileLogger.addHandler(externalFileFileHandler);
		
		System.out.println("Printed directory " + logDirectory);
	}
}

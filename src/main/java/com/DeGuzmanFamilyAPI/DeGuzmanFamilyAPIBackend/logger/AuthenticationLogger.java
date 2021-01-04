package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class AuthenticationLogger {

	static boolean append = true;
	
	public final static Logger authenticationLogger = Logger.getLogger(ClassName.class.getName());
	
	public final static String path = ".\\logs\\authentication-logs\\authentication.log";
	
	public static FileHandler authenticationHandler;
	
	public static void createLog() throws SecurityException, IOException {
		File logDirectory = new File(path);
		
		if (!logDirectory.exists()) {
			logDirectory.mkdirs();
			System.out.println("Created directory " + logDirectory);
		}
		
		authenticationHandler = new FileHandler(path,append);
		
		authenticationLogger.addHandler(authenticationHandler);
		
		// System.out.println("Created log directory" + " " + logDirectory);
	}
}

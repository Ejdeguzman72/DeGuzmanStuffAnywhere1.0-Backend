package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class AutoTrxLogger {

	static boolean append = true;
	
	public final static Logger autoTrxLogger = Logger.getLogger(ClassName.class.getName());
	
	public final static String path = ".\\logs\\auto-transaction-logs\\auto-transaction.log";
	
	public static FileHandler autoTrxHandler;
	
	public static void createLog() throws SecurityException, IOException {
		File logDirectory = new File(".\\logs\\auto-transaction-logs");
		if(!logDirectory.exists()) {
			logDirectory.mkdirs();
			System.out.println("created directory" + " " + logDirectory);
		}
		autoTrxHandler = new FileHandler(path, append);
		autoTrxLogger.addHandler(autoTrxHandler);
		
		System.out.println("Created log directory" + " " + logDirectory);
	}
}

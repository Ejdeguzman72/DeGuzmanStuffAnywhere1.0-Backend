package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


import javassist.bytecode.stackmap.TypeData.ClassName;

public class GeneralTrxLogger {

	public static boolean append = true;
	
	public final static Logger generalTrxLogger = Logger.getLogger(ClassName.class.getName());
	
	public final static String path = ".\\logs\\general-transaction-logs\\general-transaction.log";
	
	public static FileHandler generalTrxFileHandler = null;
	
	public static void createLog() throws SecurityException, IOException {
		
		File logDirectory = new File(path);
		
		if(!logDirectory.exists()) {
			
			logDirectory.mkdirs();
			
			System.out.println("created log directory" + " " + logDirectory);
		}
		
		generalTrxFileHandler = new FileHandler(path,append);
		
		generalTrxLogger.addHandler(generalTrxFileHandler);
	}
}

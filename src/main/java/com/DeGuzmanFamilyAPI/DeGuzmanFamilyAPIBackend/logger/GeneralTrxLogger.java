package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


import javassist.bytecode.stackmap.TypeData.ClassName;

public class GeneralTrxLogger {

	public static boolean append = true;
	
	public final static Logger generalTrxLogger = Logger.getLogger(ClassName.class.getName());
	
	public final static String path = "C:\\EJ-Projects\\DeGUzmanFamilyAPI-Backend\\log\\general-transaction-logs\\general-transaction.log";
	
	static FileHandler generalTrxFileHandler = null;
	
	public static void createLog() throws SecurityException, IOException {
		
		File logDirectory = new File("C:\\EJ-Projects\\DeGUzmanFamilyAPI-Backend\\log\\general-transaction-logs");
		
		if(!logDirectory.exists()) {
			
			logDirectory.mkdirs();
			
			System.out.println("created log directory" + " " + logDirectory);
		}
		
		generalTrxFileHandler = new FileHandler(path,append);
		
		generalTrxLogger.addHandler(generalTrxFileHandler);
	}
}

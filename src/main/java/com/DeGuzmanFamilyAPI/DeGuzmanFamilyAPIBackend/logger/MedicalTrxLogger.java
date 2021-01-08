package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class MedicalTrxLogger {

	public static final boolean append = true;
	
	public final static Logger medicalTrxLogger = Logger.getLogger(ClassName.class.getName());
	
	public static FileHandler medicalTrxFileHandler;
	
	public static String path = ".\\logs\\medical-transaction-logs\\medical-transaction.log";
	
	public static void createLog() throws SecurityException, IOException {
		File logDirectory = new File(path);
		if(!logDirectory.exists()) {
			logDirectory.mkdirs();
			System.out.println("created directory" + " " + logDirectory);
		}
		medicalTrxFileHandler = new FileHandler(path,append);
		medicalTrxLogger.addHandler(medicalTrxFileHandler);
		
//		System.out.println("Created directory " + logDirectory);
	}
}

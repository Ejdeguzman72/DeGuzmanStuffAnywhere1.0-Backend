package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class BooksLogger {

	public static boolean append = true;
	
	public static String path = ".//logs//books";
	
	public static String file = ".//logs//books//book-logger.txt";
	
	public static Logger booksLogger = Logger.getLogger(ClassName.class.getName());
	
	public static FileHandler booksFileHandler;
	
	public static void createLog() throws SecurityException, IOException {
		File logDirectory = new File(path);
		
		if (!logDirectory.exists()) {
			logDirectory.mkdirs();
			System.out.println("Created Directory " + logDirectory);
		}
		
		booksFileHandler = new FileHandler(file,append);
		
		booksLogger.addHandler(booksFileHandler);
	}
}

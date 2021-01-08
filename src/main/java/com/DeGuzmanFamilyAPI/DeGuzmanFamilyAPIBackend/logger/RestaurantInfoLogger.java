package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

import org.jboss.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class RestaurantInfoLogger {

	public static boolean append = true;
	
	public final static Logger restaurantLogger = Logger.getLogger(ClassName.class.getTypeName());
	
	public final static String path = ".\\logs\\restaurant-info-logs\\restaurant-info.log";
	
	public static FileHandler restaurantFileHandler;
	
	public static void createLog() throws SecurityException, IOException {
		
		File restaurantDirectory = new File(path);
		
		if (!restaurantDirectory.exists()) {
			restaurantDirectory.mkdirs();
			System.out.println("Created Directory " + restaurantDirectory);
		}
		
		restaurantFileHandler = new FileHandler(path,append);
		
	}
}

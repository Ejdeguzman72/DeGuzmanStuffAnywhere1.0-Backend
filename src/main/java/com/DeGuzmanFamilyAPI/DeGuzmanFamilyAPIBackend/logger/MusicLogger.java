package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class MusicLogger {

public static boolean append = true;
	
	public final static Logger musicLogger = Logger.getLogger(ClassName.class.getName());
	
	public final static String path = ".\\logs\\music-logs\\music.log";
	
	public static FileHandler musicFileHandler = null;
	
	public static void createLog() throws SecurityException, IOException {
		
		File logDirectory = new File(path);
		
		if(!logDirectory.exists()) {
			
			logDirectory.mkdirs();
			
			System.out.println("created log directory" + " " + logDirectory);
		}
		
		musicFileHandler = new FileHandler(path,append);
		
		musicLogger.addHandler(musicFileHandler);
	}
}

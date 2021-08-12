package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.file_upload_service.AutoTransactionFilesStorageService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.file_upload_service.GeneralTransactionFileStorageService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.file_upload_service.MedicalTransactionFilesStorageService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.file_upload_service.PhotoFilesStorageService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.file_upload_service.VideoFilesStorageService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.AuthenticationLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.AutoTrxLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.BooksLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.CarInfoLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.ExternalFileLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.GeneralTrxLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.MedicalTrxLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.MusicLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.PersonInfoLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.RestaurantInfoLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.UtilityInfoLogger;

@SpringBootApplication
public class DeGuzmanFamilyApiBackendApplication implements CommandLineRunner {
	
	@Resource
	GeneralTransactionFileStorageService generalTrxfilesStorageService;
	
	@Resource
	MedicalTransactionFilesStorageService medicalTrxFilesStorageService;
	
	@Resource
	AutoTransactionFilesStorageService autoTrxFilesStorageService;
	
	@Resource
	PhotoFilesStorageService photosFilesStorageService;
	
	@Resource 
	VideoFilesStorageService videosFilesStorageService;

	public static void main(String[] args) throws SecurityException, IOException {
		SpringApplication.run(DeGuzmanFamilyApiBackendApplication.class, args);
		int port = 8080;
		
		System.out.println("You are using port: " + port);
		
		
		// creates the logs for the application 
		AuthenticationLogger.createLog();
		
		AutoTrxLogger.createLog();
		
		CarInfoLogger.createLog();
		
		ExternalFileLogger.createLog();
		
		GeneralTrxLogger.createLog();
		
		MedicalTrxLogger.createLog();
		
		MusicLogger.createLog();
		
		PersonInfoLogger.createLog();
		
		RestaurantInfoLogger.createLog();
		
		UtilityInfoLogger.createLog();
		
		BooksLogger.createLog();
		
	}
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowCredentials(true).allowedOrigins("*").allowedMethods("*");
            }
        };
    }
	
	@Override
	public void run(String... args) throws Exception {
		generalTrxfilesStorageService.deleteAllGeneralFiles();
		generalTrxfilesStorageService.init();
		
		medicalTrxFilesStorageService.deleteAllMedicalFiles();
		medicalTrxFilesStorageService.init();
		
		autoTrxFilesStorageService.deleteAllAutoFiles();
		autoTrxFilesStorageService.init();
		
		photosFilesStorageService.deleteAllPhotos();
		photosFilesStorageService.init();
		
		videosFilesStorageService.deleteVideoFiles();
		videosFilesStorageService.init();
		
	}
}

package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.file_upload_service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


import org.springframework.core.io.Resource;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotosFilesStorageServiceImpl implements PhotoFilesStorageService {

	private final Path root =  Paths.get("photos-uploads");
	
	@Override
	public void init() {
		try {
			Files.createDirectories(root);
			System.out.println("Created root: " + root);
		} catch (IOException e) {
			throw new RuntimeException("Could not instantiate folder for upload");
		}
	}

	@Override
	public void save(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource =  new UrlResource(file.toUri());
			
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error" + e.getMessage());
		}
	}

	@Override
	public void deleteAllPhotos() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAllPhotos() {
		try  {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Could not load the files");
		}
	}
}

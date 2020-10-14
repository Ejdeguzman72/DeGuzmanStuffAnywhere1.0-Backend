package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.file_upload_controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.file_upload_models.PhotosFileInfo;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.file_upload_service.PhotoFilesStorageService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.message.ResponseMessage;

@RestController
@RequestMapping("/app/photo-uploads")
@CrossOrigin
public class PhotoFilesController {

	@Autowired
	private PhotoFilesStorageService photosFilesStorageService;
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@PathVariable("file") MultipartFile file) {
		String message = "";
		try {
			photosFilesStorageService.save(file);
			
			message = "Uploaded the file successfully" + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}
	
	@GetMapping("/files")
	public ResponseEntity<List<PhotosFileInfo>> getListFiles() {
		List<PhotosFileInfo> photoFileInfos = (List<PhotosFileInfo>) photosFilesStorageService.loadAllPhotos().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder
					.fromMethodName(PhotoFilesController.class, "getPhotoFile",path.getFileName().toString()).build().toString();
			
			return new PhotosFileInfo(filename,url);
		}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(photoFileInfos);
	}
	
	@GetMapping("/files/{filename}")
	@ResponseBody
	public ResponseEntity<Resource> getPhotoFile(@PathVariable String filename) {
		Resource file = (Resource) photosFilesStorageService.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
}

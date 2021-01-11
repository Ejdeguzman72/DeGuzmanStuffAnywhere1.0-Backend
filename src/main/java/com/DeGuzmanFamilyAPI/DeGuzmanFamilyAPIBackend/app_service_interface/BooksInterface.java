package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Books;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

public interface BooksInterface {

	public List<Books> findAllBooksInformation();
	
	public ResponseEntity<Books> findBooksInformationById(@PathVariable int book_id) throws ResourceNotFoundException;
	
	public ResponseEntity<Books> findBookInformationByName(String name);
	
	public Books addBooksInformation(@Valid @RequestBody Books book);
	
	public ResponseEntity<Books> updateBooksInformation(@PathVariable int book_id,
			@Valid @RequestBody Books book);
	
	public Map<Boolean,String> deleteBookInformation(@PathVariable int book_id);
	
	public Map<Boolean, String> deleteAllBookInformation();
	
	public long getBookCount();


}

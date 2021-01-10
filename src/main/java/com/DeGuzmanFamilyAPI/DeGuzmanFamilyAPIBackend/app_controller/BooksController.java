package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Books;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service.BooksService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/app/books")
@CrossOrigin
public class BooksController {

	@Autowired
	BooksService booksService;
	
	@GetMapping("/all")
	public List<Books> getAllBooksInformation() {
		return booksService.findAllBooksInformation();
	}
	
	@GetMapping("/book/{book_id}")
	public ResponseEntity<Books> getBookInformationById(@PathVariable int book_id) throws ResourceNotFoundException {
		return booksService.findBooksInformationById(book_id);
	}
	
	@GetMapping("/book-count") 
	public long getBookCount() {
		return booksService.getBookCount();
	}
	
	@PostMapping("/add-book-information")
	public Books addBookInformation(@Valid @RequestBody Books book) {
		return booksService.addBooksInformation(book);
	}
	
	@PutMapping("/update-book-information/book/{book_id}")
	public ResponseEntity<Books> updateBookInformation(@PathVariable int book_id,
			@Valid @RequestBody Books bookDetails) {
		return booksService.updateBooksInformation(book_id, bookDetails);
	}
	
	@DeleteMapping("/delete-book/book/{book_id}")
	public Map<Boolean,String> deleteBookInformation(@PathVariable int book_id) {
		return booksService.deleteBookInformation(book_id);
	}
	
}

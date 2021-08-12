package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Books;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.BooksRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service.BooksService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/app/books")
@CrossOrigin
public class BooksController {

	@Autowired
	BooksService booksService;

	@Autowired
	BooksRepository booksRepository;

	@GetMapping("/all")
	public List<Books> getAllBooksInformation() {
		return booksService.findAllBooksInformation();
	}

	@GetMapping("/all-books")
	public ResponseEntity<Map<String, Object>> getAllTutorialsPage(@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		try {

			List<Books> books = booksRepository.findAll();

			Pageable paging = PageRequest.of(page, size);

			Page<Books> pageBooks;

			if (name == null) {
				pageBooks = booksRepository.findAll(paging);
			} else {
				pageBooks = booksRepository.findByNameContaining(name, paging);
			}

			books = pageBooks.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("books", books);
			response.put("currentPage", pageBooks.getNumber());
			response.put("totalItems", pageBooks.getTotalElements());
			response.put("totalPages", pageBooks.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/book/{book_id}")
	public ResponseEntity<Books> getBookInformationById(@PathVariable int book_id) throws ResourceNotFoundException {
		return booksService.findBooksInformationById(book_id);
	}

	@GetMapping("/book-count")
	public long getBookCount() {
		return booksService.getBookCount();
	}

	@GetMapping("/find-book-by-name/{name}")
	public ResponseEntity<Books> findBookInformationById(@PathVariable String name) {
		return booksService.findBookInformationByName(name);
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
	public Map<Boolean, String> deleteBookInformation(@PathVariable int book_id) {
		return booksService.deleteBookInformation(book_id);
	}

	@DeleteMapping("/delete-all")
	public Map<Boolean, String> deleteAllBookInformation() {
		return booksService.deleteAllBookInformation();
	}

}

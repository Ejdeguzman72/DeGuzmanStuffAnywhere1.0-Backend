package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Books;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.BooksRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface.BooksInterface;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.BooksLogger;

@Service
public class BooksService implements BooksInterface {

	@Autowired
	BooksRepository booksRepository;
	
	@Override
	public List<Books> findAllBooksInformation() {
		List<Books> list = booksRepository.findAll();
		
		if (list.size() == 0 || list.isEmpty()) {
			BooksLogger.booksLogger.warning("Books list is empty: " + list.size());
		}
		
		return booksRepository.findAll();
	}

	@Override
	public ResponseEntity<Books> findBooksInformationById(@PathVariable int book_id) throws ResourceNotFoundException {
		
		Books books = null;
		
		if (book_id <= 0) {
			BooksLogger.booksLogger.warning("Invalid Book ID Number");
		} else {
			books = booksRepository.findById(book_id)
					.orElseThrow(() -> new ResourceNotFoundException("Cannot find Book with ID Number: " + book_id));	
			BooksLogger.booksLogger.info("Retrieved Book information for ID Number: " + book_id);
		}
		
		return ResponseEntity.ok().body(books);
	}
	
	@Override
	public ResponseEntity<Books> findBookInformationByName(@PathVariable String name) {
		
		Books books = null;
		
		if (name == null || name == "") {
			BooksLogger.booksLogger.warning("Name cannot be nulll");
		} else {
			books = booksRepository.findBookByName(name);
			BooksLogger.booksLogger.info(name + " was found");
		}
		
		return ResponseEntity.ok().body(books);
	}

	@Override
	public Books addBooksInformation(@Valid @RequestBody Books book) {
		if (book.author == "" || book.author == null) {
			BooksLogger.booksLogger.warning("Author is null");
		} else if (book.name == "" || book.name == null) {
			BooksLogger.booksLogger.warning("Name is null");
		} else {
			BooksLogger.booksLogger.info(book.name + " " + "has been added!");
		}
		return booksRepository.save(book);
	}

	@Override
	public ResponseEntity<Books> updateBooksInformation(int book_id, @Valid @RequestBody Books bookDetails) {
		Books book = null;
		try {
			book = booksRepository.findById(book_id)
					.orElseThrow(() -> new ResourceNotFoundException("Book information"));
			book.setAuthor(bookDetails.getAuthor());
			book.setName(bookDetails.getName());
			
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		
		final Books updatedBookDetails = booksRepository.save(bookDetails);
		BooksLogger.booksLogger.info(updatedBookDetails.name + " " + "has been added");
		return ResponseEntity.ok().body(updatedBookDetails);
	}

	@Override
	public Map<Boolean, String> deleteBookInformation(int book_id) {
		
		Map<Boolean,String> response = null;
		
		if (book_id <= 0) {
			BooksLogger.booksLogger.warning("Book ID number is invalid");
		} else {
			BooksLogger.booksLogger.info("Book has been deleted with ID " + book_id);
			booksRepository.deleteById(book_id);
			response = new HashMap<Boolean,String>();
			response.put(Boolean.TRUE,"Deleted");
		}
		return response;
	}
	
	@Override
	public Map<Boolean,String> deleteAllBookInformation() {
		
		List<Books> list = booksRepository.findAll();
		
		Map<Boolean,String> response = new HashMap<>();
		
		booksRepository.deleteAll();
		
		if (list.size() == 0 || list.isEmpty()) {
			response.put(Boolean.TRUE, "All Books Information has been deleted");
			BooksLogger.booksLogger.info("All books have been deleted");
		}
		
		return response;
	}

	@Override
	public long getBookCount() {
		return booksRepository.count();
	}

}

package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books,Integer> {

	@Query(value = "SELECT * FROM book WHERE name = ?1", nativeQuery=true)
	Books findBookByName(String name);
	
	@Query(value = "SELECT * FROM BOOKS ORDER BY NAME ASC",nativeQuery=true)
	Books findAllBooksAlpha();
	
	Page<Books> findAll(Pageable pageable);
	
//	Page<Books> findAllBooksAlpha(Pageable pageable);
	
	Page<Books> findByNameContaining(String name, Pageable pageable);
//	
//	Page<Books> findByNameContaining(String name, Sort sort);
}

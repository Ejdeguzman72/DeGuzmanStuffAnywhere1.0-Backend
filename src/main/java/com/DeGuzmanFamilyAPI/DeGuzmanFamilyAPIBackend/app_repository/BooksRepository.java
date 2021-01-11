package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books,Integer> {

	@Query(value = "SELECT * FROM book WHERE name = ?1", nativeQuery=true)
	Books findBookByName(String name);
}

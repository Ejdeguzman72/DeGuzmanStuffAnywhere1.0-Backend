package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	Users findUserByUsername(String username);
	List<Users> findAll();
	
	@Query(value = "SELECT * FROM USERS WHERE USER_STATUS = 1", nativeQuery=true)
	List<Users> findAllPendingUsers();
}

 package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.RestaurantType;

@Repository
public interface RestaurantTypeRepository extends JpaRepository<RestaurantType, Integer> {
	
	@Query(value = "select * from restaurant_type where descr = ?1", nativeQuery=true)
	public RestaurantType findRestaurantTypeByName(String descr );

}

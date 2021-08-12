package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.RestaurantType;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.RestaurantTypeRepository;

@Service
public class RestaurantTypeService {

	@Autowired
	private RestaurantTypeRepository restaurantTypeRepository;
	
	public List<RestaurantType> findAllRestaurantType() {
		return restaurantTypeRepository.findAll();
	}
	
	public ResponseEntity<RestaurantType> findRestaurantTypeByName(String restaurantDescr) {
		RestaurantType restuarantType = restaurantTypeRepository.findRestaurantTypeByName(restaurantDescr);
		return ResponseEntity.ok().body(restuarantType);
	}
}

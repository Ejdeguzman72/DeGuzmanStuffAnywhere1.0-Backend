package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Restaurant;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface RestaurantInterface {
	
	public List<Restaurant> findAllRestaurants();
	
	public ResponseEntity<Restaurant> findRestaurantById(@PathVariable int restaurantid) throws ResourceNotFoundException;
	
	public Restaurant addRestaurantInformation(@Valid @RequestBody Restaurant restaurant);
	
	public ResponseEntity<Restaurant> updateRestaurantInformation(@PathVariable int restaurantid,
			@Valid @RequestBody Restaurant restaurantDetails) throws ResourceNotFoundException;
	
	public Map<Boolean,String> deleteRestaurantInformation(@PathVariable int restaurantid);
	
	public long getRestaurantCount();
}

package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Restaurant;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.RestaurantRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface.RestaurantInterface;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.RestaurantInfoLogger;

@Service
public class RestaurantService implements RestaurantInterface {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public List<Restaurant> findAllRestaurants() {
		List<Restaurant> list = restaurantRepository.findAll();
		
		if (list.size() == 0 || list.isEmpty()) {
			RestaurantInfoLogger.restaurantLogger.warn("Restaurant List is Empty: " + list.size()); 
		}
		return list;
	}

	@Override
	public ResponseEntity<Restaurant> findRestaurantById(@PathVariable int restaurantid) throws ResourceNotFoundException {
		Restaurant restaurant = restaurantRepository.findById(restaurantid)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find Restaurant"));
		if (restaurantid == 0) {
			RestaurantInfoLogger.restaurantLogger.warn("Restaurant ID Number is null");
		} else {
			RestaurantInfoLogger.restaurantLogger.info("Restaurant ID Number: " + restaurantid + " " + "has been retrieved");
		}
		return ResponseEntity.ok().body(restaurant);
	}

	public Restaurant addRestaurantInformation(@Valid Restaurant restaurant) {
		if (restaurant.address == "" || restaurant.address == null) {
			RestaurantInfoLogger.restaurantLogger.warn("Restaurant address is null");
		} else if (restaurant.city == "" || restaurant.city == null) {
			RestaurantInfoLogger.restaurantLogger.warn("Restaurant city is null");
		} else if (restaurant.name == "" || restaurant.name == null) {
			RestaurantInfoLogger.restaurantLogger.warn("Restaurant name is null");
		} else if (restaurant.state == "" || restaurant.state == null) {
			RestaurantInfoLogger.restaurantLogger.warn("Restaruant state is null");
		} else if (restaurant.type == "" || restaurant.type == null) {
			RestaurantInfoLogger.restaurantLogger.warn("Restaurant type is null");
		} else if (restaurant.zip == "" || restaurant.zip == null) {
			RestaurantInfoLogger.restaurantLogger.warn("Restaurant zip is null");
		} else {
			RestaurantInfoLogger.restaurantLogger.info("Restaurant Info has been added: " + restaurant.name);
		}
		return restaurantRepository.save(restaurant);
	}

	@Override
	public ResponseEntity<Restaurant> updateRestaurantInformation(int restaurantid,
			@Valid Restaurant restaurantDetails) throws ResourceNotFoundException {
		Restaurant restaurant = restaurantRepository.findById(restaurantid)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find restaurant with id + " + restaurantid));
		try {
			restaurant.setAddress(restaurantDetails.getAddress());
			restaurant.setCity(restaurantDetails.getCity());
			restaurant.setName(restaurantDetails.getName());
			restaurant.setState(restaurantDetails.getState());
			restaurant.setZip(restaurantDetails.getZip());
			restaurant.setType(restaurantDetails.getType());
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		final Restaurant updatedRestaurantDetails = restaurantRepository.save(restaurant);
		RestaurantInfoLogger.restaurantLogger.info("Restaurant info has been updated: " + updatedRestaurantDetails.name);
		return ResponseEntity.ok().body(updatedRestaurantDetails);
	}

	@Override
	public Map<Boolean, String> deleteRestaurantInformation(@PathVariable int restaurantid) {
		
		if (restaurantid == 0) {
			RestaurantInfoLogger.restaurantLogger.warn("Restaurant ID Number is invalid");
		} else {
			restaurantRepository.deleteById(restaurantid);
		}
		Map<Boolean,String> response = new HashMap<>();
		response.put(Boolean.TRUE, "deleted");			
		return response;
	}

	@Override
	public long getRestaurantCount() {
		return restaurantRepository.count();
	}


}

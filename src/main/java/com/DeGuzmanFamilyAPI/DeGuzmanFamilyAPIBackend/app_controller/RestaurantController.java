package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Restaurant;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.RestaurantRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service.RestaurantService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/app/restaurants")
@CrossOrigin
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/all")
	public List<Restaurant> getAllRestaurantInformation() {
		return restaurantService.findAllRestaurants();
	}
	
	@GetMapping("/restaurant/{restaurantid}")
	public ResponseEntity<Restaurant> getRestaurantInformationById(@PathVariable int restaurantid) throws ResourceNotFoundException {
		return restaurantService.findRestaurantById(restaurantid);
	}
	
	@PostMapping("/add-restaurant-information")
	public Restaurant addRestaurantInformation(@Valid @RequestBody Restaurant restaurant) {
		return restaurantService.addRestaurantInformation(restaurant);
	}
	
	@PutMapping("/update-restaurat/{restaurantid}")
	public ResponseEntity<Restaurant> updateRestaurantInformation(@PathVariable int restaurantid,
			@Valid @RequestBody Restaurant restaurantDetails) throws ResourceNotFoundException {
		return restaurantService.updateRestaurantInformation(restaurantid, restaurantDetails);
	}
	
	@DeleteMapping("/delete-restaurant/{restaurantid}")
	public Map<Boolean,String> deleteRestaurantInformation(@PathVariable int restaurantid) {
		return restaurantService.deleteRestaurantInformation(restaurantid);
	}
	
	@GetMapping("/restaurant-count")
	public long getRestaurantCount() {
		return restaurantService.getRestaurantCount();
	}
}

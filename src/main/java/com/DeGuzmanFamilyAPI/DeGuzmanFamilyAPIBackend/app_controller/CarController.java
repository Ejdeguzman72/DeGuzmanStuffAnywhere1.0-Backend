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

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Car;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service.CarService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

@RestController
@CrossOrigin
@RequestMapping("/app/cars")
public class CarController {

	@Autowired
	private CarService carService;
	
	@RequestMapping("/all")
	public List<Car> getAllCars() {
		return carService.findAllCarInformation();
	}
	
	@RequestMapping("/car/{carid}")
	public ResponseEntity<Car> findCarInformationByIdController(@PathVariable long carid) throws ResourceNotFoundException {
		return carService.findCarInformationById(carid);
	}
	
	@PostMapping("/add-a-car")
	public Car addCarInformationController(@Valid @RequestBody Car car) {
		return carService.addCarInformation(car);
	}
	
	@PutMapping("/car/{carid}")
	public ResponseEntity<Car> updateCarInformationController(@PathVariable long carid,
			@Valid @RequestBody Car carDetails) {
		return carService.updateCarInformation(carid, carDetails);
	}
	
	@DeleteMapping("/delete-car-information")
	public Map<String,Boolean> deleteCarInformation(@PathVariable long carid) {
		return carService.deleteCarInformation(carid);
	}
	
	@GetMapping("/count-of-cars")
	public long getCountOfCars() {
		return carService.getCountofCars();
	}
}

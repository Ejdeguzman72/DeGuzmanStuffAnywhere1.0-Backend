package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Car;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.CarRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface.CarInterface;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.CarInfoLogger;

@Service
public class CarService implements CarInterface {

	@Autowired
	private CarRepository carRepository;
	
	public List<Car> findAllCarInformation() {
		
		List<Car> list = carRepository.findAll();
		
		if (list.size() == 0 || list.isEmpty()) {
			CarInfoLogger.carInfoLogger.warning("Car Info List is Empty: " + list.size());
		}
		
		return carRepository.findAll();
	}
	
	public ResponseEntity<Car> findCarInformationById(@PathVariable long carid) throws ResourceNotFoundException {
		Car car = carRepository.findById(carid)
				.orElseThrow(() -> new ResourceNotFoundException("Car is not found with the id of " + carid));
		
		if (car == null) {
			CarInfoLogger.carInfoLogger.warning("Car ID Numberis null");
		}
		
		else {
			CarInfoLogger.carInfoLogger.info("Car Info for " + car.make + " " + car.model + " " + "was retrieved");
		}
		
		return ResponseEntity.ok().body(car);
	}
	
	public Car addCarInformation(@Valid @RequestBody Car car) {
		if (car.capacity == 0) {
			CarInfoLogger.carInfoLogger.warning("Car capacity is empty");
		} else if (car.make == "" || car.model == null) {
			CarInfoLogger.carInfoLogger.warning("Car make is empty");
		} else if (car.model == "" || car.model == null) {
			CarInfoLogger.carInfoLogger.warning("Car model is empty");
		} else if (car.transmission == "" || car.transmission == null) {
			CarInfoLogger.carInfoLogger.warning("Car transmission is empty");
		} else {
			CarInfoLogger.carInfoLogger.info("Car Information has been added: " + car.make + " " + car.model);
		}
		return carRepository.save(car);
	}
	
	public ResponseEntity<Car> updateCarInformation(@PathVariable long carid,
			@Valid @RequestBody Car carDetails) {
		Car car = null;
		try {
			car = carRepository.findById(carid)
					.orElseThrow(() -> new ResourceNotFoundException("Cannot find car with ID: " + carid));
			car.setCapactity(carDetails.getCapactity());
			car.setMake(carDetails.getMake());
			car.setModel(carDetails.getModel());
			car.setTransmission(carDetails.getTransmission());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		final Car updatedCarDetails = carRepository.save(car);
		CarInfoLogger.carInfoLogger.info("Car Info has been updated: " + updatedCarDetails.make + " " + updatedCarDetails.model);
		return ResponseEntity.ok().body(car);
	}
	
	public Map<String,Boolean> deleteCarInformation(@PathVariable long carid) {
		
		carRepository.deleteById(carid);
		
		Map<String,Boolean> response = new HashMap<>();
		
		response.put("deleted", Boolean.TRUE);
		
		CarInfoLogger.carInfoLogger.info("Car Info for ID Number: " + carid + " " + "has been deleted");
		
		return response;
	}
	
	public long getCountofCars() {
		return carRepository.count();
	}

}

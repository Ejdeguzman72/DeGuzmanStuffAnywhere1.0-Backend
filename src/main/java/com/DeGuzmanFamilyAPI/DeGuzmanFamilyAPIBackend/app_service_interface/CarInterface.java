package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Car;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

public interface CarInterface {

	public List<Car> findAllCarInformation();
	
	public ResponseEntity<Car> findCarInformationById(@PathVariable long carid) throws ResourceNotFoundException;
	
	public Car addCarInformation(@Valid @RequestBody Car carInformation);
	
	public ResponseEntity<Car> updateCarInformation(@PathVariable long carid,
			@Valid @RequestBody Car carDetails);
	
	public Map<String,Boolean> deleteCarInformation(@PathVariable long carid);
	
	public long getCountofCars();
}

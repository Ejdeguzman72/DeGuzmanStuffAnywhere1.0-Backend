package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Utility;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.UtilityRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface.UtilityServiceInterface;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.UtilityInfoLogger;

@Service
public class UtilityService implements UtilityServiceInterface {

	@Autowired
	private UtilityRepository utilityRepository;
	
	public List<Utility> findAllUtilityInformation() {
		
		List<Utility> list = utilityRepository.findAll();
		
		if (list.size() == 0 || list.isEmpty()) {
			UtilityInfoLogger.utilityInfoLogger.warning("Utility List is empty: " + list.size());
		} else {
			UtilityInfoLogger.utilityInfoLogger.info("Restaurant List: " + list.size());
		}
		
		return utilityRepository.findAll();
	}
	
	public ResponseEntity<Utility> findUtilityInformationById(@PathVariable Long utilityid) throws ResourceNotFoundException {
		
		Utility utility = utilityRepository.findById(utilityid).
				orElseThrow(() -> new ResourceNotFoundException("not found"));
		
		if (utility == null || utilityid == 0) {
			UtilityInfoLogger.utilityInfoLogger.warning("Cannot search invalid criteria!");
		} else {
			UtilityInfoLogger.utilityInfoLogger.info("Utility info has been retrieved for ID " + utilityid);
		}
		
		return ResponseEntity.ok().body(utility);
	}
	
	public Utility addUtilityInformation(@Valid @RequestBody Utility utility) {
		if (utility.dueDate == null || utility.dueDate == "") {
			UtilityInfoLogger.utilityInfoLogger.warning("Utility due date is null");
		} else if (utility.name == null || utility.name == "") {
			UtilityInfoLogger.utilityInfoLogger.warning("Utility name is null");
		} else if (utility.phone == "" || utility.phone == null) {
			UtilityInfoLogger.utilityInfoLogger.warning("Utility phone is null");
		} else if (utility.url == "" || utility.url == null) {
			UtilityInfoLogger.utilityInfoLogger.warning("Utility url is null");
		} else {
			UtilityInfoLogger.utilityInfoLogger.info("Utility Information has been added: " + utility.name);
		}
		return utilityRepository.save(utility);
	}
	
	public ResponseEntity<Utility> updateUtilityInformation(@PathVariable long utilityid,
			@Valid @RequestBody Utility utilityDetails) throws ResourceNotFoundException {
		Utility utility = null;
		try {
			utility = utilityRepository.findById(utilityid)
					.orElseThrow(() -> new ResourceNotFoundException("Not found"));
			utility.setDueDate(utilityDetails.getDueDate());
			utility.setName(utilityDetails.getName());
			utility.setPhone(utilityDetails.getPhone());
			utility.setUrl(utilityDetails.getUrl());			
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		UtilityInfoLogger.utilityInfoLogger.info("Utility Information has been updated : " + utility.name);
		final Utility updatedUtility = utilityRepository.save(utility);
		return ResponseEntity.ok().body(updatedUtility);
		
	}
	
	public Map<String,Boolean> deleteUtilityInformation(@PathVariable long utilityid) {
		if (utilityid == 0) {
			UtilityInfoLogger.utilityInfoLogger.warning("Utility Information with ID Number: " + utilityid + " "  + "is invalid");
		} else {
			UtilityInfoLogger.utilityInfoLogger.info("Utility Information with ID Number: " + utilityid + " " + "has been deleted");
		}
		utilityRepository.deleteById(utilityid);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@Override
	public long findUtilityCount() {
		return utilityRepository.count();
	}
	
}

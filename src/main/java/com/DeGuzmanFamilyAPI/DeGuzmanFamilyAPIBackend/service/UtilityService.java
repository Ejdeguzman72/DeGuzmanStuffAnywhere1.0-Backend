package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service;

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
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.repository.UtilityRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service_interface.UtilityServiceInterface;

@Service
public class UtilityService implements UtilityServiceInterface {

	@Autowired
	private UtilityRepository utilityRepository;
	
	public List<Utility> findAllUtilityInformation() {
		return utilityRepository.findAll();
	}
	
	public ResponseEntity<Utility> findUtilityInformationById(@PathVariable Long utilityid) throws ResourceNotFoundException {
		Utility utility = utilityRepository.findById(utilityid).
				orElseThrow(() -> new ResourceNotFoundException("not found"));
		return ResponseEntity.ok().body(utility);
	}
	
	public Utility addUtilityInformation(@Valid @RequestBody Utility utility) {
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
		final Utility updatedUtility = utilityRepository.save(utility);
		return ResponseEntity.ok().body(updatedUtility);
		
	}
	
	public Map<String,Boolean> deleteUtilityInformation(@PathVariable long utilityid) {
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

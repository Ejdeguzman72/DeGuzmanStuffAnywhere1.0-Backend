package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service_interface;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Utility;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

public interface UtilityServiceInterface {
	
	public List<Utility> findAllUtilityInformation();
	
	public ResponseEntity<Utility> findUtilityInformationById(@PathVariable Long utilityid) throws ResourceNotFoundException;
	
	public Utility addUtilityInformation(@Valid @RequestBody Utility utility);
	
	public ResponseEntity<Utility> updateUtilityInformation(@PathVariable long utilityid,
			@Valid @RequestBody Utility utilityDetails) throws ResourceNotFoundException;
	
	public Map<String,Boolean> deleteUtilityInformation(@PathVariable long utilityid);
	
	public long findUtilityCount();

}

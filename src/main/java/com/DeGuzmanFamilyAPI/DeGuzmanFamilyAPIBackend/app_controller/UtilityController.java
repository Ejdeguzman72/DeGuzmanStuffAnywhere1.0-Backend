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

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Utility;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service.UtilityService;

@RestController
@RequestMapping("app/utility-information")
@CrossOrigin
public class UtilityController {

	@Autowired
	private UtilityService utilityService;
	
	@GetMapping("/all")
	public List<Utility> getAllUtilityInformation() {
		return utilityService.findAllUtilityInformation();
	}
	
	@GetMapping("/utility/{utilityid}")
	public ResponseEntity<Utility> getUtilityInformationById(@PathVariable long utilityid) throws ResourceNotFoundException {
		return utilityService.findUtilityInformationById(utilityid);
	}
	
	@PostMapping("/add-utility-information")
	public Utility addUtilityInfoController(@Valid @RequestBody Utility utility) {
		return utilityService.addUtilityInformation(utility);
	}
	
	@PutMapping("/utility/{utilityid}")
	public ResponseEntity<Utility> updateUtilityInformaton(@PathVariable long utilityid,
			@Valid @RequestBody Utility utilityDetails) throws ResourceNotFoundException {
		return utilityService.updateUtilityInformation(utilityid, utilityDetails);
	}
	
	@DeleteMapping("/utility/{utilityid}")
	public Map<String,Boolean> deleteUtilityInformation(@PathVariable long utilityid) {
		return utilityService.deleteUtilityInformation(utilityid);
	}
	
	@GetMapping("/count-of-utility")
	public long getCountOfUtilityInformation() {
		return utilityService.findUtilityCount();
	}
}

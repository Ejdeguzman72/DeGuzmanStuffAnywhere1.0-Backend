package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.RunTracker;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

public interface RunTrackerServiceInterface {

	public List<RunTracker> findAllRunTrackerInformation();
	
	public ResponseEntity<RunTracker> findRunTrackerInformationById(@PathVariable long runid) throws ResourceNotFoundException;
	
	public RunTracker addRunTrackerInformation(@Valid @RequestBody RunTracker runTracker);
	
	public ResponseEntity<RunTracker> updateRunTrackerInformation(@PathVariable long runid,
			@Valid @RequestBody RunTracker runTrackerDetails);
	
	public Map<String,Boolean> deleteRunTrackerInformation(@PathVariable long runid);
	
	public long findCountOfRunTrackerInformation();
}

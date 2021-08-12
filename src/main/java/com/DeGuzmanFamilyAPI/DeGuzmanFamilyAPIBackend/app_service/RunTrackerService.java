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

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.RunTracker;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.RunTrackerRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface.RunTrackerServiceInterface;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.RunTrackerLogger;

@Service
public class RunTrackerService implements RunTrackerServiceInterface {

	@Autowired
	private RunTrackerRepository runTrackerRepository;
	
	public List<RunTracker> findAllRunTrackerInformation() {
		
		List<RunTracker> list = runTrackerRepository.findAll();
		
		if (list.size() == 0 || list.isEmpty()) {
			RunTrackerLogger.runTrackerLogger.warning("Run Tracker List is Empty: " + list);
		} else {
			RunTrackerLogger.runTrackerLogger.info("Run Tracker List Count: " + list.size());
		}
		return runTrackerRepository.findAll();
	}
	
	public ResponseEntity<RunTracker> findRunTrackerInformationById(@PathVariable long runid) throws ResourceNotFoundException {
		if (runid == 0) {
			RunTrackerLogger.runTrackerLogger.warning("Run Tracker ID Number is invalid");
		} else {
			RunTrackerLogger.runTrackerLogger.info("Retrieved ID Number " + runid);
		}
		RunTracker runTracker = runTrackerRepository.findById(runid)
				.orElseThrow(() -> new ResourceNotFoundException("Not Found"));
		return ResponseEntity.ok().body(runTracker);
	}
	
	public RunTracker addRunTrackerInformation(@Valid @RequestBody RunTracker runTracker) {
		
		if (runTracker.firstname == "" || runTracker.firstname == null) {
			RunTrackerLogger.runTrackerLogger.info("First name cannot be null");
		} else if (runTracker.lastname == "" || runTracker.lastname == null) {
			RunTrackerLogger.runTrackerLogger.warning("Last Name cannot be null");
		} else if (runTracker.runDate == null) {
			RunTrackerLogger.runTrackerLogger.warning("Run date cannot be null");
		} else if (runTracker.runDistance == 0) {
			RunTrackerLogger.runTrackerLogger.warning("Run Tracker distance cannot be null");
		} else if (runTracker.runTime == "" || runTracker.runTime == null) {
			RunTrackerLogger.runTrackerLogger.warning("Run time cannot be null");
		} else {
			RunTrackerLogger.runTrackerLogger.info("Added Run Tracker Information for " + runTracker.firstname + " " + runTracker.lastname);
		}
		return runTrackerRepository.save(runTracker);
	}
	
	public ResponseEntity<RunTracker> updateRunTrackerInformation(@PathVariable long runid,
			@Valid @RequestBody RunTracker runTrackerDetails) {
		RunTracker runTracker = null;
		try {
			runTrackerDetails = runTrackerRepository.findById(runid)
					.orElseThrow(() -> new ResourceNotFoundException("Not Found"));
			
			runTracker.setFirstname(runTrackerDetails.getFirstname());
			runTracker.setLastname(runTrackerDetails.getLastname());
			runTracker.setRunDate(runTrackerDetails.getRunDate());
			runTracker.setRunDistance(runTrackerDetails.getRunDistance());
			runTracker.setRunTime(runTracker.getRunTime());
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		final RunTracker updatedRunTrackerDetails = runTrackerRepository.save(runTracker);
		RunTrackerLogger.runTrackerLogger.info("Run Information has been updated for runid " + runid);
		return ResponseEntity.ok().body(updatedRunTrackerDetails);
	}
	
	public Map<String,Boolean> deleteRunTrackerInformation(@PathVariable long runid) {
		if (runid == 0) {
			RunTrackerLogger.runTrackerLogger.warning("Run ID Number is null/invalid");
		} else {
			RunTrackerLogger.runTrackerLogger.info("Run Tracker with ID Number " + runid + " has been deleted");
		}
		runTrackerRepository.deleteById(runid);
		Map<String,Boolean> response =  new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@Override
	public long findCountOfRunTrackerInformation() {
		return runTrackerRepository.count();
	}
}

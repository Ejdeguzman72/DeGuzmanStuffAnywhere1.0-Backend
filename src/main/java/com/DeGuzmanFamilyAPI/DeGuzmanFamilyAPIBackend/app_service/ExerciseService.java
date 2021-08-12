package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Exercise;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.ExerciseType;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Users;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.ExerciseRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.ExerciseTypeRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.UserRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface.RunTrackerServiceInterface;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

@Service
public class ExerciseService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RunTrackerServiceInterface.class);
	
	@Autowired
	private ExerciseRepository exerciseRepository;
	
	@Autowired
	private ExerciseTypeRepository exerciseTypeRepository;
	
	@Autowired
	private UserRepository usersRepository;
	
	public List<Exercise> findAllExercise() {
		return exerciseRepository.findAll();
	}
	
	public ResponseEntity<Exercise> findExerciseById(@PathVariable int exerciseid) throws ResourceNotFoundException {
		Exercise exercise = exerciseRepository.findById(exerciseid)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find Exercise with ID: " + exerciseid));
		
		return ResponseEntity.ok().body(exercise);
	}
	
	public Exercise addExerciseInformation(@Valid @RequestBody Exercise exercise) throws ResourceNotFoundException {
		String exerciseName = exercise.getExerciseName();
		int reps = exercise.getReps();
		int sets = exercise.getSets();
		double weight = exercise.getWeight();
		Date date = exercise.getDate();
		String exerciseType = exercise.getExerciseType();
		String user = exercise.getUser();
		Exercise newExercise = new Exercise(
				exerciseName, reps, sets, weight, date, exerciseType, user);
		return exerciseRepository.save(newExercise);
	}
	
//	public ResponseEntity<Exercise> updateExerciseInformation(@PathVariable int exerciseid, 
//			@Valid @RequestBody Exercise exerciseDetails) {
//		Exercise exercise = null;
//		try {
//			exercise = exerciseRepository.findById(exerciseid)
//					.orElseThrow(() -> new ResourceNotFoundException("Cannot find exercise with ID: " + exerciseid));
//			exercise.setExerciseName(exerciseDetails.getExerciseName());
//			exercise.setExerciseType(exerciseTypeRepository.findById(exerciseDetails.getExerciseType().getExerciseTypeId())
//					.orElseThrow(() -> new ResourceNotFoundException("Cannot find exercise type resource of ID: " + exerciseDetails.getExerciseType().getExerciseTypeId())));
//			exercise.setReps(exerciseDetails.getReps());
//			exercise.setSets(exerciseDetails.getSets());
//			exercise.setWeight(exerciseDetails.getWeight());
//			exercise.setDate(exerciseDetails.getDate());
//			exercise.setUser(usersRepository.findById(exerciseDetails.getUser().getUserid())
//					.orElseThrow(() -> new ResourceNotFoundException("Cannot find user with ID: " + exerciseDetails.getUser().getUserid())));
//			
//		}
//		
//		catch (ResourceNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		final Exercise updatedExerciseDetails = exerciseRepository.save(exerciseDetails);
//		return ResponseEntity.ok().body(updatedExerciseDetails);
//	}
	
	public Map<String,Boolean> deleteExerciseInfoById(@PathVariable int exerciseid) {
		exerciseRepository.deleteById(exerciseid);
		Map<String,Boolean> response = new HashMap<>();
		response.put("Exercise Info has been deleted", Boolean.TRUE);
		return response;
	}
}

package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Person;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.PersonInfoLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.message.LoggerMessage;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	// returns all Person information in a list
	public List<Person> findAllPersonInformation() throws SecurityException, IOException {
		List<Person> personList = personRepository.findAll();
		System.out.println(personList.size());
		if (personList.isEmpty() || personList.size() == 0) {
			PersonInfoLogger.personInfoLogger.severe(LoggerMessage.GET_ALL_PERSON_INFO_ERROR_MESSAGE);
		}
		
		else {
		PersonInfoLogger.personInfoLogger.info(LoggerMessage.GET_ALL_PERSON_INFO + ":" + " " + personList.size());
			
		}
		return personRepository.findAll();
	}
	
	// based on the pathvariable thrown, this returns the Person object that has the corresponding ID
	public ResponseEntity<Person> findPersonById(@PathVariable Long personid) throws ResourceNotFoundException, SecurityException, IOException {
		Person person = personRepository.findById(personid)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot find"));
		
		if (personid == null) {
			PersonInfoLogger.personInfoLogger.severe(LoggerMessage.GET_PERSON_INFO_BY_ID_ERROR_MESSAGE + " " + personid + " " + ":" + person);
		}
		
		else {
			PersonInfoLogger.personInfoLogger.info(LoggerMessage.GET_PERSON_INFO_BY_ID + personid + " " + "," + " " + person.firstname + " " + person.lastname);
		}
		return ResponseEntity.ok().body(person);
	}
	
	// creates an Person object based off the fields that are filled.
	public Person addPersonInformation(@Valid @RequestBody Person person) throws SecurityException, IOException {
		Person personInfo = personRepository.save(person);
		
		if (personInfo == null) {
			PersonInfoLogger.personInfoLogger.warning(LoggerMessage.ADD_PERSON_INFO_ERROR_MESSAGE);
		}
		
		else {
			PersonInfoLogger.personInfoLogger.info(LoggerMessage.ADD_PERSON_INFO + " " + personInfo.firstname + " " + personInfo.getLastname());
		}
		System.out.println(person);
		return personInfo;
	}
	
	// updates the Person based on the id number entered. Once the fields are updated, then a new Auto
		// Transaction object is created.
	public ResponseEntity<Person> updatePersonInformation(@PathVariable Long personid,
			@Valid @RequestBody Person personDetails) throws SecurityException, IOException {
		Person person = null;
		try {
			person = personRepository.findById(personid)
					.orElseThrow(() -> new ResourceNotFoundException("Not Found"));
			person.setBirthdate(personDetails.getBirthdate());
			person.setEmail(personDetails.getEmail());
			person.setFirstname(personDetails.getFirstname());
			person.setLastname(personDetails.getLastname());
			person.setPhone(personDetails.getPhone());
			
		}
		catch (ResourceNotFoundException e) {
			e.printStackTrace();
			if (personid == null || personid == 0) {
				PersonInfoLogger.personInfoLogger.severe(LoggerMessage.UPDATE_PERSON_INFO_ERROR_MESSAGE + ":" + " " + "Invalid ID / Null ID " + personid);
			}
		}
		final Person updatedPersonInfo = personRepository.save(person);
		PersonInfoLogger.personInfoLogger.info(LoggerMessage.UPDATE_PERSON_INFO + ":" + " " + "Person ID No: " + updatedPersonInfo.personid + " " + updatedPersonInfo.firstname + " " + updatedPersonInfo.lastname);
		return ResponseEntity.ok().body(updatedPersonInfo);
	}
	
	public Map<String,Boolean> deletePersonInformation(@PathVariable Long personid) throws SecurityException, IOException {
		personRepository.deleteById(personid);
		
		if (personid == null || personid == 0) {
			PersonInfoLogger.personInfoLogger.severe(LoggerMessage.DELETE_PERSON_INFO_ERROR_MESSAGE + ": " + personid);
		} 
		
		else {
			PersonInfoLogger.personInfoLogger.info(LoggerMessage.DELETE_PERSON_INFO + ": " + personid);
		}
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	// returns the count of person information  that is stored
	public long getCountOfPersonInformation() {
		return personRepository.count();
	}
}

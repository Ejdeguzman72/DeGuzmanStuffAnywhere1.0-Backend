package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service_interface;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Person;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

public interface ContactServiceInterface {
	
	public List<Person> findAllPersonInformation() throws SecurityException, IOException;
	
	public ResponseEntity<Person> findPersonById(@PathVariable Long personid) throws ResourceNotFoundException, SecurityException, IOException;
	
	public Person addPersonInformation(@Valid @RequestBody Person person) throws SecurityException, IOException;
	
	public ResponseEntity<Person> updatePersonInformation(@PathVariable Long personid,@Valid @RequestBody Person personDetails) throws SecurityException, IOException;
	
	public Map<String,Boolean> deletePersonInformation(@PathVariable Long personid) throws SecurityException, IOException;
	
	public long getCountOfPersonInformation();
}

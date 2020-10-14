package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service_interface;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.AutoTransaction;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

public interface AutoTransactionInterface {

	public List<AutoTransaction> findAllAutoTransactionInformation();
	
	public ResponseEntity<AutoTransaction> findAutoTranasctionInformationById(@PathVariable Long autoTransactionId) throws ResourceNotFoundException;
	
	public AutoTransaction addAutoTransactionInformation(@Valid @RequestBody AutoTransaction autoTransaction);
	
	public ResponseEntity<AutoTransaction> updateTransactionInformation(@PathVariable Long autoTransactionId,
			@Valid @RequestBody AutoTransaction autoTransactionDetails);
	
	public Map<String,Boolean> deleteAutoTransactionInformation(@PathVariable Long autoTransactionId);
	
	public long getCountOfAutoTransactions();
}

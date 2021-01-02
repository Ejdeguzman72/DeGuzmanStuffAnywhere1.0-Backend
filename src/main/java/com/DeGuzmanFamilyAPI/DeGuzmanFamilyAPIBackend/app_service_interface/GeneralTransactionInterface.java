package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.GeneralTransaction;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

public interface GeneralTransactionInterface {

	public List<GeneralTransaction> findAllTransactionInformation();
	
	public ResponseEntity<GeneralTransaction> findTransactionInformationByID(@PathVariable long transactionid) throws ResourceNotFoundException;
	
	public GeneralTransaction addTransactionInformation(@Valid @RequestBody GeneralTransaction transaction);
	
	public ResponseEntity<GeneralTransaction> updateTransactionInformation(@PathVariable Long transactionid, @Valid @RequestBody GeneralTransaction transactionDetails);
	
	public Map<String,Boolean> deleteTransactionInformation(@PathVariable Long transactionid);
	
	public long findCountOfGeneralTransaction();
}

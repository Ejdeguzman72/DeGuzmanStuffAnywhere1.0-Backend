package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.MedicalTransaction;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

public interface MedicalTransactionInterface {

	public List<MedicalTransaction> findAllMedicalTransactionInformation();
	
	public ResponseEntity<MedicalTransaction> findMedicalTransactionInformationById(@PathVariable Long medicalTransactionId) throws ResourceNotFoundException;
	
	public MedicalTransaction addMedicalTransactionInformation(@Valid @RequestBody MedicalTransaction medicalTransaction);
	
	public ResponseEntity<MedicalTransaction> updateMedicalTransaction(@PathVariable Long medicalTransactionId,@Valid @RequestBody MedicalTransaction medicalTransactionDetails);
	
	public Map<String,Boolean> deleteMedicalTraansactionInformation(@PathVariable Long medicalTransactionId);
	
	public long getCountOfMedicalTransactions();
}

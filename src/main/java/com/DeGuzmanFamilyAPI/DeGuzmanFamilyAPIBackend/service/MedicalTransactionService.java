package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.MedicalTransaction;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.MedicalTrxLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.message.LoggerMessage;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.repository.MedicalTransactionRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service_interface.MedicalTransactionInterface;

@Service
public class MedicalTransactionService implements MedicalTransactionInterface {

	@Autowired
	private MedicalTransactionRepository medicalTransactionRepository;
	
	// returns the Medical transactions in a list
	public List<MedicalTransaction> findAllMedicalTransactionInformation() {
		List<MedicalTransaction> medicalTrxList = medicalTransactionRepository.findAll();
		if (medicalTrxList.isEmpty() || medicalTrxList.size() == 0) {
			MedicalTrxLogger.medicalTrxLogger.warning(LoggerMessage.GET_ALL_MEDICAL_TRX_ERROR_MESSAGE);
		}
		else {
			MedicalTrxLogger.medicalTrxLogger.info(LoggerMessage.GET_ALL_MEDICAL_TRX_INFO_MESSAGE);
		}
		return medicalTransactionRepository.findAll();
	}
	
	// based on the pathvariable thrown, this returns the Medical Transaction object that has the corresponding ID
	public ResponseEntity<MedicalTransaction> findMedicalTransactionInformationById(@PathVariable Long medicalTransactionId) throws ResourceNotFoundException {
		MedicalTransaction medicalTransaction = medicalTransactionRepository.findById(medicalTransactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Not Found"));
		if (medicalTransactionId == null || medicalTransactionId == 0) {
			MedicalTrxLogger.medicalTrxLogger.severe(LoggerMessage.GET_MEDICAL_TRX_BY_ID_ERROR_MESSAGE + ": " + medicalTransactionId);
		}
		else  {
			MedicalTrxLogger.medicalTrxLogger.info(LoggerMessage.GET_MEDICAL_TRX_BY_ID_INFO_MESSAGE);
		}
		return ResponseEntity.ok().body(medicalTransaction);
	}
	
	// creates an MedicalTransaction object based off the fields that are filled.
	public MedicalTransaction addMedicalTransactionInformation(@Valid @RequestBody MedicalTransaction medicalTransaction) {
		if (medicalTransaction == null) {
			MedicalTrxLogger.medicalTrxLogger.severe(LoggerMessage.ADD_MEDICAL_TRX_ERROR_MESSAGE);
		}
		else {
			MedicalTrxLogger.medicalTrxLogger.info(LoggerMessage.ADD_MEDICAL_TRX_INFORMATION_INFO_MESSAGE + ": " + medicalTransaction.amount + " " + medicalTransaction.medicalTransactionDate);
		}
		return medicalTransactionRepository.save(medicalTransaction);
	}
	
	// updates the MedicalTransaction based on the id number entered. Once the fields are updated, then a new Auto
		// Transaction object is created.
	public ResponseEntity<MedicalTransaction> updateMedicalTransaction(@PathVariable Long medicalTransactionId,
			@Valid @RequestBody MedicalTransaction medicalTransactionDetails) {
		MedicalTransaction medicalTransaction = null;
		try {
			medicalTransaction = medicalTransactionRepository.findById(medicalTransactionId)
					.orElseThrow(() -> new ResourceNotFoundException("Not Found"));
			if (medicalTransactionId == null || medicalTransactionId == 0) {
				MedicalTrxLogger.medicalTrxLogger.warning(LoggerMessage.GET_MEDICAL_TRX_BY_ID_ERROR_MESSAGE);;
			}
			else {
				MedicalTrxLogger.medicalTrxLogger.info(LoggerMessage.GET_MEDICAL_TRX_BY_ID_INFO_MESSAGE + ": " + medicalTransactionId);
			}
			medicalTransaction.setAmount(medicalTransactionDetails.getAmount());
			medicalTransaction.setMedicalTransactionDate(medicalTransactionDetails.getMedicalTransactionDate());
			
			MedicalTrxLogger.medicalTrxLogger.info("Currently updating medical transcation information for ID");
		}
		catch (ResourceNotFoundException e)  {
			e.printStackTrace();
		}
		final MedicalTransaction updatedMedicalTransaction = medicalTransactionRepository.save(medicalTransaction);
		MedicalTrxLogger.medicalTrxLogger.info(LoggerMessage.UPDATE_AUTO_TRX_INFO_MESSAGE + updatedMedicalTransaction.amount + updatedMedicalTransaction.medicalTransactionDate);
		return ResponseEntity.ok().body(updatedMedicalTransaction);
	}
	
	public Map<String,Boolean> deleteMedicalTraansactionInformation(@PathVariable Long medicalTransactionId) {
		medicalTransactionRepository.deleteById(medicalTransactionId);
		if (medicalTransactionId == null || medicalTransactionId == 0) {
			MedicalTrxLogger.medicalTrxLogger.warning(LoggerMessage.DELETE_MEDICAL_TRX_ERROR_MESSAGE);
		}
		else {
			MedicalTrxLogger.medicalTrxLogger.info(LoggerMessage.DELETE_MEDICAL_TRX_INFORMATION_INFO_MESSAGE + ": " + medicalTransactionId);
		}
		Map<String,Boolean> response = new HashMap<>();
		response.put("Medical Transaction deleted", Boolean.TRUE);
		return response;
	}

	@Override
	public long getCountOfMedicalTransactions() {
		return medicalTransactionRepository.count();
	}
}

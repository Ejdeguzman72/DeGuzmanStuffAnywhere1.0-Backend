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

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.AutoTransaction;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.AutoTransactionRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface.AutoTransactionInterface;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.AutoTrxLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.message.LoggerMessage;

@Service
public class AutoTransactionService implements AutoTransactionInterface {

	@Autowired
	private AutoTransactionRepository autoTransactionRepository;
	
	// returns the Auto transactions in a list
	public List<AutoTransaction> findAllAutoTransactionInformation() {
		List<AutoTransaction> autoTrxList = autoTransactionRepository.findAll();
		if (autoTrxList.isEmpty() || autoTrxList.size() == 0) {
			AutoTrxLogger.autoTrxLogger.warning(LoggerMessage.GET_ALL_AUTO_TRX_INFO_ERROR_MESSAGE + ": " + autoTrxList.size());
		}
		else {
			AutoTrxLogger.autoTrxLogger.info(LoggerMessage.GET_ALL_AUTO_TRX_INFO_MESSAGE + ": " + autoTrxList.size());
		}
		return autoTransactionRepository.findAll();
	}
	
	// based on the pathvariable thrown, this returns the AutoTransaction object that has the corresponding ID
	public ResponseEntity<AutoTransaction> findAutoTranasctionInformationById(@PathVariable Long autoTransactionId) throws ResourceNotFoundException {
		AutoTransaction autoTransactions = autoTransactionRepository.findById(autoTransactionId)
				.orElseThrow(() -> new ResourceNotFoundException("Not Found"));
		if (autoTransactionId == null || autoTransactionId <= 0) {
			AutoTrxLogger.autoTrxLogger.warning(LoggerMessage.GET_AUTO_TRX_INFO_BY_ID_ERROR_MESSAGE + ": " + autoTransactionId);
		}
		else {
			AutoTrxLogger.autoTrxLogger.info(LoggerMessage.GET_AUTO_TRX_INFO_BY_ID_MESSAGE + ": " + autoTransactionId);
		}
		return ResponseEntity.ok().body(autoTransactions);
	}
	
	// creates an AutoTransaction object based off the fields that are filled.
	public AutoTransaction addAutoTransactionInformation(@Valid @RequestBody AutoTransaction autoTransaction) {
		if (autoTransaction == null) {
			AutoTrxLogger.autoTrxLogger.severe(LoggerMessage.ADD_AUTO_TRX_INFO_ERROR_MESSAGE);
		}
		else {
			AutoTrxLogger.autoTrxLogger.info(LoggerMessage.ADD_AUTO_TRX_INFO_MESSAGE + ": " + autoTransaction.amount);
		}
		return autoTransactionRepository.save(autoTransaction);
	}
	
	// updates the AutoTransaction based on the id number entered. Once the fields are updated, then a new Auto
	// Transaction object is created.
	public ResponseEntity<AutoTransaction> updateTransactionInformation(@PathVariable Long autoTransactionId,
			@Valid @RequestBody AutoTransaction autoTransactionDetails) {
		AutoTransaction autoTransaction = null;
		try {
			autoTransaction = autoTransactionRepository.findById(autoTransactionId)
					.orElseThrow(() -> new ResourceNotFoundException("Not Found"));
			if (autoTransactionId == null || autoTransactionId <= 0) {
				AutoTrxLogger.autoTrxLogger.warning(LoggerMessage.GET_AUTO_TRX_INFO_BY_ID_ERROR_MESSAGE);
			}
			autoTransaction.setAmount(autoTransactionDetails.getAmount());
			autoTransaction.setShopName(autoTransactionDetails.getShopName());
			autoTransaction.setPerson(autoTransactionDetails.getPerson());
			autoTransaction.setAutoTransactionDate(autoTransactionDetails.getAutoTransactionDate());
		}
		catch (ResourceNotFoundException e) {
			e.printStackTrace();
			AutoTrxLogger.autoTrxLogger.severe(LoggerMessage.UPDATE_AUTO_TRX_INFO_ERROR_MESSAGE + ": " + autoTransactionId);
		}
		final AutoTransaction updatedAutoTransactionDetails = autoTransactionRepository.save(autoTransaction);
		AutoTrxLogger.autoTrxLogger.info(LoggerMessage.UPDATE_AUTO_TRX_INFO_MESSAGE + ": " + updatedAutoTransactionDetails.amount);
		return ResponseEntity.ok().body(updatedAutoTransactionDetails);
	}
	
	//deletes the AutoTransaction object based off the id number passed. A HashMap is created in order to 
	// return a string and confirm  that the entity was deleted. 
	public Map<String,Boolean> deleteAutoTransactionInformation(@PathVariable Long autoTransactionId) {
		autoTransactionRepository.deleteById(autoTransactionId);
		if (autoTransactionId == null || autoTransactionId <= 0) {
			AutoTrxLogger.autoTrxLogger.warning(LoggerMessage.DELETE_AUTO_TRX_INFO_ERROR_MESSAGE);
		}
		else {
			AutoTrxLogger.autoTrxLogger.info(LoggerMessage.DELETE_AUTO_TRX_MESSAGE + ": " + autoTransactionId);
		}
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
	}

	@Override
	public long getCountOfAutoTransactions() {
		return autoTransactionRepository.count();
	}
}

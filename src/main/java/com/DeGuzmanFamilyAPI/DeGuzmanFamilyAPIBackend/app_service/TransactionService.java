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

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.GeneralTransaction;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository.TransactionRepository;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service_interface.GeneralTransactionInterface;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.logger.GeneralTrxLogger;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.message.LoggerMessage;

@Service
public class TransactionService implements GeneralTransactionInterface {

	@Autowired
	private TransactionRepository transactionRepository;
	
	// returns the general transactions in a list
	public List<GeneralTransaction> findAllTransactionInformation() {
		List<GeneralTransaction> generalTrxList = transactionRepository.findAll();
		if (generalTrxList.isEmpty() || generalTrxList.size() == 0) {
			GeneralTrxLogger.generalTrxLogger.warning(LoggerMessage.GET_ALL_GENERAL_TRX_ERROR_MESSAGE);
		}
		else {
			GeneralTrxLogger.generalTrxLogger.info(LoggerMessage.GET_ALL_GENERAL_TRX_INFO_MESSAGE +  ": " + generalTrxList.size());
		}
		return transactionRepository.findAll();
	}
	
	// based on the pathvariable thrown, this returns the General Transaction object that has the corresponding ID
	public ResponseEntity<GeneralTransaction> findTransactionInformationByID(@PathVariable long transactionid) throws ResourceNotFoundException {
		GeneralTransaction transaction = transactionRepository.findById(transactionid)
				.orElseThrow(() -> new ResourceNotFoundException("Not Found"));
		if (transactionid <= 0 || transaction == null) {
			GeneralTrxLogger.generalTrxLogger.warning(LoggerMessage.GET_GENERAL_TRX_BY_ID_INFO_MESSAGE);
		}
		else {
			GeneralTrxLogger.generalTrxLogger.info(LoggerMessage.GET_GENERAL_TRX_BY_ID_INFO_MESSAGE + ": " + transactionid);
		}
		return ResponseEntity.ok().body(transaction);
	}
	
	// creates an GeneralTransaction object based off the fields that are filled.
	public GeneralTransaction addTransactionInformation(@Valid @RequestBody GeneralTransaction transaction) {
		if (transaction == null) {
			GeneralTrxLogger.generalTrxLogger.warning(LoggerMessage.ADD_GENERAL_TRX_ERROR_MESSAGE);
		}
		else {
			GeneralTrxLogger.generalTrxLogger.info(LoggerMessage.ADD_GENERAL_TRX_INFO_MESSAGE + ": " + transaction.getAmount());
		}
		return transactionRepository.save(transaction);
	}
	
	// updates the GeneralTransaction based on the id number entered. Once the fields are updated, then a new Auto
		// Transaction object is created.
	public ResponseEntity<GeneralTransaction> updateTransactionInformation(@PathVariable Long transactionid,
			@Valid @RequestBody GeneralTransaction transactionDetails) {
		GeneralTransaction transaction = null;
		try {
			transaction = transactionRepository.findById(transactionid)
					.orElseThrow(() -> new ResourceNotFoundException("Not Found"));
			if (transactionid == null || transactionid == 0) {
				GeneralTrxLogger.generalTrxLogger.warning(LoggerMessage.GET_GENERAL_TRX_BY_ID_ERROR_MESSAGE);
			}
			else {
				GeneralTrxLogger.generalTrxLogger.info(LoggerMessage.GET_GENERAL_TRX_BY_ID_INFO_MESSAGE + ": " + transactionid);
			}
			transaction.setEntity(transactionDetails.getEntity());
			transaction.setAmount(transactionDetails.getAmount());
			transaction.setPaymentDate(transactionDetails.getPaymentDate());
			transaction.setPerson(transactionDetails.getPerson());
			GeneralTrxLogger.generalTrxLogger.info("Updating general transaction information...");
		}
		catch (ResourceNotFoundException e) {
			e.printStackTrace();
			GeneralTrxLogger.generalTrxLogger.warning(LoggerMessage.UPDATE_GENERAL_TRX_ERROR_MESSAGE);
		}
		
		final GeneralTransaction updatedTransaction = transactionRepository.save(transaction);
		GeneralTrxLogger.generalTrxLogger.info(LoggerMessage.UPDATE_GENERAL_TRX_INFO_MESSAGE + updatedTransaction.getAmount() + " " + updatedTransaction.getPaymentDate());
		return ResponseEntity.ok().body(updatedTransaction);
	}
	
	public Map<String,Boolean> deleteTransactionInformation(@PathVariable Long transactionid) {
		transactionRepository.deleteById(transactionid);
		if (transactionid == null || transactionid == 0) {
			GeneralTrxLogger.generalTrxLogger.warning(LoggerMessage.DELETE_GENERAL_TRX_ERROR_MESSAGE);
		}
		else {
			GeneralTrxLogger.generalTrxLogger.info(LoggerMessage.DELETE_GENERAL_TRX_INFO_MESSAGE);
		}
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@Override
	public long findCountOfGeneralTransaction() {
		return transactionRepository.count();
	}
	
}

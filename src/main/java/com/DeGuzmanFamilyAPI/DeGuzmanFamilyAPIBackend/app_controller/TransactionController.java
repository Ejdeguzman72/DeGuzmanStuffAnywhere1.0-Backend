package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.GeneralTransaction;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service.TransactionService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/app/general-transaction")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE })
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/all")
	public List<GeneralTransaction> getAllTransactionInformation() {
		return transactionService.findAllTransactionInformation();
	}
	
	@GetMapping("/transaction/{transactionId}")
	public ResponseEntity<GeneralTransaction> GetTransactionInformationByID(@PathVariable Long transactionid) throws ResourceNotFoundException {
		return transactionService.findTransactionInformationByID(transactionid);
	}
	
	@PostMapping("/add-transaction-information")
	public GeneralTransaction addTransaction(@Valid @RequestBody GeneralTransaction transaction) {
		return transactionService.addTransactionInformation(transaction);
	}
	
	@CrossOrigin
	@PutMapping("/transaction/{transactionId}")
	public ResponseEntity<GeneralTransaction> updateTransactionInformationController(@PathVariable Long transactionId,
			@Valid @RequestBody GeneralTransaction transactionDetails) {
		return transactionService.updateTransactionInformation(transactionId, transactionDetails);
	}
	
	@CrossOrigin
	@DeleteMapping("/transaction/{transactionId}")
	public Map<String,Boolean> deleteTransactionInformation(@PathVariable Long transactionId) {
		return transactionService.deleteTransactionInformation(transactionId);
	}
	
	@CrossOrigin
	@GetMapping("/general-transaction-count")
	public long getCountOfGeneralTransaction() {
		return transactionService.findCountOfGeneralTransaction();
	}
}

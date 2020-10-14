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

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.AutoTransaction;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.service.AutoTransactionService;

@RestController
@RequestMapping("/app/auto-transactions")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE })
public class AutoTransactionController {

	@Autowired
	private AutoTransactionService autoTransactionService;
	
	@GetMapping("/all")
	public List<AutoTransaction> getAllAutoTransactionInformation() {
		return autoTransactionService.findAllAutoTransactionInformation();
	}
	
	@GetMapping("/auto-transaction/{autoTransactionId}")
	public ResponseEntity<AutoTransaction> getAutoTransactionInformationById(@PathVariable Long autoTransactionId) throws ResourceNotFoundException {
		return autoTransactionService.findAutoTranasctionInformationById(autoTransactionId);
	}
	
	@PostMapping("/add-auto-transaction-information")
	public AutoTransaction addAutoTransactionInfoController(@Valid @RequestBody AutoTransaction autoTransaction) {
		return autoTransactionService.addAutoTransactionInformation(autoTransaction);
	}
	
	@CrossOrigin
	@PutMapping("/update-auto-transaction/{autoTransactionId}")
	public ResponseEntity<AutoTransaction> updateAutoTransactionInformationController(@PathVariable Long autoTransactionId,
			@Valid @RequestBody AutoTransaction autoTransactionDetails) {
		return autoTransactionService.updateTransactionInformation(autoTransactionId, autoTransactionDetails);
	}
	
	@CrossOrigin
	@DeleteMapping("/auto-transaction/{autoTransactionId}")
	public Map<String,Boolean> deleteAutoTransactionInformationController(@PathVariable Long autoTransactionId) {
		return autoTransactionService.deleteAutoTransactionInformation(autoTransactionId);
	}
	
	@CrossOrigin
	@GetMapping("/count")
	public long getCountOfAutoTransactions() {
		return autoTransactionService.getCountOfAutoTransactions();
	}
}

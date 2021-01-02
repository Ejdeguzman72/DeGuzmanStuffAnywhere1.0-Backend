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

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.MedicalTransaction;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_service.MedicalTransactionService;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/app/medical-transactions")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT,
		RequestMethod.DELETE })
public class MedicalTransactionController {

	@Autowired
	private MedicalTransactionService medicalTransactionService;
	
	@GetMapping("/all")
	public List<MedicalTransaction> getAllMedicalTransactionInformation() {
		return medicalTransactionService.findAllMedicalTransactionInformation();
	}
	
	@GetMapping("/medical-transaction/{medicalTransactionId}")
	public ResponseEntity<MedicalTransaction> getMedicalTransactionInformationById(@PathVariable Long medicalTranscationId) throws ResourceNotFoundException {
		return medicalTransactionService.findMedicalTransactionInformationById(medicalTranscationId);
	}
	
	@PostMapping("/add-medical-transaction")
	public MedicalTransaction addMedicalTransactionController(@Valid @RequestBody MedicalTransaction medicalTransaction) {
		return medicalTransactionService.addMedicalTransactionInformation(medicalTransaction);
	}
	
	@CrossOrigin
	@PutMapping("/medical-transaction/{medicalTransactionId}")
	public ResponseEntity<MedicalTransaction> updateMedicalTransactionController(@PathVariable Long medicalTransactionId,
			@Valid @RequestBody MedicalTransaction medicalTransactionDetails) {
		return medicalTransactionService.updateMedicalTransaction(medicalTransactionId, medicalTransactionDetails);
	}
	
	@CrossOrigin
	@DeleteMapping("/medical-transaction/{medicalTransactionId")
	public Map<String,Boolean> deleteMedicalTransactionInfoController(@PathVariable Long medicalTransactionId) {
		return medicalTransactionService.deleteMedicalTraansactionInformation(medicalTransactionId);
	}
	
	@CrossOrigin
	@GetMapping("/count-of-medical-transactions")
	public long getCountOfMedicalTransactions() {
		return medicalTransactionService.getCountOfMedicalTransactions();
	}
}

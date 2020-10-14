package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.fun_apps_controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.exception.ResourceNotFoundException;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.fun_apps_models.DailyAgenda;
import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.fun_apps_services.DailyAgendaService;

@RestController
@CrossOrigin
@RequestMapping("app/fun-apps/to-do-list")
public class DailyAgendaController {

	@Autowired
	private DailyAgendaService dailyAgendaService;
	
	@GetMapping("/all")
	public List<DailyAgenda> getAllDailyAgendaItems() {
		return dailyAgendaService.findAllDailyAgendaItems();
	}
	
	@GetMapping("all-names")
	public List<String> getAllDailyAgendaItemNames() {
		return dailyAgendaService.findDailyAgendaItemName();
	}
	
	@GetMapping("/daily-agenda-item/{agendaId}")
	public ResponseEntity<DailyAgenda> getDailyAgendaById(@PathVariable int agendaId) throws ResourceNotFoundException {
		return dailyAgendaService.findDailyAgendabyId(agendaId);
	}
	
	@PostMapping("/add-item")
	public DailyAgenda addDailyAgendaItem(@Valid @RequestBody DailyAgenda item) {
		item.setComplete(false);
		return dailyAgendaService.saveDailyAgendaItem(item);
	}
	
	@PutMapping("/daily-agenda-item/{agendaId}")
	public ResponseEntity<DailyAgenda> updateItem(@PathVariable int agendaId,
			@Valid @RequestBody DailyAgenda item) throws ResourceNotFoundException {
		return dailyAgendaService.updateDailyAgenda(agendaId,item);
	}
	
	@PutMapping("/daily-agenda/complete-item/{agendaId}")
	public ResponseEntity<DailyAgenda> completeAgendaItemController(@PathVariable int agendaId,
			@Valid @RequestBody DailyAgenda item) throws ResourceNotFoundException {
		return dailyAgendaService.completeAgendaItem(agendaId,item);
	}
	
	@DeleteMapping("daily-agenda-item/{agendaId}")
	public Map<String,Boolean> deleteDailyAgendaItem(@PathVariable int agendaId) {
		return dailyAgendaService.deleteAgendaItem(agendaId);
	}
	
	@DeleteMapping("daily-agenda/delete-all") 
	public Map<String,Boolean> deleteAllAgendaItems() {
		return dailyAgendaService.deleteAll();
	}
}

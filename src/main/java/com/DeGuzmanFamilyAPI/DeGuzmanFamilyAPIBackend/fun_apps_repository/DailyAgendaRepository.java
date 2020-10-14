package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.fun_apps_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.fun_apps_models.DailyAgenda;

@Repository
public interface DailyAgendaRepository extends JpaRepository<DailyAgenda,Integer> {
	
	@Query(value = "SELECT NAME FROM DAILY_AGENDA", nativeQuery=true)
	List<String> getAllDailyAgendaItemNames(); 
	
	@Query(value = "SELECT * FROM DAILY_AGENDA WHERE COMPLETE = TRUE", nativeQuery=true)
	List<DailyAgenda> getAllFinishedTasks();
}

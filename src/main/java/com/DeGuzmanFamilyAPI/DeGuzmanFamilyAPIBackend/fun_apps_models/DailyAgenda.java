package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.fun_apps_models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name="daily_agenda")
@CrossOrigin
public class DailyAgenda {

	public int agendaId;
	public String name;
	public boolean complete;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getAgendaId() {
		return agendaId;
	}
	public void setAgendaId(int agendaId) {
		this.agendaId = agendaId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isComplete() {
		return complete;
	}
	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	
}

package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name = "RESTAURANT_TYPE")
@CrossOrigin
public class RestaurantType {

	public int restaurant_type_id;
	public String descr;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_type_id")
	public int getRestaurantTypeId() {
		return restaurant_type_id;
	}
	public void setRestaurantTypeId(int restaurantTypeId) {
		this.restaurant_type_id = restaurantTypeId;
	}
	@Column(name = "descr")
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}

package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "RESTAURANT_TYPE")
@CrossOrigin
public class RestaurantType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int restaurant_type_id;
	public String restaurantDescr;
	
	
	public List<Restaurant> restaurant;
	
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
		return restaurantDescr;
	}
	public void setDescr(String restaurantDescr) {
		this.restaurantDescr = restaurantDescr;
	}
	
	@OneToMany(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "restaurantType")
	@JsonBackReference
	public List<Restaurant> getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(List<Restaurant> restaurant) {
		this.restaurant = restaurant;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
		result = prime * result + ((restaurantDescr == null) ? 0 : restaurantDescr.hashCode());
		result = prime * result + restaurant_type_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestaurantType other = (RestaurantType) obj;
		if (restaurant == null) {
			if (other.restaurant != null)
				return false;
		} else if (!restaurant.equals(other.restaurant))
			return false;
		if (restaurantDescr == null) {
			if (other.restaurantDescr != null)
				return false;
		} else if (!restaurantDescr.equals(other.restaurantDescr))
			return false;
		if (restaurant_type_id != other.restaurant_type_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RestaurantType [restaurant_type_id=" + restaurant_type_id + ", restaurantDescr=" + restaurantDescr
				+ ", restaurant=" + restaurant + "]";
	}
	public RestaurantType(int restaurant_type_id, String restaurantDescr, List<Restaurant> restaurant) {
		super();
		this.restaurant_type_id = restaurant_type_id;
		this.restaurantDescr = restaurantDescr;
		this.restaurant = restaurant;
	}
	public RestaurantType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

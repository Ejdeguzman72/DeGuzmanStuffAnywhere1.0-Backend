package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

@Table(name = "car")
@Entity
@CrossOrigin
public class Car {

	public long carid;
	public String make;
	public String model;
	public String year;
	public int capacity;
	public String transmission;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	public long getCarid() {
		return carid;
	}
	public void setCarid(long carid) {
		this.carid = carid;
	}
	
	@Column(name = "make")
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	@Column(name = "model")
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	@Column(name = "year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	@Column(name = "capacity")
	public int getCapactity() {
		return capacity;
	}
	public void setCapactity(int capactity) {
		this.capacity = capactity;
	}
	
	@Column(name = "transmission")
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacity;
		result = prime * result + (int) (carid ^ (carid >>> 32));
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((transmission == null) ? 0 : transmission.hashCode());
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
		Car other = (Car) obj;
		if (capacity != other.capacity)
			return false;
		if (carid != other.carid)
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (transmission == null) {
			if (other.transmission != null)
				return false;
		} else if (!transmission.equals(other.transmission))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Car [carid=" + carid + ", make=" + make + ", model=" + model + ", capacity=" + capacity
				+ ", transmission=" + transmission + "]";
	}
	public Car(long carid, String make, String model, int capacity, String transmission) {
		super();
		this.carid = carid;
		this.make = make;
		this.model = model;
		this.capacity = capacity;
		this.transmission = transmission;
	}
	
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

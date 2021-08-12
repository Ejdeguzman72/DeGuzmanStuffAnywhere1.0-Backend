package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "utility_type")
@CrossOrigin
public class UtilityType {

	public int utility_type_id;
	public String utility_type_descr;
	
	public Utility utility;
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "utility_type_id")
	public int getUtiityTypeId() {
		return utility_type_id;
	}
	public void setUtiityTypeId(int utiityTypeId) {
		this.utility_type_id = utiityTypeId;
	}
	@Column(name = "utility_type_descr")
	public String getDescr() {
		return utility_type_descr;
	}
	public void setDescr(String utility_type_descr) {
		this.utility_type_descr = utility_type_descr;
	}
	
	@OneToOne(mappedBy = "utilityType")
	@JsonBackReference
	public Utility getUtility() {
		return utility;
	}
	public void setUtility(Utility utility) {
		this.utility = utility;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((utility == null) ? 0 : utility.hashCode());
		result = prime * result + ((utility_type_descr == null) ? 0 : utility_type_descr.hashCode());
		result = prime * result + utility_type_id;
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
		UtilityType other = (UtilityType) obj;
		if (utility == null) {
			if (other.utility != null)
				return false;
		} else if (!utility.equals(other.utility))
			return false;
		if (utility_type_descr == null) {
			if (other.utility_type_descr != null)
				return false;
		} else if (!utility_type_descr.equals(other.utility_type_descr))
			return false;
		if (utility_type_id != other.utility_type_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UtilityType [utility_type_id=" + utility_type_id + ", utility_type_descr=" + utility_type_descr
				+ ", utility=" + utility + "]";
	}
	public UtilityType(int utility_type_id, String utility_type_descr, Utility utility) {
		super();
		this.utility_type_id = utility_type_id;
		this.utility_type_descr = utility_type_descr;
		this.utility = utility;
	}
	public UtilityType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

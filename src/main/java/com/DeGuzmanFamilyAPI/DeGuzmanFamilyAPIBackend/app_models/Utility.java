package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "utility")
@CrossOrigin
public class Utility {

	public Long utility_id;
	public String name;
	public String phone;
	public String url;
	public String dueDate;
	
	public UtilityType utilityType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "utility_id")
	public Long getUtility_id() {
		return utility_id;
	}
	public void setUtility_id(Long utility_id) {
		this.utility_id = utility_id;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	@Column(name = "phone")
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "due_date")
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	@OneToOne
	@JoinColumn(name = "utility_type_id")
	@JsonManagedReference
	public UtilityType getUtilityType() {
		return utilityType;
	}
	public void setUtilityType(UtilityType utilityType) {
		this.utilityType = utilityType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((utilityType == null) ? 0 : utilityType.hashCode());
		result = prime * result + ((utility_id == null) ? 0 : utility_id.hashCode());
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
		Utility other = (Utility) obj;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (utilityType == null) {
			if (other.utilityType != null)
				return false;
		} else if (!utilityType.equals(other.utilityType))
			return false;
		if (utility_id == null) {
			if (other.utility_id != null)
				return false;
		} else if (!utility_id.equals(other.utility_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Utility [utility_id=" + utility_id + ", name=" + name + ", phone=" + phone + ", url=" + url
				+ ", dueDate=" + dueDate + ", utilityType=" + utilityType + "]";
	}
	public Utility(Long utility_id, String name, String phone, String url, String dueDate, UtilityType utilityType) {
		super();
		this.utility_id = utility_id;
		this.name = name;
		this.phone = phone;
		this.url = url;
		this.dueDate = dueDate;
		this.utilityType = utilityType;
	}
	public Utility() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

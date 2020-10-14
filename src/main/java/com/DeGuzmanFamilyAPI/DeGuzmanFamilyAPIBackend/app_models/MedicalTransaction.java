package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name = "medical_transactions")
@CrossOrigin
public class MedicalTransaction {

	public Long medicalTransactionId;
	public String facillity;
	public String medicalTransactionDate;
	public double amount;
	public String person;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getMedicalTransactionId() {
		return medicalTransactionId;
	}
	public void setMedicalTransactionId(Long medicalTransactionId) {
		this.medicalTransactionId = medicalTransactionId;
	}
	public String getFacillity() {
		return facillity;
	}
	public void setFacillity(String facillity) {
		this.facillity = facillity;
	}
	public String getMedicalTransactionDate() {
		return medicalTransactionDate;
	}
	public void setMedicalTransactionDate(String medicalTransactionDate) {
		this.medicalTransactionDate = medicalTransactionDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((facillity == null) ? 0 : facillity.hashCode());
		result = prime * result + ((medicalTransactionDate == null) ? 0 : medicalTransactionDate.hashCode());
		result = prime * result + ((medicalTransactionId == null) ? 0 : medicalTransactionId.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
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
		MedicalTransaction other = (MedicalTransaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (facillity == null) {
			if (other.facillity != null)
				return false;
		} else if (!facillity.equals(other.facillity))
			return false;
		if (medicalTransactionDate == null) {
			if (other.medicalTransactionDate != null)
				return false;
		} else if (!medicalTransactionDate.equals(other.medicalTransactionDate))
			return false;
		if (medicalTransactionId == null) {
			if (other.medicalTransactionId != null)
				return false;
		} else if (!medicalTransactionId.equals(other.medicalTransactionId))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MedicalTransaction [medicalTransactionId=" + medicalTransactionId + ", facillity=" + facillity
				+ ", medicalTransactionDate=" + medicalTransactionDate + ", amount=" + amount + ", person=" + person
				+ "]";
	}
	public MedicalTransaction(Long medicalTransactionId, String facillity, String medicalTransactionDate, double amount,
			String person) {
		super();
		this.medicalTransactionId = medicalTransactionId;
		this.facillity = facillity;
		this.medicalTransactionDate = medicalTransactionDate;
		this.amount = amount;
		this.person = person;
	}
	public MedicalTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

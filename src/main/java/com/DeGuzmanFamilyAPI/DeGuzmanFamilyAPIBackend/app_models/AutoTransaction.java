package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@Table(name = "auto_transactions")
@CrossOrigin
public class AutoTransaction {
	
	public Long autoTransactionId;
	public String autoTransactionDate;
	public String shopName;
	public String transactionType;
	public double amount;
	public String person;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auto_transaction_id")
	public Long getAutoTransactionId() {
		return autoTransactionId;
	}
	public void setAutoTransactionId(Long autoTransactionId) {
		this.autoTransactionId = autoTransactionId;
	}
	
	@Column(name = "autoTransactionDate")
	public String getAutoTransactionDate() {
		return autoTransactionDate;
	}
	public void setAutoTransactionDate(String autoTransactionDate) {
		this.autoTransactionDate = autoTransactionDate;
	}
	
	@Column(name = "shopName")
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	@Column(name = "transaction_type")
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	@Column(name = "amount")
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Column(name = "person")
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
		result = prime * result + ((autoTransactionDate == null) ? 0 : autoTransactionDate.hashCode());
		result = prime * result + ((autoTransactionId == null) ? 0 : autoTransactionId.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((shopName == null) ? 0 : shopName.hashCode());
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
		AutoTransaction other = (AutoTransaction) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (autoTransactionDate == null) {
			if (other.autoTransactionDate != null)
				return false;
		} else if (!autoTransactionDate.equals(other.autoTransactionDate))
			return false;
		if (autoTransactionId == null) {
			if (other.autoTransactionId != null)
				return false;
		} else if (!autoTransactionId.equals(other.autoTransactionId))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (shopName == null) {
			if (other.shopName != null)
				return false;
		} else if (!shopName.equals(other.shopName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AutoTransaction [autoTransactionId=" + autoTransactionId + ", autoTransactionDate="
				+ autoTransactionDate + ", shopName=" + shopName + ", amount=" + amount + ", person=" + person + "]";
	}
	public AutoTransaction(Long autoTransactionId, String autoTransactionDate, String shopName, double amount,
			String person) {
		super();
		this.autoTransactionId = autoTransactionId;
		this.autoTransactionDate = autoTransactionDate;
		this.shopName = shopName;
		this.amount = amount;
		this.person = person;
	}
	public AutoTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
}

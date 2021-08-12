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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "EXERCISE_TYPE")
@CrossOrigin
@JsonIgnoreProperties(value = "hibernateLazyInitializer")
public class ExerciseType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118064847455021642L;
	public int exerciseTypeId;
	public String exerciseTypeName;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exercise_type_id")
	public int getExerciseTypeId() {
		return exerciseTypeId;
	}

	public void setExerciseTypeId(int exerciseTypeId) {
		this.exerciseTypeId = exerciseTypeId;
	}

	@Column(name = "exercise_type_name")
	public String getExerciseTypeName() {
		return exerciseTypeName;
	}

	public void setExerciseTypeName(String exerciseTypeName) {
		this.exerciseTypeName = exerciseTypeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + exerciseTypeId;
		result = prime * result + ((exerciseTypeName == null) ? 0 : exerciseTypeName.hashCode());
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
		ExerciseType other = (ExerciseType) obj;
		if (exerciseTypeId != other.exerciseTypeId)
			return false;
		if (exerciseTypeName == null) {
			if (other.exerciseTypeName != null)
				return false;
		} else if (!exerciseTypeName.equals(other.exerciseTypeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ExerciseType [exerciseTypeId=" + exerciseTypeId + ", exerciseTypeName=" + exerciseTypeName + "]";
	}

	public ExerciseType(int exerciseTypeId, String exerciseTypeName) {
		super();
		this.exerciseTypeId = exerciseTypeId;
		this.exerciseTypeName = exerciseTypeName;
	}

	public ExerciseType() {
		super();
		// TODO Auto-generated constructor stub
	}

}

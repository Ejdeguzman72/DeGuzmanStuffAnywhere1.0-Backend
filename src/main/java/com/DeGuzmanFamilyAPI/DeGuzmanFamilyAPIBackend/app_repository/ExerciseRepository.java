package com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.DeGuzmanFamilyAPI.DeGuzmanFamilyAPIBackend.app_models.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Integer>{

}

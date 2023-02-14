package com.greatlearning.ema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.ema.entity.EmployeeEntity;

public interface EmployeeRepository
     extends JpaRepository<EmployeeEntity, Integer>{

	List<EmployeeEntity> findAll();

}

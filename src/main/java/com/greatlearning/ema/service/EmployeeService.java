package com.greatlearning.ema.service;

import java.util.List;

import com.greatlearning.ema.entity.EmployeeEntity;

public interface EmployeeService {
	
	List<EmployeeEntity> listAll();
	
	void save(EmployeeEntity emp);
	
	EmployeeEntity findById(int id);
	
	void deleteById(int id);

}

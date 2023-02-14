package com.greatlearning.ema.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.ema.entity.EmployeeEntity;
import com.greatlearning.ema.repository.EmployeeRepository;
import com.greatlearning.ema.service.EmployeeService;

@Service
public class EmployeeServiceImpl
              implements EmployeeService{
	
	@Autowired
	EmployeeRepository repository;
	
	@Override
	public List<EmployeeEntity> listAll() {
		
		return repository.findAll();
	}

	@Override
	public void save(EmployeeEntity student) {
				
		repository.save(student);
	}

	@Override
	public EmployeeEntity findById(int id) {
		
		return repository.findById(id).get();
	}

	@Override
	public void deleteById(int id) {
		
		repository.deleteById(id);		
	}

	
}

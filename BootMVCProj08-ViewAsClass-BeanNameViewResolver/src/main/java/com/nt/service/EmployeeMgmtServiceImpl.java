package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.Employee;
import com.nt.repo.EmployeeRespository;

@Service
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService {
	@Autowired
	private EmployeeRespository repo;
	
	
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> list=repo.findAll();
		return list;
	}

}

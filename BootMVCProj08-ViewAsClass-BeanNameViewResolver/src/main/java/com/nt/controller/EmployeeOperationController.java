package com.nt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.model.Employee;
import com.nt.service.IEmployeeMgmtService;

@Controller
public class EmployeeOperationController {
	
	@Autowired
	private IEmployeeMgmtService service;
	
	@GetMapping("/")
	public String showHome() {
		return "welcome";
	}
	@GetMapping("/report")
	public String showReport(Map<String, Object> map,@RequestParam("type")String type) {
		List<Employee> list=service.getAllEmployees();

		System.out.println(type);
//		list.forEach(emp->{
//			System.out.println(emp);
//			
//		});
		map.put("empsList", list);
		if (type.equalsIgnoreCase("excel")) {
			return "excel_report";
		}else {
			return "pdf_report";
		}
	}
}

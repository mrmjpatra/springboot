package com.nt.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.Company;
import com.nt.model.Customer;

@RestController
public class CustomerOperation {
//	@GetMapping("/show")
//	public ResponseEntity<Customer> showData(){
//		Customer cust=new Customer("mrmj","ctc",23.4f,new String[] {"10th","+2","+3"},
//									List.of("Red","Green","Blue"),
//									Set.of("9090323291","6371655140"),
//									Map.of("aadhar",33333,"Pan",444444),
//									new Company("Samsung","Electronics","HYD")
//				);
//		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
//	}
//	
	@PostMapping("/register")
	public String register(@RequestBody Customer cust) {
		
		return cust.toString();
	}
}

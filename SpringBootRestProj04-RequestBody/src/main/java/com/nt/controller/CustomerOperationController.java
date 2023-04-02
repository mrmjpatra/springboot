package com.nt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.Customer;

@RestController
public class CustomerOperationController {

	@PostMapping("register")
	public String registerCustomer(@RequestBody Customer cust) {
		return cust.toString();
	}
	
	@GetMapping("/report/{cno}/{cname}")
	public String getReport(@PathVariable Integer cno,@PathVariable String cname) {
		return cno+""+cname;
	}	
}

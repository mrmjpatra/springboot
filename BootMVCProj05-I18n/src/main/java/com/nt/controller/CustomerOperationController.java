package com.nt.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.nt.model.Customer;

@Controller
public class CustomerOperationController {
	
	@GetMapping("/")
	public String showHome() {
		System.out.println("CustomerOperationController.showHome()");
		return "welcome";
	}
	
	@GetMapping("/register")
	public String showCustFormPage(@ModelAttribute("cust")Customer customer,Map<String, Object>map) {
		return "customer_register";
	}
}

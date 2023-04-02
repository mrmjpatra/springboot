package com.nt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankOperationController {
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/offers")
	public String showOffer() {
		return "offers";
	}
	
	@GetMapping("/loan")
	public String showLoanApprove() {
		return "loan";
	}
	
	@GetMapping("/balance")
	public String showBalance() {
		return "balance";
	}
	
	@GetMapping("/denied")
	public String showDeniedPage() {
		return "denied";
	}
}

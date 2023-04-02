package com.nt.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bank")
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
	public String showLoanApprove(Map<String, Object>map) {
		map.put("loanAmt",new Random().nextInt(100000));
		return "loan";
	}
	
	@GetMapping("/balance")
	public String showBalance(Map<String, Object>map) {
		map.put("balance",new Random().nextInt(100000));
		return "balance";
	}
	
	@GetMapping("/denied")
	public String showDeniedPage() {
		return "denied";
	}
}

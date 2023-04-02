package com.nt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerOperationController {
	
	@GetMapping("/report")
	public ResponseEntity<String> showReport(){
		return new ResponseEntity<String>("From showReport()", HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(){
		return new ResponseEntity<String>("From registerCustomer()", HttpStatus.OK);
	}
	@PutMapping("/modify")
	public ResponseEntity<String> modifyCustomer(){
		return new ResponseEntity<String>("From modifyCustomer()", HttpStatus.OK);
	}
	@PatchMapping("/pmodify")
	public ResponseEntity<String> partialModify(){
		return new ResponseEntity<String>("From partialModify()", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCustomer(){
		return new ResponseEntity<String>("from deleteCustomer()",HttpStatus.OK);
	}
	
}

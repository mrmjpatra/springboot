package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Tourist;
import com.nt.service.ITouristMgmtService;

@RestController
public class TouristMgmtControllerService {

	@Autowired
	private ITouristMgmtService service;
	
	@GetMapping("/alltourist")
	public ResponseEntity<?> getAllTouristList()throws Exception {
		List list=service.getAllTourist();
		return new ResponseEntity<List<Tourist>>(list,HttpStatus.OK);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getTourist(@PathVariable Integer id)throws Exception{
		Tourist tourist=service.getTouristById(id);
		return new ResponseEntity<Tourist>(tourist,HttpStatus.OK);
	}

	@PostMapping("/enroll")
	public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist)throws Exception {
		String msg = service.registerTourist(tourist);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateTourist(@RequestBody Tourist tourist)throws Exception{
		String msg=service.updateTourist(tourist);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTourist(@PathVariable Integer id)throws Exception{
		String msg=service.deleteTourist(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PatchMapping("/updatename/{id}/{name}")
	public ResponseEntity<String> updateName(@PathVariable Integer id,@PathVariable String name)throws Exception{
		String msg=service.updateName(id, name);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
}

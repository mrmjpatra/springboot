package com.nt.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.nt.entity.Tourist;
import com.nt.exception.TouristNotFound;

public interface ITouristMgmtService {
	//create option
	public String registerTourist(Tourist tourist);
	//read operation
	public List<Tourist> getAllTourist();
	//update operation
	public String updateTourist(Tourist tourist) throws TouristNotFound;
	//delete operation
	public String deleteTourist(Integer id) throws TouristNotFound;
	
	public Tourist getTouristById(Integer id) throws TouristNotFound;
	
	public String updateName(Integer id,String newName) throws TouristNotFound;
}

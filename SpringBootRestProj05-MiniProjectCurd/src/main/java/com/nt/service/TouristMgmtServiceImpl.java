package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Tourist;
import com.nt.exception.TouristNotFound;
import com.nt.repo.ITouriestRepo;

@Service
public class TouristMgmtServiceImpl implements ITouristMgmtService {
	@Autowired
	private ITouriestRepo touristRepo;
	
	@Override
	public String registerTourist(Tourist tourist) {
		int id=touristRepo.save(tourist).getTid();
		return "tourist details is saved with id" +id;
	}
	@Override
	public List<Tourist> getAllTourist() {
		List<Tourist> allTourist=touristRepo.findAll();
		return allTourist;
	}
	@Override
	public String updateTourist(Tourist tourist) throws TouristNotFound {
		Optional<Tourist> rTourist=touristRepo.findById(tourist.getTid());
		if (rTourist.isPresent()) {
			touristRepo.save(tourist);
			return "Tourist details updated";
		}else {
			throw new TouristNotFound("Record not found");
		}
	}
	@Override
	public String deleteTourist(Integer id) throws TouristNotFound {
		Optional<Tourist> t=touristRepo.findById(id);
		if(t.isPresent()) {
			touristRepo.delete(t.get());
			return "Record deleted";
		}else {
			throw new TouristNotFound("Record not found");
		}
	}
	@Override
	public Tourist getTouristById(Integer id) throws TouristNotFound {
		return touristRepo.findById(id).orElseThrow(()->new TouristNotFound("Record not found"));
	}
	 @Override
	public String updateName(Integer id, String newName) throws TouristNotFound {
		Optional<Tourist> t=touristRepo.findById(id);
		if(t.isPresent()) {
			Tourist present=t.get();
			present.setName(newName);
			touristRepo.save(present);
			return "Update the name";
		}else {
			throw new TouristNotFound("Not found");
		}
	}
	
}

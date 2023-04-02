package com.nt.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class WishMsgServiceImpl implements IWishMsgService {

	@Override
	public String generateMessage() {
		LocalDateTime ldt=LocalDateTime.now();
		int hour=ldt.getHour();
		if (hour<12) {
			return "Good Morning";
		}else if (hour<16) {
			return "Good AfterNoon";
		}else if (hour<20) {
			return "Good Evening";
		}else {
			return "Good Night";
		}
		
	}

}

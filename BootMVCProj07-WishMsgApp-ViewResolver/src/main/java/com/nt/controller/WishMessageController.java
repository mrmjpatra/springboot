package com.nt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.nt.service.IWishMsgService;

@Controller
public class WishMessageController {

	@Autowired
	private IWishMsgService service;
	
	
	@GetMapping("/")
	public String showHome() {
		return "welcome";
	}
	
	@GetMapping("/wish")
	public String generateWishMsg(Map<String, Object> map) {
		String msg=service.generateMessage();
		
		map.put("msg", msg);
		return "show_wish";
		
	}
}

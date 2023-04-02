package com.nt.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.nt.service.IWishService;

@Controller
public class WishMessageOptionController {
	@Autowired
	private IWishService service;
	
	@RequestMapping("/home")
	public String showHomePage() {
		return "welcome";
	}
	
	@RequestMapping("/wish")
	public String fetchWishMessage(Map<String,Object> map) {
		String wish=service.generateWishMessage();
		map.put("wish", wish);
		map.put("date", new Date());
		return "show_result";
	}
	
}

package com.nt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.model.UserDetails;
import com.nt.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;

	@GetMapping("/register")
	public String showUserRegistrationFrom(@ModelAttribute("userInfo")UserDetails user) {
		return "user_register";
	}

	@PostMapping("/register")
	public String registerUser(Map<String, Object>map,@ModelAttribute("userInfo")UserDetails user) {
		//user service to register the user
		System.out.println("UserController.registerUser()");
		String msg=service.registerUser(user);
		map.put("message", msg);
		return "user_registered_success";
	}
	
	@GetMapping("/showlogin")
	public String showLoginPage() {
		return "custom_login";
	}

}

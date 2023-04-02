package com.nt.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.repository.IUserDetailsRepo;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDetailsRepo userRepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.nt.model.UserDetails> opt = userRepo.findByUname(username);
		if (opt.isEmpty()) {
			throw new IllegalStateException("User not found");
		} else {
			com.nt.model.UserDetails details = opt.get();
			User user = new User(details.getUname(), details.getPwd(), details.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet()));
			return user;
		}
	}

	@Override
	public String registerUser(com.nt.model.UserDetails user) {
		System.out.println("UserServiceImpl.registerUser()");
		user.setPwd(encoder.encode(user.getPwd()));
		return "User is registered with id :: " + userRepo.save(user).getUnid();
	}

}

package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BootSecutiryProj01InMemoryDbApplication {
	@Bean
	public BCryptPasswordEncoder createBCPEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(BootSecutiryProj01InMemoryDbApplication.class, args);
	}

}

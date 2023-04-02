package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BootSchedulingProj01Application {

	public static void main(String[] args) {
		SpringApplication.run(BootSchedulingProj01Application.class, args);
	}

}

package com.nt.service;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class Task1 {
	
	@Scheduled(cron = "0 * * * * *")
	public void doTask() {
		System.out.println("task1......."+new Date());
	}
	
	
}

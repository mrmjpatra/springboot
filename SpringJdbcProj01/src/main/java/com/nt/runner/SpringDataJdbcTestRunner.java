package com.nt.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.model.IPLTeam;
import com.nt.service.IIPLTeamMgmtService;

@Component
public class SpringDataJdbcTestRunner implements CommandLineRunner {
	@Autowired
	private IIPLTeamMgmtService service;
	@Override
	public void run(String... args) throws Exception {
//		String msg=service.registerTeam(new IPLTeam(null, "MI","ROHIT","NITA AMBANI",0));
//		System.out.println(msg);
		
//		service.getAllTeams().forEach(System.out::println);
		service.fetchTeamsByTitleWinningCountRange(0, 2).forEach(System.out::println);
	}

}

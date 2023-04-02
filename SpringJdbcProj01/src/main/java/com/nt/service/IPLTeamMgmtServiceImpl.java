package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.IPLTeam;
import com.nt.repo.IPLTeamRepo;

@Service
public class IPLTeamMgmtServiceImpl implements IIPLTeamMgmtService {
	@Autowired
	private IPLTeamRepo repo;

	@Override
	public String registerTeam(IPLTeam team) {
		return "Team is registered with id :: "+repo.save(team).getTeamid();
	}
	@Override
	public Iterable<IPLTeam> getAllTeams() {
		return repo.findAll();
	}
	@Override
	public List<IPLTeam> fetchTeamsByTitleWinningCountRange(int startRange, int endRange) {
		return repo.getTeamsByWinningCountRange(startRange, endRange);
	}

}

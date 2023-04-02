package com.nt.service;

import java.util.List;

import com.nt.model.IPLTeam;

public interface IIPLTeamMgmtService {
	public String registerTeam(IPLTeam team);
	public Iterable<IPLTeam> getAllTeams();
	public List<IPLTeam> fetchTeamsByTitleWinningCountRange(int startRange,int endRange);
	
}

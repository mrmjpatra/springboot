package com.nt.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nt.model.IPLTeam;

public interface IPLTeamRepo extends CrudRepository<IPLTeam, Integer> {
	@Query("SELECT * FROM DATA_JDBC_IPL_TEAM WHERE titlewinningteam>=:min AND titlewinningteam<=:max")
	public List<IPLTeam> getTeamsByWinningCountRange(@Param("min")int startRange,@Param("max")int maxRanger);
	
}

package com.nt.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "DATA_JDBC_IPL_TEAM")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IPLTeam {
	
	@Id
	private Integer teamid;
	private String teamname;
	private String captainname;
	private String owner;
	private Integer titlewinningteam;
}

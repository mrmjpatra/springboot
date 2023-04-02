package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.JobSeekerInfo;

public interface IJobSeekerRepo extends JpaRepository<JobSeekerInfo, Integer> {
	@Query("select resumePath from JobSeekerInfo where jsId=:id")
	public String getResumePath(Integer id);
	
	@Query("select photoPath from JobSeekerInfo where jsId=:id")
	public String getPhotoPath(Integer id);
	
}

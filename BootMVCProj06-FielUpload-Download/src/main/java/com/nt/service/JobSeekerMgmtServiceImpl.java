package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.JobSeekerInfo;
import com.nt.repository.IJobSeekerRepo;

@Service
public class JobSeekerMgmtServiceImpl implements IJobSeekerMgmtService {
	@Autowired
	private IJobSeekerRepo jsRepo;
	

	@Override
	public String registerJobSeeker(JobSeekerInfo info) {
		return "Job Seeker is saved with "+jsRepo.save(info).getJsId()+" id value";
	}
	@Override
	public List<JobSeekerInfo> fetchAllJobSeeker() {
		return jsRepo.findAll();
	}
	
	@Override
	public String fetchPhotoPathByJsId(Integer jsId) {
		return jsRepo.getPhotoPath(jsId);
	}
	@Override
	public String fetchResumePathByJsId(Integer jsId) {
		return jsRepo.getResumePath(jsId);
	}
}

package com.nt.listener;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobMonitoringListener implements JobExecutionListener {
	
	private long startTime,endTime;
	
	
	public JobMonitoringListener() {
		System.out.println("JobMonitoringListener.JobMonitoringListener()");
	}
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("job is about to begin "+new Date());
		startTime=System.currentTimeMillis();
		System.out.println("Job exection status "+jobExecution.getStatus());

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("job is completed "+new Date());
		endTime=System.currentTimeMillis();
		System.out.println("Job Status "+jobExecution.getStatus());
		System.out.println("Job execution time : "+(endTime-startTime));
		System.out.println("Job exit status "+jobExecution.getExitStatus());
	}

}

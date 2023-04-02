package com.nt.listener;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobMonitoringListener implements JobExecutionListener {
	private long startTime,endTime;
	@Override
	public void beforeJob(JobExecution jobExecution) {
		startTime=System.currentTimeMillis();
		System.out.println("JobMonitoringListener.beforeJob()");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		endTime=System.currentTimeMillis();
		System.out.println("Job completed at :: "+new Date());
		System.out.println("Job exection time :; "+(endTime-startTime));
		System.out.println("Job exection status ::"+jobExecution.getStatus());

	}

}

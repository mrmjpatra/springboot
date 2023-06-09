package com.nt.runner;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CsvToDBBatchProcessingRunner  {
	
	@Autowired
	private JobLauncher launcher;
	@Autowired
	private Job job;
	
	@Scheduled(cron = "0 * * * * *")
	public void runBatch() throws Exception {
		JobParameters param=new JobParametersBuilder().addLong("sysTime", System.currentTimeMillis()).toJobParameters();
		JobExecution execution=launcher.run(job, param);
		System.out.println("Job completion status : "+execution.getStatus());
	}

}

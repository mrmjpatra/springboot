package com.nt.runner;

import javax.batch.runtime.JobExecution;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BatchProcessingTestRunner implements CommandLineRunner {
	@Autowired
	private JobLauncher launcer;
	@Autowired
	private Job job;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("BatchProcessingTestRunner.run()");
		JobParameters parameter=new JobParametersBuilder()
									.addLong("time",System.currentTimeMillis()).toJobParameters();
		org.springframework.batch.core.JobExecution execution=launcer.run(job, parameter);

	}

}

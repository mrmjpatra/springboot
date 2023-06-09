package com.nt.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nt.listener.JobMonitoringListener;
import com.nt.processor.BookDetailsProcessor;
import com.nt.reader.BookDetailsReader;
import com.nt.writer.BookDetailsWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	private JobMonitoringListener jobListener;
	@Autowired
	private JobBuilderFactory jobFactory;
	@Autowired
	private StepBuilderFactory stepFactory;
	@Autowired
	private BookDetailsReader reader;
	@Autowired
	private BookDetailsProcessor processor;
	@Autowired
	private BookDetailsWriter writer;
	
	@Bean("step1")
	public Step createStep() {
		return stepFactory.get("step1")
				.<String,String>chunk(2)
				.reader(reader)
				.writer(writer)
				.processor(processor)
				.build();
	}
	
	@Bean("job1")
	public Job createJob() {
		System.out.println("BatchConfig.createJob()");
		return jobFactory.get("job1")
				.incrementer(new RunIdIncrementer())
				.listener(jobListener)
				.start(createStep())
				.build();
	}
	
	
}

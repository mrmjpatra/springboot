package com.nt.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.nt.document.OExamResult;
import com.nt.listener.JobMonitoringListener;
import com.nt.model.IExamResult;
import com.nt.processor.ExamResultItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jbFactory;
	@Autowired
	private StepBuilderFactory sbFactory;
	@Autowired
	private ExamResultItemProcessor processor;
	@Autowired
	private MongoTemplate template;
	@Autowired
	private JobMonitoringListener listener;
	
	@Bean
	public FlatFileItemReader<IExamResult> createReader(){
		return  new FlatFileItemReaderBuilder<IExamResult>()
				                  .name("csv-reader")
				                  .resource(new FileSystemResource("/Users/mrutyunjayapatra/csvs/TopBrains.csv"))
				                  .delimited().delimiter(",")
				                  .names("id","dob","semester","percentage")
				                  .targetType(IExamResult.class)
				                  .build();
	}
	
	@Bean
	public  MongoItemWriter<OExamResult> createWriter(){
		  return  new MongoItemWriterBuilder<OExamResult>()
				        .collection("superBrains1")
				        .template(template)
				        .build();
	}
	
	@Bean(name="step1")
	public Step createStep() {
		return sbFactory.get("step1")
				.<IExamResult,OExamResult>chunk(5)
  		      	.reader(createReader())
  		      	.writer(createWriter())
  		      	.processor(processor)
  		      	.build();
	}
	
	@Bean(name="job1")
	public Job createJob() {
		return jbFactory.get("job1")
					.incrementer(new RunIdIncrementer())
					.listener(listener)
					.start(createStep())
					.build();
	}
}

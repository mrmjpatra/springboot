package com.nt.config;


import java.util.HashMap;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.nt.listener.JobMonitoringListener;
import com.nt.model.IExamResult;
import com.nt.model.OExamResult;
import com.nt.processor.ExamResultItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private JobBuilderFactory jbFactory;
	@Autowired
	private StepBuilderFactory sbFactory;
	@Autowired
	private JobMonitoringListener listener;
	@Autowired
	private ExamResultItemProcessor processor;
	@Autowired
	private	 MongoTemplate mongoTemplate;
	
	
	@Bean
	public MongoItemReader<IExamResult> createReader(){
		System.out.println("BatchConfig.createReader()");
		MongoItemReader<IExamResult> reader = new MongoItemReader<>();
		reader.setCollection("superBrains1");
	    reader.setTemplate(mongoTemplate);
	    reader.setSort(new HashMap<String, Sort.Direction>() {{
	      put("_id", Direction.DESC);
	    }});
	    reader.setTargetType(IExamResult.class);
	    reader.setQuery("{ }");
	    return reader;
	}
	
	@Bean
	public FlatFileItemWriter<OExamResult> createWriter(){
		System.out.println("BatchConfig.createWriter()");
		return new FlatFileItemWriterBuilder<OExamResult>()
				.name("writer")
				.resource(new FileSystemResource("//Users//mrutyunjayapatra//csvs1//TopBrains.csv"))
				.delimited().delimiter(",")
				.names("id","dob","semester","percentage")
				.build();		
	}
	
	@Bean(name="step")
	public Step createStep() {
		System.out.println("BatchConfig.createStep()");
		return sbFactory.get("step")
				.<IExamResult,OExamResult>chunk(5)
  		      	.reader(createReader())
  		      	.writer(createWriter())
  		      	.processor(processor)
  		      	.build();
	}
	
	@Bean(name="job")
	public Job createJob() {
		return jbFactory.get("job1")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(createStep())
				.build();
	}
}

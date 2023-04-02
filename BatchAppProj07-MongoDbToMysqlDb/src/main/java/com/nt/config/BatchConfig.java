package com.nt.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.nt.listener.JobMonitoringListener;
import com.nt.model.ExamResult;
import com.nt.processor.ExamResultItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	private StepBuilderFactory sbFactory;
	@Autowired
	private JobBuilderFactory jbFactory;
	@Autowired
	private JobMonitoringListener listener;
	@Autowired
	private ExamResultItemProcessor processor;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private DataSource ds;

	@Bean
	public MongoItemReader<ExamResult> createReader() {
		System.out.println("BatchConfig.createReader()");
		MongoItemReader<ExamResult> reader = new MongoItemReader<>();
		reader.setCollection("superBrains1");
		reader.setTemplate(mongoTemplate);
		reader.setSort(new HashMap<String, Sort.Direction>() {
			{
				put("_id", Direction.DESC);
			}
		});
		reader.setTargetType(ExamResult.class);
		reader.setQuery("{ }");
		return reader;
	}
	
	@Bean
	public  JdbcBatchItemWriter<ExamResult> createWriter(){
		return new  JdbcBatchItemWriterBuilder<ExamResult>()
				              .dataSource(ds)
				              .sql("INSERT INTO Exam_Result_Batch VALUES(:id,:dob,:semester,:percentage)")
				              .beanMapped()
				              .build();
		
	}
	
	@Bean(name = "step")
	public Step createStep() {
		return sbFactory.get("step1")
					.<ExamResult,ExamResult>chunk(10)
					.reader(createReader())
					.processor(processor)
					.writer(createWriter())
					.build();
				
	}
	
	@Bean(name="job")
	public Job createJob() {
		return jbFactory
					.get("job")
					.incrementer(new RunIdIncrementer())
					.listener(listener)
					.start(createStep())
					.build();
	}
	
	
	
}

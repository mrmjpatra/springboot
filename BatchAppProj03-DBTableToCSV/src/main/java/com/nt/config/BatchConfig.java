package com.nt.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.nt.listener.JobMonitoringListener;
import com.nt.model.ExamResult;
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
	private DataSource ds;
	
	@Bean
	public JdbcCursorItemReader<ExamResult> createReader(){
		return new JdbcCursorItemReaderBuilder<ExamResult>()
										.name("reader")
										.dataSource(ds)
										.sql("select id,dob,percentage,semester from exam_result")
										.beanRowMapper(ExamResult.class)
										.build();
	}
	
	@Bean
	public FlatFileItemWriter<ExamResult> createWriter(){
		return new FlatFileItemWriterBuilder<ExamResult>()
				.name("writer")
				.resource(new FileSystemResource("//Users//mrutyunjayapatra//csvs//TopBrains.csv"))
				.delimited().delimiter(",")
				.names("id","dob","semester","percentage")
				.build();		
	}
	
	@Bean(name = "step1")
	public Step createStep() {
		return sbFactory.get("step1")
						.<ExamResult,ExamResult>chunk(10)
						.reader(createReader())
						.processor(processor)
						.writer(createWriter())
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

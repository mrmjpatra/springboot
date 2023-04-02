package com.nt.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.nt.listener.JobMonitoringListener;
import com.nt.model.Employee;
import com.nt.processor.EmployeeItemProcessor;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
	@Autowired
	private JobBuilderFactory jbFactory;
	@Autowired
	private  StepBuilderFactory sbFactory;
	@Autowired
	private  JobMonitoringListener  listener;
	@Autowired
	private  EmployeeItemProcessor processor;
	@Autowired
	private   DataSource ds;
	
//	@Bean
//	public ItemReader<Employee> createReader(){
//		//create object for flat item reader
//		 FlatFileItemReader<Employee> reader=new FlatFileItemReader<Employee>();
//		 //set source csv file location
//		 reader.setResource(new ClassPathResource("Employee_info.csv"));
//		 reader.setLineMapper(new DefaultLineMapper<Employee>() {{
//				setLineTokenizer(new DelimitedLineTokenizer() {{
//					 setDelimiter(",");
//					 setNames("empno","empname","empaddrs","salary");
//				}});
//				setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {{
//					setTargetType(Employee.class);
//				}});
//			}});
//		 
//		 return reader;
//	}
	
	
	//reader
	@Bean(name="reader")
	public  FlatFileItemReader<Employee>  createReader(){
		return new  FlatFileItemReaderBuilder<Employee>()
				                 .name("file-reader")
				                 .resource(new ClassPathResource("EmployeesInfo.csv"))
				                 .delimited().delimiter(",")
				                 .names("empno","empname","empaddrs","salary")
				                 .targetType(Employee.class)
				                 .build();
	}
	
	//writer
	
	
	/*@Bean
	public ItemWriter<Employee> createWriter(){
		JdbcBatchItemWriter<Employee> writer=new JdbcBatchItemWriter<>();
		
		writer.setDataSource(ds);
		//set sql query  with named params
		writer.setSql("INSERT INTO BATCH_EMPLOYEE VALUES(:empno,:ename,:salary,:eadd,:grossSalary,:netSalary");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>());
		return writer;
	}*/
	
	@Bean(name="writer")
	public  JdbcBatchItemWriter<Employee> createWriter(){
		return new  JdbcBatchItemWriterBuilder<Employee>()
				              .dataSource(ds)
				              .sql("INSERT INTO BATCH_EMPLOYEE VALUES(:empno,:empname,:salary,:empaddrs,:grossSalary,:netSalary)")
				              .beanMapped()
				              .build();
		
	}
	
	@Bean(name="step1")
	public  Step createStep1() {
		return  sbFactory.get("step1")
				     .<Employee,Employee>chunk(3)
				     .reader(createReader())
				     .writer(createWriter())
				     .processor(processor)
				     .build();
	}//method
	
	@Bean(name="job1")
	public   Job createJob1() {
		return jbFactory.get("job1")
				    .listener(listener)
				    .incrementer(new RunIdIncrementer())
				    .start(createStep1())
				    .build();
	}
	

}

package com.wlanboy.cloudtask;

import java.util.ArrayList;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@EnableTask
@EnableBatchProcessing
@SpringBootApplication
public class CloudtaskApplication {

	public static void main(String[] args) {
		ArrayList<String> scripts = new ArrayList<String>(Arrays.asList(args));
		scripts.add("time=" + System.currentTimeMillis());
		SpringApplication.run(CloudtaskApplication.class, scripts.stream().toArray(String[]::new));
	}

	@Autowired
	private DataSource dataSource;

    @Bean
    public CloudtaskApplicationConfigurer getTaskConfigurer()
    {
		return new CloudtaskApplicationConfigurer(dataSource);
    }
    
	@Bean
	public TaskListenerLogger taskListener() {
		return new TaskListenerLogger();
	}

	@Bean
	public ParameterLogger parameterLogger() {
		return new ParameterLogger();
	}
}

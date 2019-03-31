package com.wlanboy.cloudtask.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wlanboy.cloudtask.tasks.SimpleJobFirstTask;
import com.wlanboy.cloudtask.tasks.SimpleJobSecondTask;

@Configuration
public class SimpleJobConfiguration {

	private static Logger logger = LoggerFactory.getLogger(SimpleJobConfiguration.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job customerReportJob() {
		Job simpleJob = jobBuilderFactory.get("two-step-simple-job").start(simpleJobFirstStep()).next(simpleJobSecondStep())
				.build();
		return simpleJob;
	}

	@Bean
	public Step simpleJobFirstStep() {
		logger.info("creating first step");
		return stepBuilderFactory.get("first-step").tasklet(taskletOne()).build();
	}

	@Bean
	public Step simpleJobSecondStep() {
		logger.info("creating second step");
		return stepBuilderFactory.get("second-step").tasklet(taskletTwo()).build();
	}

	@Bean
	public Tasklet taskletOne() {
		return new SimpleJobFirstTask();
	}

	@Bean
	public Tasklet taskletTwo() {
		return new SimpleJobSecondTask();
	}
}

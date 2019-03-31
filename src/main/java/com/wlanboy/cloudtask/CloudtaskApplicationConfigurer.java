package com.wlanboy.cloudtask;

import javax.sql.DataSource;

import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;

public class CloudtaskApplicationConfigurer extends DefaultTaskConfigurer {

	public CloudtaskApplicationConfigurer(DataSource dataSource) {
		super(dataSource);
	}
}

package com.wlanboy.cloudtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class ParameterLogger implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(ParameterLogger.class.getCanonicalName());

	@Override
	public void run(String... args) throws Exception {
		logger.info("ParameterLogger: " + String.join(", ", args));
	}

}

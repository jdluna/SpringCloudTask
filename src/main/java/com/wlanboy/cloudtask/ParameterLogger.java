package com.wlanboy.cloudtask;

import java.util.logging.Logger;

import org.springframework.boot.CommandLineRunner;

public class ParameterLogger implements CommandLineRunner {

	private static final Logger logger = Logger.getLogger(ParameterLogger.class.getCanonicalName());

	@Override
	public void run(String... args) throws Exception {
		logger.info("ParameterLogger: " + String.join(", ", args));
	}

}

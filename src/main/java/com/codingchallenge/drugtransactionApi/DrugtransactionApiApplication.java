package com.codingchallenge.drugtransactionApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DrugtransactionApiApplication {

	public static void main(String[] args) {
		  final Logger logger = LoggerFactory.getLogger(DrugtransactionApiApplication.class);
		  logger.info("Starting DrugtransactionApiApplication ");
		SpringApplication.run(DrugtransactionApiApplication.class, args);
	}

}

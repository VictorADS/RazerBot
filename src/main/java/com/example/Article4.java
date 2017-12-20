package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan
public class Article4 {
	private static final Logger log = LogManager.getRootLogger();

	public static void main(String[] args) {
		log.error("Application has started");
		SpringApplication.run(
				new Object[] { Article4.class }, args);
	}
}
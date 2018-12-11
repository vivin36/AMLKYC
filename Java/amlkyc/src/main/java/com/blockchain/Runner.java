package com.blockchain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com")
@SpringBootApplication
@EnableScheduling
public class Runner extends SpringBootServletInitializer {
	
	private static Logger logger = LogManager.getLogger(Runner.class);
	
	public static void main(String[] args) {
		logger.info("Application being started!!!");
		new Runner().configure(new SpringApplicationBuilder(Runner.class)).run(args);
	}
}
package com.blockchain;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com")
@SpringBootApplication
public class Runner extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		new Runner().configure(new SpringApplicationBuilder(Runner.class)).run(args);
	}
}
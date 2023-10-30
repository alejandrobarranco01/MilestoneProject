package com.gcu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.gcu")
public class MilestoneProject {

	public static void main(String[] args) {
		SpringApplication.run(MilestoneProject.class, args);
	}

}

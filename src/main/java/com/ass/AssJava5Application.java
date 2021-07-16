package com.ass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AssJava5Application {

	public static void main(String[] args) {
		SpringApplication.run(AssJava5Application.class, args);
	}

}

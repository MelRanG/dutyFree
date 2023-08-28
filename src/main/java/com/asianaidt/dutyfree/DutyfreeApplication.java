package com.asianaidt.dutyfree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DutyfreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DutyfreeApplication.class, args);
	}

}

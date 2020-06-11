package com.altimetrik.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Challenge {

	public static void main(String[] args) {
		SpringApplication.run(Challenge.class, args);
	}

}


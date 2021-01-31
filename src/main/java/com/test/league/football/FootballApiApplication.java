package com.test.league.football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FootballApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballApiApplication.class, args);
	}

}

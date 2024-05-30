package com.example.vrcapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.vrcapp.repository"})
public class VrcappApplication {

	public static void main(String[] args) {
		SpringApplication.run(VrcappApplication.class, args);
	}

}

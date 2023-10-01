package com.demo.hackyeah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.demo.hackyeah")
@SpringBootApplication(scanBasePackages = {"com.demo.hackyeah"})
@EntityScan("com.demo.hackyeah")
public class HackYeahApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackYeahApplication.class, args);
	}
}


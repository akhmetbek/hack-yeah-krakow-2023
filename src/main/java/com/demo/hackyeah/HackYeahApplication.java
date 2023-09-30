package com.demo.hackyeah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.demo.hackyeah.service", "com.demo.hackyeah.controller"})
public class HackYeahApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackYeahApplication.class, args);
	}
}


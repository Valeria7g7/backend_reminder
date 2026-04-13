package com.valeria.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "com.valeria.backend")
public class BackendApplication {

	//public static void main(String[] args) {
	//SpringApplication.run(BackendApplication.class, args);
//	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}

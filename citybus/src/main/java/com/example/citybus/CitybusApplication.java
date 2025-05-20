package com.example.citybus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CitybusApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitybusApplication.class, args);
		 System.out.println(new BCryptPasswordEncoder().encode("admin123"));
	}

}

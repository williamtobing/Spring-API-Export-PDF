package com.example.numbersix;

import com.example.numbersix.controller.RestAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NumbersixApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumbersixApplication.class, args);
		System.out.println("Started!");
		RestAPI.callRest();
	}

}

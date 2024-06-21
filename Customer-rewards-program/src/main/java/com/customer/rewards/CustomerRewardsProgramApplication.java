package com.customer.rewards;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Customer Reward program",
				description = "A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.",
				version = "v1.0"
		)
)
public class CustomerRewardsProgramApplication {

	public static void main(String[] args) {

		SpringApplication.run(CustomerRewardsProgramApplication.class, args);
		System.out.println("Welcome");
	}

}

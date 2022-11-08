package com.example.Consumer.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumerApplication {
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	public CommandLineRunner CommandLineRunnerBean() {
		return (args) -> {
			System.out.println("Consumer server starting");
			ConsumerService.InitializeWorkingThreads();
			for (String arg : args) {
				System.out.println(arg);
			}
		};
	}
}

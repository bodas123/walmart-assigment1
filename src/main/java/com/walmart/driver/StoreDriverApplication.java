package com.walmart.driver;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("com.walmart.driver.entity")
@EnableJpaRepositories("com.walmart.driver.repository")
@ComponentScan("com.walmart.driver")
@SpringBootApplication
public class StoreDriverApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreDriverApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

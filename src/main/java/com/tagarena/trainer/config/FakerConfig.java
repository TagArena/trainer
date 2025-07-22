package com.tagarena.trainer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.javafaker.Faker;

@Configuration
public class FakerConfig {


	@Bean
	Faker getFaker() {

		return new Faker();
	}
}

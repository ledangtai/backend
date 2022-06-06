package com.abc.FlowerBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.abc.controller"})
@EntityScan("com.abc.entity")
@ComponentScan({"com.abc.controller","com.abc.jwt.configs","com.abc.jwt.filters","com.abc.jwt.util"})
@EnableJpaRepositories("com.abc.repository")
@SpringBootApplication
public class FlowerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowerBackendApplication.class, args);
	}

}

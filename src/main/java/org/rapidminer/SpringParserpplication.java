package org.rapidminer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("org.rapidminer.*")
public class SpringParserpplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringParserpplication.class, args);
	}
}
package com.gsretail.gst.b2e.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Search application.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.gsretail.gst.b2e.search"})
public class SearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}
}

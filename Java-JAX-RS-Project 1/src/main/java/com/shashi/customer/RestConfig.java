package com.shashi.customer;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig extends ResourceConfig {

	/*
	 * here we are doing basic package configuration which is required by Spring
	 * project, since we are using spring boot project for this current project
	 */
	
	public RestConfig() {
		this.packages("com.shashi.customer.resources");
		this.packages("com.shashi.customer.providers");
	}

}

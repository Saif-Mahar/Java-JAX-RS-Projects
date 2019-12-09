package com.shashi.customer.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

		// This is just the simple Test Class

@Path("/hello")
public class HelloWorld {

	@GET
	public String welcome() {
		return "Welcome to Rest Resources";
	}

}

package com.shashi.customer.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/*for this project we are using in memory DataBase with  serialization and this class is acting
as a DataBase to store the elements */
		
public class CustomerDB implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<Long, Customer> customers = new HashMap<>();

	private Long id = 1000L;

	public Map<Long, Customer> getCustomers() {
		return customers;
	}

	public Long nextId() {
		id++;
		return id;
	}

}

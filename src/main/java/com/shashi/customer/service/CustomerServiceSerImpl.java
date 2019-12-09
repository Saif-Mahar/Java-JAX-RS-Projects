package com.shashi.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.shashi.customer.exception.AppExceptionUtil;
import com.shashi.customer.exception.InvalidDataException;
import com.shashi.customer.model.Customer;
import com.shashi.customer.model.CustomerDB;

@Service
public class CustomerServiceSerImpl implements CustomerService {

	
	// this is the Code Logic for @POST method to create the Customer
	@Override
	public Customer create(Customer customer) {
		validateCustomer(customer);
		final CustomerDB db = SerializeUtils.deserialize();
		customer.setId(db.nextId());
		db.getCustomers().put(customer.getId(), customer);
		SerializeUtils.serialize(db);
		return customer;
	}

	// this is the code Logic for @PUT method to update the Customer
	@Override
	public void modify(Customer customer) {
		validateCustomer(customer);
		final CustomerDB db = SerializeUtils.deserialize();
		if (db.getCustomers().containsKey(customer.getId())) {
			db.getCustomers().put(customer.getId(), customer);
			SerializeUtils.serialize(db);
		} else {
			throw AppExceptionUtil.EX_CUSTOMER_NOT_FOUND;
		}
	}

	// this is the code logic for @GET by ID, to find the Customer by ID
	@Override
	public Customer find(Long id) {
		final CustomerDB db = SerializeUtils.deserialize();
		if (db.getCustomers().containsKey(id)) {
			return db.getCustomers().get(id);
		} else {
			throw AppExceptionUtil.EX_CUSTOMER_NOT_FOUND;
		}
	}

	// This is the Code Logic for @DELETE method to delete the Customer 
	@Override
	public void remove(Long id) {
		final CustomerDB db = SerializeUtils.deserialize();
		if (db.getCustomers().containsKey(id)) {
			db.getCustomers().remove(id);
			SerializeUtils.serialize(db);
		} else {
			throw AppExceptionUtil.EX_CUSTOMER_NOT_FOUND;
		}

	}

	// this is the code logic for @GET List, to get all the Customer
	@Override
	public List<Customer> getAll() {
		final CustomerDB db = SerializeUtils.deserialize();
		return new ArrayList<>(db.getCustomers().values());
	}

	
// Main Method was just  there to Run the code with in same Class/Layer insted of running it in some other Class
	public static void main(String[] args) {
		CustomerService cs = new CustomerServiceSerImpl();
		cs.remove(1001L);
		System.out.println(cs.getAll());
	}
	
	
	// this is the code logic for @GET for Search, to Search Customer by First and Last Name
	@Override
	public List<Customer> search(String firstName, String lastName) {
		List<Customer> results = new ArrayList<>();
		final CustomerDB db = SerializeUtils.deserialize();
		db.getCustomers().values().forEach(customer -> {
			if ((firstName != null && customer.getFirstName().contains(firstName))
					|| (lastName != null && customer.getLastName().contains(lastName))) {
				results.add(customer);
			}
		});
		return results;
	}

	// this is just a simple code for a Custome Exception  
	private void validateCustomer(Customer customer) {
		if (StringUtils.isEmpty(customer.getFirstName()) || StringUtils.isEmpty(customer.getLastName())) {
			throw new InvalidDataException("Invalid data to persist the customer");
		}
	}
}

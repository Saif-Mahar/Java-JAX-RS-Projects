package com.shashi.customer.service;

import java.util.List;

import com.shashi.customer.model.Customer;

// Basic Interface for Service Class 		

public interface CustomerService {

	Customer create(final Customer customer);

	void modify(final Customer customer);

	Customer find(Long id);

	void remove(Long id);

	List<Customer> getAll();

	List<Customer> search(final String firstName, final String lastName);

}

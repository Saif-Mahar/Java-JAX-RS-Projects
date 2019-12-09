package com.shashi.customer.exception;

public class AppExceptionUtil {

	// this is the basic exception message for Customer NOt Found
	
	public final static CustomerNotFoundException EX_CUSTOMER_NOT_FOUND = new CustomerNotFoundException(
			"Customer not found in system");

	private AppExceptionUtil() {

	}

}

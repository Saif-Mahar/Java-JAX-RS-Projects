package com.shashi.customer.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.shashi.customer.model.Customer;
import com.shashi.customer.model.CustomerDB;


/*This Utils Call we are using for Serializtion, we have defined two method to Serialize the objects
and also DeSerialize objects.*/

public class SerializeUtils {

	/*
	 * this the file location where the POJO Objects are being saved in file in
	 * binary format by using Serializtion.
	 */
	private final static String FILE_LOC = "C:\\develop\\config\\Customer.ser";

	// This is a simple Constructor no-argument for the Class
	private SerializeUtils() {
	}

	// this is the Serializtion Code Logic, we are using CustomerDB Class which our in-memory DataBase is.
	public static void serialize(CustomerDB customerDb) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_LOC))) {
			out.writeObject(customerDb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// this is the De-serializtion Code Logic, we are using CustomerDB Class which our in-memory DataBase is.
	public static CustomerDB deserialize() {
		CustomerDB result = new CustomerDB();
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_LOC))) {
			result = (CustomerDB) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

// This is just the main method to run the code within same Class instead of creating new Class/Layer to run code
	public static void main(String[] args) {
		CustomerDB customers = new CustomerDB();
		// Customer customer = new Customer(customers.nextId(), "James", "Norman");
		Customer customer = Customer.builder().firstName("James").lastName("Norman").build();
		customers.getCustomers().put(customer.getId(), customer);
		SerializeUtils.serialize(customers);
		customers = SerializeUtils.deserialize();
		System.out.println(customers.getCustomers());
	}

}

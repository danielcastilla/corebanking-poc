package org.dancas.customer.service;

import java.util.List;

import org.dancas.customer.payload.Customer;

public interface CustomerService {

	Customer create(Customer customer);
	
	Customer getById(String id);
	
	List<Customer> getAll();
	
	String deleteById(String id);
	
}

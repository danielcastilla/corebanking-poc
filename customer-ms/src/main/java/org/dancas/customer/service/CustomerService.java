package org.dancas.customer.service;

import org.dancas.customer.payload.Customer;

public interface CustomerService {

	Customer create(Customer customer);
	
	Customer getById(String id);
	
}

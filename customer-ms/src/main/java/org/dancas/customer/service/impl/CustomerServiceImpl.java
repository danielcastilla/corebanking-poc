package org.dancas.customer.service.impl;

import org.dancas.customer.payload.Customer;
import org.dancas.customer.repository.CustomerRepository;
import org.dancas.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getById(String id) {
		return customerRepository.findOne(id);
	}

	
	
	
}

package org.dancas.customer.controller;

import java.util.List;

import org.dancas.customer.payload.Customer;
import org.dancas.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
	
	@Autowired 
	CustomerService customerService;

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Customer> create(@RequestBody Customer customer){
		return new ResponseEntity<Customer>(customerService.create(customer), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Customer> getById(@PathVariable String id){
		return new ResponseEntity<Customer>(customerService.getById(id), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAll(){
		List<Customer> customers =  customerService.getAll();
		return new ResponseEntity<List<Customer>> (customers, HttpStatus.OK);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	public ResponseEntity deleteById(@PathVariable String id){		
		return new ResponseEntity (customerService.deleteById(id), HttpStatus.OK);
	}
	
}

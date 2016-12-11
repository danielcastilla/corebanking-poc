package org.dancas.customer.repository;

import org.dancas.customer.payload.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String>{

}

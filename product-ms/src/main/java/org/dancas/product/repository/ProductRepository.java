package org.dancas.product.repository;

import org.dancas.product.payload.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>{

}

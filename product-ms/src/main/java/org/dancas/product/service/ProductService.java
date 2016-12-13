package org.dancas.product.service;

import java.util.List;

import org.dancas.product.payload.Product;

public interface ProductService {

	public Product create(Product product);
	
	public List<Product> getAll();
	
	public Product getById(String id);
	
}

package org.dancas.product.service.impl;

import java.util.List;

import org.dancas.product.payload.Product;
import org.dancas.product.repository.ProductRepository;
import org.dancas.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product create(Product product) {
		return productRepository.insert(product);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getById(String id) {
		return productRepository.findOne(id);
	}

}

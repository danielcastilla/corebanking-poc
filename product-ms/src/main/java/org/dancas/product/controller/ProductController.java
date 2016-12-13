package org.dancas.product.controller;

import java.util.List;

import org.dancas.product.payload.Product;
import org.dancas.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

	@Autowired 
	ProductService productService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Product> create(@RequestBody Product product){
		return new ResponseEntity<Product>(productService.create(product), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Product> getById(@PathVariable String id){
		return new ResponseEntity<Product>(productService.getById(id), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Product>> getAll(){
		return new ResponseEntity<List<Product>>(productService.getAll(), HttpStatus.OK);
	}
	
	
}

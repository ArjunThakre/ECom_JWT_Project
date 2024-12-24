package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;

public interface ProductService {

	  public Product createProduct(Product product);
	   
	   public List<Product> allProduct();
	   
	   public Product findProduct(int id);
	   
	   
}

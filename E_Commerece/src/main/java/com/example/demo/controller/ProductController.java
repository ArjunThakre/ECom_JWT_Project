package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService pdservice;
	
	
	  
	@PostMapping("/product")
	    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
	        Product savedProduct = pdservice.createProduct(product);
	        return ResponseEntity.ok(savedProduct); // Returns HTTP 200 with the saved product
	    }
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> listProducts() {
        List<Product> products = pdservice.allProduct();
        return ResponseEntity.ok(products); // Returns HTTP 200 with the list of products
    }
	
	
}
package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productrepo;
	
	@Override
	public Product createProduct(Product product) {
		return productrepo.save(product);
	}

	@Override
	public List<Product> allProduct() {
		return productrepo.findAll() ;
	}

	@Override
	public Product findProduct(int id) {
		return productrepo.findById(id).orElseThrow();
	}

}

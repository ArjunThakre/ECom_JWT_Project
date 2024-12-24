package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message="Product Name is Mandatory")
	private String product_name;
	
	@NotBlank(message="Product Price is Mandatory")
	private Double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Product(int id, @NotBlank(message = "Product Name is Mandatory") String product_name,
			@NotBlank(message = "Product Price is Mandatory") Double price) {
		super();
		this.id = id;
		this.product_name = product_name;
		this.price = price;
	}

	public Product() {
		super();
	}


	 
	

}

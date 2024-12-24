package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductPriceController {
	
	 private final Map<Integer, Double> productPrices = new HashMap<>();
	 private final Map<Integer, String> purchases = new HashMap<>();

//	    public ProductPriceController() {
//	        // Initialize product prices
//	        productPrices.put(1, 100.0);
//	        productPrices.put(2, 200.0);
//	        productPrices.put(3, 300.0);
//	    }
	 
	 @PostMapping("/buy/{product_id}")
	    public Map<String, Object> buyProduct(@PathVariable("product_id") int productId) {
	   
	        purchases.put(productId, "Purchased");
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Purchase successful");
	        response.put("product_id", productId);
	        return response;
	    }
     
	    @Scheduled(fixedRate = 5000)
	    public void simulatePriceUpdates() {
	        Random random = new Random();
	        productPrices.forEach((productId, price) -> {
	            double newPrice = price + (random.nextDouble() - 0.5) * 10;
	            productPrices.put(productId, Math.round(newPrice * 100.0) / 100.0);
	        });
	    }
	    
	    @MessageMapping("/ws/product-price/{product_id}")
	    @SendTo("/topic/product-price")
	    public Map<String, Object> sendRealTimePrice(@PathVariable("product_id") int productId) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("product_id", productId);
	        response.put("real_time_price", productPrices.getOrDefault(productId, 0.0));
	        return response;
	    }

}

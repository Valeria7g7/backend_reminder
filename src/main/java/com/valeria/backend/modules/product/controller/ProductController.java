package com.valeria.backend.modules.product.controller;
import org.springframework.web.bind.annotation.*;
import com.valeria.backend.modules.product.model.Product;
import com.valeria.backend.modules.product.service.ProductService;

import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;
//endpoints
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	 private final ProductService service;
	 public ProductController(ProductService service) {
		 this.service=service;
	 }
	 //@GetMapping("/api/product/getProducts")
	 @GetMapping("/getProducts")
	    public List<Product> getProducts() {
		 return service.getAllProducts();

	       /* List<Product> products = List.of(
	            new Product(
	                1L,
	                "Laptop",
	                "Laptop gamer",
	                15000.00,
	                LocalDateTime.now()
	            ),
	            new Product(
	                2L,
	                "Mouse",
	                "Mouse inalámbrico",
	                500.00,
	                LocalDateTime.now()
	            )
	        );*/
		  

	       // return products;
	    }
	
	@GetMapping("/api/product/test")
    public String test() {
       return "ok";
    }
}

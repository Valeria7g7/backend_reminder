package com.valeria.backend.modules.product.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.valeria.backend.modules.product.dto.ProductResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.valeria.backend.modules.product.model.Product;
import com.valeria.backend.modules.product.service.ProductService;

import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;
import com.valeria.backend.dto.ApiResponse;
import com.valeria.backend.response.PaginatedResponse;
import com.valeria.backend.response.PaginationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.servlet.http.HttpServletRequest;
//endpoints
@RestController
@RequestMapping("/api/product")
public class ProductController  {
	
	 private final ProductService service;
	 public ProductController(ProductService service) {
		 this.service=service;
	 }
	 //@GetMapping("/api/product/getProducts")
//	 @PostMapping("/search")
//	    public List<Product> getProducts() {
//		 System.out.println("valeriay");
//		// System.out.println("consultando productos7667676767");
//		 return service.getAllProducts();
//	    }
	  @PostMapping("/search")
	    public PaginatedResponse<Product> getProducts(Pageable pageable, HttpServletRequest request) {
	        Page<Product> page = service.getAllProducts(pageable);
	        return PaginationMapper.map( page, request);
	    }
	 @PostMapping//("/")
	 public ResponseEntity<?> save(@RequestBody Product product) {
		 try {
		 System.out.println("producto request "+product.getName());
		  product = this.service.save(product);
		// ProductResponse response=new ProductResponse(product);
		 return ResponseEntity.ok(new ApiResponse<>(product));
		 
		 }catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					 .body(e.getMessage());
			 
		 }
	 }
	 @PutMapping("/{id}")
	 public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
		 try {
		 System.out.println("sabe product121212");
		  product = this.service.update(id,product);
		// ProductResponse response=new ProductResponse(product);
		 return ResponseEntity.ok(new ApiResponse<>(product));
		 
		 }catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					 .body(e.getMessage());
			 
		 }
	 }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<String>delete(@PathVariable Long id){
		 try {
			 this.service.delete(id);
			 return ResponseEntity.ok("Registro eliminado correctamente");
		 }catch(Exception e){
			 return ResponseEntity.status(HttpStatus.CONFLICT)
					 .body(e.getMessage());
			 
		 }
		 
	 }
}

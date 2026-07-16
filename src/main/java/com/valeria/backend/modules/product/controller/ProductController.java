package com.valeria.backend.modules.product.controller;
import com.valeria.backend.dto.*;
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
import org.springframework.data.domain.PageRequest;
//endpoints
@RestController
@RequestMapping("/api/product")
public class ProductController  {
	
	 private final ProductService service;
	 public ProductController(ProductService service) {
		 this.service=service;
	 }

	  @PostMapping("/search")
	    public PaginatedResponse<Product> getProducts(@RequestBody SearchRequest request, HttpServletRequest httpRequest) {
	       Pageable pageable=PageRequest.of(request.getPage()-1,request.getLimit());
	       Page<Product> page=service.getAllProducts(pageable,request);
	       return PaginationMapper.map( page, httpRequest);
	    }
	 @PostMapping//("/")
	 public ResponseEntity<?> save(@RequestBody Product product) {
		 try {
		 System.out.println("producto request "+product.getName());
		 Product pp=new Product();
		 
		  product = this.service.save(product);
		// ProductResponse response=new ProductResponse(product);
		 return ResponseEntity.ok(new ApiResponse<>(product));
		 
		 }catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
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

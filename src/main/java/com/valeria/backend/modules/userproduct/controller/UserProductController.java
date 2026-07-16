package com.valeria.backend.modules.userproduct.controller;
import com.valeria.backend.modules.userproduct.service.UserProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.valeria.backend.modules.userproduct.model.UserProduct;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.valeria.backend.dto.ApiResponse;
import com.valeria.backend.response.PaginatedResponse;
import com.valeria.backend.response.PaginationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.valeria.backend.dto.ApiResponse;
import com.valeria.backend.response.PaginatedResponse;
import com.valeria.backend.response.PaginationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.servlet.http.HttpServletRequest;
import com.valeria.backend.modules.userproduct.dto.*;
@RestController
@RequestMapping("/api/user-products")
public class UserProductController {
private final UserProductService service;
public UserProductController(UserProductService service) {
	this.service=service;
}
@PostMapping("/search")
public PaginatedResponse<UserProduct> getUserProducts(Pageable pageable, HttpServletRequest request ){
	Page<UserProduct> page=service.getAllUserProducts(pageable);
	 return PaginationMapper.map( page, request);
}
//@PostMapping
//public ResponseEntity<?>save(@RequestBody UserProductRequest request){
//	try {
//		System.out.println("vamos a registrar userproductuser");
//		 this.service.save(request);
//		return ResponseEntity.ok(new ApiResponse<>(null));
//		
//	}catch(Exception e) {
//		 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//				 .body(e.getMessage());
//	}
//	
//}
}

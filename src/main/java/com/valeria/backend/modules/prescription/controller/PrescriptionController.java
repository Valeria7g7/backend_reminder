package com.valeria.backend.modules.prescription.controller;
import com.valeria.backend.modules.prescription.model.Prescription;
import org.springframework.web.bind.annotation.PathVariable;


import com.valeria.backend.modules.product.service.ProductService;
import com.valeria.backend.modules.userproduct.service.UserProductService;
import com.valeria.backend.modules.prescription.model.Prescription;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;

import com.valeria.backend.modules.prescription.service.*;
import com.valeria.backend.modules.userproduct.dto.UserProductRequest;
import com.valeria.backend.response.PaginatedResponse;
import com.valeria.backend.response.PaginationMapper;
import com.valeria.backend.dto.ApiResponse;
import com.valeria.backend.dto.SearchRequest;
import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
	private PrescriptionService service;
	private UserProductService userProductService;
	public PrescriptionController(PrescriptionService service,UserProductService userProductService) {
		this.service=service;
		this.userProductService = userProductService;
	}
	@PostMapping("/search")
	public PaginatedResponse<Prescription> search(@RequestBody SearchRequest request, HttpServletRequest httpRequest){
		Pageable pageable=PageRequest.of(request.getPage()-1,request.getLimit());
		Page<Prescription> page=service.search(pageable, request);
		return PaginationMapper.map(page, httpRequest);
		
		
		
	}
	@PostMapping
	public ResponseEntity<?>save(@RequestBody UserProductRequest request){
		try {
			System.out.println("guardadno prerscripton");
			 Prescription pres=this.service.save(request);
			 userProductService.save(request, pres);
			 
			return ResponseEntity.ok(new ApiResponse<>(pres));
			
		}catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					 .body(e.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>delete(@PathVariable Long id){
		try {
			this.service.delete(id);
			return ResponseEntity.ok("Registro eliminado correctamente");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

}

package com.valeria.backend.modules.auth.controller;
import com.valeria.backend.modules.auth.service.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.valeria.backend.modules.auth.dto.AuthRequest;
import com.valeria.backend.modules.user.model.User;
import com.valeria.backend.modules.auth.dto.AuthResponse;



import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;
import com.valeria.backend.dto.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService service;
	public AuthController(AuthService service) {
		this.service=service;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		try {
		System.out.println("logeando");
//		System.out.println("logeando"+request.getEmail());
		 User user= service.login(request.getEmail(), request.getPassword());
		AuthResponse response=new AuthResponse(user);
        return ResponseEntity.ok(new ApiResponse<>(response));

		}catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(e.getMessage());
		}
		
	}
//	@PostMapping("/login")
//	public User login(@RequestBody AuthRequest request) {
//		System.out.println("logeando");
//		return service.login(request.getEmail(), request.getPassword());
//		
//	}
}

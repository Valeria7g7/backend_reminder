package com.valeria.backend.modules.auth.controller;
import org.springframework.data.domain.Pageable;
import com.valeria.backend.security.JwtService;
import org.springframework.security.core.Authentication;

import com.valeria.backend.modules.auth.repository.AuthRepository;
import com.valeria.backend.modules.auth.service.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.valeria.backend.modules.auth.dto.AuthRequest;
import com.valeria.backend.modules.user.model.User;
import com.valeria.backend.modules.auth.dto.AuthResponse;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.ExpiredJwtException;
import java.time.LocalDateTime;
import java.util.List;
import com.valeria.backend.dto.ApiResponse;
import com.valeria.backend.modules.user.service.UserService;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final UserService uService;
	private final AuthRepository authRepository;
	private final AuthService service;
	private final JwtService jwtService;
	public AuthController(AuthService service, AuthRepository authRepository,JwtService jwtService,UserService uService) {
		this.service=service;
		this.authRepository = authRepository;
		this.jwtService=jwtService;
		this.uService=uService;
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request, HttpServletResponse response) {
		try {
		
		if (request.getEmail().isEmpty() || request.getPassword().isEmpty()) {
				throw new RuntimeException("El email y password son requeridos");}
		 User user= service.login(request.getEmail(), request.getPassword());
		 String accesToken=service.generateToken(user.getId(), user.getEmail());
		 String refreshToken=service.generateRefreshToken(user.getId(), user.getEmail());
		 
		 // 🔥 COOKIE refresh token
		    ResponseCookie refreshCookie = ResponseCookie.from("refreshToken", refreshToken)
		            .httpOnly(true)
		            .secure(false) // true en producción con HTTPS
		            .path("/")//.path("/api/auth/refresh")
		            .maxAge(7 * 24 * 60 * 60) // 7 días
		            .sameSite("Lax")   //.sameSite("Strict")
		            .build();

		    response.addHeader("Set-Cookie", refreshCookie.toString());
		 
		 
		 
		 AuthResponse responseR=new AuthResponse(user,accesToken,refreshToken);
        return ResponseEntity.ok(new ApiResponse<>(responseR));

		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(e.getMessage());
		}
		
	}
	@PostMapping("/refresh")
	public ResponseEntity<?> refresh( @CookieValue(name = "refreshToken", required = false) String refreshToken,HttpServletResponse response) {	
		System.out.println("refresh token "+refreshToken);

	    if (refreshToken == null) {
	    	System.out.println("refresh token es null");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No refresh token");
	    }
	    try {
	    System.out.println("intentamos obtener email" );

	    if (!jwtService.isTokenValid(refreshToken)) {
	    	System.out.println("refresh token invalidoooooo");
	    	 ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
	                 .httpOnly(true)
	                 .secure(false) // true en producción con HTTPS
	                 .path("/")
	                 .maxAge(0)
	                 .build();
	    	 response.addHeader("Set-Cookie", deleteCookie.toString());
	    	
	    	 System.out.println("vamos a retornar aqui ");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token inválido");
	    }
	    String email = jwtService.extractEmail(refreshToken);
	    System.out.println("email  "+email );
	    System.out.println("es valido oken "+jwtService.isTokenValid(refreshToken) );
	    User user = uService.findUser(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	    String newAccessToken = jwtService.generateToken(user.getId(), user.getEmail());

	    return ResponseEntity.ok(newAccessToken);
	    
	    }catch (ExpiredJwtException e) {

	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body("Refresh token expirado");
	    }
	}
//	
	
	 @PostMapping("/register")
	 public ResponseEntity<?> register(@RequestBody User user) {
		 try {
		  user = this.uService.save(user);
		  String accesToken=service.generateToken(user.getId(), user.getEmail());
			 String refreshToken=service.generateRefreshToken(user.getId(), user.getEmail());
			 AuthResponse response=new AuthResponse(user,accesToken,refreshToken);
	        return ResponseEntity.ok(new ApiResponse<>(response));
		 }catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					 .body(e.getMessage());
			 
		 }
	 }


	 @GetMapping("/me")
	 public ResponseEntity<?> me(Authentication authentication) {
		 System.out.println("en metodo meee"+authentication);
		  UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	     return ResponseEntity.ok(userDetails);
	 }

}

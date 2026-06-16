package com.valeria.backend.modules.auth.service;
import com.valeria.backend.modules.user.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.*;
import com.valeria.backend.modules.user.repository.UserRepository;
import com.valeria.backend.security.JwtService;
import org.springframework.stereotype.*;
import java.util.Optional;
@Service
public class AuthService {
	private final UserRepository repository;
	private final JwtService jwtService;
	public AuthService(UserRepository repository,JwtService jwtService) {
		this.repository=repository;
		this.jwtService=jwtService;
	}
	
	public User login(String email, String password) {
		System.out.println("Buscando user111: ");
		System.out.println(email);
		System.out.println(password);

//		User user = repository.findByEmail(email)
//			    .orElseThrow(() -> new RuntimeException("User no encontrado"));
//		
//
//;
//		
		Optional <User> userOptional=repository.findByEmail(email);
		if(userOptional.isEmpty()) {
			throw new RuntimeException("Usuario no encontrado");
		}else {
			System.out.println("usuario encontrado : "+userOptional);
		}
		User user=userOptional.get();
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		if(!encoder.matches(password,user.getPassword())) {
			throw new RuntimeException("Contraseña incorrecta");	
		}
		
		return user;
		
	}
	public String generateToken(Long id, String email) {
		try {
		 String token=jwtService.generateToken(id, email);
		 return token;}catch(Exception e){
			 throw new RuntimeException("Error al generar el token"+e.getMessage());
		 }
		
	}
	public String generateRefreshToken(Long id, String email) {
		try {
		 String token=jwtService.generateRefreshToken(id, email);
		 return token;}catch(Exception e){
			 throw new RuntimeException("Error al generar el token"+e.getMessage());
		 }
		
	}
	public Optional<User> me(String email) {
		return repository.findByEmail(email);
	}
}

package com.valeria.backend.modules.auth.service;
import com.valeria.backend.modules.user.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.*;
import com.valeria.backend.modules.user.repository.UserRepository;

import org.springframework.stereotype.*;
import java.util.Optional;
@Service
public class AuthService {
	private final UserRepository userRepository;
	public AuthService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	public User login(String email, String password) {
		
		System.out.println("Buscando user: ");
		Optional <User> userOptional=userRepository.findByEmail(email);
//		System.out.println("Buscando user2 : "+userOptional);

//		if(userOptional.isEmpty()) {
//			throw new RuntimeException("Usuario no encontrado");
//		}else {
//			System.out.println("usuario encontrado : "+userOptional);
//		}
		User user=userOptional.get();
		
//		System.out.println("user: "+user);
//		if(!user.getPassword().equals(password)) {
//			throw new RuntimeException("Contraseña incorrecta");
//		}
		
		BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
		if(!encoder.matches(password,user.getPassword())) {
			throw new RuntimeException("Contraseña incorrecta");	
		}
		
		return user;
		
	}
}

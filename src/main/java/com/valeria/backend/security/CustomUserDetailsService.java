package com.valeria.backend.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.valeria.backend.modules.user.model.User;
import com.valeria.backend.modules.user.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService{
	private UserRepository repository;
	
	public CustomUserDetailsService(UserRepository repository) {
		this.repository=repository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=repository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));
		return new CustomUserDetails(
				user.getId(),
				user.getEmail(),
				user.getPassword());
	}
	

}

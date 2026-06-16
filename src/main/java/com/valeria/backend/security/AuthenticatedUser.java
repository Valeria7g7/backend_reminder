package com.valeria.backend.security;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {
	 public Long getUserId() {

	        Authentication auth =
	                SecurityContextHolder
	                        .getContext()
	                        .getAuthentication();

	        CustomUserDetails user =
	                (CustomUserDetails)
	                        auth.getPrincipal();

	        return user.getId();
	    }
}

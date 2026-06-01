package com.valeria.backend.modules.auth.dto;
import com.valeria.backend.modules.user.model.User;
public class AuthResponse {
	private User user;
	public AuthResponse(User user) {
		this.user=user;
		
	}
	
	public User getUser() {
		return this.user;
	}
	  public void setUser(User user) {
	        this.user = user;
	    }
}

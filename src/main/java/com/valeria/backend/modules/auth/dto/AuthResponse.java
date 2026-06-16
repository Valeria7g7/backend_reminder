package com.valeria.backend.modules.auth.dto;
import com.valeria.backend.modules.user.model.User;
public class AuthResponse {
	private User user;
	private String accesToken;
	private String refreshToken;
	public AuthResponse(User user,String accesToken,String refreshToken) {
		this.user=user;
		this.accesToken=accesToken;
		this.refreshToken=refreshToken;
		
	}
	
	public User getUser() {
		return this.user;
	}
	  public void setUser(User user) {
	        this.user = user;
	    }

	  public String getAccesToken() {
		  return accesToken;
	  }

	  public void setAccesToken(String accesToken) {
		  this.accesToken = accesToken;
	  }

	  public String getRefreshToken() {
		  return refreshToken;
	  }

	  public void setRefreshToken(String refreshToken) {
		  this.refreshToken = refreshToken;
	  }

	 
	  
	  
}

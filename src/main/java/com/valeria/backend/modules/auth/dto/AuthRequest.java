package com.valeria.backend.modules.auth.dto;

import java.util.UUID;

public class AuthRequest {
	private UUID id;
	private String email;
	private String name;
	private String last_name;
	private String second_last_name;
	private String phone;
	private String password;
	//getters
	public String getEmail() {return email;}
	public UUID getId() {return id;}
	public String getName() {return name;}
	public String getSecondLastName() {return second_last_name;}
	public String getLastName() {return last_name;}
	public String getPhone() {return phone;}
	public String getPassword() {return password;}
	//setters
	public void setEmail(String email) {
		this.email= email;}
	public void setId(UUID id) {
		this.id=id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setSecondLastName(String secondLastName) {
		this.second_last_name=secondLastName;
		}
	public void setLastName(String last_name) {
		this.last_name=last_name;
	}
	public void setPhone(String phone) {
		this.phone=phone;
	}
	public void setPassword(String password) {
		this.password=password;
	}
}

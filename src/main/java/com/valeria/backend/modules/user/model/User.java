package com.valeria.backend.modules.user.model;
import java.util.UUID;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import com.valeria.backend.model.BaseEntity;
//JPA (Java Persistence API) es una forma estándar de guardar y leer datos de una base de datos usando objetos Java.
import lombok.Getter;
import lombok.Setter;
//@Getter
//@Setter
@Entity
@Table(name="users")
@SQLDelete(sql = "UPDATE users SET deleted_at = SYSTIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//genera el id automaticamente
	private Long id;
	private String name;
	@Column(name="last_name",nullable=false)
	private String lastName;
	@Column(name="second_last_name",nullable=false)
	private String secondLastName;
	private String phone;
	private String email;
	@Column(name="account_owner")
	private boolean account_owner=false;
	
	//private String password;
	//para ocultar la password a la hora de regresar un user
	@JsonIgnore
	private String password;
	
	public User() {}
	public User(Long id,
			String name,
			String email,
			String last_name,
			String second_last_name ,
			String phone,
			String password,
			Boolean account_owner
			) {
		this.id=id;
		this.name=name;
		this.email=email;
		this.lastName=last_name;
		this.secondLastName=second_last_name;
		this.phone=phone;
		this.password=password;
		this.account_owner=account_owner;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    public String getName() { return name; }

	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String last_name) {
		this.lastName = last_name;
	}
	public String getSecondLastName() {
		return secondLastName;
	}
	public void setSecondLastName(String second_last_name) {
		this.secondLastName = second_last_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getAccountOwner() {
		return account_owner;
	}
	public void setAccountOwner(boolean account_owner) {
		this.account_owner = account_owner;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
}

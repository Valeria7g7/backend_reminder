package com.valeria.backend.modules.user.model;
import java.util.UUID;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import  java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;
import com.valeria.backend.model.BaseEntity;
//JPA (Java Persistence API) es una forma estándar de guardar y leer datos de una base de datos usando objetos Java.
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@Column(unique = true, nullable = false)
	private String email;
	private LocalDate birthDate;
	private String gender;
	private String allergies;
	@Column(name="account_owner")
	private Boolean account_owner=false;
	
	//agg la fk user
	@ManyToOne
	@JoinColumn(name="users_id")
	private User parentUser;
	
	@OneToMany(mappedBy = "parentUser")
	@JsonIgnore
	private List<User> children;
	
	
	//private String password;
	//para ocultar la password a la hora de regresar un user
	//@JsonIgnore
	private String password;
	
	public User() {}
	public User(Long id,
			String name,
			String email,
			String lastName,
			String secondLastName ,
			String phone,
			LocalDate birthDate,
			String gender,
			String allergies,
			String password,
			Boolean accountOwner
			) {
		this.id=id;
		this.name=name;
		this.email=email;
		this.lastName=lastName;
		this.secondLastName=secondLastName;
		this.phone=phone;
		this.birthDate=birthDate;
		this.gender=gender;
		this.allergies=allergies;
		this.password=password;
		this.account_owner=accountOwner;
		
		
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
	public Boolean getAccountOwner() {
		return account_owner;
	}
	public void setAccountOwner(Boolean account_owner) {
		this.account_owner = account_owner;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	
	
	
	
	
}

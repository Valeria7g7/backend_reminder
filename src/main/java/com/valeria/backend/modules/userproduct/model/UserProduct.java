package com.valeria.backend.modules.userproduct.model;
import com.valeria.backend.modules.prescription.model.Prescription;
import com.valeria.backend.model.BaseEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.time.LocalTime;
import java.time.LocalDate;
import com.valeria.backend.modules.user.model.User;
import com.valeria.backend.modules.product.model.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
@Table(name="user_product")
@SQLDelete(sql="update user_product set deleted_at=SYSTIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at is null")
public class UserProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@Column(nullable=false)
    private Integer howDays;
    @Column(nullable=false)
    private Integer howOften;
    @Column(nullable=false)
    private Integer howManyTimes;//cuantas veces tomar
    @Column(nullable=true)
    private LocalTime firstTake;
    @Column(nullable=true)
    private LocalDate firstTakeDate;
    @Column(nullable=true)
    private String description;
    
    @ManyToOne
    @JoinColumn(name="users_id",nullable=false)
    private User user;
    
    
    @ManyToOne
    @JoinColumn(name="product_id",nullable=false)
    private Product product;
    
    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="prescription_id",nullable=false)
    private Prescription prescription;
    public UserProduct () {}
    public UserProduct (
    		Long id,Integer howDays,
    		Integer howOften, Integer howManyTimes,  LocalTime firstTake,
    		String description,LocalDate firstTakeDate) {
    	this.id=id;
    	this.howDays=howDays;
    	this.howOften=howOften;
    	this.howManyTimes=howManyTimes;
    	this.firstTake=firstTake;
    	this.description=description;
    	this.firstTakeDate=firstTakeDate;
    	
    }
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getHowDays() {
		return howDays;
	}
	public void setHowDays(Integer howDays) {
		this.howDays = howDays;
	}
	
	public Integer getHowOften() {
		return howOften;
	}
	public void setHowOften(Integer howOften) {
		this.howOften = howOften;
	}
	
	public Integer getHowManyTimes() {
		return howManyTimes;
	}
	public void setHowManyTimes(Integer howManyTimes) {
		this.howManyTimes = howManyTimes;
	}
	public LocalTime getFirstTake() {
		return firstTake;
	}
	public void setFirstTake(LocalTime firstTake) {
		this.firstTake = firstTake;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Prescription getPrescription() {
		return prescription;
	}
	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	public LocalDate getFirstTakeDate() {
		return firstTakeDate;
	}
	public void setFirstTakeDate(LocalDate firstTakeDate) {
		this.firstTakeDate = firstTakeDate;
	}


}

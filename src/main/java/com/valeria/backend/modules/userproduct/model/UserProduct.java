package com.valeria.backend.modules.userproduct.model;
import com.valeria.backend.model.BaseEntity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.time.LocalTime;

import com.valeria.backend.modules.user.model.User;
import com.valeria.backend.modules.product.model.Product;

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
    private LocalTime firstTake;
    @Column(nullable=true)
    private String description;
    
    @ManyToOne
    @JoinColumn(name="users_id",nullable=false)
    private User user;
    
    
    @ManyToOne
    @JoinColumn(name="product_id",nullable=false)
    private Product product;
    public UserProduct () {}
    public UserProduct (
    		Long id,Integer howDays,
    		Integer howOften, LocalTime firstTake,
    		String description) {
    	this.id=id;
    	this.howDays=howDays;
    	this.howOften=howOften;
    	this.firstTake=firstTake;
    	this.description=description;
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


}

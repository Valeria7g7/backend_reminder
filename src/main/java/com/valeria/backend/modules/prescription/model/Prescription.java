package com.valeria.backend.modules.prescription.model;
import com.valeria.backend.modules.user.model.User;
import com.valeria.backend.modules.userproduct.model.UserProduct;
import com.valeria.backend.model.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="prescriptions")
@SQLDelete(sql="update prescriptions set deleted_at=SYSTIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at is null")
public class Prescription extends BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="users_id",nullable=false)
	private User user;
	
	@JsonManagedReference//evita el ciclo, ya que regresamos la entidad directamente sin dto
    @OneToMany(mappedBy="prescription",//indica que es el dueño de  la relacion prescription definida e UserProduct
    //cascade=CascadeType.ALL,
    fetch=FetchType.LAZY)
	private List<UserProduct>products=new ArrayList<>();
	
	
	public Prescription() {}
	public Prescription(Long id) {
		this.id=id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<UserProduct> getProducts() {
		return products;
	}
	public void setProducts(List<UserProduct> products) {
		this.products = products;
	}

	

}

package com.valeria.backend.modules.product.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
//import java.time.LocalDateTime;
import java.util.UUID;

import com.valeria.backend.model.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.math.BigDecimal;
import jakarta.persistence.*;
//Representa una tabla
//@Getter
//@Setter
@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted_at = SYSTIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
//@Data
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//genera el id automaticamente
    private Long id;
	private String name;
    private String description;
    private BigDecimal price;
    //private LocalDateTime createdAt;
    public Product() {}
    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    //setters
    public void setName(String name) {
    	this.name=name;
    }
    public void setDescription(String description) {
    	this.description=description;
    }
    public void setPrice(BigDecimal price) {
    	this.price=price;
    }
    public Number getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
	public void setId(Long id) {
		this.id = id;
	}
    
   // public LocalDateTime getCreatedAt() { return createdAt; }
}

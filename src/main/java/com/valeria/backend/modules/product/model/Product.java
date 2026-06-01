package com.valeria.backend.modules.product.model;

//import java.time.LocalDateTime;
import java.util.UUID;

import com.valeria.backend.model.BaseEntity;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.math.BigDecimal;
import jakarta.persistence.*;
//Representa una tabla
@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted_at = SYSTIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
public class Product extends BaseEntity {
    //@Id
	//private UUID id;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//genera el id automaticamente
  // @GeneratedValue
   // private UUID id;
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
    // getters
    public Number getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
   // public LocalDateTime getCreatedAt() { return createdAt; }
}

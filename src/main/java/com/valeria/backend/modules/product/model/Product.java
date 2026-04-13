package com.valeria.backend.modules.product.model;

//import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;
//Representa una tabla
@Entity
@Table(name = "product")
public class Product{
    //@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)//genera el id automaticamente
	//private UUID id;
    @Id
    private UUID id;
	private String name;
    private String description;
    private double price;
    //private LocalDateTime createdAt;
    public Product() {}
    public Product(UUID id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
     //   this.createdAt = createdAt;
    }

    // getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
   // public LocalDateTime getCreatedAt() { return createdAt; }
}

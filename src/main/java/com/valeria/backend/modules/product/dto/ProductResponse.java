package com.valeria.backend.modules.product.dto;
//para no exponer la entidad directamente
import com.valeria.backend.modules.product.model.Product;

public class ProductResponse {
	private Product product;
	public ProductResponse(Product product) {
		this.product=product;
	}
	
	public Product getProduct() {
		return this.product;
		
	}
	public void setProduct(Product product) {
		this.product=product;
	}

}

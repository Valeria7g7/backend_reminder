package com.valeria.backend.modules.product.service;
import com.valeria.backend.modules.product.model.Product;
import com.valeria.backend.modules.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
//logica de negocio
@Service
public class ProductService {
	private final ProductRepository productRepository;
	public ProductService(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
}

package com.valeria.backend.modules.product.service;
import com.valeria.backend.modules.product.model.Product;

import com.valeria.backend.modules.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


//logica de negocio
@Service
public class ProductService {
	private final ProductRepository productRepository;
	public ProductService(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	public Page<Product> getAllProducts(Pageable pageable){
		return productRepository.findAll(pageable);
	}
//	public List<Product> getAllProducts(){
//		return productRepository.findAll();
//	}
	public Product save(Product product){
		return productRepository.save(product);
	}
	public Product update(Long id,Product request){
		//current entity
		Product currentEntity=productRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Producto no encontrdo"));
		
		currentEntity.setName(request.getName());
		currentEntity.setDescription(request.getDescription());
		currentEntity.setPrice(request.getPrice());
		
		return productRepository.save(currentEntity);
	}
	public void delete(Long id) {
		
		Product currentEntity = productRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Producto no encontrdo"));
		
		 productRepository.deleteById(id);
		
	}
	
}

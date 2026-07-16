package com.valeria.backend.modules.product.service;
import com.valeria.backend.dto.SearchRequest;
import com.valeria.backend.modules.product.model.Product;

import com.valeria.backend.modules.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
//logica de negocio
@Service
public class ProductService {
	private final ProductRepository repository;
	public ProductService(ProductRepository repository) {
		this.repository=repository;
	}
	public Page<Product> getAllProducts(Pageable pageable,SearchRequest request){
		 String value=(request.getSearch()!=null)? value=request.getSearch().getValue():null;
	       Specification<Product> spec=(root,query,cb)->cb.conjunction();
	       if(value!=null) {
	       spec=spec.and((root,query,cb)->
	       cb.or(
	    		   cb.like(cb.lower(root.get("name")), "%"+value.toLowerCase()+"%")    		   
	    		   
	    		   ));
	       }
	       
		return repository.findAll(spec,pageable);
	}
//	public List<Product> getAllProducts(){
//		return productRepository.findAll();
//	}
	public Product save(Product product){
		return repository.save(product);
	}
	public Product update(Long id,Product request){
		//current entity
		Product currentEntity=repository.findById(id)
				.orElseThrow(()-> new RuntimeException("Producto no encontrdo"));
		
		currentEntity.setName(request.getName());
		currentEntity.setDescription(request.getDescription());
		currentEntity.setPrice(request.getPrice());
		
		return repository.save(currentEntity);
	}
	public void delete(Long id) {
		
		Product currentEntity = repository.findById(id)
				.orElseThrow(()-> new RuntimeException("Producto no encontrdo"));
		
		 repository.deleteById(id);
		
	}
	
}

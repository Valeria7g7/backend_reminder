package com.valeria.backend.modules.userproduct.service;
import com.valeria.backend.modules.userproduct.repository.UserProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.valeria.backend.modules.userproduct.model.UserProduct;
@Service
public class UserProductService {
	private final UserProductRepository repository;
	public UserProductService(UserProductRepository repository) {
		this.repository=repository;
	}
	public Page<UserProduct> getAllUserProducts(Pageable pageable){
		return repository.findAll(pageable);
	}

}

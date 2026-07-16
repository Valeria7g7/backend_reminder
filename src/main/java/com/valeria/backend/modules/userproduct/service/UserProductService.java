package com.valeria.backend.modules.userproduct.service;
import com.valeria.backend.modules.prescription.model.Prescription;
import com.valeria.backend.modules.userproduct.repository.UserProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.valeria.backend.modules.userproduct.model.UserProduct;
import com.valeria.backend.modules.userproduct.dto.*;
import com.valeria.backend.modules.product.model.*;
import com.valeria.backend.modules.user.model.User;

import java.util.List;
import java.time.LocalTime;
import java.time.LocalDate;
@Service
public class UserProductService {
	private final UserProductRepository repository;
	public UserProductService(UserProductRepository repository) {
		this.repository=repository;
	}
	public Page<UserProduct> getAllUserProducts(Pageable pageable){
		return repository.findAll(pageable);
	}
	public void save(UserProductRequest request,Prescription prescription) {
		//products
		Long userId=request.getUserId();
		System.out.println("vamos a registrar userproductuser+"+userId);

		List<UserProductRequest.ProductItem> products=request.getProducts();
		System.out.println(products);

		for(UserProductRequest.ProductItem item:products) {
			
			UserProduct up=new UserProduct();
			
			Integer howManyDays=0;
			howManyDays=(item.getHowDays()*24)/item.getHowOften();
			
			up.setHowDays(item.getHowDays());
			up.setHowOften(item.getHowOften());
			up.setHowManyTimes(howManyDays);
			up.setDescription(item.getDescription());
			up.setFirstTake(item.getFirstTake());
			up.setFirstTakeDate(item.getFirstTakeDate());
			Product product = new Product();
			product.setId(item.getProductId());
			up.setProduct(product);

			User user=new User();
			user.setId(userId);
			up.setUser(user);
			
			up.setPrescription(prescription);
			
			
			repository.save(up);
			
		}
		//return repository.save(request);
	}
	

}

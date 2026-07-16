package com.valeria.backend.modules.prescription.service;
import com.valeria.backend.modules.userproduct.dto.UserProductRequest;

import com.valeria.backend.dto.SearchRequest;
import com.valeria.backend.modules.prescription.model.Prescription;
import com.valeria.backend.modules.prescription.repository.PrescriptionRepository;
import com.valeria.backend.modules.user.model.User;
import com.valeria.backend.modules.userproduct.dto.UserProductRequest;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
@Service
public class PrescriptionService {
	private final PrescriptionRepository repository;
	
	public PrescriptionService(PrescriptionRepository repository) {
		this.repository=repository;
	}
	public Page<Prescription>search(Pageable pageable, SearchRequest request){
		String value=(request.getSearch()!=null)?value=request.getSearch().getValue():null;
		Specification<Prescription> spec=(root,query,cb)->cb.conjunction();
		if(value!=null) {
			spec=spec.and((root,query,cb)->cb.or(cb.like(cb.lower(root.get("name")), "%"+value.toLowerCase()+"%")));
		}
	
		return repository.findAll(spec,pageable);
		
	}
	public Prescription save(UserProductRequest request) {
		Long userId=request.getUserId();
		Prescription pres=new Prescription();
		User user=new User();
		user.setId(userId);
		pres.setUser(user);
	
		pres=repository.save(pres);
		pres.getProducts();
		return pres;
		
	}
	
	public void delete(Long id) 
	{
		Prescription entity=repository.findById(id).orElseThrow(()->new RuntimeException("Prescription not found"));
		repository.deleteById(id);
	}
}

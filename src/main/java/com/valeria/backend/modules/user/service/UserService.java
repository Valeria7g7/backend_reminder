package com.valeria.backend.modules.user.service;
import org.springframework.stereotype.Service;
import com.valeria.backend.modules.user.repository.UserRepository;
import com.valeria.backend.modules.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.jpa.domain.Specification;
@Service
public class UserService {
	private final UserRepository repository;
	public UserService(UserRepository repository) {
		this.repository=repository;
	}
	//@SuppressWarnings("null")
	public Page<User> getAllUsers(Pageable pageable,String value){
		//return repository.findAll(pageable);
		//Specification<User> spec = null;
		Specification<User> spec =
		        (root, query, cb) -> cb.conjunction();
		    if (value != null && !value.isBlank()) {
		        spec = spec.and((root, query, cb) ->
		                cb.or(
		                        cb.like(cb.lower(root.get("name")), "%" + value.toLowerCase() + "%"),
		                        cb.like(cb.lower(root.get("email")), "%" + value.toLowerCase() + "%"),
		                        cb.like(cb.lower(root.get("phone")), "%" + value.toLowerCase() + "%")
		                ));
		    }

		    return repository.findAll(spec, pageable);
	}
	public User save(User user) {
		
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String passwordHash= encoder.encode(user.getPassword());
		user.setPassword(passwordHash);
		return repository.save(user);
	}
	public User update(Long id,User user) {
		User currentEntity = repository.findById(id)
				.orElseThrow(()-> new RuntimeException("Usuario no encontrdo"));
		currentEntity.setName(user.getName());
		currentEntity.setLastName(user.getLastName());
		currentEntity.setSecondLastName(user.getSecondLastName());
		currentEntity.setPhone(user.getPhone());
		currentEntity.setEmail(user.getEmail());
		return repository.save(currentEntity);
	}
	public void delete(Long id) {
		User currentEntity=  repository.findById(id)
				.orElseThrow(()->new RuntimeException("Registro no encontrado"));
		repository.deleteById(id);
		
	}
	public Optional<User> findUser(String email) {
		return repository.findByEmail(email);
	}
	
//	public List<User> getAllUsers(){
//	return repository.findAll();
//}

}

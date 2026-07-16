package com.valeria.backend.modules.user.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;
import com.valeria.backend.modules.user.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
//public interface UserRepository extends JpaRepository<User,Long> {
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

	
	//query methods= 
	 Optional<User> findByEmail(String email);
	 Optional<User> findByName(String name);
	 Optional<User> findByEmailOrName(String email, String name);
	 //ejemplo si tengo varios usuarios con el mismo nombre
	 Optional<User> countBy(String name);
	 

}

package com.valeria.backend.modules.user.controller;
import com.valeria.backend.modules.user.service.UserService;
import com.valeria.backend.dto.ApiResponse;
import com.valeria.backend.response.PaginatedResponse;
import com.valeria.backend.response.PaginationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;

import com.valeria.backend.modules.auth.dto.AuthResponse;
import com.valeria.backend.modules.auth.service.AuthService;
import com.valeria.backend.modules.user.model.User;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.valeria.backend.modules.auth.service.AuthService;
@RestController
//@RestControllerAdvice//esta clase centralizada intercepta el error y le asigna el código HTTP correcto.
@RequestMapping("/api/users")
public class UserController {
	private final UserService service;
	private final AuthService authService;
	public UserController(UserService service, AuthService authService) {
		this.service=service;
		this.authService=authService;
	}
	
	@PostMapping("/search")
	public PaginatedResponse<User> getUsers(Pageable pageable,HttpServletRequest request){
		Page<User> page=service.getAllUsers(pageable);
		System.out.println("page ");
		System.out.println(page);
		return PaginationMapper.map(page, request);
		
	}
	 @PostMapping
	 public ResponseEntity<?> save(@RequestBody User user) {
		 try {
		System.out.println("guardadno user"+user.getName());
		  user = this.service.save(user);
//		 if(user.getAccountOwner()) {
//			 String token=authService.generateToken(user.getId(), user.getEmail());
//			 AuthResponse response=new AuthResponse(user,token);
//		        return ResponseEntity.ok(new ApiResponse<>(response));
//		 }
		// ProductResponse response=new ProductResponse(product);
		 return ResponseEntity.ok(new ApiResponse<>(user));
		 
		 }catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					 .body(e.getMessage());
			 
		 }
	 }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user){
		 try {
			 user=this.service.update(id,user);
			 return ResponseEntity.ok(new ApiResponse<>(user));
		 }catch(Exception e){
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					 .body(e.getMessage());
		 }
	 }
	 @DeleteMapping("/{id}")
	 public ResponseEntity<String>delete(@PathVariable Long id){
		 try {
			  this.service.delete(id);
			  return ResponseEntity.ok("Registro eliminado correctamente");
			  
		 }catch(Exception e){
			  return ResponseEntity.status(HttpStatus.CONFLICT)
					 .body(e.getMessage());
			 
		 }
	 }
//	 @PostMapping("/search")
//    public List<User> getUsers() {
//	 System.out.println("res ");
//	 return service.getAllUsers();
//    }
}

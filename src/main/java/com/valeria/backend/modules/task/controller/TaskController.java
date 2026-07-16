package com.valeria.backend.modules.task.controller;
import com.valeria.backend.modules.user.service.UserService;
import com.valeria.backend.dto.*;
import com.valeria.backend.dto.ApiResponse;
import com.valeria.backend.response.PaginatedResponse;
import com.valeria.backend.response.PaginationMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;

import com.valeria.backend.modules.auth.dto.AuthResponse;
import com.valeria.backend.modules.product.model.Product;
import com.valeria.backend.modules.task.service.TaskService;
import com.valeria.backend.modules.task.model.Task;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.PageRequest;
@RestController
@RequestMapping("/api/task")
public class TaskController {
	private final TaskService service;
	public TaskController(TaskService service) {
	this.service=service;
	}
	@PostMapping("/search")
	public PaginatedResponse<Task>getTask(@RequestBody SearchRequest request, HttpServletRequest httpRequest){
		Pageable pageable=PageRequest.of(request.getPage()-1, request.getLimit());
		Page<Task>page=service.getAllTask(pageable, request);
		return PaginationMapper.map(page,httpRequest);
	}

	
	 @PostMapping//("/")
	 public ResponseEntity<?> save(@RequestBody Task task) {
		 try {
			 System.out.println("entra al controller task");
		  task = this.service.save(task);
		// ProductResponse response=new ProductResponse(product);
		 return ResponseEntity.ok(new ApiResponse<>(task));
		 
		 }catch(Exception e) {
			 return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					 .body(e.getMessage());
			 
		 }
	 }
	 @PutMapping("/{id}")
	 public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Task task) {
		 try {
		 System.out.println("sabe product121212");
		  task = this.service.update(id,task);
		// ProductResponse response=new ProductResponse(product);
		 return ResponseEntity.ok(new ApiResponse<>(task));
		 
		 }catch(Exception e) {
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

}

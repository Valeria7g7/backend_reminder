package com.valeria.backend.modules.task.service;
import com.valeria.backend.dto.SearchRequest;
import com.valeria.backend.modules.task.model.Task;

import com.valeria.backend.modules.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
//logica de negocio
@Service
public class TaskService {
	private final TaskRepository repository;
	public TaskService(TaskRepository repository) {
	this.repository=repository;
	}
	public Page<Task> getAllTask(Pageable pageable, SearchRequest request){
		 String value=(request.getSearch()!=null)? value=request.getSearch().getValue():null;
		Specification<Task>spec=(root,query,cb)->cb.conjunction();
		if(value!=null) {
			spec=spec.and((root,query,cb)->
			cb.or(cb.like(cb.lower(root.get("name")), "%"+value.toLowerCase()+"%")
					
					));
		}
		return repository.findAll(spec,pageable);
		
		
	}
	
	
	public Task save(Task task) {
		return repository.save(task);
	}
	public Task update(Long id, Task request) {
		Task currentEntity=repository.findById(id).orElseThrow(()->new RuntimeException("Task no encontrado"));
		currentEntity.setName(request.getName());
		currentEntity.setDescription(request.getDescription());
		return repository.save(currentEntity);
	}
	public void delete(Long id) {
		Task currentEntity=repository.findById(id).orElseThrow(()->new RuntimeException("Task no encontrado"));
		repository.deleteById(id);
	}
	
	
}

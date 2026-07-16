package com.valeria.backend.modules.task.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;
import com.valeria.backend.modules.task.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long>,JpaSpecificationExecutor<Task>{
	

}

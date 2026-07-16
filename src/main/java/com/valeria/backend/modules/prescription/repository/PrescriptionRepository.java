package com.valeria.backend.modules.prescription.repository;
import com.valeria.backend.modules.prescription.model.Prescription;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface PrescriptionRepository extends JpaRepository<Prescription,Long>,JpaSpecificationExecutor<Prescription>{

	@EntityGraph(attributePaths= {"products"})//con EntityGraph indicamos que tambien se carge la relacion products en la misma consulta en este metodo
	List<Prescription>findAll();
}

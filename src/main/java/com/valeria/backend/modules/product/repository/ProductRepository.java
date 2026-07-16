package com.valeria.backend.modules.product.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.valeria.backend.modules.product.model.Product;
//acceso a la bd

public interface ProductRepository extends JpaRepository<Product, Long>,JpaSpecificationExecutor<Product> {// findAll(), save(), delete(), etc.

}

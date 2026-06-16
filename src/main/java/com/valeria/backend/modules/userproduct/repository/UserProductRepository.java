package com.valeria.backend.modules.userproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.valeria.backend.modules.userproduct.model.UserProduct;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> {

}

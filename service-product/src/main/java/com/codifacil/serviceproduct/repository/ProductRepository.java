package com.codifacil.serviceproduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codifacil.serviceproduct.entity.Category;
import com.codifacil.serviceproduct.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByCategory(Category category);
	
}

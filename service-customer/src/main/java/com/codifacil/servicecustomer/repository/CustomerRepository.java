package com.codifacil.servicecustomer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codifacil.servicecustomer.entity.Customer;
import com.codifacil.servicecustomer.entity.Region;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public Customer findByNumeroDocumento(String numeroDocumento);
	public List<Customer> findByLastName(String lastName);
	public List<Customer> findByRegion(Region region);

}

package com.codifacil.servicecustomer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codifacil.servicecustomer.entity.Customer;
import com.codifacil.servicecustomer.entity.Region;
import com.codifacil.servicecustomer.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> findCustomerAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public List<Customer> findCustomersByRegion(Region region) {
		// TODO Auto-generated method stub
		return customerRepository.findByRegion(region);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Customer customerDB = customerRepository.findByNumeroDocumento(customer.getNumeroDocumento());
		if(customerDB!=null)
			return customerDB;
		
		customer.setState("CREATED");
		customerDB = customerRepository.save(customer);
		return customerDB;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Customer customerDB = getCustomer(customer.getId());
		
		if(customerDB == null)
			return null;
		
		customerDB.setFirstName(customer.getFirstName());
		customerDB.setLastName(customer.getLastName());
		customerDB.setEmail(customer.getEmail());
		customerDB.setPhotoUrl(customer.getPhotoUrl());
		
		return customerRepository.save(customerDB);
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Customer customerDB = getCustomer(customer.getId());
		
		if(customerDB == null)
			return null;
		
		customerDB.setState("DELETED");
		
		return customerRepository.save(customerDB);
	}

	@Override
	public Customer getCustomer(Long id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id).orElse(null);
	}

}

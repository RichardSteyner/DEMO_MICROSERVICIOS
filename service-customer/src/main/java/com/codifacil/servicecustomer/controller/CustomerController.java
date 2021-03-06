package com.codifacil.servicecustomer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.codifacil.servicecustomer.entity.Customer;
import com.codifacil.servicecustomer.entity.Region;
import com.codifacil.servicecustomer.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<Customer>> listAllCustomers(@RequestParam(name = "regionId", required = false) Long regionId){
		List<Customer> customers = new ArrayList<>();
		if(regionId==null) {
			customers = customerService.findCustomerAll();
			if(customers.isEmpty())
				return ResponseEntity.noContent().build();
		}
		else {
			customers = customerService.findCustomersByRegion(Region.builder().
					id(regionId).build());
			if(customers == null)
			{
				log.error("Customers con region id {} not found.", regionId);
				return ResponseEntity.notFound().build();
			}
			/*if(products.isEmpty())
				return ResponseEntity.notFound().build();*///preferible mostrar la lista vacía
		}
		
		return ResponseEntity.ok(customers);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id){
		Customer customer = customerService.getCustomer(id);
		
		if(customer == null) {
			log.error("Customer with id {} not found.", id);
			return ResponseEntity.notFound().build();
		}
		

		return  ResponseEntity.ok(customer);
	}
	
	@PostMapping
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer, BindingResult result){
		if(result.hasErrors())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
	
		Customer customerCreate = customerService.createCustomer(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(customerCreate);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer){
		customer.setId(id);
		Customer customerUpdate = customerService.updateCustomer(customer);
		if(customerUpdate==null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(customerUpdate);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Customer> deleteProduct(@PathVariable("id") Long id){
		Customer customerDelete = customerService.getCustomer(id);
		
		if(customerDelete == null)
			return ResponseEntity.notFound().build();
		
		customerDelete = customerService.deleteCustomer(customerDelete);
		
		return ResponseEntity.ok(customerDelete);
	}
	
	private String formatMessage(BindingResult result) {
		List<Map<String, String>> errores = result.getFieldErrors().stream()
				.map(err -> {
					Map<String, String> error =  new HashMap<>();
					error.put(err.getField(), err.getDefaultMessage());
					return error;
				}).collect(Collectors.toList());
		
		ErrorMessage errorMessage = ErrorMessage.builder()
				.code("01")
				.messages(errores)
				.build();
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(errorMessage);
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}

}

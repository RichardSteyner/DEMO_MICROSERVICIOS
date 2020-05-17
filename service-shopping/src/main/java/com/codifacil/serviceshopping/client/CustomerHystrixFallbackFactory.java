package com.codifacil.serviceshopping.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.codifacil.serviceshopping.modelo.Customer;

@Component //para hacer otro tipo de inyección de dependencia
public class CustomerHystrixFallbackFactory implements CustomerClient {

	@Override
	public ResponseEntity<Customer> getCustomer(Long id) {
		Customer customer = Customer.builder()
				.firstName("none")
				.lastName("none")
				.email("none")
				.photoUrl("none").build();
		return ResponseEntity.ok(customer);
	}

}

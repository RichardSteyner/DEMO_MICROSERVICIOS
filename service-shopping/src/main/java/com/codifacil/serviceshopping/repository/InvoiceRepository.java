package com.codifacil.serviceshopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codifacil.serviceshopping.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	public List<Invoice> findByCustomerId(Long customerId );
    public Invoice findByNumberInvoice(String numberInvoice);
	

}

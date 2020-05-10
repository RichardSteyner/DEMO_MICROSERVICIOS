package com.codifacil.serviceshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codifacil.serviceshopping.entity.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

}

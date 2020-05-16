package com.codifacil.serviceshopping.modelo;

import java.util.Date;

import lombok.Data;

@Data
public class Product {
	
	private Long id;
	private String name;
	private String description;
	private Double stock;
	private Double price;
	private String status;
	private Date createAt;
	private Category category;

}

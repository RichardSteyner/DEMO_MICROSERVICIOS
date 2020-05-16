package com.codifacil.serviceshopping.modelo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Customer {
	
	private Long id;
	private String numeroDocumento;
	private String firstName;
	private String lastName;
	private String email;
	private String photoUrl;
	private String state;
	private Region region;

}

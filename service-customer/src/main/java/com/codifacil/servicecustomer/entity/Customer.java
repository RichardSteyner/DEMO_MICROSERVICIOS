package com.codifacil.servicecustomer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customer")
@Getter @Setter
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "El número de documento no puede ser vacío")
	@Size(max = 8, min = 8, message = "El tamaño del número de documento debe ser 8")
	@Column(name = "numero_documento", unique = true, length = 8, nullable = false) //las columnas en h2 no pueden tener mayusculas entre palabras
	private String numeroDocumento;
	
	@NotEmpty(message = "El nombre no puede ser vacío")
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@NotEmpty(message = "El apellido no puede ser vacío")
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@NotEmpty(message = "El correo no puede ser vacío")
	@Email(message = "No es una dirección de correo bien formateada")
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	private String state;
	
	@NotNull(message = "Región no puede ser vacía")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_region")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Region region;

}

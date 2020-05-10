package com.codifacil.demo;

public class Saludo {

	private Long id;
	private String mensaje;
	
	
	
	public Saludo(Long id, String mensaje) {
		super();
		this.id = id;
		this.mensaje = mensaje;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
}

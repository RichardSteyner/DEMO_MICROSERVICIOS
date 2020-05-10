package com.codifacil.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="saludo")
public class SaludoController {

	private AtomicLong contador = new AtomicLong();
	private String template = "Hola %s";
	
	@GetMapping
	public Saludo saludar(@RequestParam(value="name", defaultValue = "Steyner.!") String name) {
		return new Saludo(contador.incrementAndGet(), String.format(template, name));
	}
	
}

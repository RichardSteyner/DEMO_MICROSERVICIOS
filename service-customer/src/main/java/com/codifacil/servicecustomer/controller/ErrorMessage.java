package com.codifacil.servicecustomer.controller;

import java.util.List;
import java.util.Map;

import com.codifacil.servicecustomer.controller.ErrorMessage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class ErrorMessage {

	private String code;
	private List<Map<String, String>> messages;
	
}

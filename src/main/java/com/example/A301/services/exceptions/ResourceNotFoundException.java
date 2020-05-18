package com.example.A301.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;  //Subclasse do RunTimeException do Java

	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id " + id);  //Personalizar o erro (404). 
	}
}

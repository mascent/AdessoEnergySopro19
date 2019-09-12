package de.sopro.util.exception;

public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException() {
		super();
	}
	
	public ResourceNotFoundException(String err) {
		super(err);
	}
}

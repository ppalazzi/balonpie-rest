package com.palazzisoft.balonpie.service.exception;

public class BalonpieException extends Exception {

	private static final long serialVersionUID = 1L;

	public BalonpieException(String message) {
		super(message);
	}
	
	public BalonpieException(String message, Throwable e) {
		super(message, e);
	}
}

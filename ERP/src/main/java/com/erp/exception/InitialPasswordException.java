package com.erp.exception;

import org.springframework.security.core.AuthenticationException;

public class InitialPasswordException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InitialPasswordException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	
	public InitialPasswordException(String msg, Throwable t) {
		super(msg,t);
	}
	
}

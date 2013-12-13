package com.test.cloudcomputing.util;

public class UserNameTakenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameTakenException(String msg){
		super(msg);
	}
}

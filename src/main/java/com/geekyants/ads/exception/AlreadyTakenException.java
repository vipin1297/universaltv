package com.geekyants.ads.exception;

public class AlreadyTakenException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AlreadyTakenException(String message) {
		super(message);
	}


}

/*
 * Copyright 2018 (C) Oracle Corporation
 * 
 * Created on : 01-07-2018
 * 
 *
 *-----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 *-----------------------------------------------------------------------------
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 * OLD/NEW     DATE                RFC NO
 *-----------------------------------------------------------------------------
 * --/1.0  | 01-07-2018    | Initial Create.
 *         |               |
 *---------|---------------|---------------------------------------------------
 *         | author        | Defect ID 1/Description
 *         | dd-mm-yy      | 
 *---------|---------------|---------------------------------------------------
 */
package com.javainuse.swaggertest.exception;

import org.springframework.http.HttpStatus;

/**
 * The Class UnauthorizedException.
 */
public class UnauthorizedException extends Exception {

	private HttpStatus httpStatus;

	/**
	 * Instantiates a new unauthorized exception.
	 *
	 * @param message
	 *            the message
	 */
	public UnauthorizedException(String message) {
		super(message);
	}

	public UnauthorizedException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

}

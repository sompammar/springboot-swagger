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
 * The Class ValidationFieldException.
 */
public class ValidationFieldException extends RuntimeException {

	private HttpStatus httpStatus;

	public ValidationFieldException(String message) {
		super(message);
	}

	public ValidationFieldException(String message, HttpStatus httpStatus) {
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

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
 * --/1.0  |               | Initial Create.
 *         | 01-07-2018    |
 *---------|---------------|---------------------------------------------------
 *         | author        | Defect ID 1/Description
 *         | dd-mm-yyyy    | 
 *---------|---------------|---------------------------------------------------
 */
package com.javainuse.swaggertest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

/**
 * The Class ConfigExceptionHandler.
 * 
 * 
 * @version 1.0
 * @since 01-07-2018
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(basePackages = "com.javainuse.swaggertest", annotations = RestController.class)
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

	/**
	 * Handle generic exception.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> handleGenericException(Exception ex, WebRequest request) {

		logger.error("ErrorInfo occurred " + request, ex);
		return handleError(ex, request);
	}

	private ResponseEntity<ErrorInfo> handleError(Exception ex, WebRequest request) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setContext(request.getContextPath());
		errorInfo.setMessage(ex.getMessage());

		HttpStatus status = HttpStatus.BAD_REQUEST;
		if (ex instanceof UnauthorizedException) {
			UnauthorizedException e = (UnauthorizedException) ex;
			if(e != null && e.getHttpStatus() != null) {
				status = e.getHttpStatus();
			} else {
				status = HttpStatus.UNAUTHORIZED;
			}
		}
		errorInfo.setStatus(status);
		return new ResponseEntity<>(errorInfo, status);
	}

	/**
	 * Handle runtime exception.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorInfo> handleRuntimeException(RuntimeException ex, WebRequest request) {
		logger.error("ErrorInfo occurred " + request, ex);
		return handleError(ex, request);

	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		logger.error("ErrorInfo occurred " + request, ex);
		return handleError(ex, request);

	}

	@ExceptionHandler(ValidationFieldException.class)
	public ResponseEntity<ErrorInfo> handleValidationFieldException(ValidationFieldException ex, WebRequest request) {
		logger.error("ErrorInfo occurred " + request, ex);
		return handleError(ex, request);

	}

	/**
	 * Handle rest exception.
	 *
	 * @param ex
	 *            the ex
	 * @param request
	 *            the request
	 * @return the response entity
	 */
	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<ErrorInfo> handleRestException(RestClientException ex, WebRequest request) {
		logger.error("ErrorInfo occurred " + request, ex);
		return handleError(ex, request);
	}
}

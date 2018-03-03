package com.turvo.shipment.exception;

import java.util.Optional;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Generic exception handler
 * 
 * @author schau32
 *
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
	@ExceptionHandler(ShipmentRuntimeException.class)
	public ResponseEntity<Object> genericRuntimeException(final ShipmentRuntimeException e) {
		return error(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
	}

	private ResponseEntity<Object> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
		final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
		return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
	}
}

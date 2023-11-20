package com.nt.microservice.exception.adapter.web.error;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.nt.microservice.exception.core.usecase.service.exception.UserDoesNotException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Ravi kumar
 */
@Configuration
@RestControllerAdvice
@Slf4j
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(RestControllerExceptionHandler.now(),HttpStatus.BAD_REQUEST.value(), "Validation error", ex.getBindingResult().getAllErrors().stream()	.map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
		return throwAndLogError(HttpStatus.BAD_REQUEST, errorDetails, ex);

	}

	@ExceptionHandler(UserDoesNotException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserDoesNotException e) {
		ErrorDetails errorDetails = new ErrorDetails(RestControllerExceptionHandler.now(),HttpStatus.INTERNAL_SERVER_ERROR.value(), "User not found error", e.getMessage());
		return throwAndLogError(HttpStatus.INTERNAL_SERVER_ERROR, errorDetails, e);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,	HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(RestControllerExceptionHandler.now(),HttpStatus.METHOD_NOT_ALLOWED.value(),String.format("Request %s not supported for request url", ex.getMethod()),
				String.format("Request %s not supported for this url", ex.getMethod()));
		return throwAndLogError(HttpStatus.METHOD_NOT_ALLOWED, errorDetails, ex);
	}
	
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleUnknownException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(RestControllerExceptionHandler.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "General error", String.format("An unexpected error encountered while processing your request. %s", ex.getMessage()));
        return throwAndLogError(HttpStatus.INTERNAL_SERVER_ERROR, errorDetails, ex);

    }

	private static LocalDateTime now() {
		return LocalDateTime.now();
	}

	private ResponseEntity<Object> throwAndLogError(HttpStatus status, ErrorDetails errorDetails, Exception e) {
		log.error("Exception :", e);
		return ResponseEntity.status(status).body(errorDetails);
	}

}

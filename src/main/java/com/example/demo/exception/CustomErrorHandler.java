//package com.example.demo.exception;
//
//import com.example.demo.model.ApiError;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.LinkedCaseInsensitiveMap;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import java.util.*;
//import java.util.stream.Collectors;
//
//@ControllerAdvice(basePackages = "com.example.demo")
//public class CustomErrorHandler extends ResponseEntityExceptionHandler {
////
////	@ExceptionHandler({ ConstraintViolationException.class })
////	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
////		List<String> errors = new ArrayList<String>();
////		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
////			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
////					+ violation.getMessage());
////		}
////
////		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
////		return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
////	}
//
//}
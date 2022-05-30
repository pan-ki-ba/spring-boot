package com.pankiba.reststarter.server.exception;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.pankiba.reststarter.server.ApiErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class RestExceptionHandler {

	private static final Function<Exception, String> stackTraceSummary = exception -> Arrays
			.stream(exception.getStackTrace()).limit(20).map(StackTraceElement::toString).collect(joining("\n"));

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handleHttpMessageNotReadableException(
			HttpMessageNotReadableException httpMessageNotReadableException, WebRequest webRequest) {

		log.error(" {} ", webRequest.getDescription(false));

		Throwable mostSpecificClause = httpMessageNotReadableException.getMostSpecificCause();
		if (mostSpecificClause != null) {
			return new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Request Body should be valid JSON object",
					mostSpecificClause.getMessage(), stackTraceSummary.apply(httpMessageNotReadableException));
		}

		return new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Problem parsing JSON",
				httpMessageNotReadableException.getMessage(),
				ExceptionUtils.getStackTrace(httpMessageNotReadableException));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrorResponse handleIllegalArgumentException(IllegalArgumentException illegalArgumentException,
			WebRequest webRequest) {

		log.error(" {} ", webRequest.getDescription(false));
		return new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Bad argument", illegalArgumentException.getMessage(),
				illegalArgumentException.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ApiErrorResponse handleHttpMediaTypeNotAcceptableException(
			HttpMediaTypeNotAcceptableException httpMediaTypeNotAcceptableException, WebRequest webRequest) {
		log.error(" {} ", webRequest.getDescription(false));

		return new ApiErrorResponse(HttpStatus.NOT_ACCEPTABLE, "Media Type Not Accepted",
				httpMediaTypeNotAcceptableException.getSupportedMediaTypes().toString(),
				httpMediaTypeNotAcceptableException.getMessage());
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public ApiErrorResponse handleHttpMediaTypeNotSupportedException(
			HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException, WebRequest webRequest) {

		log.error(" {} ", webRequest.getDescription(false));

		String supportedMediaTypes = "Supported content type: " + httpMediaTypeNotSupportedException.getContentType();
		String unSupportedMediaTypes = "Unsupported content type: "
				+ MediaType.toString(httpMediaTypeNotSupportedException.getSupportedMediaTypes());

		return new ApiErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Media Type Not Supported",
				String.join("", unSupportedMediaTypes + " || " + supportedMediaTypes),
				ExceptionUtils.getStackTrace(httpMediaTypeNotSupportedException));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ApiErrorResponse handleMethodArgumentNotValidException(
			MethodArgumentNotValidException methodArgumentNotValidException, WebRequest webRequest) {

		log.error(" {} ", webRequest.getDescription(false));

		Object exceptionDetails = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.groupingBy(FieldError::getField,
						Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

		return new ApiErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, "Validation Error", exceptionDetails,
				ExceptionUtils.getStackTrace(methodArgumentNotValidException));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public ApiErrorResponse handleDataIntegrityViolation(
			DataIntegrityViolationException dataIntegrityViolationException, WebRequest webRequest) {

		log.error(" {} ", webRequest.getDescription(false));
		return new ApiErrorResponse(HttpStatus.CONFLICT, "Database Error",
				dataIntegrityViolationException.getLocalizedMessage(),
				ExceptionUtils.getStackTrace(dataIntegrityViolationException));
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorResponse handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
			WebRequest webRequest) {
		
		log.error(" {} ", webRequest.getDescription(false));
		return new ApiErrorResponse(HttpStatus.NOT_FOUND, "Resource Not Found", resourceNotFoundException.getMessage(),
				ExceptionUtils.getStackTrace(resourceNotFoundException));
	}

}

package com.pankiba.reststarter.server.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.pankiba.reststarter.server.ApiErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
public class GenericExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiErrorResponse handleException(Exception exception, WebRequest webRequest) {

		log.error(" {} ", webRequest.getDescription(false));
		return new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
				exception.getLocalizedMessage(), ExceptionUtils.getStackTrace(exception));
	}

}

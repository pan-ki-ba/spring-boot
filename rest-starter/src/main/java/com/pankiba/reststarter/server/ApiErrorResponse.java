package com.pankiba.reststarter.server;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {

	/** date-time instance of when the error happened */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;

	/** http operation status. anything from 4xx to show client errors or 5xx to server errors */
	private HttpStatus httpStatus;

	/** user-friendly message about the error */
	private String errorMessage;

	/** system message describing the error in more detail */
	private Object exceptionDetails;

	/** exception stack trace which will developer in debug process */
	private String developerMessage;

	private ApiErrorResponse() {
		this.timeStamp = LocalDateTime.now();
	}

	public ApiErrorResponse(HttpStatus httpStatus, Throwable throwable) {
		this();
		this.httpStatus = httpStatus;
		this.errorMessage = "Unexpected Error";
		this.exceptionDetails = throwable.getLocalizedMessage();
	}

	public ApiErrorResponse(HttpStatus httpStatus, String errorMessage, Object exceptionDetails, String developerMessage) {
		this();
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.exceptionDetails = exceptionDetails;
		this.developerMessage = developerMessage;
	}

}

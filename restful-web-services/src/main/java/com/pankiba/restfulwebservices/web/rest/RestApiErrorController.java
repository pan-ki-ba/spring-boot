package com.pankiba.restfulwebservices.web.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiErrorController extends AbstractErrorController {

	public RestApiErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping("/error")
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest httpServletRequest) {

		Map<String, Object> body = this.getErrorAttributes(httpServletRequest, ErrorAttributeOptions.defaults());
		HttpStatus httpStatus = this.getStatus(httpServletRequest);
		
		return new ResponseEntity<>(body, httpStatus);
	}

}

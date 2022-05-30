package com.pankiba.restfulwebservices.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WelcomeResource {

	@Autowired
	private MessageSource messageSource;

	@GetMapping(path = "/")
	public String helloWorld() {
		return "Welcome to Spring Boot, world of new possibilities !!";
	}

	@GetMapping(path = "/internationalized")
	public String helloWorldInternationalized() {
		/**
		 * @LocaleConextHolder.getLocale() Return the Locale associated with the given user context, if any, or the
		 * system default Locale otherwise. This is effectively a replacement for Locale.getDefault, able to optionally
		 * respect a user level Locale setting
		 */
		return messageSource.getMessage("welcome.message", null, LocaleContextHolder.getLocale());
	}
	
}

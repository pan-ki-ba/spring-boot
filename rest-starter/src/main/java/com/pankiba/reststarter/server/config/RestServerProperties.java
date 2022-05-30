package com.pankiba.reststarter.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "rest.server")
@Getter
@Setter
public class RestServerProperties {

	private boolean enabled = true;

	private ExceptionHandler exceptionHandler = new ExceptionHandler();
	
	@Getter
	@Setter
	public static class ExceptionHandler {
		private boolean enabled = true;
	}
	
}

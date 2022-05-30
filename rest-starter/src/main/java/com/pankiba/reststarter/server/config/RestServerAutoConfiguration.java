package com.pankiba.reststarter.server.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pankiba.reststarter.server.audit.LoggingAspect;
import com.pankiba.reststarter.server.exception.GenericExceptionHandler;
import com.pankiba.reststarter.server.exception.RestExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ConditionalOnProperty(prefix = "rest.server", name = "enabled", havingValue = "true", matchIfMissing = true)
@Configuration
@EnableConfigurationProperties(RestServerProperties.class)
@Slf4j
public class RestServerAutoConfiguration {

	@Bean
	public LoggingAspect loggingAspect() {
		log.info(" initializing loggingAspect ");
		return new LoggingAspect();
	}

	@Bean
	@ConditionalOnProperty(prefix = "rest.server", name = "exception-handler.enabled", havingValue = "true", matchIfMissing = true)
	public GenericExceptionHandler genericExceptionHandler() {
		
		log.info(" initializing genericExceptionHandler ");
		return new GenericExceptionHandler();
	}

	@Bean
	@ConditionalOnProperty(prefix = "rest.server", name = "exception-handler.enabled", havingValue = "true", matchIfMissing = true)
	public RestExceptionHandler restExceptionHandler() {
		
		log.info(" initializing restExceptionHandler ");
		return new RestExceptionHandler();
	}

}

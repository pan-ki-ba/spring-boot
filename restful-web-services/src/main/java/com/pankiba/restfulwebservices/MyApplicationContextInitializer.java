package com.pankiba.restfulwebservices;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	/**
	 * This code executed before the Spring application context gets completely created. For example, we can use an
	 * ApplicationContextInitializer to set or add Spring environment profile programmatically,
	 */
	@Override
	public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

		log.info("calling MyApplicationContextInitializer initialize ");
	}

}

package com.pankiba.restfulwebservices;

import javax.servlet.ServletContainerInitializer;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.SpringServletContainerInitializer;

/**
 * <p>
 * This is a helper Java class that provides an alternative to creating a {@code web.xml}. This will be invoked only
 * when the application is deployed to a Servlet container like Tomcat, JBoss etc.
 * 
 * <p>
 * Spring {@link SpringBootServletInitializer} provides a programmatic way to configure the Spring DispatcherServlet and
 * ContextLoaderListener in Servlet 3.0+ compliant servlet containers , rather than adding this configuration through a
 * web.xml file.
 * 
 * <p>
 * Implementors of {@link ServletContainerInitializer} interface are notified during the context startup phase and can
 * perform any programatic registration through the provided ServletContext.
 * 
 * <p>
 * Spring implements the {@link ServletContainerInitializer} through {@link SpringServletContainerInitializer} class.
 * This implementation must be declared in a META-INF/services/javax.servlet.ServletContainerInitializer file of the
 * libraries jar file - Spring declares this in spring-web*.jar jar file and has an entry
 * <b>org.springframework.web.SpringServletContainerInitializer</b>
 * 
 * <p>
 * {@link SpringServletContainerInitializer} class has a @HandlerTypes annotation with a value of
 * WebApplicationInitializer, this means that the Servlet container will scan for classes implementing the
 * WebApplicationInitializer implementation and call the onStartUp method with these classes and that is where the
 * WebApplicationInitializer fits in.
 * 
 */
public class ApplicationWebXml extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {

		/*
		 * setting default profile as DEV if no other profile is configured. This needs to be done before calling run
		 * method on SpringApplication
		 */
		return springApplicationBuilder.sources(RestfulWebServicesApplication.class);
	}
}
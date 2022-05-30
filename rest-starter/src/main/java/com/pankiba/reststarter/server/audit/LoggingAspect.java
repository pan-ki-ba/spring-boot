package com.pankiba.reststarter.server.audit;

import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

/**
 *  Aspect to log method execution time of all classes that Advice matches
 * @author pankiba
 */
@Aspect
@Slf4j
public class LoggingAspect {

	@Around("within(@org.springframework.web.bind.annotation.RestController *)")
	public Object restLayerLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		String methodName = proceedingJoinPoint.getSignature().getName();
        StopWatch stopWatch = StopWatch.createStarted();
        
		Object returnValue = null;
		try {

			returnValue = proceedingJoinPoint.proceed();
			stopWatch.stop();
			
			log.info("method " + methodName + " executed successfully in (HH:mm:ss.SSS) : " + stopWatch);

		} catch (Throwable throwable) {
			log.info("error while executing method ");
			throw throwable;
		}
		return returnValue;
	}

}

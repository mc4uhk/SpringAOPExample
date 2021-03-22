package com.jcg.examples.profiler;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.jcg.examples.bo.BusinessTargetObject;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class Profiler2 {

	@Before("execution(* sayHello(..))")
	public void logBeforeTxn(JoinPoint joinpoint)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		log.info(BeanUtils.describe(joinpoint).toString());
		//log.info(joinpoint.getArgs()[0].toString());
		log.info(joinpoint.getTarget().toString());
		BusinessTargetObject bo = (BusinessTargetObject) joinpoint.getTarget();
		bo.setName("Evie");
		System.out.println("Beginning execution for " + joinpoint.getSignature().getName());
	}

	@After("execution(* sayHello(..))")
	public void logAfterTxn(JoinPoint joinpoint) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
	}

	@Around("execution(* merryGoAround(..))")
	public void logAroundTxn(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("Beginning execution for " + proceedingJoinPoint.getSignature().getName());
		proceedingJoinPoint.proceed();
		System.out.println("Execution completed for " + proceedingJoinPoint.getSignature().getName());
	}

	@AfterReturning(pointcut = "execution(* performTransaction(..))", returning = "retVal")
	public void logResultsAfterTxn(JoinPoint joinpoint, Object retVal) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
		System.out.println("Value being returned is " + retVal);
	}

	@AfterThrowing(pointcut = "execution(* throwException(..))", throwing = "exception")
	public void logResultsAfterError(JoinPoint joinpoint, Throwable exception) {
		System.out.println("Execution completed for " + joinpoint.getSignature().getName());
		System.out.println("Error in Join Point due to : " + exception.getMessage());
		System.out.println("Advice complete");
	}
}

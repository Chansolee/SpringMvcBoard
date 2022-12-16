package kr.or.ddit.advice;

import javax.print.attribute.standard.JobOriginatingUserName;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Cross Cutting Concern 코드화 -> Advice
 *
 */
@Slf4j
//@Component
@Aspect
public class LoggingAdvice {
	@Pointcut("execution(* kr.or.ddit.*.service.*.*(..))")
	public void dummy() {
		
	}
	
//	@Before("dummy()")
	public void before(JoinPoint joinPoint) {
		log.info("===================================");
		Signature signature = joinPoint.getSignature();// 타겟 메소드의 선언부 정보
		String targetMethodName = signature.getName();
		Object target = joinPoint.getTarget();
		String targetClassName = target.getClass().getSimpleName();
		Object[] args = joinPoint.getArgs();
		log.info("{}.{}({})",targetClassName, targetMethodName,args);
	}
	
//	@AfterReturning(pointcut="dummy()",returning="retValue")
	public void afterReturn(JoinPoint joinPoint, Object retValue) {
		Signature signature = joinPoint.getSignature();// 타겟 메소드의 선언부 정보
		String targetMethodName = signature.getName();
		Object target = joinPoint.getTarget();
		String targetClassName = target.getClass().getSimpleName();
		log.info("{},{} 의 반환값 : {}",targetClassName, targetMethodName, retValue);
	}
	
	@Around("dummy()")
	public Object aroud(ProceedingJoinPoint joinPoint) throws Throwable{
		log.info("===================================");
		Signature signature = joinPoint.getSignature();// 타겟 메소드의 선언부 정보
		String targetMethodName = signature.getName();
		Object target = joinPoint.getTarget();
		String targetClassName = target.getClass().getSimpleName();
		Object[] args = joinPoint.getArgs();
		log.info("{}.{}({})",targetClassName, targetMethodName,args);
		long start = System.currentTimeMillis();
		
		Object retValue;
	
			retValue = joinPoint.proceed(args);
			long end = System.currentTimeMillis();
			log.info("{},{} 의 반환값 : {}\n 소요시간 : {}ms",targetClassName, targetMethodName, retValue
					, (end-start));
			return retValue;
		

	}

}

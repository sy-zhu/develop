package com.develop.util.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.develop.model.User;

/**
 * ϵͳ�������Aspect����Bean
 * 
 * @author zhushouyang
 */
//��������һ�����
@Component
//��������һ������Bean
@Aspect
public class ServiceAspect {

	private final static Log log = LogFactory.getLog(ServiceAspect.class);

	// ���������,�÷����޷�����,��ҪΪ����ͬ������������ʹ�ô˴����õ������
	@Pointcut("execution(* com.develop.controller.*..*(..))")
	public void aspect() {
		
	}

	/*
	 * ����ǰ��֪ͨ,ʹ���ڷ���aspect()��ע�������� ͬʱ����JoinPoint��������,����û�иò���
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
		System.out.println("ִ��before.....");

	}

	// ���ú���֪ͨ,ʹ���ڷ���aspect()��ע��������
	@After("aspect()")
	public void after(JoinPoint joinPoint) {
		System.out.println("ִ��after.....");
	}

	// ���û���֪ͨ,ʹ���ڷ���aspect()��ע��������
//	@Around("aspect()")
//	public void around(JoinPoint joinPoint) {

//		long start = System.currentTimeMillis();
//		try {
//			((ProceedingJoinPoint) joinPoint).proceed();
//			long end = System.currentTimeMillis();
//			if (log.isInfoEnabled()) {
//				log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
//			}
//		} catch (Throwable e) {
//			long end = System.currentTimeMillis();
//			if (log.isInfoEnabled()) {
//				log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : "
//						+ e.getMessage());
//			}
//		}
//	}

	// ���ú��÷���֪ͨ,ʹ���ڷ���aspect()��ע��������
//	@AfterReturning("aspect()")
//	public void afterReturn(JoinPoint joinPoint) {
//		if (log.isInfoEnabled()) {
//			log.info("afterReturn " + joinPoint);
//		}
//	}
//
//	// �����׳��쳣��֪ͨ,ʹ���ڷ���aspect()��ע��������
//	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
//	public void afterThrow(JoinPoint joinPoint, Exception ex) {
//		if (log.isInfoEnabled()) {
//			log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
//		}
//	}

}
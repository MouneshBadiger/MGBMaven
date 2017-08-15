package com.mgb.controllers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.mgb.daos.IAspectj;

@Aspect
public class AspectjImpl implements IAspectj{
	
	
	@Before("execution(* com.mgb.controllers.WelcomeController.*(..))")
	public void testAspectj() {
		System.out.println("testAspectj-------");
		
	}
}

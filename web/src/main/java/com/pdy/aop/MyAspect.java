package com.pdy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by pengdeyao on 2019/3/14
 */
@Component
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.pdy.service.*.*(..))")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("###n around advice");
        return pjp.proceed();
    }
}

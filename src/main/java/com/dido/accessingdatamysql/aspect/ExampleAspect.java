package com.dido.accessingdatamysql.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {

    //@Around. This is our advice
    @Around("@annotation(com.dido.accessingdatamysql.annotations.LogExecutionTime)") // point cut argument
    //The method logExecutionTime() itself is our advice
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature() + " >>> EXECUTED IN " + executionTime + "ms");

        return proceed;
    }

}

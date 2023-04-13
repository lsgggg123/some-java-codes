package com.lsgggg123.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 简单做下方法的计时
 */
@Slf4j
@Aspect
@Component
public class ProfilerAopAspect {

    @Around("@annotation(Profile)")
    public Object profiler(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String name = signature.getDeclaringTypeName() + "#" + signature.getName();
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            log.info("{} cost: {} millis.", name, (System.currentTimeMillis() - start));
        }
    }
}

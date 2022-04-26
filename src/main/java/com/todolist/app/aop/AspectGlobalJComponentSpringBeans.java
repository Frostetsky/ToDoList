package com.todolist.app.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;
import java.util.Objects;

@Aspect
@Slf4j
public class AspectGlobalJComponentSpringBeans {

    @Pointcut("within((@org.springframework.stereotype.Service *)" +
              "    || (@org.springframework.stereotype.Component *) " +
              "    || (@org.springframework.stereotype.Repository *) " +
              "    || (@org.springframework.web.bind.annotation.RestController *))")
    public void beanAnnotationAllComponent() {}

    @AfterReturning(pointcut = "beanAnnotationAllComponent()", returning = "result")
    public void beanAnnotationWithServiceAndComponentAndRepositoryAndControllerAfterResult(JoinPoint joinPoint, Object result) {
        log.info("<=== {}.{}() with result = {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Objects.nonNull(result) ? result.toString() : "null");
    }

    @Around("beanAnnotationAllComponent()")
    public Object log(ProceedingJoinPoint joinPoint) {
        try {
            Object result = joinPoint.proceed();
            log.info("===> {}.{}() with params = {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
            return result;
        } catch (Throwable e) {
            log.error("<=== {} in {}.{}()",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
            throw new RuntimeException(e);
        }
    }
}

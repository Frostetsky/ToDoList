package com.todolist.app.config;

import com.todolist.app.aop.AspectGlobalJComponentSpringBeans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@Configuration
@Slf4j
public class AspectJConfiguration {

    @Bean
    public AspectGlobalJComponentSpringBeans aspectGlobalJComponentSpringBeans() {
        var globalAspect = new AspectGlobalJComponentSpringBeans();
        log.info("{} has bean created.", globalAspect.getClass().getSimpleName());
        return globalAspect;
    }
}

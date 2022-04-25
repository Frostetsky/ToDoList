package com.todolist.app.config;

import com.todolist.app.handler.LogoutHandler;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Data
public class AppConfiguration {

    private Environment environment;

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutHandler(environment);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

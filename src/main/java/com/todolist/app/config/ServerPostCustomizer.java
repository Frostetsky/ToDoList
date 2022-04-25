package com.todolist.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class ServerPostCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Value("${configuration-server.port}")
    private String port;

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(Integer.parseInt(port));
    }
}

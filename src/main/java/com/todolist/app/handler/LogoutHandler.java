package com.todolist.app.handler;

import com.todolist.app.util.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LogoutHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    private final Environment environment;

    public LogoutHandler(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = Objects.requireNonNull(environment.getProperty(Keys.ANONYMOUS_PATHS, String[].class))[1];
        authentication.setAuthenticated(false);
        response.sendRedirect(redirectUrl);
        super.onLogoutSuccess(request, response, authentication);
    }
}

package me.litzrsh.security.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;

public class DefaultUsernamePasswordAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    public static final String DEFAULT_LOGIN_PATH = "/api/v1.0/login";

    public DefaultUsernamePasswordAuthenticationProcessingFilter() {
        super(DEFAULT_LOGIN_PATH);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        return null;
    }
}

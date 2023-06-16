package me.litzrsh.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.litzrsh.commons.utils.StringUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class ThirdPartyOAuth2AuthenticationFilter implements Filter {

    public static final String TOKEN_HEADER_NAME = "Authorization";

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        doFilter(
                (HttpServletRequest) servletRequest,
                (HttpServletResponse) servletResponse,
                filterChain
        );
    }

    public void doFilter(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null ||
                AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
            String tokenHeader = request.getHeader(TOKEN_HEADER_NAME);
            if (StringUtils.hasText(tokenHeader)) {

            }
        }
        filterChain.doFilter(request, response);
    }
}

package me.litzrsh.security.config;

import lombok.extern.slf4j.Slf4j;
import me.litzrsh.security.authentication.JdbcAuthenticationProvider;
import me.litzrsh.user.service.QuickUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class DefaultSecurityConfig {

    @Value("${debug:false}")
    private boolean debug;

    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);
        if (this.debug) {
            http.authorizeHttpRequests(req ->
                    req
                            .anyRequest().permitAll()
            );
        } else {
            http.authorizeHttpRequests(req ->
                    req
                            .anyRequest().authenticated()
            );
        }
        return http.build();
    }

    @Bean
    public JdbcAuthenticationProvider jdbcAuthenticationProvider(
            PasswordEncoder passwordEncoder,
            QuickUserDetailsService userDetailsService
    ) {
        JdbcAuthenticationProvider provider = new JdbcAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        provider.setUserDetailsPasswordService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

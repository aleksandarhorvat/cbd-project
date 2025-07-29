package org.example.apigateway.config;

import org.example.apigateway.filter.ApiKeyAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        ApiKeyAuthenticationManager authManager = new ApiKeyAuthenticationManager();
        ApiKeyAuthenticationFilter authFilter = new ApiKeyAuthenticationFilter(authManager);

        return http
                .csrf(csrf -> csrf.disable())
                .addFilterAt(authFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/user/register", "/user/login").permitAll()
                        .pathMatchers("/user/admin/**").hasRole("ADMIN")
                        .pathMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .anyExchange().denyAll()
                )
                .build();
    }
}
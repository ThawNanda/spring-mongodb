package com.mongodb.demo.config;

import com.mongodb.demo.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html",
            "/graphql/**"
    };

    @Bean
    @Order(1)
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        log.info("security config - 0");
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/api/v1/auth/logout"));
        return http.build();
    }

    @Bean
    DefaultSecurityFilterChain springWebFilterChain(HttpSecurity http) throws Exception {
        log.info("security config - 1");
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests.requestMatchers("/graphql/**").permitAll())
                .build();
    }
}

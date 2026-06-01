package com.valeria.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // ENDPOINTS PUBLICOS
                .requestMatchers(
                    "/api/auth/login",
                    "/auth/register",
                    "/api/product/**",
                    "/api/product/save",
                    "/api/product/getProducts",
                    "/api/product/search",
                    "/api/product",
                    "/api/users/**",
                    "/api/users/save",
                    "/api/users",
                    "/api/users/search"

                    
                    
                
                ).permitAll()

                // TODO LO DEMÁS REQUIERE LOGIN
                .anyRequest().authenticated()
            )

            .formLogin(form -> form.disable());

        return http.build();
    }
}
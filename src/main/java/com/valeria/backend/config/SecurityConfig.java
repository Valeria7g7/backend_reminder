package com.valeria.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
import com.valeria.backend.security.JwtAuthenticationFilter;
import org.springframework.security.config.Customizer;
@Configuration
public class SecurityConfig {
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	public SecurityConfig(
	        JwtAuthenticationFilter jwtAuthenticationFilter
	) {
	    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
          
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session ->
            session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
            )
    )
            .authorizeHttpRequests(auth -> auth
            		  // permitir preflight de CORS(OPTIONS), solo para pruebas
            		 .requestMatchers(
            			        HttpMethod.OPTIONS,
            			        "/**"
            			    ).permitAll()
                // ENDPOINTS PUBLICOS
                .requestMatchers(
                    "/api/auth/login",
                    "/api/auth/register"//,
                    ,"/api/auth/refresh"
                  //  "/api/auth/me"
                    //"/api/users/**"
                   // ,"/api/product/**"
//                    "/api/product/save",
//                    "/api/product/getProducts",
//                    "/api/product/search",
//                    "/api/product",
//                    "/api/users/**",
//                    "/api/users/save",
//                    "/api/users",
//                    "/api/users/search"
                ).permitAll()
                // TODO LO DEMÁS REQUIERE LOGIN
                .anyRequest().authenticated()
            ) 
            //aqui le decimos ntes de procesar cada peticion, ejecuta JwtAuthenticationFilter
            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            )

            .formLogin(form -> form.disable())
            .cors(Customizer.withDefaults());

        return http.build();
    }
}
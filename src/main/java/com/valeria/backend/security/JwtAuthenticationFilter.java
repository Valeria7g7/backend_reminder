package com.valeria.backend.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//Leer token
//↓
//Validar firma
//↓
//Obtener email
//↓
//Buscar usuario
//↓
//Crear Authentication
//↓
//Guardar usuario autenticado en SecurityContextHolder
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService userDetailsService
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
    	//String path = request.getServletPath();
    	String path = request.getRequestURI();

    	if (
    			path.startsWith("/api/auth/refresh") || 
    			path.startsWith("/api/auth/login") ||
    	    path.startsWith("/api/auth/register")) {
    	    filterChain.doFilter(request, response);

    	    return;
    	}
    	
    	
    	try {    	
        final String authHeader =
                request.getHeader("Authorization");
        //if( authHeader==null|| !authHeader.startsWith("Bearer ")) throw new RuntimeException("Usuario no encontrado");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = authHeader.substring(7);

        String email =jwtService.extractEmail(token);
        if (email == null || SecurityContextHolder.getContext().getAuthentication() != null) {
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        	return;
        }
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if (jwtService.isTokenValid(token)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
        
    	
    	
    	}        
        catch (io.jsonwebtoken.ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
    }
    	
    	
}

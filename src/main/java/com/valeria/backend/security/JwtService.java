package com.valeria.backend.security;
import io.jsonwebtoken.ExpiredJwtException;
import java.security.Key;
//import java.security.SecretKey;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

//Crear JWT
//Validar JWT
//Leer email del JWT
//Crear tokens
//Leer tokens
//Validar tokens
//Obtener email del token
@Service
public class JwtService {

	@Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-expiration}")
    private long accessExpiration;
    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    //private Key getSigningKey() 
    private SecretKey getSigningKey()
    {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(Long userId,String email) 
    {

        return Jwts.builder()
                .subject(email)
                .claim("userId", userId)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                + accessExpiration
                        )
                )
                .signWith(getSigningKey())
                .compact();
    }
    public String generateRefreshToken(Long userId,String email) 
    {

        return Jwts.builder()
                .subject(email)
                .claim("userId", userId)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(getSigningKey())
                .compact();
    }
    public String extractEmail(
            String token
    ) {

        return extractAllClaims(token)
                .getSubject();
    }

    public Long extractUserId(
            String token
    ) {

        return extractAllClaims(token)
                .get("userId", Long.class);
    }

    public boolean isTokenValid(
            String token//,
           // String email
    ) {
    	 try {

    	        extractAllClaims(token);

    	        return true;

    	    } catch (ExpiredJwtException e) {

    	        return false;

    	    } catch (Exception e) {

    	        return false;
    	    }

//        return extractEmail(token)
//                .equals(email)
//                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(
            String token
    ) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractAllClaims(
            String token
    ) {

        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}

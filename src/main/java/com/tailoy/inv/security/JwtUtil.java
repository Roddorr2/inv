package com.tailoy.inv.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final String jwtSecret = "Vh9s7T8l0P@z!xD3Jq#M1r9K4Bn$AeFz";
	private final int jwtExpirationMs = 86400000;
	private final Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(key)
				.compact();
	}
	
	public String getUsernameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build()
				.parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}

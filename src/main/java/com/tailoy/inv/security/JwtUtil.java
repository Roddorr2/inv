package com.tailoy.inv.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtil {
	private final String jwtSecret = "nj8f+OwZtegrWTovLoUUcfq9BbaC9T9QyB7dIiqjybpRTMqizrdGDPmel2CYdgpV54CGlyyjqpglJI4E0i5knw==";
	private final int jwtExpirationMs = 86400000;
	private final Key key = Keys.hmacShaKeyFor(java.util.Base64.getDecoder().decode(jwtSecret));
	
	public JwtUtil() {
		System.out.println("CLAVE SECRETA USADA EN JwtUtil: " +
				Base64.getEncoder().encodeToString(key.getEncoded()));
	}
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(key, SignatureAlgorithm.HS256)
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

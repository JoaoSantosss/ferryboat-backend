package com.ferryboat.app.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final String secretkey = "cc31652ba4024518627a6b8bfbf146b6d8e9b88c329a6af1f5ecc31dcd262523";
    
    @Value("${security.jwt.expiration-time}")
    private Long jwtExpirationTime;
        

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		
		Claims claims = extractAllClaims(token);
		
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public String generateToken(UserDetails user) {
		return generateToken(new HashMap<>(), user);
	}

	private String generateToken(Map<String, Object> extraClaims, UserDetails user) {
		return buildToken(extraClaims, user);
	}


	private String buildToken(Map<String, Object> extraClaims, UserDetails user) {		
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationTime))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretkey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public boolean isTokenValid(String token, UserDetails user) {
		String username = extractUsername(token);
		return username.equals(user.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

}

package com.MedNex.healthcare.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

	private final String SECRET_KEY = "super_secret_key_super_secret_key_super_secret_key";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);// getPassword()
	}

	public <T> T extractClaim(String token, java.util.function.Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parserBuilder()// getPassword()
				.setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))// Keys cannot be resolved
				.build().parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}

	public String generateToken(UserDetails userDetails, String tenant) {
		return Jwts.builder().setSubject(userDetails.getUsername())
				.claim("role", userDetails.getAuthorities().iterator().next().getAuthority()).claim("tenant", tenant)
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256).compact();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}
}

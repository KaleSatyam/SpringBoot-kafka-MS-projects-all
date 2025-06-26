package com.blogapplication.Blog.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {
	
	public static final long JWT_TOKEN_VALIDITY=5 * 60 * 60;
	
	public String secret="jwtTokenKey";
	
	//retrive username from token
	public String getuserNameFromToken(String token)
	{
		return getClaimFromToken(token,Claims::getSubject);
	}

	//retrive expiration date from token
	public Date getExpirationDate(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimresolver) {
		// TODO Auto-generated method stub
		final Claims claim=getAllclaimsFromToken(token);
		return claimresolver.apply(claim);
	}

	//fro retriving any information from token we will need secret key
	private Claims getAllclaimsFromToken(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
     }
	
	private boolean isTokenExpired(String token) {
		final Date date=getExpirationDate(token);
		return date.before(new Date());
	}
	
	//generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object>  claims=new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
	}
	
//	while creating the token-
//	1. define claims of the token, like issuer, expiration , subject, and the ID
//	2. sign the jwt using the HS512 algorithm and secret key.
//	3. accorrding to jws compact serialization(https://tools/ietf.org/html/draft-ietf-jose)
//		compaction of the jwt to a URL-Safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*100))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	//validate token
	public boolean validateToken(String token,UserDetails userdetails) {
		final String username=getuserNameFromToken(token);
		
		return (username.equals(userdetails.getUsername()) && !isTokenExpired(token));
	}

}

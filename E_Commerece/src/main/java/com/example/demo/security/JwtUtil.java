package com.example.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "I_Am_Immortal";
    private static final long ACCESS_TOKEN_EXPIRY = 1000 * 60 * 15; // 15 minutes
    private static final long REFRESH_TOKEN_EXPIRY = 1000 * 60 * 60 * 24; // 24 hours

//    public String generateToken(String email, long expiration) {
//        return Jwts.builder()
//            .setSubject(email)
//            .setIssuedAt(new Date())
//            .setExpiration(new Date(System.currentTimeMillis() + expiration))
//            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
//            .compact();
//    }
    
  //generate Token For user
  	public String generateToken(UserDetails userdetails) {
  		Map<String,Object> claims= new HashMap<>();
  		return doGenerateToken(claims, userdetails.getUsername());
  	}
private String doGenerateToken(Map<String, Object> claims,String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRY*1000))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
				//.signWith(secret,SignatureAlgorithm.HS512).compact();
	}
  	

    public static String generateAccessToken(String email,Map<String, Object>) {
        return generateToken(email, ACCESS_TOKEN_EXPIRY);
    }

    public String generateRefreshToken(String email,Map<String, Object>) {
        return generateToken(email, REFRESH_TOKEN_EXPIRY);
    }

//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
    public static String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    
  //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY ).parseClaimsJws(token).getBody();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

package com.example.vrcapp.service;

import com.example.vrcapp.config.JwtAuthenticationFilter;
import com.example.vrcapp.model.Users;
import com.example.vrcapp.payload.request.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.util.Map;

import static io.jsonwebtoken.Jwts.SIG.ES256;

@Service
public class JwtService {

    private static Logger LOGGER = LoggerFactory.getLogger(JwtService.class);

    private static final String SECRET_KEY = "b8e8f2a5c1d9e26c1b0a0f9d1e6e3e7c6f59c8d1f74c3e7d5a8d10e6fefb98e7";

    public String generateToken(LoginUser  loginUser)
    {
        LOGGER.info("In Generate Token Method");
        return generateToken(new HashMap<>(),loginUser);
    }

    public String extractUserName(String jwtToken) {

        return extractClaim(jwtToken,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(
            Map<String,Object> extractClaims,
            LoginUser loginUser)
    {
        LOGGER.info("In Generate Token Method with login details");
        return Jwts.builder().claims().add(extractClaims)
                .subject(loginUser.getEmailId())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 1000*60*60*1))
                .and().signWith(getSignInKey())
               // .and().signWith(getSignInKey(), SecureDigestAlgorithm(SECRET_KEY,String))
                .compact();
    }

    public boolean isTokenValid(String token, Users users)
    {
        final String email = extractUserName(token);
        return (users.getEmailId().equals(email)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private PublicKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return (PublicKey) Keys.hmacShaKeyFor(keyBytes);
    }
}

package com.revature.security;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Component
public class TokenGenerator {
  private final int expiration;
  private final SecretKey secretKey;
  private final UserDAO userDAO;

  @Autowired
  public TokenGenerator(@Value("${jwt.key}") String key,
                        @Value("${jwt.expirationDateInMs}") int expiration, UserDAO userDAO) {
    this.expiration = expiration;
    this.secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA512");
    this.userDAO = userDAO;
  }

  public String generateToken(Authentication auth) {
    String email = auth.getName();
    Date now = new Date();
    Date expire = new Date(now.getTime() + expiration);
    User user = userDAO.findByEmail(email);

    return Jwts.builder()
      .setSubject(email)
      .claim("id", user.getId())
      .claim("role", user.getRole().getTitle())
      .claim("first_name", user.getFirstName())
      .claim("last_name", user.getLastName())
      .setIssuedAt(now)
      .setExpiration(expire)
      .signWith(secretKey, SignatureAlgorithm.HS512)
      .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJwt(token);

      return true;
    } catch (Exception e) {
      throw new AuthenticationCredentialsNotFoundException(
        "Token is expired or invalid"
      );
    }
  }

  public String getEmailFromToken(String token) {
    if (token != null && token.startsWith("Bearer")) {
      token = token.substring(7);
    }

    Claims claims = Jwts.parserBuilder()
      .setSigningKey(secretKey)
      .build()
      .parseClaimsJws(token)
      .getBody();

    return claims.getSubject();
  }
}

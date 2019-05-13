package com.greenfoxacademy.goddesstribesbackend.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

import static java.util.Collections.emptyList;

public class JWTUtility {

  public static String generateToken(String username) {
    String jwtToken = Jwts.builder()
            .setSubject(username)
            .signWith(SignatureAlgorithm.HS256, SecurityCostants.SECRET)
            .compact();
    return jwtToken;
  }

  public static Authentication getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(SecurityCostants.HEADER_STRING);
    String username;

    if (token != null) {
      try {
        username = Jwts.parser()
                .setSigningKey(SecurityCostants.SECRET)
                .parseClaimsJws(token.replace(SecurityCostants.TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        return new UsernamePasswordAuthenticationToken(username, null, emptyList());

      } catch (SignatureException e) {
        return null;
      }
    }
    return null;
  }

}
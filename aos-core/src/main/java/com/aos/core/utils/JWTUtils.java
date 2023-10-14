package com.aos.core.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Duration;
import java.time.Instant;

public class JWTUtils {

  private static final String secretKey = "aos_in_suzhou_falling_in_love";

  private static final String claimSubject = "bizId";

  public static String createToken(Integer bizId,String bizInfo, long expireHours) {
    return JWT.create()
        .withSubject(String.valueOf(bizId))
        .withClaim(claimSubject, bizId)
        .withExpiresAt(Instant.now().plus(Duration.ofHours(expireHours)))
        .sign(Algorithm.HMAC256(secretKey));
  }

  public static String getBizId(String jwtToken) {
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
    return verifier.verify(jwtToken).getClaim(claimSubject).asString();
  }

  public static Integer getBizId() {
    return Integer.valueOf(((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
  }


  public static Authentication getAuthentication(String jwtToken) {
    return new UsernamePasswordAuthenticationToken(getSubject(jwtToken), null, null);
  }

  public static String getSubject(String jwtToken) {
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
    return verifier.verify(jwtToken).getSubject();
  }
}

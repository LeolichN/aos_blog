package com.aos.core.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Duration;
import java.time.Instant;

public class JWTUtils {

  private static final String secretKey = "aos_in_suzhou_falling_in_love";

  private static final String claimSubject = "bizId";

  public static String createToken(String bizId, long expireHours) {
    return JWT.create()
        .withSubject(bizId)
        .withClaim(claimSubject, bizId)
        .withExpiresAt(Instant.now().plus(Duration.ofHours(expireHours)))
        .sign(Algorithm.HMAC256(secretKey));
  }

  public static String getBizId(String jwtToken) {
    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
    return verifier.verify(jwtToken).getClaim(claimSubject).asString();
  }
}

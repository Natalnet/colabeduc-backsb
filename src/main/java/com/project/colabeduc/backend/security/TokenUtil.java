package com.project.colabeduc.backend.security;

import static com.project.colabeduc.backend.security.SecurityConstants.EXPIRATION_TIME;
import static com.project.colabeduc.backend.security.SecurityConstants.SECRET;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class TokenUtil {
  public static String getToken(String idUsuario) {
    String token = JWT.create()
							.withSubject(idUsuario)
							.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
							.sign(Algorithm.HMAC512(SECRET.getBytes()));
    return token;
  }
}

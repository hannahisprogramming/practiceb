package com.hannah.practiceb.services;

import com.hannah.practiceb.auth.JwtConfig;
import com.hannah.practiceb.dtos.UserDTO;
import com.hannah.practiceb.exceptions.FailedAuthenticationException;
import com.hannah.practiceb.exceptions.TokenExpiredException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {
    private JwtConfig jwtConfig;

    public TokenService (JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createToken(UserDTO user) {
        String jws = "";

        if (user != null && user.getUsername() != null) {
            long now = System.currentTimeMillis();

            jws = Jwts.builder()
                    .setId(String.valueOf(user.getId()))
                    .setSubject(user.getUsername())
                    .setIssuer(jwtConfig.getIssuer())
                    .setIssuedAt(new Date(now))
                    .setExpiration(new Date(now + jwtConfig.getExpiration()))
                    .signWith(jwtConfig.getSigningKey())
                    .compact();
        }

        return jws;
    }

    public int getDefaultExpiration() {
        return jwtConfig.getExpiration();
    }

    public Optional<UserDTO> validateToken(String token)
            throws FailedAuthenticationException, TokenExpiredException {
        try {
            Claims jwtClaims = Jwts.parserBuilder()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            long now = System.currentTimeMillis();
            if (jwtClaims.getExpiration().before(new Date(now))) {
                throw new TokenExpiredException();
            }

            UserDTO userDTO = parseUser(jwtClaims);

            return Optional.of(userDTO);
        } catch (JwtException e) {
            throw new FailedAuthenticationException();
        }
    }

    private UserDTO parseUser(Claims claims) {
        Long id = Long.parseLong(claims.getId());
        String username = claims.getSubject();

        return new UserDTO(id, username);
    }
}

package com.hannah.practiceb.auth;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Component
public class JwtConfig {
    //value allows you to use SpEL, but also get properties from the application.properties/yml
    @Value("${jwt.secret}")
    private String salt;

    //example of using value w SpEL (spring expression language)
    //to calculate the number of milliseconds in a day.
    //Spring will then set this value for us
    @Value("#{24*60*60*1000}")
    private int expiration;

    //this algorithm requires a 256 character (2048 bit) key
    private static final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
    private Key signingKey;

    private String issuer = "hannah";

    //this annotation means that the method should be
    //executed after dependency injection is complete
    @PostConstruct
    public void createKey() {
        byte[] saltyBytes = DatatypeConverter.parseBase64Binary(salt);
        signingKey = new SecretKeySpec(saltyBytes, sigAlg.getJcaName());
    }

    public int getExpiration() {
        return this.expiration;
    }

    public static SignatureAlgorithm getSigAlg() {
        return sigAlg;
    }

    public Key getSigningKey() {
        return signingKey;
    }

    public String getIssuer() {
        return issuer;
    }
}

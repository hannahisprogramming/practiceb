package com.hannah.practiceb.advice;

import com.hannah.practiceb.annotations.Authorized;
import com.hannah.practiceb.dtos.UserDTO;
import com.hannah.practiceb.exceptions.FailedAuthenticationException;
import com.hannah.practiceb.exceptions.TokenExpiredException;
import com.hannah.practiceb.services.TokenService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AuthAspect {
    private TokenService tokenService;

    //Spring will autowire a proxy object for the request
    //it isnt a request object itself, but if there is an active request
    //the proxy will pass method calls to the real request
    private final HttpServletRequest currentReq;

    public AuthAspect(TokenService tokenService, HttpServletRequest req) {
        this.tokenService = tokenService;
        this.currentReq = req;
    }

    @Around("methodsWithAuthAnnotation()")
    public Object authenticate(ProceedingJoinPoint joinPoint) throws Throwable {
        Authorized authAnnotation = ((MethodSignature) joinPoint.getSignature())
                .getMethod()
                .getAnnotation(Authorized.class);
        final boolean requireSelfAction = authAnnotation.requireSelfAction();

        final String jws = currentReq.getHeader("Auth");
        final String currentUser = currentReq.getHeader("Username");

        //if user has an empty token header
        if (jws == null || jws.equals("")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Empty token.");
        }

        Optional<UserDTO> userDtoOpt = Optional.empty();
        try {
            userDtoOpt = tokenService.validateToken(jws);
        } catch (FailedAuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
        } catch (TokenExpiredException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Expired token.");
        }

        if (!userDtoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user info present.");
        }

        final String username = userDtoOpt.get().getUsername();

        //username has to be the same as the token
        //in other words, the user has to be doing the request to themselves
        //this is to prevent users from modifying other users
        if (requireSelfAction) {
            if (currentUser != null) {
                final boolean doesUserMatchToken = currentUser.equals(username);
                if (!doesUserMatchToken) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username does not match the token.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no username in the header.");
            }
        }
        return joinPoint.proceed();
    }

    @Pointcut("@annotation(com.hannah.practiceb.annotations.Authorized)")
    public void methodsWithAuthAnnotation() {
        //i dont know why this has to be empty but it do
    }

}

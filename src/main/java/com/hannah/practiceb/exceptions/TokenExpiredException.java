package com.hannah.practiceb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for when a java web token expires. Should return a
 * 401 UNAUTHORIZED when thrown in a controller.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "The token is expired!")
public class TokenExpiredException extends Exception{

}

package com.revature.exceptions.advisors;

import com.revature.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionAdvisor extends ResponseEntityExceptionHandler {
  @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
  public ResponseEntity<?> handleCredentialsNotFoundException(
    AuthenticationCredentialsNotFoundException acnfe, WebRequest wr) {
    String msg = "Token is expired or invalid";
    Map<String, Object> body = new HashMap<>();

    body.put("timestamp", LocalDateTime.now());
    body.put("message", msg);
    log.warn(msg);

    return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(UserRegistrationException.class)
  public ResponseEntity<?> handleUserRegistrationException(
    UserRegistrationException ure, WebRequest wr) {

    Map<String, Object> body = new HashMap<>();

    body.put("timestamp", LocalDateTime.now());
    body.put("message", "Registration could not be completed");

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserUpdateException.class)
  public ResponseEntity<?> handleUserUpdateException(
    UserUpdateException uue, WebRequest wr) {

    Map<String, Object> body = new HashMap<>();

    body.put("timestamp", LocalDateTime.now());
    body.put("message", "User information could not be updated");

    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}

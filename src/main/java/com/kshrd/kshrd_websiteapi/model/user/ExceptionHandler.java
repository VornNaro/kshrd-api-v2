package com.kshrd.kshrd_websiteapi.model.user;

import com.kshrd.kshrd_websiteapi.configuration.interceptor.LoggingInterceptor;
import com.kshrd.kshrd_websiteapi.rest.message.BaseApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.sql.Timestamp;

@ControllerAdvice
public class ExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<BaseApiResponse<UserResponse>> handleInternalAuthenticationServiceException(
            InternalAuthenticationServiceException e) {

        BaseApiResponse<UserResponse> response = new BaseApiResponse<>();
        response.setMessage("User not found");
        response.setData(null);
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RequestRejectedException.class)
    public ResponseEntity<BaseApiResponse<UserResponse>> handleRejectedRequestException(RequestRejectedException e) {
        logger.info("Recieved URL with malicious string: {}", e.getLocalizedMessage());
        BaseApiResponse<UserResponse> response = new BaseApiResponse<>();
        response.setMessage("invalid url request");
        response.setData(null);
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }

}
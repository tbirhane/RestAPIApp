package com.javainterview.java_apple.controller;

import com.javainterview.java_apple.controller.error.ErrorResponse;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.util.logging.Logger;

@ControllerAdvice
public class ErrorController {
public static Logger logger = Logger.getLogger(ErrorController.class.getName());

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Exception")
    @ExceptionHandler(IOException.class)
    public void h2ExceptionHandler(){
        logger.warning("IOException ");
    }
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequestExceptionHandler(final HttpClientErrorException.BadRequest ex, final HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(request.getRequestURI());
        logger.warning("Invalid input Exception: " +ex.getMessage());
        return error;
    }
    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse responseNotFound(final ResourceNotFoundException ex, final HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(request.getRequestURI());
        logger.info("Resource not found: " + ex.getMessage());
        return error;
    }
    @ExceptionHandler(value = Exception.class)
  //  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse hadleAllException(final Exception ex, final HttpServletRequest request){
        ErrorResponse error = new ErrorResponse();
        error.setErrorMessage(ex.getMessage());
        error.setRequestedURI(request.getRequestURI());
        logger.info("Exception occured: "+ ex.getMessage());
        return error;
    }

}

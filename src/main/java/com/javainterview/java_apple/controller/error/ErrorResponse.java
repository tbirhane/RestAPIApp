package com.javainterview.java_apple.controller.error;

public class ErrorResponse {

    private String errorMessage;
    private String requestedURI;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestedURI() {
        return requestedURI;
    }

    public void setRequestedURI(final String requestedURI) {
        this.requestedURI = requestedURI;
    }
}

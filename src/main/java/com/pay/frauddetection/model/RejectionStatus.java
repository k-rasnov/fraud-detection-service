package com.pay.frauddetection.model;

public enum RejectionStatus {
    OK("OK", "No fraud detected"),
    FRAUD_DETECTED("FRAUD_DETECTED", "Possible fraud detected");

    private final String statusCode;
    private final String message;

    RejectionStatus(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}

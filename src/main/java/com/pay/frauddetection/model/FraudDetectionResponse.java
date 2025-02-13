package com.pay.frauddetection.model;

public record FraudDetectionResponse(
        String status,
        String message,
        int fraudScore) {}

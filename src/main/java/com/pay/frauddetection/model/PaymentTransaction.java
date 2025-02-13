package com.pay.frauddetection.model;

public record PaymentTransaction(
   float amount,
   CurrencyCode currencyCode,
   Terminal terminal,
   String cardNumber) {}

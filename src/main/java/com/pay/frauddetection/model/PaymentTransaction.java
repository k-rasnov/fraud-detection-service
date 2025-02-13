package com.pay.frauddetection.model;

import java.util.Date;

public record PaymentTransaction(
   float amount,
   Enum<CurrencyCode> currencyCode,
   Terminal terminal,
   String cardNumber) {}

package com.pay.frauddetection.rules;

import com.pay.frauddetection.model.Terminal;
import com.pay.frauddetection.model.PaymentTransaction;

public class TerminalAmountLimitRule implements TerminalRule {
    private final static int multiplier = 100;

    @Override
    public boolean evaluate(PaymentTransaction paymentTransaction, Terminal terminal) {
        return paymentTransaction.amount() <= terminal.threatScore() * multiplier;
    }
}

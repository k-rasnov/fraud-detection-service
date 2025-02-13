package com.pay.frauddetection.rules;

import com.pay.frauddetection.model.Terminal;
import com.pay.frauddetection.model.PaymentTransaction;

public interface TerminalRule {

    boolean evaluate(PaymentTransaction paymentTransaction, Terminal terminal);
}

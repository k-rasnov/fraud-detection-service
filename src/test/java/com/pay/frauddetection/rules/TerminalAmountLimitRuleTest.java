package com.pay.frauddetection.rules;

import com.pay.frauddetection.model.CurrencyCode;
import com.pay.frauddetection.model.Terminal;
import com.pay.frauddetection.model.PaymentTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TerminalAmountLimitRuleTest {

    private TerminalAmountLimitRule rule;

    @BeforeEach
    void setUp() {
        rule = new TerminalAmountLimitRule();
    }

    /**
     * If the amount is below the limit given the threat score for the terminal, the rule evaluation should pass.
     */
    @Test
    void testTransactionWithinLimit() {
        //given
        Terminal terminal = new Terminal(1, 5);
        PaymentTransaction paymentTransaction = new PaymentTransaction(400.0f, CurrencyCode.DKK, null, "123");

        //when
        boolean result = rule.evaluate(paymentTransaction, terminal);

        //then
        assertTrue(result, "Transaction is compliant with the rule");
    }

    /**
     * If the amount is at the allowed limit given the threat score for the terminal, the rule evaluation should pass.
     */
    @Test
    void testTransactionAtLimit() {
        //given
        Terminal terminal = new Terminal(1, 5);
        PaymentTransaction paymentTransaction = new PaymentTransaction(500.0f, CurrencyCode.DKK, null, "123");

        //when
        boolean result = rule.evaluate(paymentTransaction, terminal);

        //then
        assertTrue(result, "Transaction is compliant with the rule");
    }

    /**
     * If the amount is too large given the threat score for the terminal, the rule evaluation should fail.
     */
    @Test
    void testTransactionAboveLimit() {
        //given
        Terminal terminal = new Terminal(1, 5);
        PaymentTransaction paymentTransaction = new PaymentTransaction(500.1f, CurrencyCode.DKK, null, "123");

        //when
        boolean result = rule.evaluate(paymentTransaction, terminal);

        //then
        assertFalse(result, "Transaction not compliant with the rule");
    }

}

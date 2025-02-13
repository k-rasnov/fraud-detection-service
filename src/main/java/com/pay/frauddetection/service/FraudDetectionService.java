package com.pay.frauddetection.service;

import com.pay.frauddetection.model.*;
import com.pay.frauddetection.repository.TerminalRepository;
import com.pay.frauddetection.rules.TerminalAmountLimitRule;
import com.pay.frauddetection.rules.TerminalRule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FraudDetectionService {

    private final TerminalRepository terminalRepository;

    private final List<TerminalRule> terminalRulesGroup = List.of(new TerminalAmountLimitRule());

    public FraudDetectionService(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }

    public FraudDetectionResponse verify(PaymentTransaction paymentTransaction) {
        Optional<Terminal> terminal = terminalRepository.get(paymentTransaction.terminal().id());

        if(terminal.isEmpty()) {
            throw new IllegalArgumentException("Terminal with id " + paymentTransaction.terminal().id() + " not found");
        }

        int fraudScore = terminalRulesGroup.stream()
                .map(rule -> rule.evaluate(paymentTransaction, terminal.get()) ? 0 : 1)
                .reduce(0, Integer::sum);

        RejectionStatus status = fraudScore == 0 ? RejectionStatus.OK : RejectionStatus.FRAUD_DETECTED;

        return new FraudDetectionResponse(status.getStatusCode(), status.getMessage(), fraudScore);
    }
}

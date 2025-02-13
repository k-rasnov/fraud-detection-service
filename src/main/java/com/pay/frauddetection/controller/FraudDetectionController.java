package com.pay.frauddetection.controller;

import com.pay.frauddetection.model.FraudDetectionResponse;
import com.pay.frauddetection.model.PaymentTransaction;
import com.pay.frauddetection.service.FraudDetectionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/verify")
public class FraudDetectionController {

    private final FraudDetectionService fraudDetectionService;

    public FraudDetectionController(FraudDetectionService fraudDetectionService) {
        this.fraudDetectionService = fraudDetectionService;
    }


    @PostMapping("")
    public FraudDetectionResponse verify(@RequestBody PaymentTransaction paymentTransaction) {
        return fraudDetectionService.verify(paymentTransaction);
    }
}

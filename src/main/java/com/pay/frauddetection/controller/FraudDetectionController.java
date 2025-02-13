package com.pay.frauddetection.controller;

import com.pay.frauddetection.model.FraudDetectionResponse;
import com.pay.frauddetection.model.PaymentTransaction;
import com.pay.frauddetection.service.FraudDetectionService;
import io.micrometer.core.instrument.Counter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("api/verify")
public class FraudDetectionController {

    private final FraudDetectionService fraudDetectionService;
    private final Counter fraudDetectionCount;
    private final Counter errorCount;
    Logger logger = LoggerFactory.getLogger(FraudDetectionController.class);

    public FraudDetectionController(FraudDetectionService fraudDetectionService,
                                    MeterRegistry registry) {
        this.fraudDetectionService = fraudDetectionService;

        //fraud detection count
        this.fraudDetectionCount = Counter.builder("fraud_detection_count")
                .description("Number of times that fraud has been detected")
                .register(registry);

        //error count
        this.errorCount = Counter.builder("error_count")
                .description("Number of errors")
                .register(registry);
    }


    @PostMapping("")
    public FraudDetectionResponse verify(@RequestBody PaymentTransaction paymentTransaction) {
        FraudDetectionResponse response = fraudDetectionService.verify(paymentTransaction);

        if(response.fraudScore() > 0) {
            this.fraudDetectionCount.increment();
        }

        return response;
    }

    //general exception handler to ensure that exceptions are logged and that metrics are updated.
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(Exception e, WebRequest request) {
        logger.error(String.join("Exception occured while processing request ",
                request.getContextPath(),": "+e.getMessage()));

        this.errorCount.increment();

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

package com.example.payment.client;

import com.example.payment.PaymentService;
import com.example.payment.model.PaymentRequest;
import com.example.payment.model.PaymentResult;

public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentResult handleCheckout(PaymentRequest request) {
        if (!paymentService.validatePayment(request)) {
            return new PaymentResult(request.getTransactionId(), false, "Validation failed");
        }
        return paymentService.processPayment(request);
    }
}

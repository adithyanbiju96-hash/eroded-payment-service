package com.example.payment.client;

import com.example.payment.PaymentService;
import com.example.payment.model.PaymentResult;
import com.example.payment.model.RefundRequest;

public class RefundController {
    private final PaymentService paymentService;

    public RefundController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentResult requestRefund(RefundRequest request) {
        paymentService.cancelPayment(request.getTransactionId());
        return paymentService.refundPayment(request);
    }
}

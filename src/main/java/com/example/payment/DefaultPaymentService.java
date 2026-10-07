package com.example.payment;

import com.example.payment.model.PaymentRequest;
import com.example.payment.model.PaymentResult;
import com.example.payment.model.RefundRequest;
import java.util.HashMap;
import java.util.Map;

public class DefaultPaymentService implements PaymentService {
    private final Map<String, PaymentResult> transactions = new HashMap<>();

    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        PaymentResult result = new PaymentResult(request.getTransactionId(), true, "Payment processed successfully");
        transactions.put(request.getTransactionId(), result);
        return result;
    }

    @Override
    public PaymentResult refundPayment(RefundRequest request) {
        PaymentResult result = new PaymentResult(request.getTransactionId(), true, "Refund issued: " + request.getReason());
        transactions.put(request.getTransactionId(), result);
        return result;
    }
}

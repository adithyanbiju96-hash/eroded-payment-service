package com.example.payment;

import com.example.payment.model.PaymentRequest;
import com.example.payment.model.PaymentResult;
import com.example.payment.model.RefundRequest;

public interface PaymentService {
    PaymentResult processPayment(PaymentRequest request);
    PaymentResult refundPayment(RefundRequest request);
}

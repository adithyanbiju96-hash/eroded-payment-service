package com.example.payment.client;

import com.example.payment.PaymentService;

public class NotificationService {
    private final PaymentService paymentService;

    public NotificationService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void notifyCustomer(String email, String message) {
        paymentService.sendPaymentNotification(email, message);
    }
}

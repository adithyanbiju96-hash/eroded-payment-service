package com.example.payment;

import com.example.payment.model.FraudCheckResult;
import com.example.payment.model.PaymentReport;
import com.example.payment.model.PaymentRequest;
import com.example.payment.model.PaymentResult;
import com.example.payment.model.Receipt;
import com.example.payment.model.RefundRequest;

public interface PaymentService {
    PaymentResult processPayment(PaymentRequest request);
    PaymentResult refundPayment(RefundRequest request);
    boolean validatePayment(PaymentRequest request);
    String getPaymentStatus(String transactionId);
    boolean cancelPayment(String transactionId);
    Receipt generateReceipt(String transactionId);
    void sendPaymentNotification(String customerEmail, String message);
    PaymentReport exportPaymentReport(String startDate, String endDate);
    double calculateFees(PaymentRequest request);
    FraudCheckResult handleFraudCheck(PaymentRequest request);
}

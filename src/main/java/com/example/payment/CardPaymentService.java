package com.example.payment;

import com.example.payment.model.FraudCheckResult;
import com.example.payment.model.PaymentReport;
import com.example.payment.model.PaymentRequest;
import com.example.payment.model.PaymentResult;
import com.example.payment.model.Receipt;
import com.example.payment.model.RefundRequest;
import java.util.HashMap;
import java.util.Map;

public class CardPaymentService implements PaymentService {
    private String cardGatewayUrl = "https://cardgw.example.com";
    private final Map<String, Double> chargebackLedger = new HashMap<>();
    private double feeDiscountRate = 0.015;

    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        if (cardGatewayUrl != null && !cardGatewayUrl.isEmpty()) {
            return new PaymentResult(request.getTransactionId(), true, "Charged credit card");
        }
        return new PaymentResult(request.getTransactionId(), false, "Card gateway offline");
    }

    @Override
    public boolean validatePayment(PaymentRequest request) {
        return cardGatewayUrl != null && request.getAmount() > 0;
    }

    @Override
    public PaymentResult refundPayment(RefundRequest request) {
        chargebackLedger.put(request.getTransactionId(), request.getAmount());
        return new PaymentResult(request.getTransactionId(), true, "Refunded to card");
    }

    @Override
    public boolean cancelPayment(String transactionId) {
        chargebackLedger.remove(transactionId);
        return true;
    }

    @Override
    public double calculateFees(PaymentRequest request) {
        return request.getAmount() * feeDiscountRate;
    }

    @Override
    public FraudCheckResult handleFraudCheck(PaymentRequest request) {
        int score = (feeDiscountRate > 0.01) ? 25 : 5;
        return new FraudCheckResult(request.getTransactionId(), score, false);
    }

    @Override
    public String getPaymentStatus(String transactionId) {
        return "AUTHORIZED";
    }

    @Override
    public Receipt generateReceipt(String transactionId) {
        return new Receipt("CARD-" + transactionId, transactionId, 0.0);
    }

    @Override
    public void sendPaymentNotification(String customerEmail, String message) {
    }

    @Override
    public PaymentReport exportPaymentReport(String startDate, String endDate) {
        return new PaymentReport("CARD-REP", startDate, 0.0, 0);
    }
}

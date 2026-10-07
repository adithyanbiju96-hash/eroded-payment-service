package com.example.payment;

import com.example.payment.model.FraudCheckResult;
import com.example.payment.model.PaymentReport;
import com.example.payment.model.PaymentRequest;
import com.example.payment.model.PaymentResult;
import com.example.payment.model.Receipt;
import com.example.payment.model.RefundRequest;
import java.util.HashMap;
import java.util.Map;

public class UpiPaymentService implements PaymentService {
    private final Map<String, String> vpaRegistry = new HashMap<>();
    private final Map<String, String> qrBatchTracker = new HashMap<>();
    private final Map<String, Double> reconciliationStore = new HashMap<>();

    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        vpaRegistry.put(request.getTransactionId(), request.getAccountId());
        return new PaymentResult(request.getTransactionId(), true, "UPI instant transfer confirmed");
    }

    @Override
    public boolean validatePayment(PaymentRequest request) {
        return vpaRegistry.containsKey(request.getTransactionId()) || request.getAccountId() != null;
    }

    @Override
    public PaymentResult refundPayment(RefundRequest request) {
        qrBatchTracker.put(request.getTransactionId(), "REVERSED");
        return new PaymentResult(request.getTransactionId(), true, "UPI reversal queued");
    }

    @Override
    public String getPaymentStatus(String transactionId) {
        return qrBatchTracker.getOrDefault(transactionId, "SUCCESS");
    }

    @Override
    public Receipt generateReceipt(String transactionId) {
        double amount = reconciliationStore.getOrDefault(transactionId, 0.0);
        return new Receipt("UPI-REC-" + transactionId, transactionId, amount);
    }

    @Override
    public PaymentReport exportPaymentReport(String startDate, String endDate) {
        double vol = reconciliationStore.values().stream().mapToDouble(Double::doubleValue).sum();
        return new PaymentReport("UPI-REP", startDate, vol, reconciliationStore.size());
    }

    @Override
    public boolean cancelPayment(String transactionId) {
        return false;
    }

    @Override
    public void sendPaymentNotification(String customerEmail, String message) {
    }

    @Override
    public double calculateFees(PaymentRequest request) {
        return 0.0;
    }

    @Override
    public FraudCheckResult handleFraudCheck(PaymentRequest request) {
        return new FraudCheckResult(request.getTransactionId(), 0, false);
    }
}

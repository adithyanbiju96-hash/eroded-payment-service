package com.example.payment;

import com.example.payment.model.FraudCheckResult;
import com.example.payment.model.PaymentReport;
import com.example.payment.model.PaymentRequest;
import com.example.payment.model.PaymentResult;
import com.example.payment.model.Receipt;
import com.example.payment.model.RefundRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultPaymentService implements PaymentService {
    private final Map<String, PaymentResult> transactions = new HashMap<>();
    private final Map<String, String> statusMap = new HashMap<>();
    private final List<String> notificationLog = new ArrayList<>();

    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        PaymentResult result = new PaymentResult(request.getTransactionId(), true, "Payment processed successfully");
        transactions.put(request.getTransactionId(), result);
        statusMap.put(request.getTransactionId(), "COMPLETED");
        return result;
    }

    @Override
    public PaymentResult refundPayment(RefundRequest request) {
        PaymentResult result = new PaymentResult(request.getTransactionId(), true, "Refund issued: " + request.getReason());
        transactions.put(request.getTransactionId(), result);
        statusMap.put(request.getTransactionId(), "REFUNDED");
        return result;
    }

    @Override
    public boolean validatePayment(PaymentRequest request) {
        return request != null && request.getAmount() > 0 && request.getAccountId() != null;
    }

    @Override
    public String getPaymentStatus(String transactionId) {
        return statusMap.getOrDefault(transactionId, "UNKNOWN");
    }

    @Override
    public boolean cancelPayment(String transactionId) {
        if (transactions.containsKey(transactionId)) {
            statusMap.put(transactionId, "CANCELLED");
            return true;
        }
        return false;
    }

    @Override
    public Receipt generateReceipt(String transactionId) {
        PaymentResult res = transactions.get(transactionId);
        double amount = (res != null) ? 100.0 : 0.0;
        return new Receipt("REC-" + transactionId, transactionId, amount);
    }

    @Override
    public void sendPaymentNotification(String customerEmail, String message) {
        notificationLog.add("To: " + customerEmail + " | " + message);
    }

    @Override
    public PaymentReport exportPaymentReport(String startDate, String endDate) {
        return new PaymentReport("RPT-ALL", startDate + " to " + endDate, 5000.0, transactions.size());
    }

    @Override
    public double calculateFees(PaymentRequest request) {
        return request.getAmount() * 0.025;
    }

    @Override
    public FraudCheckResult handleFraudCheck(PaymentRequest request) {
        boolean flagged = request.getAmount() > 10000.0;
        return new FraudCheckResult(request.getTransactionId(), flagged ? 85 : 10, flagged);
    }
}

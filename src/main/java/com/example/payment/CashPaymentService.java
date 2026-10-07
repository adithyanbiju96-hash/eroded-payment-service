package com.example.payment;

import com.example.payment.model.FraudCheckResult;
import com.example.payment.model.PaymentReport;
import com.example.payment.model.PaymentRequest;
import com.example.payment.model.PaymentResult;
import com.example.payment.model.Receipt;
import com.example.payment.model.RefundRequest;
import java.util.ArrayList;
import java.util.List;

public class CashPaymentService implements PaymentService {
    private double registerDrawerBalance = 500.0;
    private int physicalReceiptRollCount = 100;
    private final List<String> cashierLog = new ArrayList<>();

    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        registerDrawerBalance += request.getAmount();
        return new PaymentResult(request.getTransactionId(), true, "Cash received in drawer");
    }

    @Override
    public PaymentResult refundPayment(RefundRequest request) {
        registerDrawerBalance -= request.getAmount();
        return new PaymentResult(request.getTransactionId(), true, "Cash refunded from drawer");
    }

    @Override
    public Receipt generateReceipt(String transactionId) {
        physicalReceiptRollCount--;
        return new Receipt("CASH-TICKET-" + physicalReceiptRollCount, transactionId, 0.0);
    }

    @Override
    public void sendPaymentNotification(String customerEmail, String message) {
        cashierLog.add("Notice handed to customer: " + message);
    }

    @Override
    public boolean validatePayment(PaymentRequest request) {
        return request.getAmount() <= registerDrawerBalance;
    }

    @Override
    public String getPaymentStatus(String transactionId) {
        return "CASH_SETTLED";
    }

    @Override
    public boolean cancelPayment(String transactionId) {
        return false;
    }

    @Override
    public PaymentReport exportPaymentReport(String startDate, String endDate) {
        return new PaymentReport("CASH-DAILY", startDate, registerDrawerBalance, cashierLog.size());
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

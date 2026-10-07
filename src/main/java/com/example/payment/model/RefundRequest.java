package com.example.payment.model;

public class RefundRequest {
    private String transactionId;
    private double amount;
    private String reason;

    public RefundRequest(String transactionId, double amount, String reason) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.reason = reason;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }
}

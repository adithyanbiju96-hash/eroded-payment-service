package com.example.payment.model;

public class PaymentResult {
    private String transactionId;
    private boolean successful;
    private String message;

    public PaymentResult(String transactionId, boolean successful, String message) {
        this.transactionId = transactionId;
        this.successful = successful;
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }
}

package com.example.payment.model;

public class PaymentRequest {
    private String transactionId;
    private String accountId;
    private double amount;
    private String currency;

    public PaymentRequest(String transactionId, String accountId, double amount, String currency) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.currency = currency;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}

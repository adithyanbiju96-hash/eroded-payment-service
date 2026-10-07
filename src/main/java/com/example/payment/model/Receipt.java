package com.example.payment.model;

public class Receipt {
    private String receiptNumber;
    private String transactionId;
    private double amountPaid;

    public Receipt(String receiptNumber, String transactionId, double amountPaid) {
        this.receiptNumber = receiptNumber;
        this.transactionId = transactionId;
        this.amountPaid = amountPaid;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}

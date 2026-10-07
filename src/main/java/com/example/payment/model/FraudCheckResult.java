package com.example.payment.model;

public class FraudCheckResult {
    private String transactionId;
    private int riskScore;
    private boolean flagged;

    public FraudCheckResult(String transactionId, int riskScore, boolean flagged) {
        this.transactionId = transactionId;
        this.riskScore = riskScore;
        this.flagged = flagged;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public int getRiskScore() {
        return riskScore;
    }

    public boolean isFlagged() {
        return flagged;
    }
}

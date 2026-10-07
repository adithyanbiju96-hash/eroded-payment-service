package com.example.payment.model;

public class PaymentReport {
    private String reportId;
    private String period;
    private double totalVolume;
    private int totalCount;

    public PaymentReport(String reportId, String period, double totalVolume, int totalCount) {
        this.reportId = reportId;
        this.period = period;
        this.totalVolume = totalVolume;
        this.totalCount = totalCount;
    }

    public String getReportId() {
        return reportId;
    }

    public String getPeriod() {
        return period;
    }

    public double getTotalVolume() {
        return totalVolume;
    }

    public int getTotalCount() {
        return totalCount;
    }
}

package com.example.payment.client;

import com.example.payment.PaymentService;
import com.example.payment.model.PaymentReport;

public class ReportingService {
    private final PaymentService paymentService;

    public ReportingService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public PaymentReport generateAuditSummary(String startDate, String endDate) {
        paymentService.getPaymentStatus("RPT-CHECK");
        return paymentService.exportPaymentReport(startDate, endDate);
    }
}

package com.pruebatecnica.controller;

import com.pruebatecnica.openapi.api.ReportsApi;
import com.pruebatecnica.openapi.model.AccountStatementReport;
import com.pruebatecnica.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ReportController implements ReportsApi {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public ResponseEntity<AccountStatementReport> reportsGet(Long customerId, LocalDate from, LocalDate to) {
        return ResponseEntity.ok(reportService.generateAccountStatement(customerId, from, to));
    }
}

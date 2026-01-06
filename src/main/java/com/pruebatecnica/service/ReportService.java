package com.pruebatecnica.service;

import com.pruebatecnica.openapi.model.AccountStatementReport;

import java.time.LocalDate;

public interface ReportService {
    AccountStatementReport generateAccountStatement(Long customerId, LocalDate from, LocalDate to);
}

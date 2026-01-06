package com.pruebatecnica.service.impl;

import com.pruebatecnica.exception.NotFoundException;
import com.pruebatecnica.model.Customer;
import com.pruebatecnica.openapi.model.AccountReport;
import com.pruebatecnica.openapi.model.AccountStatementReport;
import com.pruebatecnica.openapi.model.TransactionReport;
import com.pruebatecnica.report.PdfReporteGenerator;
import com.pruebatecnica.service.CustomerService;
import com.pruebatecnica.model.Account;
import com.pruebatecnica.service.AccountService;
import com.pruebatecnica.model.Transaction;
import com.pruebatecnica.service.TransactionService;
import com.pruebatecnica.model.TransactionType;
import com.pruebatecnica.service.ReportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    private final CustomerService customerService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    public ReportServiceImpl(CustomerService customerService, AccountService accountService, TransactionService transactionService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Override
    public AccountStatementReport generateAccountStatement(Long customerId, LocalDate from, LocalDate to) {
        if (customerId == null) {
            throw new NotFoundException("Customer not found");
        }

        Customer customer = customerService.obtenerPorId(customerId);

        LocalDateTime desdeDt = LocalDateTime.of(from, LocalTime.MIN);
        LocalDateTime hastaDt = LocalDateTime.of(to, LocalTime.MAX);

        List<Account> accounts = accountService.listByCustomerId(customerId);
        List<AccountReport> accountsReport = new ArrayList<>();

        BigDecimal totalCreditos = BigDecimal.ZERO;
        BigDecimal totalDebitos = BigDecimal.ZERO;

        for (Account account : accounts) {
            List<Transaction> transactions = transactionService.listByAccountAndDateRange(account.getId(), desdeDt, hastaDt);

            AccountReport cr = new AccountReport();
            cr.setAccountId(account.getId());
            cr.setAccountNumber(account.getAccountNumber());
            cr.setAccountType(account.getAccountType().name());
            cr.setInitialBalance(account.getInitialBalance() == null ? null : account.getInitialBalance().doubleValue());

            List<TransactionReport> transactionsReport = new ArrayList<>();
            BigDecimal saldoDisponible = account.getInitialBalance();

            for (Transaction m : transactions) {
                TransactionReport mr = new TransactionReport();
                mr.setDate(m.getDate() == null ? null : m.getDate().toString());
                mr.setTransactionType(m.getTransactionType().name());
                mr.setAmount(m.getAmount() == null ? null : m.getAmount().doubleValue());
                mr.setBalance(m.getBalance() == null ? null : m.getBalance().doubleValue());
                transactionsReport.add(mr);

                saldoDisponible = m.getBalance();

                if (m.getTransactionType() == TransactionType.CREDITO) {
                    totalCreditos = totalCreditos.add(m.getAmount().abs());
                } else {
                    totalDebitos = totalDebitos.add(m.getAmount().abs());
                }
            }

            cr.setAvailableBalance(saldoDisponible == null ? null : saldoDisponible.doubleValue());
            cr.setTransactions(transactionsReport);
            accountsReport.add(cr);
        }

        AccountStatementReport reporte = new AccountStatementReport();
        reporte.setCustomerId(customerId);
        reporte.setCustomerName(customer.getName());
        reporte.setFrom(from.toString());
        reporte.setTo(to.toString());
        reporte.setAccounts(accountsReport);
        reporte.setTotalCredits(totalCreditos.doubleValue());
        reporte.setTotalDebits(totalDebitos.doubleValue());

        PdfReporteGenerator generator = new PdfReporteGenerator();
        byte[] pdfBytes = generator.generar(reporte);
        reporte.setPdfBase64(Base64.getEncoder().encodeToString(pdfBytes));

        return reporte;
    }
}

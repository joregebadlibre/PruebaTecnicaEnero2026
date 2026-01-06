package com.pruebatecnica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.openapi.model.AccountRequest;
import com.pruebatecnica.openapi.model.AccountType;
import com.pruebatecnica.openapi.model.CustomerRequest;
import com.pruebatecnica.openapi.model.TransactionRequest;
import com.pruebatecnica.openapi.model.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ReportControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @Test
    void shouldGenerateReportWithPdfBase64() throws Exception {
        Long customerId = createCustomer();
        Long accountId = createAccount(customerId, BigDecimal.valueOf(1000));
        createTransaction(accountId, TransactionType.CREDITO, 100.0);
        createTransaction(accountId, TransactionType.DEBITO, 50.0);

        mockMvc.perform(get("/reports")
                        .queryParam("customerId", String.valueOf(customerId))
                        .queryParam("from", "2026-01-01")
                        .queryParam("to", "2026-01-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(customerId))
                .andExpect(jsonPath("$.accounts").isArray())
                .andExpect(jsonPath("$.pdfBase64").isNotEmpty());
    }

    private Long createCustomer() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setName("Report Customer");
        request.setGender("F");
        request.setAge(22);
        request.setIdentification(String.valueOf(ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L)));
        request.setAddress("Street");
        request.setPhone("0991111111");
        request.setPassword("pass");
        request.setActive(true);

        String createdJson = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readTree(createdJson).get("id").asLong();
    }

    private Long createAccount(Long customerId, BigDecimal initialBalance) throws Exception {
        AccountRequest request = new AccountRequest();
        request.setAccountNumber(ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L));
        request.setAccountType(AccountType.AHORRO);
        request.setInitialBalance(initialBalance.doubleValue());
        request.setActive(true);
        request.setCustomerId(customerId);

        String createdJson = mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readTree(createdJson).get("id").asLong();
    }

    private Long createTransaction(Long accountId, TransactionType type, double amount) throws Exception {
        TransactionRequest request = new TransactionRequest();
        request.setAccountId(accountId);
        request.setTransactionType(type);
        request.setAmount(amount);

        String createdJson = mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readTree(createdJson).get("id").asLong();
    }
}

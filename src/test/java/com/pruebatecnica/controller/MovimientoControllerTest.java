package com.pruebatecnica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.openapi.model.CustomerRequest;
import com.pruebatecnica.openapi.model.AccountRequest;
import com.pruebatecnica.openapi.model.TransactionRequest;
import com.pruebatecnica.openapi.model.AccountType;
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
class TransactionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldRejectDebitWhenInsufficientBalance() throws Exception {
        Long customerId = createCustomer();
        Long accountId = createAccount(customerId, BigDecimal.ZERO);

        TransactionRequest request = new TransactionRequest();
        request.setAccountId(accountId);
        request.setTransactionType(TransactionType.DEBITO);
        request.setAmount(10.0);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldCreateCreditThenDebitAndUpdateBalances() throws Exception {
        Long customerId = createCustomer();
        Long accountId = createAccount(customerId, BigDecimal.valueOf(1000));

        TransactionRequest credit = new TransactionRequest();
        credit.setAccountId(accountId);
        credit.setTransactionType(TransactionType.CREDITO);
        credit.setAmount(200.0);

        String creditJson = mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credit)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.balance").value(1200.0))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long creditId = objectMapper.readTree(creditJson).get("id").asLong();

        TransactionRequest debit = new TransactionRequest();
        debit.setAccountId(accountId);
        debit.setTransactionType(TransactionType.DEBITO);
        debit.setAmount(50.0);

        String debitJson = mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debit)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.balance").value(1150.0))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long debitId = objectMapper.readTree(debitJson).get("id").asLong();

        mockMvc.perform(get("/transactions/{id}", creditId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(1200.0));

        mockMvc.perform(get("/transactions/{id}", debitId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(1150.0));
    }

    @Test
    void shouldRejectUpdateWhenNotLastTransactionOfAccount() throws Exception {
        Long customerId = createCustomer();
        Long accountId = createAccount(customerId, BigDecimal.valueOf(1000));

        TransactionRequest credit = new TransactionRequest();
        credit.setAccountId(accountId);
        credit.setTransactionType(TransactionType.CREDITO);
        credit.setAmount(100.0);

        String firstJson = mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(credit)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        Long firstId = objectMapper.readTree(firstJson).get("id").asLong();

        TransactionRequest debit = new TransactionRequest();
        debit.setAccountId(accountId);
        debit.setTransactionType(TransactionType.DEBITO);
        debit.setAmount(10.0);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(debit)))
                .andExpect(status().isCreated());

        TransactionRequest updateFirst = new TransactionRequest();
        updateFirst.setAccountId(accountId);
        updateFirst.setTransactionType(TransactionType.CREDITO);
        updateFirst.setAmount(999.0);

        mockMvc.perform(put("/transactions/{id}", firstId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateFirst)))
                .andExpect(status().isBadRequest());
    }

    private Long createCustomer() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setName("Ana");
        request.setGender("F");
        request.setAge(25);
        request.setIdentification(String.valueOf(ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L)));
        request.setAddress("Street 2");
        request.setPhone("0888888888");
        request.setPassword("pass");
        request.setActive(true);

        String createdJson = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
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
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readTree(createdJson).get("id").asLong();
    }
}

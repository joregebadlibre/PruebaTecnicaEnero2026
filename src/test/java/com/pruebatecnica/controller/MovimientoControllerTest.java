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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private Long createCustomer() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setName("Ana");
        request.setGender("F");
        request.setAge(25);
        request.setIdentification("9876543210");
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
        request.setAccountNumber(1002003004L);
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

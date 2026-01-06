package com.pruebatecnica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.openapi.model.AccountRequest;
import com.pruebatecnica.openapi.model.AccountType;
import com.pruebatecnica.openapi.model.CustomerRequest;
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
class AccountControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @Test
    void shouldCreateGetUpdateDeleteAccount() throws Exception {
        Long customerId = createCustomer();
        Long accountId = createAccount(customerId, BigDecimal.valueOf(1000));

        mockMvc.perform(get("/accounts/{id}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountId))
                .andExpect(jsonPath("$.customerId").value(customerId));

        AccountRequest update = new AccountRequest();
        update.setAccountNumber(ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L));
        update.setAccountType(AccountType.CORRIENTE);
        update.setInitialBalance(2000.0);
        update.setActive(true);
        update.setCustomerId(customerId);

        mockMvc.perform(put("/accounts/{id}", accountId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountType").value("CORRIENTE"))
                .andExpect(jsonPath("$.initialBalance").value(2000.0));

        mockMvc.perform(delete("/accounts/{id}", accountId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/accounts/{id}", accountId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldListAccountsByCustomerId() throws Exception {
        Long customerId = createCustomer();
        createAccount(customerId, BigDecimal.valueOf(100));

        mockMvc.perform(get("/accounts").queryParam("customerId", String.valueOf(customerId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerId").value(customerId));
    }

    private Long createCustomer() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setName("Test Customer");
        request.setGender("M");
        request.setAge(20);
        request.setIdentification(String.valueOf(ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L)));
        request.setAddress("Street");
        request.setPhone("0990000000");
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
}

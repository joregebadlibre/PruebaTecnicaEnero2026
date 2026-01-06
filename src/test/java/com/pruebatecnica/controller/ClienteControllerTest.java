package com.pruebatecnica.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pruebatecnica.openapi.model.CustomerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldCreateAndGetCustomer() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setName("Juan");
        request.setGender("M");
        request.setAge(30);
        request.setIdentification(String.valueOf(ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L)));
        request.setAddress("Street 1");
        request.setPhone("0999999999");
        request.setPassword("pass");
        request.setActive(true);

        String createdJson = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = objectMapper.readTree(createdJson).get("id").asLong();

        mockMvc.perform(get("/customers/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Juan"));
    }

    @Test
    void shouldListCustomers() throws Exception {
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldPatchCustomerStatusAndDeleteCustomer() throws Exception {
        CustomerRequest request = new CustomerRequest();
        request.setName("Maria");
        request.setGender("F");
        request.setAge(28);
        request.setIdentification(String.valueOf(ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L)));
        request.setAddress("Street 10");
        request.setPhone("0777777777");
        request.setPassword("pass");
        request.setActive(true);

        String createdJson = mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = objectMapper.readTree(createdJson).get("id").asLong();

        mockMvc.perform(patch("/customers/{id}/status", id)
                        .queryParam("active", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(false));

        mockMvc.perform(delete("/customers/{id}", id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/customers/{id}", id))
                .andExpect(status().isNotFound());
    }
}

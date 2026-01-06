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
        request.setIdentification("1234567890");
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
}

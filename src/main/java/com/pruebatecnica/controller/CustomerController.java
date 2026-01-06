package com.pruebatecnica.controller;

import com.pruebatecnica.mapper.CustomerMapper;
import com.pruebatecnica.model.Customer;
import com.pruebatecnica.openapi.api.CustomersApi;
import com.pruebatecnica.openapi.model.CustomerRequest;
import com.pruebatecnica.openapi.model.CustomerResponse;
import com.pruebatecnica.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController implements CustomersApi {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Override
    public ResponseEntity<List<CustomerResponse>> customersGet() {
        List<CustomerResponse> response = customerService.listar().stream()
                .map(customerMapper::toOpenApi)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CustomerResponse> customersPost(@Valid CustomerRequest customerRequest) {
        Customer customer = customerMapper.toEntity(customerRequest);
        Customer created = customerService.crear(customer);
        return ResponseEntity.status(201).body(customerMapper.toOpenApi(created));
    }

    @Override
    public ResponseEntity<CustomerResponse> customersIdGet(Long id) {
        return ResponseEntity.ok(customerMapper.toOpenApi(customerService.obtenerPorId(id)));
    }

    @Override
    public ResponseEntity<CustomerResponse> customersIdPut(Long id, @Valid CustomerRequest customerRequest) {
        Customer customer = customerMapper.toEntity(customerRequest);
        return ResponseEntity.ok(customerMapper.toOpenApi(customerService.actualizar(id, customer)));
    }

    @Override
    public ResponseEntity<Void> customersIdDelete(Long id) {
        customerService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CustomerResponse> customersIdStatusPatch(Long id, Boolean active) {
        Customer existing = customerService.obtenerPorId(id);
        existing.setActive(active);
        return ResponseEntity.ok(customerMapper.toOpenApi(customerService.actualizar(id, existing)));
    }
}

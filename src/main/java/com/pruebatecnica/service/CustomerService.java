package com.pruebatecnica.service;

import com.pruebatecnica.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer crear(Customer customer);

    Customer obtenerPorId(Long id);

    List<Customer> listar();

    Customer actualizar(Long id, Customer customer);

    void eliminar(Long id);
}

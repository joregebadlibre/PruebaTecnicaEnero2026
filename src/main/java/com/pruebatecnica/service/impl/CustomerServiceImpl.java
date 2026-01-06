package com.pruebatecnica.service.impl;

import com.pruebatecnica.exception.NotFoundException;
import com.pruebatecnica.model.Customer;
import com.pruebatecnica.repository.CustomerRepository;
import com.pruebatecnica.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    public Customer crear(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer obtenerPorId(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> listar() {
        return customerRepository.findAll();
    }

    @Override
    public Customer actualizar(Long id, Customer customer) {
        Customer existing = obtenerPorId(id);
        existing.setName(customer.getName());
        existing.setGender(customer.getGender());
        existing.setAge(customer.getAge());
        existing.setIdentification(customer.getIdentification());
        existing.setAddress(customer.getAddress());
        existing.setPhone(customer.getPhone());
        existing.setPassword(customer.getPassword());
        existing.setActive(customer.getActive());
        return customerRepository.save(existing);
    }

    @Override
    public void eliminar(Long id) {
        Customer existing = obtenerPorId(id);
        customerRepository.delete(existing);
    }
}

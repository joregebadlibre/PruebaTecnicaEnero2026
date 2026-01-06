package com.pruebatecnica.mapper;

import com.pruebatecnica.model.Customer;
import com.pruebatecnica.openapi.model.CustomerRequest;
import com.pruebatecnica.openapi.model.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest request) {
        Customer c = new Customer();
        c.setName(request.getName());
        c.setGender(request.getGender());
        c.setAge(request.getAge());
        c.setIdentification(request.getIdentification());
        c.setAddress(request.getAddress());
        c.setPhone(request.getPhone());
        c.setPassword(request.getPassword());
        c.setActive(request.getActive());
        return c;
    }

    public CustomerResponse toOpenApi(Customer customer) {
        CustomerResponse r = new CustomerResponse();
        r.setId(customer.getId());
        r.setName(customer.getName());
        r.setGender(customer.getGender());
        r.setAge(customer.getAge());
        r.setIdentification(customer.getIdentification());
        r.setAddress(customer.getAddress());
        r.setPhone(customer.getPhone());
        r.setPassword(customer.getPassword());
        r.setActive(customer.getActive());
        return r;
    }
}

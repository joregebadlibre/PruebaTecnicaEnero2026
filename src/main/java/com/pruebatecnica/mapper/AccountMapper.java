package com.pruebatecnica.mapper;

import com.pruebatecnica.model.Customer;
import com.pruebatecnica.model.Account;
import com.pruebatecnica.openapi.model.AccountRequest;
import com.pruebatecnica.openapi.model.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toEntity(AccountRequest request) {
        Account c = new Account();
        c.setAccountNumber(request.getAccountNumber());
        c.setAccountType(com.pruebatecnica.model.AccountType.valueOf(request.getAccountType().name()));
        c.setInitialBalance(java.math.BigDecimal.valueOf(request.getInitialBalance()));
        c.setActive(request.getActive());

        Customer customer = new Customer();
        customer.setCustomerId(request.getCustomerId());
        c.setCustomer(customer);

        return c;
    }

    public AccountResponse toOpenApi(Account account) {
        AccountResponse r = new AccountResponse();
        r.setId(account.getId());
        r.setAccountNumber(account.getAccountNumber());
        r.setAccountType(com.pruebatecnica.openapi.model.AccountType.valueOf(account.getAccountType().name()));
        r.setInitialBalance(account.getInitialBalance() == null ? null : account.getInitialBalance().doubleValue());
        r.setActive(account.getActive());
        r.setCustomerId(account.getCustomer() == null ? null : account.getCustomer().getCustomerId());
        return r;
    }
}

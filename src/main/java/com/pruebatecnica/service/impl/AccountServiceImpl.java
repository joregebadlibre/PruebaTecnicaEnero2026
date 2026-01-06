package com.pruebatecnica.service.impl;

import com.pruebatecnica.exception.NotFoundException;
import com.pruebatecnica.model.Customer;
import com.pruebatecnica.model.Account;
import com.pruebatecnica.repository.AccountRepository;
import com.pruebatecnica.service.CustomerService;
import com.pruebatecnica.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
    }

    @Override
    public Account crear(Account account) {
        if (account.getCustomer() != null && account.getCustomer().getCustomerId() != null) {
            Customer customer = customerService.obtenerPorId(account.getCustomer().getCustomerId());
            account.setCustomer(customer);
        }
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account obtenerPorId(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Account not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> listar() {
        return accountRepository.findAll();
    }

    @Override
    public Account actualizar(Long id, Account account) {
        Account existing = obtenerPorId(id);
        existing.setAccountNumber(account.getAccountNumber());
        existing.setAccountType(account.getAccountType());
        existing.setInitialBalance(account.getInitialBalance());
        existing.setActive(account.getActive());
        if (account.getCustomer() != null && account.getCustomer().getCustomerId() != null) {
            Customer customer = customerService.obtenerPorId(account.getCustomer().getCustomerId());
            existing.setCustomer(customer);
        }
        return accountRepository.save(existing);
    }

    @Override
    public void eliminar(Long id) {
        Account existing = obtenerPorId(id);
        accountRepository.delete(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> listByCustomerId(Long customerId) {
        return accountRepository.findByCustomer_Id(customerId);
    }
}

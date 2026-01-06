package com.pruebatecnica.service;

import com.pruebatecnica.model.Account;

import java.util.List;

public interface AccountService {
    Account crear(Account account);

    Account obtenerPorId(Long id);

    List<Account> listar();

    Account actualizar(Long id, Account account);

    void eliminar(Long id);

    List<Account> listByCustomerId(Long customerId);
}

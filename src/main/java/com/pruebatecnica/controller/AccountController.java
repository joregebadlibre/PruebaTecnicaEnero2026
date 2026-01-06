package com.pruebatecnica.controller;

import com.pruebatecnica.mapper.AccountMapper;
import com.pruebatecnica.model.Account;
import com.pruebatecnica.openapi.api.AccountsApi;
import com.pruebatecnica.openapi.model.AccountRequest;
import com.pruebatecnica.openapi.model.AccountResponse;
import com.pruebatecnica.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController implements AccountsApi {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @Override
    public ResponseEntity<List<AccountResponse>> accountsGet(Long customerId) {
        List<Account> cuentas = (customerId != null)
                ? accountService.listByCustomerId(customerId)
                : accountService.listar();

        List<AccountResponse> response = cuentas.stream()
                .map(accountMapper::toOpenApi)
                .toList();

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<AccountResponse> accountsPost(@Valid AccountRequest accountRequest) {
        Account account = accountMapper.toEntity(accountRequest);
        Account created = accountService.crear(account);
        return ResponseEntity.status(201).body(accountMapper.toOpenApi(created));
    }

    @Override
    public ResponseEntity<AccountResponse> accountsIdGet(Long id) {
        return ResponseEntity.ok(accountMapper.toOpenApi(accountService.obtenerPorId(id)));
    }

    @Override
    public ResponseEntity<AccountResponse> accountsIdPut(Long id, @Valid AccountRequest accountRequest) {
        Account account = accountMapper.toEntity(accountRequest);
        return ResponseEntity.ok(accountMapper.toOpenApi(accountService.actualizar(id, account)));
    }

    @Override
    public ResponseEntity<Void> accountsIdDelete(Long id) {
        accountService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

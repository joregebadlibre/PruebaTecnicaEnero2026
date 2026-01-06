package com.pruebatecnica.service;

import com.pruebatecnica.model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
    Transaction crear(Transaction transaction);

    Transaction obtenerPorId(Long id);

    List<Transaction> listar();

    Transaction actualizar(Long id, Transaction transaction);

    void eliminar(Long id);

    List<Transaction> listByAccountAndDateRange(Long accountId, LocalDateTime from, LocalDateTime to);
}

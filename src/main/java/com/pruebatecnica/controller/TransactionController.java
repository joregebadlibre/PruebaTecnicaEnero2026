package com.pruebatecnica.controller;

import com.pruebatecnica.mapper.TransactionMapper;
import com.pruebatecnica.model.Transaction;
import com.pruebatecnica.openapi.api.TransactionsApi;
import com.pruebatecnica.openapi.model.TransactionRequest;
import com.pruebatecnica.openapi.model.TransactionResponse;
import com.pruebatecnica.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController implements TransactionsApi {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public ResponseEntity<List<TransactionResponse>> transactionsGet() {
        List<TransactionResponse> response = transactionService.listar().stream()
                .map(transactionMapper::toOpenApi)
                .toList();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<TransactionResponse> transactionsPost(@Valid TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.toEntity(transactionRequest);
        Transaction created = transactionService.crear(transaction);
        return ResponseEntity.status(201).body(transactionMapper.toOpenApi(created));
    }

    @Override
    public ResponseEntity<TransactionResponse> transactionsIdGet(Long id) {
        return ResponseEntity.ok(transactionMapper.toOpenApi(transactionService.obtenerPorId(id)));
    }

    @Override
    public ResponseEntity<TransactionResponse> transactionsIdPut(Long id, @Valid TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.toEntity(transactionRequest);
        return ResponseEntity.ok(transactionMapper.toOpenApi(transactionService.actualizar(id, transaction)));
    }

    @Override
    public ResponseEntity<Void> transactionsIdDelete(Long id) {
        transactionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

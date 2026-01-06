package com.pruebatecnica.mapper;

import com.pruebatecnica.model.Account;
import com.pruebatecnica.model.Transaction;
import com.pruebatecnica.openapi.model.TransactionRequest;
import com.pruebatecnica.openapi.model.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public Transaction toEntity(TransactionRequest request) {
        Transaction t = new Transaction();
        Account account = new Account();
        account.setId(request.getAccountId());
        t.setAccount(account);
        t.setTransactionType(com.pruebatecnica.model.TransactionType.valueOf(request.getTransactionType().name()));
        t.setAmount(java.math.BigDecimal.valueOf(request.getAmount()));
        return t;
    }

    public TransactionResponse toOpenApi(Transaction transaction) {
        TransactionResponse r = new TransactionResponse();
        r.setId(transaction.getId());
        r.setAccountId(transaction.getAccount() == null ? null : transaction.getAccount().getId());
        r.setTransactionType(com.pruebatecnica.openapi.model.TransactionType.valueOf(transaction.getTransactionType().name()));
        r.setAmount(transaction.getAmount() == null ? null : transaction.getAmount().doubleValue());
        r.setBalance(transaction.getBalance() == null ? null : transaction.getBalance().doubleValue());
        if (transaction.getDate() != null) {
            r.setDate(transaction.getDate().atOffset(java.time.ZoneOffset.UTC));
        }
        return r;
    }
}

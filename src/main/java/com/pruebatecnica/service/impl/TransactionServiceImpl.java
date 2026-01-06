package com.pruebatecnica.service.impl;

import com.pruebatecnica.exception.BusinessException;
import com.pruebatecnica.exception.NotFoundException;
import com.pruebatecnica.model.Account;
import com.pruebatecnica.model.Transaction;
import com.pruebatecnica.model.TransactionType;
import com.pruebatecnica.repository.TransactionRepository;
import com.pruebatecnica.service.AccountService;
import com.pruebatecnica.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    @Override
    public Transaction crear(Transaction transaction) {
        if (transaction.getAccount() == null || transaction.getAccount().getId() == null) {
            throw new BusinessException("Account is required");
        }

        Account account = accountService.obtenerPorId(transaction.getAccount().getId());

        BigDecimal previousBalance = getCurrentBalance(account.getId(), account.getInitialBalance());

        TransactionType tipo = transaction.getTransactionType();
        if (tipo == null) {
            throw new BusinessException("Transaction type is required");
        }

        BigDecimal valor = transaction.getAmount();
        if (valor == null) {
            throw new BusinessException("Amount is required");
        }

        BigDecimal normalizedAmount;
        if (tipo == TransactionType.CREDITO) {
            normalizedAmount = valor.abs();
        } else {
            if (previousBalance.compareTo(BigDecimal.ZERO) == 0) {
                throw new BusinessException("Insufficient balance");
            }
            normalizedAmount = valor.abs().negate();
        }

        BigDecimal newBalance = previousBalance.add(normalizedAmount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Insufficient balance");
        }

        transaction.setAccount(account);
        transaction.setAmount(normalizedAmount);
        transaction.setBalance(newBalance);
        if (transaction.getDate() == null) {
            transaction.setDate(LocalDateTime.now());
        }

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public Transaction obtenerPorId(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> listar() {
        return transactionRepository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        Transaction existing = obtenerPorId(id);
        transactionRepository.delete(existing);
    }

    @Override
    public Transaction actualizar(Long id, Transaction transaction) {
        Transaction existing = obtenerPorId(id);

        if (existing.getAccount() == null || existing.getAccount().getId() == null) {
            throw new BusinessException("Account is required");
        }

        Long accountId = existing.getAccount().getId();
        Transaction last = transactionRepository.findTopByAccount_IdOrderByDateDescIdDesc(accountId);
        if (last == null || !last.getId().equals(existing.getId())) {
            throw new BusinessException("Only the last account transaction can be updated");
        }

        if (transaction.getTransactionType() == null) {
            throw new BusinessException("Transaction type is required");
        }

        if (transaction.getAmount() == null) {
            throw new BusinessException("Amount is required");
        }

        BigDecimal previousBalance = getPreviousBalanceForLastTransaction(existing);

        BigDecimal normalizedAmount;
        if (transaction.getTransactionType() == TransactionType.CREDITO) {
            normalizedAmount = transaction.getAmount().abs();
        } else {
            if (previousBalance.compareTo(BigDecimal.ZERO) == 0) {
                throw new BusinessException("Insufficient balance");
            }
            normalizedAmount = transaction.getAmount().abs().negate();
        }

        BigDecimal newBalance = previousBalance.add(normalizedAmount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Insufficient balance");
        }

        existing.setTransactionType(transaction.getTransactionType());
        existing.setAmount(normalizedAmount);
        existing.setBalance(newBalance);

        return transactionRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> listByAccountAndDateRange(Long accountId, LocalDateTime from, LocalDateTime to) {
        return transactionRepository.findByAccount_IdAndDateBetweenOrderByDateAsc(accountId, from, to);
    }

    private BigDecimal getCurrentBalance(Long accountId, BigDecimal initialBalance) {
        Transaction last = transactionRepository.findTopByAccount_IdOrderByDateDesc(accountId);
        if (last == null) {
            return initialBalance;
        }
        return last.getBalance();
    }

    private BigDecimal getPreviousBalanceForLastTransaction(Transaction lastTransaction) {
        Long accountId = lastTransaction.getAccount().getId();
        Account account = accountService.obtenerPorId(accountId);

        List<Transaction> transactions = transactionRepository.findByAccount_IdAndDateBetweenOrderByDateAsc(
                accountId,
                LocalDateTime.MIN,
                LocalDateTime.MAX
        );

        if (transactions.isEmpty()) {
            return account.getInitialBalance();
        }

        BigDecimal previousBalance = account.getInitialBalance();
        for (Transaction t : transactions) {
            if (t.getId().equals(lastTransaction.getId())) {
                return previousBalance;
            }
            previousBalance = t.getBalance();
        }
        return previousBalance;
    }
}

package com.pruebatecnica.repository;

import com.pruebatecnica.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Override
    @EntityGraph(attributePaths = {"account", "account.customer"})
    List<Transaction> findAll();

    @Override
    @EntityGraph(attributePaths = {"account", "account.customer"})
    Optional<Transaction> findById(Long id);

    @EntityGraph(attributePaths = {"account", "account.customer"})
    List<Transaction> findByAccount_IdAndDateBetweenOrderByDateAsc(Long accountId, LocalDateTime from, LocalDateTime to);

    @EntityGraph(attributePaths = {"account", "account.customer"})
    Transaction findTopByAccount_IdOrderByDateDesc(Long accountId);

    @EntityGraph(attributePaths = {"account", "account.customer"})
    Transaction findTopByAccount_IdOrderByDateDescIdDesc(Long accountId);
}

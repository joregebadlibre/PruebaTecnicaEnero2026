package com.pruebatecnica.repository;

import com.pruebatecnica.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Override
    @EntityGraph(attributePaths = {"customer"})
    List<Account> findAll();

    @Override
    @EntityGraph(attributePaths = {"customer"})
    Optional<Account> findById(Long id);

    @EntityGraph(attributePaths = {"customer"})
    List<Account> findByCustomer_Id(Long customerId);
}

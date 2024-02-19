package br.com.guilherme.rinhabackend2024q1.repositories;

import br.com.guilherme.rinhabackend2024q1.entities.TransactionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    @Query("SELECT t FROM TransactionEntity t WHERE t.cliente.id = :clientId ORDER BY t.realizadaEm DESC")
    List<TransactionEntity> findLastNTransactionsByClientId(@Param("clientId") Integer clientId, Pageable pageable);
}

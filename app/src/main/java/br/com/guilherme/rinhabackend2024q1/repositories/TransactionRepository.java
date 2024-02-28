package br.com.guilherme.rinhabackend2024q1.repositories;

import br.com.guilherme.rinhabackend2024q1.entities.TransactionEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findByClienteIdOrderByRealizadaEmDesc(Integer id, Limit limit);
}

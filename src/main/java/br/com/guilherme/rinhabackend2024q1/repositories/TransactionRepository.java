package br.com.guilherme.rinhabackend2024q1.repositories;

import br.com.guilherme.rinhabackend2024q1.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
}

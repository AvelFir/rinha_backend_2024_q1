package br.com.guilherme.rinhabackend2024q1.repositories;

import br.com.guilherme.rinhabackend2024q1.entities.ClienteEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<ClienteEntity> findClienteById(Integer id);
}

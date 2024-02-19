package br.com.guilherme.rinhabackend2024q1.repositories;

import br.com.guilherme.rinhabackend2024q1.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
}

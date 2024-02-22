package br.com.guilherme.rinhabackend2024q1.services;

import br.com.guilherme.rinhabackend2024q1.entities.ClienteEntity;
import br.com.guilherme.rinhabackend2024q1.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public Optional<ClienteEntity> getCliente(Integer id){
        return clienteRepository.findById(id);
    }

    @Transactional
    public void saveCliente(ClienteEntity clienteEntity){
        clienteRepository.save(clienteEntity);
    }
}

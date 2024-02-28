package br.com.guilherme.rinhabackend2024q1.services;

import br.com.guilherme.rinhabackend2024q1.dtos.responses.ExtratoResponse;
import br.com.guilherme.rinhabackend2024q1.entities.ClienteEntity;
import br.com.guilherme.rinhabackend2024q1.entities.TransactionEntity;
import br.com.guilherme.rinhabackend2024q1.exception.ClienteNotFoundException;
import br.com.guilherme.rinhabackend2024q1.repositories.ClienteRepository;
import br.com.guilherme.rinhabackend2024q1.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    TransactionRepository transacaoRepository;

    @Transactional
    public ExtratoResponse extrato(Integer id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(ClienteNotFoundException::new);

//        Saldo saldo = new Saldo(cliente.getSaldo(), LocalDateTime.now(), cliente.getLimite());
        List<TransactionEntity> transacoes = transacaoRepository
                .findByClienteIdOrderByRealizadaEmDesc(id, Limit.of(10)).stream()
                .toList();


        ExtratoResponse extrato = new ExtratoResponse(cliente);
        extrato.addTransactions(transacoes);

        return extrato;
    }
}

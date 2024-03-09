package br.com.guilherme.rinhabackend2024q1.services;

import br.com.guilherme.rinhabackend2024q1.dtos.responses.extrato.ExtratoResponse;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.extrato.SaldoResponse;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.extrato.TransactionResponse;
import br.com.guilherme.rinhabackend2024q1.entities.ClienteEntity;
import br.com.guilherme.rinhabackend2024q1.exception.ClienteNotFoundException;
import br.com.guilherme.rinhabackend2024q1.repositories.ClienteRepository;
import br.com.guilherme.rinhabackend2024q1.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

        return new ExtratoResponse(new SaldoResponse(cliente.getSaldo(), LocalDateTime.now(), cliente.getLimite()),
                transacaoRepository.findByClienteIdOrderByRealizadaEmDesc(id, Limit.of(10))
                .stream()
                .map(entity -> new TransactionResponse(entity.getValor(), entity.getTipo(), entity.getDescricao(), entity.getRealizadaEm()))
                .collect(Collectors.toList())
        );
    }
}

package br.com.guilherme.rinhabackend2024q1.services;

import br.com.guilherme.rinhabackend2024q1.dtos.request.TransactionRequest;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.transaction.TransactionResponse;
import br.com.guilherme.rinhabackend2024q1.entities.ClienteEntity;
import br.com.guilherme.rinhabackend2024q1.entities.TransactionEntity;
import br.com.guilherme.rinhabackend2024q1.enuns.TransactionType;
import br.com.guilherme.rinhabackend2024q1.exception.ClienteLimitExceededException;
import br.com.guilherme.rinhabackend2024q1.exception.ClienteNotFoundException;
import br.com.guilherme.rinhabackend2024q1.repositories.ClienteRepository;
import br.com.guilherme.rinhabackend2024q1.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Transactional
    public TransactionResponse transacao(Integer id, TransactionRequest request) {
        ClienteEntity cliente = clienteRepository.findClienteById(id)
                .orElseThrow(ClienteNotFoundException::new);

        if (TransactionType.CREDITO.equals(request.tipo())) {
            cliente.increaseSaldo(request.valor());
        } else {
            if (cliente.getSaldo() - request.valor() < -cliente.getLimite()) {
                throw new ClienteLimitExceededException();
            }
            cliente.decreaseSaldo(request.valor());
        }

        TransactionEntity transacao =
                TransactionEntity.builder()
                .tipo(request.tipo())
                .cliente(cliente)
                .valor(request.valor())
                .descricao(request.descricao())
                .realizadaEm(LocalDateTime.now())
                .build();

        cliente.adicionarTransacao(transacao);
        clienteRepository.save(cliente);

        return new TransactionResponse(cliente.getLimite(), cliente.getSaldo());
    }

}

package br.com.guilherme.rinhabackend2024q1.services;

import br.com.guilherme.rinhabackend2024q1.dtos.TransactionDto;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.SavedTransactionResponse;
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
    public SavedTransactionResponse transacao(Integer id, TransactionDto request) {
        ClienteEntity cliente = clienteRepository.findClienteById(id)
                .orElseThrow(ClienteNotFoundException::new);

        if (TransactionType.CREDITO.equals(request.getTipo())) {
            cliente.increaseSaldo(request.getValor());
        } else {
            if (cliente.getSaldo() - request.getValor() < -cliente.getLimite()) {
                throw new ClienteLimitExceededException();
            }
            cliente.decreaseSaldo(request.getValor());
        }

        TransactionEntity transacao = criarTransacao(request, cliente, request.getTipo());
        cliente.criarTransacao(transacao);
        clienteRepository.save(cliente);
        SavedTransactionResponse response = new SavedTransactionResponse(cliente.getLimite(), cliente.getSaldo());

        return response;
    }

    private TransactionEntity criarTransacao(TransactionDto request, ClienteEntity cliente, TransactionType tipo) {
        TransactionEntity transacao = new TransactionEntity();
        transacao.setCliente(cliente);
        transacao.setTipo(tipo);
        transacao.setValor(request.getValor());
        transacao.setDescricao(request.getDescricao());
        transacao.setRealizadaEm(LocalDateTime.now());
        return transacao;
    }

}

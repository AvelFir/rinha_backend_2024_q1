package br.com.guilherme.rinhabackend2024q1.controllers;

import br.com.guilherme.rinhabackend2024q1.dtos.TransactionDto;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.TransactionResponse;
import br.com.guilherme.rinhabackend2024q1.entities.ClienteEntity;
import br.com.guilherme.rinhabackend2024q1.entities.TransactionEntity;
import br.com.guilherme.rinhabackend2024q1.enuns.TransactionType;
import br.com.guilherme.rinhabackend2024q1.services.ClienteService;
import br.com.guilherme.rinhabackend2024q1.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/clientes/")
public class ClientsController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("{id}/transacoes")
    public ResponseEntity<?> postTransaction(@PathVariable(value = "id") Integer id, @Validated @RequestBody TransactionDto transactionDto) {
        ClienteEntity clienteEntity = clienteService.getCliente(id).orElse(null);
        if (clienteEntity == null) {
            return ResponseEntity.notFound().build();
        }
        if (TransactionType.CREDITO.equals(transactionDto.getTipo())) {
            clienteEntity.increaseSaldo(transactionDto.getValor());
        } else {
            clienteEntity.decreaseSaldo(transactionDto.getValor());
            if (clienteEntity.getSaldo() < -clienteEntity.getLimite()) {
                return ResponseEntity.unprocessableEntity().body("Falha ao debitar! saldo menor que o limite");
            }
        }
        TransactionEntity transactionEntity = createTransactionEntity(transactionDto, clienteEntity);
        transactionService.saveTransaction(transactionEntity);
        clienteService.saveCliente(clienteEntity);
        return ResponseEntity.ok(new TransactionResponse(clienteEntity.getLimite(), clienteEntity.getSaldo()));
    }

    private TransactionEntity createTransactionEntity(TransactionDto transactionDto, ClienteEntity clienteEntity) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setCliente(clienteEntity);
        transactionEntity.setValor(transactionDto.getValor());
        transactionEntity.setDescricao(transactionDto.getDescricao());
        transactionEntity.setTipo(transactionDto.getTipo());
        transactionEntity.setRealizadaEm(LocalDateTime.now());
        return transactionEntity;
    }
}

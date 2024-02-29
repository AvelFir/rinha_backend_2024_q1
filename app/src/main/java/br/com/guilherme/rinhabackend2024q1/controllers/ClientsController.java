package br.com.guilherme.rinhabackend2024q1.controllers;

import br.com.guilherme.rinhabackend2024q1.dtos.TransactionDto;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.ExtratoResponse;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.SavedTransactionResponse;
import br.com.guilherme.rinhabackend2024q1.services.ClienteService;
import br.com.guilherme.rinhabackend2024q1.services.TransactionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes/")
public class ClientsController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TransactionService transactionService;

    private static final Logger logger = LoggerFactory.getLogger(ClientsController.class);

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<?> transacao(@PathVariable Integer id,
                                                       @RequestBody @Valid TransactionDto request) {
        SavedTransactionResponse transacao = transactionService.transacao(id, request);

        return ResponseEntity.ok(transacao);
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<ExtratoResponse> extrato(@PathVariable Integer id) {
        ExtratoResponse extrato = clienteService.extrato(id);

        return ResponseEntity.ok(extrato);
    }

}

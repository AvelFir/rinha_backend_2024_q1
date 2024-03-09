package br.com.guilherme.rinhabackend2024q1.controllers;

import br.com.guilherme.rinhabackend2024q1.dtos.request.TransactionRequest;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.extrato.ExtratoResponse;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.transaction.TransactionResponse;
import br.com.guilherme.rinhabackend2024q1.services.ClienteService;
import br.com.guilherme.rinhabackend2024q1.services.TransactionService;
import jakarta.validation.Valid;
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

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<?> transacao(@PathVariable Integer id,
                                                       @RequestBody @Valid TransactionRequest request) {
        TransactionResponse transacao = transactionService.transacao(id, request);
        return ResponseEntity.ok(transacao);
    }

    @GetMapping("/{id}/extrato")
    public ResponseEntity<ExtratoResponse> extrato(@PathVariable Integer id) {
        ExtratoResponse extrato = clienteService.extrato(id);
        return ResponseEntity.ok(extrato);
    }

}

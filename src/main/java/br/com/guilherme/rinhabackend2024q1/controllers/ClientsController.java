package br.com.guilherme.rinhabackend2024q1.controllers;

import br.com.guilherme.rinhabackend2024q1.dtos.TransactionDto;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.TransactionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes/")
public class ClientsController {

    @PostMapping("{id}/transacoes")
    public ResponseEntity<?> postTransaction(@PathVariable(value = "id") Long id, @Validated @RequestBody TransactionDto transactionDto){
        System.out.println("id: " + id);
        System.out.println(transactionDto);
        return ResponseEntity.status(HttpStatus.OK).body(new TransactionResponse());
    }
}

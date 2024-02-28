package br.com.guilherme.rinhabackend2024q1.controllers;

import br.com.guilherme.rinhabackend2024q1.dtos.TransactionDto;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.ExtratoResponse;
import br.com.guilherme.rinhabackend2024q1.dtos.responses.SavedTransactionResponse;
import br.com.guilherme.rinhabackend2024q1.entities.ClienteEntity;
import br.com.guilherme.rinhabackend2024q1.entities.TransactionEntity;
import br.com.guilherme.rinhabackend2024q1.repositories.ClienteRepository;
import br.com.guilherme.rinhabackend2024q1.repositories.TransactionRepository;
import br.com.guilherme.rinhabackend2024q1.services.ClienteService;
import br.com.guilherme.rinhabackend2024q1.services.TransactionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/clientes/")
public class ClientsController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    ClienteRepository clienteRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClientsController.class);

//    @PostMapping("{id}/transacoes")
//    @Transactional
//    public ResponseEntity<?> postTransaction(@PathVariable(value = "id") Integer id, @Validated @RequestBody TransactionDto transactionDto) {
//            logger.info("TRANSACAO - EVENTO RECEBIDO - {}", transactionDto.toString());
////            ClienteEntity clienteEntity = clienteService.getCliente(id).orElse(null);
////            if (clienteEntity == null) {
////                logger.error("TRANSACAO - 404 - CLIENTE NAO ENCONTRADO - {}", id);
////                return ResponseEntity.notFound().build();
////            }
//            ClienteEntity clienteEntity = clienteService.getCliente(id).orElse(null);
//            if (TransactionType.CREDITO.equals(transactionDto.getTipo())) {
//                clienteEntity.increaseSaldo(transactionDto.getValor());
//            } else {
//                clienteEntity.decreaseSaldo(transactionDto.getValor());
//                if (clienteEntity.getSaldo() < -clienteEntity.getLimite()) {
//                    logger.error("TRANSACAO - 422 - EVENTO INVALIDO - {}", clienteEntity);
//                    throw new IllegalArgumentException("Saldo Menor do que o limite");
//                }
//            }
//            TransactionEntity transactionEntity = createTransactionEntity(transactionDto, clienteEntity);
//            clienteEntity.criarTransacao(transactionEntity);
//            transactionRepository.save(transactionEntity);
//            clienteRepository.save(clienteEntity);
//            logger.info("TRANSACAO - 200 - EVENTO PROCESSADO COM SUCESSO  - {}", transactionEntity);
//            return ResponseEntity.ok(new SavedTransactionResponse(clienteEntity.getLimite(), clienteEntity.getSaldo()));
//    }

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

    private TransactionEntity createTransactionEntity(TransactionDto transactionDto, ClienteEntity id) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setCliente(id);
        transactionEntity.setValor(transactionDto.getValor());
        transactionEntity.setDescricao(transactionDto.getDescricao());
        transactionEntity.setTipo(transactionDto.getTipo());
        transactionEntity.setRealizadaEm(LocalDateTime.now());
        return transactionEntity;
    }
}

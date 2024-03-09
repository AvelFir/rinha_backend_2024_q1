package br.com.guilherme.rinhabackend2024q1.dtos.responses;

import br.com.guilherme.rinhabackend2024q1.entities.ClienteEntity;
import br.com.guilherme.rinhabackend2024q1.entities.TransactionEntity;
import br.com.guilherme.rinhabackend2024q1.enuns.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
public class ExtratoResponse {

    private SaldoResponse saldo;

    @JsonProperty("ultimas_transacoes")
    @Setter(AccessLevel.PRIVATE)
    private List<DetailTransactionResponse> lastTransactions = new LinkedList<>();

    public ExtratoResponse(ClienteEntity cliente){
        saldo = new SaldoResponse(cliente.getSaldo(),LocalDateTime.now(),cliente.getLimite());
    }


    @Data
    @NoArgsConstructor
    static class DetailTransactionResponse {
        private long valor;
        private TransactionType tipo;
        private String descricao;
        @JsonProperty("realizada_em")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
        private LocalDateTime realizadaEm;

        public DetailTransactionResponse(TransactionEntity transaction){
            valor = transaction.getValor();
            tipo = transaction.getTipo();
            descricao = transaction.getDescricao();
            realizadaEm = transaction.getRealizadaEm();
        }
    }

    @Data
    @AllArgsConstructor
    static class SaldoResponse {
        private Integer total;

        @JsonProperty("data_extrato")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
        private LocalDateTime dataExtrato;

        private Integer limite;
    }

    public void addTransaction(TransactionEntity transactions){
        lastTransactions.add(new DetailTransactionResponse(transactions));
    }

    public void addTransactions(List<TransactionEntity> transactions){
        transactions.forEach(this::addTransaction);
    }
}

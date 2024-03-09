package br.com.guilherme.rinhabackend2024q1.dtos.responses.extrato;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ExtratoResponse(
        SaldoResponse saldo,
        @JsonProperty("ultimas_transacoes")
        List<TransactionResponse> lastTransactions
) {}

package br.com.guilherme.rinhabackend2024q1.dtos.responses.extrato;

import br.com.guilherme.rinhabackend2024q1.enuns.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record TransactionResponse(
         long valor,
         TransactionType tipo,
         String descricao,
         @JsonProperty("realizada_em")
         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
         LocalDateTime realizadaEm
) {}

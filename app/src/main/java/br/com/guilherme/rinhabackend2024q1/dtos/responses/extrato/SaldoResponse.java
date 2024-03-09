package br.com.guilherme.rinhabackend2024q1.dtos.responses.extrato;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record SaldoResponse(
        Integer total,
        @JsonProperty("data_extrato")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
        LocalDateTime dataExtrato,
        Integer limite
) {}

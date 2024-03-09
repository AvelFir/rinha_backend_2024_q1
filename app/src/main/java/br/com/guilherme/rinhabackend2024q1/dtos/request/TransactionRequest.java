package br.com.guilherme.rinhabackend2024q1.dtos.request;

import br.com.guilherme.rinhabackend2024q1.enuns.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;



public record TransactionRequest(
        @NotNull Integer valor,
        @NotNull TransactionType tipo,
        @NotBlank @Length(min = 1,max = 10) String descricao
) {}

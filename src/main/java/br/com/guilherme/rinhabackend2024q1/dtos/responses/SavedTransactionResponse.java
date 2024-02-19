package br.com.guilherme.rinhabackend2024q1.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavedTransactionResponse {
    private long limite;
    private long saldo;
}

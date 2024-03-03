package br.com.guilherme.rinhabackend2024q1.dtos;

import br.com.guilherme.rinhabackend2024q1.enuns.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    @NotNull
    private Integer valor;
    @NotNull
    private TransactionType tipo;
    @NotBlank
    @Length(min = 1,max = 10)
    private String descricao;

}

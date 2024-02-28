package br.com.guilherme.rinhabackend2024q1.entities;


import br.com.guilherme.rinhabackend2024q1.enuns.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transacoes")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "valor", nullable = false)
    private Integer valor;

    @Column(name = "tipo", nullable = false, length = 1)
    @Enumerated(EnumType.ORDINAL)
    private TransactionType tipo;

    @Column(name = "descricao", nullable = false, length = 10)
    private String descricao;

    @Column(name = "realizada_em", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime realizadaEm;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;
}

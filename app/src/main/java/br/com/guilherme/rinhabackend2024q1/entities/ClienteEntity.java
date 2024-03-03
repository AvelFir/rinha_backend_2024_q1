package br.com.guilherme.rinhabackend2024q1.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name= "clientes")
public class ClienteEntity {

    @Id
    private Integer id;

    @Column
    private int limite;

    @Column
    private int saldo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<TransactionEntity> transacoes;

    public void increaseSaldo(int saldo){
        this.saldo += saldo;
    }

    public void decreaseSaldo(int saldo){
        this.saldo -= saldo;
    }

    public void adicionarTransacao(TransactionEntity transacao) {
        this.transacoes.add(transacao);
    }

}

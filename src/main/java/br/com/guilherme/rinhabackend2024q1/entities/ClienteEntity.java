package br.com.guilherme.rinhabackend2024q1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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

    public void increaseSaldo(int saldo){
        this.saldo += saldo;
    }

    public void decreaseSaldo(int saldo){
        this.saldo -= saldo;
    }
}

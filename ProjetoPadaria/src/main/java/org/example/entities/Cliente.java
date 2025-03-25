package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Cliente implements Serializable {

    //Coluna ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    //Coluna NOME
    @Column(length = 55, name = "NOME", nullable = false)
    private String nome;

    //Coluna CPF e CNPJ
    @Column(name = "cpfCnpj", nullable = false)
    private String cpfCnpj;

    //Coluna DATA de NASCIMENTO
    @Column(name = "dataNascimento", nullable = false)
    private LocalDate dataNascimento;

    //Coluna STATUS
    @Column(length = 55, name = "status", nullable = false)
    private String status;

    //Construtor vazio
    public Cliente() {
    }

    //Construtor com argumentos
    public Cliente(Long id, String nome, String cpfCnpj, LocalDate dataNascimento, String status) {
        this.id = id;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.dataNascimento = dataNascimento;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
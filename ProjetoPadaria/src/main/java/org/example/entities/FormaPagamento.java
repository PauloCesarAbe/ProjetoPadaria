package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class FormaPagamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(length = 55, name = "Descrição", nullable = false)
    private String descricao;

    @Column(length = 55, name = "Tipo", nullable = false)
    private String tipo;

    @Column(length = 55, name = "status", nullable = false)
    private String status;

    public FormaPagamento() {
    }

    public FormaPagamento(Long id, String descricao, String tipo, String status) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
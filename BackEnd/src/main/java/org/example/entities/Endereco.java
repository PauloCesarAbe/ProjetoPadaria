package org.example.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "END_ID")
    private Long endId;

    @NotBlank(message = "Rua é obrigatório")
    @Size(max = 100, message = "Rua inválida")
    @Column(name = "END_RUA", nullable = false, length = 100)
    private String endRua;

    @NotBlank(message = "Número é obrigatório")
    @Size(max = 10, message = "Número inválido")
    @Column(name = "END_NUMERO", nullable = false, length = 10)
    private String endNumero;

    @NotBlank(message = "Cidade é obrigatória")
    @Size(max = 100, message = "Cidade inválida")
    @Column(name = "END_CIDADE", nullable = false, length = 100)
    private String endCidade;

    @NotBlank(message = "CEP é obrigatório")
    @Size(max = 8, message = "CEP inválido")
    @Column(name = "END_CEP", nullable = false, length = 8)
    private String endCep;

    @NotBlank(message = "Estado é obrigatório")
    @Size(max = 2, message = "Estado inválido")
    @Column(name = "END_ESTADO", length = 2)
    private String endEstado;

    public Endereco() {
    }

    public Endereco(Long endId, String endRua, String endNumero, String endCidade, String endCep, String endEstado) {
        this.endId = endId;
        this.endRua = endRua;
        this.endNumero = endNumero;
        this.endCidade = endCidade;
        this.endCep = endCep;
        this.endEstado = endEstado;
    }

    public Long getEndId() {
        return endId;
    }

    public void setEndId(Long endId) {
        this.endId = endId;
    }

    public String getEndRua() {
        return endRua;
    }

    public void setEndRua(String endRua) {
        this.endRua = endRua;
    }

    public String getEndNumero() {
        return endNumero;
    }

    public void setEndNumero(String endNumero) {
        this.endNumero = endNumero;
    }

    public String getEndCidade() {
        return endCidade;
    }

    public void setEndCidade(String endCidade) {
        this.endCidade = endCidade;
    }

    public String getEndCep() {
        return endCep;
    }

    public void setEndCep(String endCep) {
        this.endCep = endCep;
    }

    public String getEndEstado() {
        return endEstado;
    }

    public void setEndEstado(String endEstado) {
        this.endEstado = endEstado;
    }
}
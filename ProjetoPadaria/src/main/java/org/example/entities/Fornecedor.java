package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Fornecedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    @Column(length = 55, name = "RazaoSocial", nullable = false)
    private String razaoSocial;

    @Column(length = 55, name = "NomeFantasia", nullable = false)
    private String nomeFantasia;

    @Column(length = 55, name = "cnpj", nullable = false)
    private String cnpj;

    @Column(length = 55, name = "status", nullable = false)
    private String status;

    public Fornecedor() {
    }

    public Fornecedor(String id, String razaoSocial, String nomeFantasia, String cnpj, String status) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
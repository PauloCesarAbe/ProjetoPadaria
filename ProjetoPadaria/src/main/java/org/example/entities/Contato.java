package org.example.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(length = 55, name = "telefone", nullable = false)
    private String telefone;

    @Column(length = 100, name = "celular", nullable = false)
    private String celular;

    @Column(length = 100, name = "email", nullable = false)
    private String email;

    @ManyToMany
    @JoinColumn(name = "Cliente", nullable = false)
    private Cliente cliente;

    public Contato() {
    }

    public Contato(Long id, String telefone, String celular, String email, Cliente cliente) {
        this.id = id;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public org.example.entities.Cliente getCliente() {
        return cliente;
    }

    public void setCliente(org.example.entities.Cliente cliente) {
        this.cliente = cliente;
    }
}